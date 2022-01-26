package com.example.sagaorc.service.steps;

import com.example.common_dto.dto.ResourceRequestDTO;
import com.example.common_dto.dto.ResourceResponseDTO;
import com.example.common_dto.enums.ResourceStatus;
import com.example.sagaorc.service.WorkflowStep;
import com.example.sagaorc.service.WorkflowStepStatus;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

public class ResourceStep implements WorkflowStep {

    private final WebClient webClient;
    private final ResourceRequestDTO requestDTO;
    private WorkflowStepStatus stepStatus = WorkflowStepStatus.PENDING;

    public ResourceStep(WebClient webClient, ResourceRequestDTO requestDTO) {
        this.webClient = webClient;
        this.requestDTO = requestDTO;
    }

    @Override
    public WorkflowStepStatus getStatus() {
        return this.stepStatus;
    }

    @Override
    public Mono<Boolean> process() {
        return this.webClient
                .post()
                .uri("/content/deduct")
                .body(BodyInserters.fromValue(this.requestDTO))
                .retrieve()
                .bodyToMono(ResourceResponseDTO.class)
                .map(r -> r.getStatus().equals(ResourceStatus.AVAILABLE))
                .doOnNext(b -> this.stepStatus = b ? WorkflowStepStatus.COMPLETE : WorkflowStepStatus.FAILED);
    }

    @Override
    public Mono<Boolean> revert() {
        return this.webClient
                    .get()
                    .uri("/content/all")
                    .retrieve()
                    .bodyToMono(Void.class)
                    .map(r ->true)
                    .onErrorReturn(false);
    }
}
