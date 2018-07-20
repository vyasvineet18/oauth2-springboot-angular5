package com.server.auth.entity;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 * Created by Agus Suhardi on 22-Jun-17.
 */
@Entity
@Table(name = "role")
public class Role implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = -5954764196852861916L;

	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Long id;
    
    private String role;
    
    @ManyToMany(cascade = CascadeType.ALL , mappedBy = "userRoles")
    private Set<User> user = new HashSet<>(0);

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Set<User> getUser() {
		return user;
	}

	public void setUser(Set<User> user) {
		this.user = user;
	}
	
	
    
}