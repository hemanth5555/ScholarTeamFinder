package com.web.cyneuro.user;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.bson.types.ObjectId;

@Document(collection = "users")
public class users {
	@Id
	private ObjectId id;
    private String user_id;
    private String user_FirstName; 
    private String user_LastName;
    private String username;
    private String email; 
    private String password; 
    private String affiliation;
    private String title;
    private String description;
    private String created_at;
    private String updated_at;
    private String interests;
    
    public ObjectId getId() {
		return id;
	}

	public void setId(ObjectId id) {
		this.id = id;
	}
    
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
    public String getUseid() {
		return user_id;
	}

	public void setUserid(String user_id) {
		this.user_id = user_id;
	}

    public String getUserFirstName() {
        return user_FirstName;
    }

    public void setUserFirstName(String user_FirstName) {
        this.user_FirstName = user_FirstName;
    }

    public String getUserLastName() {
        return user_LastName;
    }

    public void setUserLastName(String user_LastName) {
        this.user_LastName = user_LastName;
    }

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getAffiliation() {
		return affiliation;
	}

	public void setAffiliation(String affiliation) {
		this.affiliation = affiliation;
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	

	public String getCreateat() {
		return created_at;
	}

	public void setCreateat(String created_at) {
		this.created_at = created_at;
	}
	
	public String getUpdatedat() {
		return updated_at;
	}

	public void setUpdatedat(String updated_at) {
		this.updated_at = updated_at;
	}
	
	public String getInterets() {
		return interests;
	}

	public void setInterets(String interests) {
		this.interests = interests;
	}
	
}
