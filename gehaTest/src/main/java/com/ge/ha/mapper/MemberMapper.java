package com.ge.ha.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.ge.ha.domain.Member;

@Mapper
public interface MemberMapper {

	public void insertUser(@Param("member")Member member);
	
	public Member findById(String id);
	//public void insertUserAutority(String authorityCode, String authority);
	
	public int idCheck(String id);
	
	public void createAuthKey(String user_email, String user_authCode);
	
	public void userAuth(String id);
}
