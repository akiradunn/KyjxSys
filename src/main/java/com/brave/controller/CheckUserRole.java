package com.brave.controller;

import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CheckUserRole {
	@RequestMapping(value = "/checkUserRole", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
	public String checkLogin(HttpServletRequest request) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Collection<? extends GrantedAuthority> authStore = auth.getAuthorities();
		Iterator<?> it = authStore.iterator();
		String role = it.next().toString();
		return "{\"username\":\"" + auth.getName() + "\",\"role\":\"" + role + "\"}";
	}
}
