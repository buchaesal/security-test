package com.ge.ha.service;

import com.ge.ha.domain.Member;

public interface MemberService {
	public void save(Member member) throws Exception;
	
	public int idCheck(String id);
	
	public void userAuth(String id);
}
