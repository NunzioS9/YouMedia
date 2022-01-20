package dsbd2122.youmedia.DataModel;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity

public class MediaResource {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private Integer id;

    @NotNull(message = "the name parameter cannot be blank!")

    private String name;

    @NotNull(message = "the file_path parameter cannot be blank!")
    //@Column(unique = true)
    //@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)

    private String path1;

    // Metodi Getter
    public Integer getId(){
        return id;
    }
    public String getName(){
        return name;
    }
    public String getPath1(){
        return path1;
    }

    // Metodi Setter
    public MediaResource setId(Integer id){
        this.id = id;
        return this;
    }
    public MediaResource setName(String name){
        this.name = name;
        return this;
    }
    public MediaResource setPath1(String path1){
        this.path1 = path1;
        return this;
    }

    @Override
    public String toString(){
        return "Customer{id=" + id + "  name=" + name + "  file path=" + path1 + "}";
    }
}