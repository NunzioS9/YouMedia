package dsbd2122.youmedia.Api;

import com.example.common_dto.dto.CustomerRequestDTO;
import com.example.common_dto.dto.CustomerResponseDTO;
import com.example.common_dto.dto.OrchestratorResponseDTO;
import dsbd2122.youmedia.DataModel.Customer;
import dsbd2122.youmedia.DataModel.CustomerRepository;
import dsbd2122.youmedia.Services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
@RequestMapping(path = "/user")
public class CustomerController {

    @Autowired
    CustomerRepository repository;
    CustomerService service;

    // GET htpp://localhost:8080/user/{id}
    @GetMapping(path = "/{id}")
    public @ResponseBody
    Customer getUser(@PathVariable Integer id) {
        return repository.findById(id).get();
    }

    // GET htpp://localhost:8080/user/all
    @GetMapping(path = "/all")
    public @ResponseBody
    Iterable<Customer> getAll() {
        return repository.findAll();
    }

    // GET htpp://localhost:8080/user/email/{email}
    @GetMapping(path = "/email/{email}")
    public @ResponseBody
    Customer getUser(@PathVariable String email) {
        return repository.findByEmail(email);
    }

    // POST htpp://localhost:8080/user/register
    @PostMapping(path = "/register", consumes = "application/json")
    public @ResponseBody
    Customer register(@RequestBody Customer user) {
        return repository.save(user);
    }

    // POST htpp://localhost:8080/user/register
    @PostMapping("/create")
    public CustomerResponseDTO createOrder(@RequestBody CustomerRequestDTO requestDTO){
        requestDTO.setSagaId(UUID.randomUUID());
        return this.service.createOrder(requestDTO);
    }

    // DELETE htpp://localhost:8080/user/{id}
    @DeleteMapping(path = "/{id}")
    public @ResponseBody
    String delete(@PathVariable Integer id) {
        if (repository.findById(id).isPresent()) {
            repository.deleteById(id);
            return "User with id: " + id + " has been deleted!";
        }
        return "User with id: " + id + " is not present";
    }

}

