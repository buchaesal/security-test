package com.ge.ha.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ge.ha.domain.Member;
import com.ge.ha.mapper.MemberMapper;
import com.ge.ha.security.SecurityMember;

//데이터베이스에서 등록된 사용자로 로그인되도록 설정하는 클래스
@Service
public class UserDetailService implements UserDetailsService {

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
