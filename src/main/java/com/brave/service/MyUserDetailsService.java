package com.brave.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.brave.config.MyUserDetails;
import com.brave.dao.AuthoritiesDao;
import com.brave.dao.VisitDao;
import com.brave.entity.Visit;

@Service("myuserDetailsService")
public class MyUserDetailsService implements UserDetailsService {
	@Autowired
	@Qualifier("authoritiesDao")
	private AuthoritiesDao authoritiesDao;
	@Autowired
	@Qualifier("visitDao")
	private VisitDao visitDao;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Visit visit = visitDao.getVisit(username);
		if (visit == null) {
			System.out.println("test");
			throw new UsernameNotFoundException("用户名:" + username + "无法找到！");
		}
		List<String> authoritiesCollection = authoritiesDao.getAuthorities(visit.getR_id());
		Set<GrantedAuthority> authoritiesSet = new HashSet<GrantedAuthority>();
		for (String a : authoritiesCollection) {
			authoritiesSet.add(new SimpleGrantedAuthority(a));
		}
		MyUserDetails test = new MyUserDetails(visit, authoritiesSet);
		return test;
	}
}
