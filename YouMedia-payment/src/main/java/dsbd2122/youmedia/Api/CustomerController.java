package dsbd2122.youmedia.Api;

import dsbd2122.youmedia.DataModel.Customer;
import dsbd2122.youmedia.DataModel.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path="/user")
public class CustomerController {

    @Autowired
    CustomerRepository repository;
    
    // GET http://localhost:8080/user/{id}
    @GetMapping(path="/{id}")
    public @ResponseBody
    Customer getUser(@PathVariable Integer id) {
        return repository.findById(id).get();
    }

    // GET http://localhost:8080/user/all
    @GetMapping(path="/all")
    public @ResponseBody Iterable<Customer> getAll() {
        return repository.findAll();
    }

    // GET http://localhost:8080/user/email/{email}
    @GetMapping(path="/email/{email}")
    public @ResponseBody Customer getUser(@PathVariable String email) {
        return repository.findByEmail(email);
    }

    // POST http://localhost:8080/user/register
    @PostMapping(path="/register", consumes = "application/json")
    public @ResponseBody Customer register(@RequestBody Customer user) {
        return repository.save(user);
    }

    // DELETE http://localhost:8080/user/{id}
    @DeleteMapping(path="/{id}")
    public @ResponseBody String delete(@PathVariable Integer id){
        if(repository.findById(id).isPresent()){
            repository.deleteById(id);
            return "User with id: " + id + " has been deleted!";
        }
        return "User with id: " + id + " is not present";
    }

}

