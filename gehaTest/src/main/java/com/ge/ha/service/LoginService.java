package com.ge.ha.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ge.ha.domain.Member;
import com.ge.ha.mapper.MemberMapper;
import com.ge.ha.security.SecurityMember;

@Service
public class LoginService implements UserDetailsService {

	@Autowired
	MemberMapper memberMapper;

	@Override
	public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {

		Member member = memberMapper.findById(id);
		if (member != null) {
			return new SecurityMember(member);
		} else {
			return null;
		}
	}

}
