package com.web.cyneuro.publication;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Column;

import org.bson.types.ObjectId;

@Document(collection = "articles_10000")
public class publication {
    @Id
    private ObjectId id;
    private String title;
    @Column(name = "abstract")
    private String abstracts;
    private String authors;
    private String journal;
    private String url;
    private String publish_time;
    private String level;

    public ObjectId getId() {
		return id;
	}

	public void setId(ObjectId id) {
		this.id = id;
	}

    public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

    public String getAbstracts() {
		return abstracts;
	}

	public void setAbstracts(String abstracts) {
		this.abstracts = abstracts;
	}

    public String getAuthors() {
		return authors;
	}

	public void setAuthors(String authors) {
		this.authors = authors;
	}

    public String getJournal() {
		return journal;
	}

	public void setJournal(String journal) {
		this.journal = journal;
	}

    public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

    public String getPublishTime() {
		return publish_time;
	}

	public void setPublishTime(String publish_time) {
		this.publish_time = publish_time;
	}

    public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}
}
