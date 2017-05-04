package com.virglk.jwtsecurity.security;

import java.sql.Date;
import java.util.Calendar;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.virglk.jwtsecurity.model.AuthorityName;

public class JwtUser implements UserDetails {
	
	
	private final Long id;
	private final String email;
	private final String passwordHash;
	private final boolean isActive;
	private final AuthorityName role;
	private final Date lastPayDate;
	private final Collection<? extends GrantedAuthority> authorities;
	

	public JwtUser(Long id, String email, String passwordHash, boolean isActive, AuthorityName role, Date lastPayDate,
			Collection<? extends GrantedAuthority> authorities) {
		this.id = id;
		this.email = email;
		this.passwordHash = passwordHash;
		this.isActive = isActive;
		this.role = role;
		this.lastPayDate = lastPayDate;
		this.authorities = authorities;
	}
	
	@JsonIgnore
    public Long getId() {
        return id;
    }
	
	
	public boolean isActive() {
		return isActive;
	}

	public AuthorityName getRole() {
		return role;
	}

	public Date getLastPayDate() {
		return lastPayDate;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return authorities;
	}
	
	@JsonIgnore
	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return passwordHash;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return email;
	}

	@JsonIgnore
	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		if(getDateDiffInDays(lastPayDate) > 365){
			return false;
		}
		return true;
	}

	
	@JsonIgnore
	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return isActive;
	}
	
	private long getDateDiffInDays(Date lastPayDate) {
		Calendar lastPaidDate = Calendar.getInstance();
		lastPaidDate.setTime(lastPayDate);
		
		Calendar today = Calendar.getInstance();
		long diff = today.getTimeInMillis() - lastPaidDate.getTimeInMillis(); //result in millis
		
		long days = diff / (24 * 60 * 60 * 1000);
		
		return days;
	}

}
