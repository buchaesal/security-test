package com.ge.ha.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import com.ge.ha.domain.Member;

public class SecurityMember extends User implements UserDetails {
	private static final String ROLE_PREFIX = "ROLE_";
	private static final long serialVersionUID = 1L;
	
	public SecurityMember(Member member){
		super(member.getId(),member.getPassword(),makeGrantedAuthority(member));
	}
	
	private static List<GrantedAuthority> makeGrantedAuthority(Member member){
		List<GrantedAuthority> list = new ArrayList<>();
		list.add(new SimpleGrantedAuthority(ROLE_PREFIX + member.getAuthorityCode()));
		return list;
	}
}
