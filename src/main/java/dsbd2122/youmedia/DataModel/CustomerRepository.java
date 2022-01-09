package dsbd2122.youmedia.DataModel;

import org.springframework.data.repository.CrudRepository;

import javax.persistence.Table;

@Table
public interface CustomerRepository extends CrudRepository<Customer, Integer> {

    Customer findByEmail(String email);
}
