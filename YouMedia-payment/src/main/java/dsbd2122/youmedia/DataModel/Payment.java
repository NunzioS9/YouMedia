package dsbd2122.youmedia.DataModel;

import javax.persistence.Entity;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity

public class Payment {
    @Id
    @GeneratedValue

    private Integer id;

    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private Integer userId;

    @NotNull(message = "the remainder parameter cannot be blank!")
    private Integer remainder;

    @NotNull(message = "the expiry parameter cannot be blank!")
    private Date expiry;

    @NotNull(message = "crediti parameter cannot be blank!")

    private double crediti;


    public Integer getUser() {
        return userId;
    }

    public Integer getId() {
        return id;
    }

    public Integer getRemainder() {
        return remainder;
    }

    public Date getExpiry() {
        return expiry;
    }

    public double getCrediti() {
        return crediti;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setUser(Integer userId) {
        this.userId = userId;
    }

    public void setRemainder(Integer remainder) {
        this.remainder = remainder;
    }

    public void setExpiry(Date expiry) {
        this.expiry = expiry;
    }

    public void setCrediti(double crediti) { this.crediti = crediti; }

    @Override
    public String toString() {
        return "PaymentInfo for user.getName() { id=" + id + "remainder" + remainder + "expiry=" + expiry + " crediti residui=" + crediti + "}";
    }
}
