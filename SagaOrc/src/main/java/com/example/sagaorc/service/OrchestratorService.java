package com.example.sagaorc.service;

import com.example.common_dto.dto.*;
import com.example.common_dto.enums.*;
import com.example.sagaorc.service.steps.PaymentStep;
import com.example.sagaorc.service.steps.ResourceStep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class OrchestratorService {

    @Autowired
    @Qualifier("payment")
    private WebClient paymentClient;

    @Autowired
    @Qualifier("resource")
    private WebClient resourceClient;

    public Mono<OrchestratorResponseDTO> orderProduct(final OrchestratorRequestDTO requestDTO){
        Workflow orderWorkflow = this.getOrderWorkflow(requestDTO);
        return Flux.fromStream(() -> orderWorkflow.getSteps().stream())
                .flatMap(WorkflowStep::process)
                .handle(((aBoolean, synchronousSink) -> {
                    if(aBoolean)
                        synchronousSink.next(true);
                    else
                        synchronousSink.error(new WorkflowException("create order failed!"));
                }))
                .then(Mono.fromCallable(() -> getResponseDTO(requestDTO, CustomerStatus.SAGA_COMPLETED)))
                .onErrorResume(ex -> this.revertOrder(orderWorkflow, requestDTO));

    }

    private Mono<OrchestratorResponseDTO> revertOrder(final Workflow workflow, final OrchestratorRequestDTO requestDTO){
        return Flux.fromStream(() -> workflow.getSteps().stream())
                .filter(wf -> wf.getStatus().equals(WorkflowStepStatus.COMPLETE))
                .flatMap(WorkflowStep::revert)
                .retry(3)
                .then(Mono.just(this.getResponseDTO(requestDTO, CustomerStatus.SAGA_CANCELLED)));
    }

    private Workflow getOrderWorkflow(OrchestratorRequestDTO requestDTO){
        WorkflowStep paymentStep = new PaymentStep(this.paymentClient, this.getPaymentRequestDTO(requestDTO));
        WorkflowStep resourceStep = new ResourceStep(this.resourceClient, this.getResourceRequestDTO(requestDTO));
        //ArrayList<WorkflowStep> list = new ArrayList<WorkflowStep>();
        //return new OrderWorkflow(list.add);
        List<WorkflowStep> list = Arrays.asList(paymentStep, resourceStep);
        return new OrderWorkflow(list);
    }

    private OrchestratorResponseDTO getResponseDTO(OrchestratorRequestDTO requestDTO, CustomerStatus status){
        OrchestratorResponseDTO responseDTO = new OrchestratorResponseDTO();
        responseDTO.setSagaId(requestDTO.getSagaId());
        responseDTO.setAmount(requestDTO.getAmount());
        responseDTO.setResourceId(requestDTO.getResourceId());
        responseDTO.setCustomerId(requestDTO.getCustomerId());
        responseDTO.setStatus(status);
        return responseDTO;
    }

    private PaymentRequestDTO getPaymentRequestDTO(OrchestratorRequestDTO requestDTO){
        PaymentRequestDTO paymentRequestDTO = new PaymentRequestDTO();
        paymentRequestDTO.setCustomerId(requestDTO.getCustomerId());
        paymentRequestDTO.setAmount(requestDTO.getAmount());
        paymentRequestDTO.setSagaId(requestDTO.getSagaId());
        return paymentRequestDTO;
    }

    private ResourceRequestDTO getResourceRequestDTO(OrchestratorRequestDTO requestDTO){
        ResourceRequestDTO resourceRequestDTO = new ResourceRequestDTO();
        resourceRequestDTO.setCustomerId(requestDTO.getCustomerId());
        resourceRequestDTO.setResourceId(requestDTO.getResourceId());
        resourceRequestDTO.setSagaId(requestDTO.getSagaId());
        return resourceRequestDTO;
    }

}
