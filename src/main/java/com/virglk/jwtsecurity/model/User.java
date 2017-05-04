package com.virglk.jwtsecurity.model;

import java.sql.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;


@Entity
@Table(name="user")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, updatable= false)
	private Long id;
	
	@Column(name = "email", nullable = false, unique = true)
	private String email;
	
	@Column(name = "password_hash", nullable = false)
	private String passwordHash;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "user_authority",
			joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
			inverseJoinColumns = {@JoinColumn(name = "authority_id", referencedColumnName = "id")})
	private List<Authority> authorities;
	
	@Column(name = "is_active", nullable = false)
	private boolean isActive;
	
	@Column(name = "last_pay_date", nullable = false)
	private Date lastPayDate;
	
	public User(){}

	public User(Long id, String email, String passwordHash, List<Authority> authorities, boolean isActive,
			Date lastPayDate) {
		super();
		this.id = id;
		this.email = email;
		this.passwordHash = passwordHash;
		this.authorities = authorities;
		this.isActive = isActive;
		this.lastPayDate = lastPayDate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPasswordHash() {
		return passwordHash;
	}

	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}

	public List<Authority> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(List<Authority> authorities) {
		this.authorities = authorities;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public Date getLastPayDate() {
		return lastPayDate;
	}

	public void setLastPayDate(Date lastPayDate) {
		this.lastPayDate = lastPayDate;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", email=" + email + ", passwordHash=" + passwordHash + ", authorities=" + authorities
				+ ", isActive=" + isActive + ", lastPayDate=" + lastPayDate + "]";
	}

		
}