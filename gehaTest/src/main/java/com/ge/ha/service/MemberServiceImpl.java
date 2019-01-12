package com.ge.ha.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ge.ha.domain.Member;
import com.ge.ha.mapper.MemberMapper;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	private MemberMapper memberMapper;

	public void save(Member member) {
		memberMapper.insertUser(member);
		//memberMapper.insertUserAutority(member.getAuthorityCode(), authority);
		
	}
}
