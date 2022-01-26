package dsbd2122.youmediaresource.DataModel;

import dsbd2122.youmediaresource.DataModel.MediaResource;
import org.springframework.data.repository.CrudRepository;

import javax.persistence.Table;

@Table
public interface MediaResourceRepository extends CrudRepository<MediaResource, Integer> {

    MediaResource findByPath1(String path1);
}
