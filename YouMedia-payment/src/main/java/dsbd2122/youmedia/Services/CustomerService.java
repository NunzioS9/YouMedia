package dsbd2122.youmedia.Services;

import dsbd2122.youmedia.DataModel.Customer;
import dsbd2122.youmedia.DataModel.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CustomerService {

    @Autowired
    CustomerRepository repository;

    public Customer register(Customer user){
        return repository.save(user);
    }

    public Customer getUser(Integer id){
        return repository.findById(id).get();
        /* prova anche
        public Optional<Customer> getUser(Integer id){
        return repository.findById(id);
         */
    }

    public Iterable<Customer> getAll(){
        return repository.findAll();
    }

    public Customer getUser(String email){
        return repository.findByEmail(email);

    /* prova anche
    public Optional<Customer> getUser(String email){
        return repository.findByEmail(email);

     */
    }

    public String delete(Integer id) {
       /* try {
            repository.deleteById(id);
            return "the user identified by " + id + "has ben deleted";
        } catch (IllegalArgumentException ex) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "IllegalArgumentException: the user " + id + "is not present /" + ex);

        }*/

        if (repository.findById(id).isPresent()) {
            repository.deleteById(id);
            return "User with id: " + id + " has been deleted!";
        }
        return "User with id: " + id + " is not present";

    }
}
