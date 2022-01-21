package dsbd2122.youmedia.Services;

import dsbd2122.youmedia.DataModel.Customer;
import dsbd2122.youmedia.DataModel.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class CustomerService {

    @Autowired
    CustomerRepository repository;

    public  Customer getUser(Integer id){
        return repository.findById(id).get();
    }

    public Iterable<Customer> getAll(){
        return repository.findAll();
    }

    public Customer register(Customer user){
        return repository.save(user);
    }

    public String delete(Integer id){
        repository.deleteById(id);
        return "User with id=" + id + "has been deleted!";

    }
}

