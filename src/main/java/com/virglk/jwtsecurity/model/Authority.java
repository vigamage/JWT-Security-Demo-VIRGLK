package com.virglk.jwtsecurity.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "authority")
public class Authority {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, updatable= false)
	private long id;
	
	@Column(name = "authority_name")
    @NotNull
    @Enumerated(EnumType.STRING)
	private AuthorityName name;
	
	@ManyToMany(mappedBy = "authorities", fetch = FetchType.LAZY)
	private List<User> users;

	public Authority() {
		
	}

	public Authority(long id, AuthorityName name, List<User> users) {
		this.id = id;
		this.name = name;
		this.users = users;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public AuthorityName getName() {
		return name;
	}

	public void setName(AuthorityName name) {
		this.name = name;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	@Override
	public String toString() {
		return "Authority [id=" + id + ", name=" + name + ", users=" + users + "]";
	}
	
		
}
