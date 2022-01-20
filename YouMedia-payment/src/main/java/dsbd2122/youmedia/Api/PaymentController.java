package dsbd2122.youmedia.Api;


import dsbd2122.youmedia.DataModel.Customer;
import dsbd2122.youmedia.DataModel.CustomerRepository;
import dsbd2122.youmedia.DataModel.Payment;
import dsbd2122.youmedia.DataModel.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path="/payment")
public class PaymentController {

    @Autowired
    PaymentRepository repository;
    CustomerRepository userRepo;

    // GET http://localhost:8080/payment/{id}
    @GetMapping(path="{id}")
    public @ResponseBody
    Payment getPayment(@PathVariable Integer id) {
        return repository.findById(id).get();
    }

    // GET http://localhost:8080/payment/user/{id}
    @GetMapping(path="/user/{id}")
    public @ResponseBody
    Payment getPaymentByUser(@PathVariable Integer id) {
        return repository.findByUserId(id);
    }

    // GET http://localhost:8080/payment/all
    @GetMapping(path="/all")
    public @ResponseBody Iterable<Payment> getAll() {
        return repository.findAll();
    }

    // POST http://localhost:8080/payment/renew
    @PostMapping(path="/renew", consumes = "application/json")
    public @ResponseBody Payment register(@RequestBody Payment sub) {
        return repository.save(sub);
    }

    // DELETE http://localhost:8080/payment/{id}
    @DeleteMapping(path="/{id}")
    public @ResponseBody String delete(@PathVariable Integer id){
        if(repository.findById(id).isPresent()){
            repository.deleteById(id);
            return "Payment with id: " + id + " has been deleted!";
        }
        return "Payment with id: " + id + " is not present";
    }

}
