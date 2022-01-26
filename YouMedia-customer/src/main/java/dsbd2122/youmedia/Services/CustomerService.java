package dsbd2122.youmedia.Services;

import com.example.common_dto.dto.CustomerRequestDTO;
import com.example.common_dto.dto.CustomerResponseDTO;
import com.example.common_dto.dto.OrchestratorRequestDTO;
import com.example.common_dto.enums.CustomerStatus;
import dsbd2122.youmedia.DataModel.Customer;
import dsbd2122.youmedia.DataModel.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.FluxSink;

import javax.transaction.Transactional;

@Service
@Transactional
public class CustomerService {

    @Autowired
    CustomerRepository repository;


    private FluxSink<OrchestratorRequestDTO> sink;

    public Customer getUser(Integer id) {
        return repository.findById(id).get();
    }

    public Iterable<Customer> getAll() {
        return repository.findAll();
    }

    public Customer register(Customer user) {
        return repository.save(user);
    }

    public String delete(Integer id) {
        repository.deleteById(id);
        return "User with id=" + id + "has been deleted!";

    }

    public CustomerResponseDTO createOrder(CustomerRequestDTO customerRequestDTO){
        CustomerResponseDTO customerResponseDTO = new CustomerResponseDTO();
        customerResponseDTO.setSagaId(customerRequestDTO.getSagaId());
        customerResponseDTO.setCustomerId(customerRequestDTO.getCustomerId());
        customerResponseDTO.setResourceId(customerRequestDTO.getResourceId());
        customerResponseDTO.setAmount(0.00);
        customerResponseDTO.setStatus(CustomerStatus.SAGA_CREATED);
        this.sink.next(this.getOrchestratorRequestDTO(customerRequestDTO));
        return customerResponseDTO;
    }

    public OrchestratorRequestDTO getOrchestratorRequestDTO(CustomerRequestDTO orderRequestDTO){
        OrchestratorRequestDTO requestDTO = new OrchestratorRequestDTO();
        requestDTO.setSagaId(orderRequestDTO.getSagaId());
        requestDTO.setAmount(0.00);
        requestDTO.setCustomerId(orderRequestDTO.getCustomerId());
        requestDTO.setResourceId(orderRequestDTO.getResourceId());
        return requestDTO;
    }
}

