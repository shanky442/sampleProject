package shl;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface GamesRepository extends MongoRepository<Games, String> {

    public Games findById(Id id);
    public List<Games> findByFirstParty(String firstParty);
    public List<Games> findBySecondParty(String firstParty);

}
