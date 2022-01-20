package dsbd2122.youmedia.DataModel;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity

public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private Integer id;

    @NotNull(message = "the name parameter cannot be blank!")

    private String name;

    @NotNull(message = "the email parameter cannot be blank!")
    @Column(unique = true)

    private String email;

    @NotNull(message = "the password parameter cannot be blank!")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)

    private String password;

    // Metodi Getter
    public Integer getId(){
        return id;
    }
    public String getName(){
        return name;
    }
    public String getEmail(){
        return email;
    }
    public String getPassword() {
        return password;
    }

    // Metodi Setter
    public Customer setId(Integer id){
        this.id = id;
        return this;
    }
    public Customer setName(String name){
        this.name = name;
        return this;
    }
    public Customer setEmail(String email){
        this.email = email;
        return this;
    }
    public Customer setPassword(String password){
        this.password = password;
        return this;
    }

    @Override
    public String toString(){
        return "Customer{id=" + id + "  name=" + name + "  email=" + email + "  password=" + password + "}";
    }
}