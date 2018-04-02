package com.brave.config;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.brave.entity.Visit;

public class MyUserDetails implements UserDetails {
	/**
	 * 
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Visit visit;
	private Collection<? extends GrantedAuthority> authoritiesCollection;

	public MyUserDetails(Visit visit, Collection<? extends GrantedAuthority> authoritiesCollection) {
		this.visit = visit;
		this.authoritiesCollection = authoritiesCollection;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return this.authoritiesCollection;
	}

	@Override
	public String getPassword() {
		return visit.getPassword();
	}

	@Override
	public String getUsername() {
		return visit.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

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
		return true;
	}

	@Override
	public String toString() {
		return "用户名:" + visit.getUsername() + ";密码:" + visit.getPassword() + "权限:" + this.getAuthorities();
	}
}
