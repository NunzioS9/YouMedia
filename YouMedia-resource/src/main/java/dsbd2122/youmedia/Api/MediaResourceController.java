package dsbd2122.youmedia.Api;

import dsbd2122.youmedia.DataModel.MediaResource;
import dsbd2122.youmedia.DataModel.MediaResourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path="/content")
public class MediaResourceController {

    @Autowired
    MediaResourceRepository repository;
    
    // GET http://localhost:8080/content/{id}
    @GetMapping(path="/{id}")
    public @ResponseBody
    MediaResource getContent(@PathVariable Integer id) {
        return repository.findById(id).get();
    }

    // GET http://localhost:8080/content/all
    @GetMapping(path="/all")
    public @ResponseBody Iterable<MediaResource> getAll() {
        return repository.findAll();
    }

    // GET http://localhost:8080/content/path1/{path1}
    @GetMapping(path="/path1/{path1}")
    public @ResponseBody MediaResource getContent(@PathVariable String path1) {
        return repository.findByPath1(path1);
    }

    // POST http://localhost:8080/content/register
    @PostMapping(path="/register", consumes = "application/json")
    public @ResponseBody MediaResource register(@RequestBody MediaResource content) {
        return repository.save(content);
    }

    // DELETE http://localhost:8080/content/{id}
    @DeleteMapping(path="/{id}")
    public @ResponseBody String delete(@PathVariable Integer id){
        if(repository.findById(id).isPresent()){
            repository.deleteById(id);
            return "Content with id: " + id + " has been deleted!";
        }
        return "Content with id: " + id + " is not present";
    }

}

