package dsbd2122.youmedia.Services;

import dsbd2122.youmedia.DataModel.Payment;
import dsbd2122.youmedia.DataModel.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PaymentService {

    @Autowired
    PaymentRepository repository;

    public Payment register(Payment payment){
        return repository.save(Payment);
    }

    public Payment getPayment(Integer id){
        return repository.findById(id).get();

    }

    public Payment getPaymentByUser(Integer id){
        return repository.findById(id).get();

    }

    public Iterable<Customer> getAll(){
        return repository.findAll();
    }

    

    public String delete(Integer id) {

        if (repository.findById(id).isPresent()) {
            repository.deleteById(id);
            return "Payment with id: " + id + " has been deleted!";
        }
        return "Payment with id: " + id + " is not present";

    }
}
