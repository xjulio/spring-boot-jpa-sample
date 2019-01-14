package org.xjulio.demo.model;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
/**
 * @author <a href="mailto:xjulio@gmail.com">Julio Cesar Damasceno</a>
 */
public class Event {

	@Id
    private Long id;
	
    private String type;
    
    @ManyToOne
    @JoinColumn(name = "ACTOR_ID")
    @Cascade(CascadeType.ALL)
    private Actor actor;

    @ManyToOne
    @JoinColumn(name = "REPO_ID")
    @Cascade(CascadeType.ALL)
    private Repo repo;
    
    @JsonProperty(value="created_at")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone="UTC")
    private Timestamp createdAt;

    public Event() {
    }

    public Event(Long id, String type, Actor actor, Repo repo, Timestamp createdAt) {
        this.id = id;
        this.type = type;
        this.actor = actor;
        this.repo = repo;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Actor getActor() {
        return actor;
    }

    public void setActor(Actor actor) {
        this.actor = actor;
    }

    public Repo getRepo() {
        return repo;
    }

    public void setRepo(Repo repo) {
        this.repo = repo;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Event [" + (id != null ? "id=" + id + ", " : "") + (type != null ? "type=" + type + ", " : "")
				+ (actor != null ? "actor=" + actor + ", " : "") + (repo != null ? "repo=" + repo + ", " : "")
				+ (createdAt != null ? "createdAt=" + createdAt : "") + "]";
	}
}