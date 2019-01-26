package com.ge.ha.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ge.ha.domain.Member;
import com.ge.ha.mapper.MemberMapper;
import com.ge.ha.util.MailHandler;
import com.ge.ha.util.TempKey;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	private MemberMapper memberMapper;
	
	@Autowired
	private JavaMailSender mailSender;
	
	@Transactional
	public void save(Member member) throws Exception {
		memberMapper.insertUser(member);
		//memberMapper.insertUserAutority(member.getAuthorityCode(), authority);
		
		String key = new TempKey().getKey(50, false); // 인증키 생성

		memberMapper.createAuthKey(member.getId(), key); // 인증키 DB저장

		MailHandler sendMail = new MailHandler(mailSender);
		sendMail.setSubject("[#GEHA 서비스 이메일 인증]");
		sendMail.setText(
				new StringBuffer().append("<h1>메일인증</h1>")
				.append("<a href='http://localhost:8888/emailConfirm?id=")
				.append(member.getId()).append("&key=").append(key)
				.append("' target='_blenk'>이메일 인증 확인</a>").toString());
		sendMail.setFrom("eks4116@gmail.com", "샵게하 운영자");
		sendMail.setTo(member.getId());
		sendMail.send();
		
		/*emailService.sendSimpleMessage(member.getId(), "[GEHA 회원가입 이메일 인증]", 
				new StringBuffer().append("<h1>메일인증</h1>")
				.append("<a href='http://localhost:8888/user/emailConfirm?id=")
				.append(member.getId()).append("&key=").append(key)
				.append("' target='_blank'>이메일 인증 확인</a>").toString());*/
	}

	@Override
	public int idCheck(String id) {
		return memberMapper.idCheck(id);
	}

	@Override
	public void userAuth(String id) {
		memberMapper.userAuth(id);		
	}
}
