package dsbd2122.youmedia.Services;

import com.example.common_dto.dto.CustomerResponseDTO;
import com.example.common_dto.dto.PaymentRequestDTO;
import com.example.common_dto.dto.PaymentResponseDTO;
import com.example.common_dto.enums.PaymentStatus;
import dsbd2122.youmedia.DataModel.Payment;
import dsbd2122.youmedia.DataModel.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Map;

@Service
@Transactional
public class PaymentService {

    @Autowired
    PaymentRepository repository;

    public Payment register(Payment payment) {
        return repository.save(payment);
    }

    /*public Payment getPayment(Integer id) {
        return repository.findById(id).get();

    }

    public Payment getPaymentByUser(Integer id) {
        return repository.findById(id).get();

    }

    public Iterable<Payment> getAll() {
        return repository.findAll();
    }


    public String delete(Integer id) {

        if (repository.findById(id).isPresent()) {
            repository.deleteById(id);
            return "Payment with id: " + id + " has been deleted!";
        }
        return "Payment with id: " + id + " is not present";

    }*/
    PaymentRepository repo;

    public PaymentResponseDTO debit(final PaymentRequestDTO requestDTO){
        //double balance = this.userBalanceMap.getOrDefault(requestDTO.getCustomerId(), 0d);
        Integer userId = requestDTO.getCustomerId();
        Payment p = new Payment();
        p = repo.findByUserId(userId);
        PaymentResponseDTO responseDTO = new PaymentResponseDTO();
        responseDTO.setAmount(requestDTO.getAmount());
        responseDTO.setCustomerId(requestDTO.getCustomerId());
        responseDTO.setSagaId(requestDTO.getSagaId());
        responseDTO.setStatus(PaymentStatus.PAYMENT_REJECTED);
        if(p.getCrediti() >= requestDTO.getAmount()){
            responseDTO.setStatus(PaymentStatus.PAYMENT_APPROVED);
            responseDTO.setCrediti(p.getCrediti() - requestDTO.getAmount());
        }
        return responseDTO;
    }

    public void credit(final PaymentRequestDTO requestDTO){
        Integer userId = requestDTO.getCustomerId();
        Payment p = new Payment();
        p = repo.findByUserId(userId);
        p.setCrediti(p.getCrediti() + requestDTO.getAmount());
        register(p);
    }
}
