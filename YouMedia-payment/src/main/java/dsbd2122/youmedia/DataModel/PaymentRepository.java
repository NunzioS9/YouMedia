package dsbd2122.youmedia.DataModel;


import org.springframework.data.repository.CrudRepository;

import javax.persistence.Table;

@Table
public interface PaymentRepository extends CrudRepository<Payment, Integer> {
    Payment findByUserId(Integer user_id);
}
