package dsbd2122.youmediaresource.DataModel;

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

    @NotNull(message = "costo_crediti parameter cannot be blank!")

    private Integer costo_crediti;

    @NotNull(message = "views parameter cannot be blank!")

    private Integer views;

    // Metodi Getter
    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPath1() {
        return path1;
    }

    public Integer getCosto_crediti() {
        return costo_crediti;
    }

    public Integer getViews() {
        return views;
    }

    // Metodi Setter
    public MediaResource setId(Integer id) {
        this.id = id;
        return this;
    }

    public MediaResource setName(String name) {
        this.name = name;
        return this;
    }

    public MediaResource setPath1(String path1) {
        this.path1 = path1;
        return this;
    }

    public MediaResource setCosto_crediti(Integer costo_crediti) {
        this.costo_crediti = costo_crediti;
        return this;
    }

    public MediaResource setViews(Integer views) {
        this.views = views;
        return this;
    }

    @Override
    public String toString() {
        return "Resource{id=" + id + "  name=" + name + "  file path=" + path1 + " costo (crediti)=" + costo_crediti + " visualizzazioni=" + views + "}";
    }
}