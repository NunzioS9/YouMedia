package dsbd2122.youmediaresource.Services;

import com.example.common_dto.dto.ResourceRequestDTO;
import com.example.common_dto.dto.ResourceResponseDTO;
import com.example.common_dto.enums.ResourceStatus;
import dsbd2122.youmediaresource.DataModel.MediaResource;
import dsbd2122.youmediaresource.DataModel.MediaResourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MediaResourceService {

    @Autowired
    MediaResourceRepository repository;

    public MediaResource register(MediaResource resource) {
        return repository.save(resource);
    }

    public MediaResource getContent(Integer id) {
        return repository.findById(id).get();

    }

    public Iterable<MediaResource> getAll() {
        return repository.findAll();
    }


    public String delete(Integer id) {

        if (repository.findById(id).isPresent()) {
            repository.deleteById(id);
            return "Content with id: " + id + " has been deleted!";
        }
        return "Content with id:" + id + " is not present";

    }

    public ResourceResponseDTO deductResource(final ResourceRequestDTO requestDTO){

        ResourceResponseDTO responseDTO = new ResourceResponseDTO();
        responseDTO.setSagaId(requestDTO.getSagaId());
        responseDTO.setCustomerId(requestDTO.getCustomerId());
        responseDTO.setResourceId(requestDTO.getResourceId());
        if(repository.findById(requestDTO.getResourceId()).isPresent()) {
            responseDTO.setStatus(ResourceStatus.AVAILABLE);
            responseDTO.setViews(requestDTO.getViews() + 1);
        }
        else
            responseDTO.setStatus(ResourceStatus.UNAVAILABLE);

        return responseDTO;
    }
}
