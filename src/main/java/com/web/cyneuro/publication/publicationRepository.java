package com.web.cyneuro.publication;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
 
import com.web.cyneuro.publication.publication;

@Repository
public interface publicationRepository extends MongoRepository<publication,Long>  {
    public List<publication> findAll();
}
