package com.web.cyneuro.user.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
 
import com.web.cyneuro.user.users;


@Repository
public interface userRepository extends MongoRepository<users,Long> {

	public users findByUsernameAndPassword(String username, String password);
	
	public List<users> findByUsername(String username);
	
	public users findByEmail(String email);
	
	public users findByEmailAndPassword(String email, String password);

	public List<users> findAll();
	
}
