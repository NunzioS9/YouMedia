package dsbd2122.youmedia.Services;

import dsbd2122.youmedia.DataModel.MediaResource;
import dsbd2122.youmedia.DataModel.MediaResourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MediaResourceService{

    @Autowired
    MediaResourceRepository repository;

    public MediaResource register(MediaResource resource){
        return repository.save(resource);
    }

    public MediaResource getContent(Integer id){
        return repository.findById(id).get();

    }

    public Iterable<MediaResource> getAll(){
        return repository.findAll();
    }

    

    public String delete(Integer id) {

        if (repository.findById(id).isPresent()) {
            repository.deleteById(id);
            return "Content with id: " + id + " has been deleted!";
        }
        return "Content with id:" + id + " is not present";

    }
}
