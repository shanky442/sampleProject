package shl.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import shl.models.FileDetails;

public interface FileRepository extends MongoRepository<FileDetails, String>{
    //String findById(String id);
    //FileDetails findById(String id);
}
