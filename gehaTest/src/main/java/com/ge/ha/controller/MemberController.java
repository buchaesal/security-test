package com.ge.ha.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ge.ha.domain.Member;
import com.ge.ha.security.SecurityMember;
import com.ge.ha.service.EmailServiceImpl;
import com.ge.ha.service.MemberService;

@Controller
public class MemberController {

	@Autowired
	MemberService service;

	@Autowired
	EmailServiceImpl emailService;


	
	@PostMapping(value="/idcheck.do")
    @ResponseBody
    public Map<Object, Object> idcheck(@RequestBody String id) {
        
        int count = 0;
        Map<Object, Object> map = new HashMap<Object, Object>();
 
        count = service.idCheck(id);
        map.put("cnt", count);
 
        return map;
    }
	
	@RequestMapping("/login")
	public void login() {
		
	}
	@RequestMapping("/goFindPw")
	public String goFindPw() {
		
		return "/findPw";
	}

	@PostMapping(value="findPw.do")
	@ResponseBody
	public String findPw(@RequestBody String id) throws Exception {
		
		id = id.replace("%40", "@").substring(3);
		
		char[] charSet = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
				'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O',
				'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };
		
		int idx = 0;
		StringBuffer sb = new StringBuffer();

		for (int i = 0; i < 10; i++) { 
			idx = (int) (charSet.length * Math.random()); 
			 sb.append(charSet[idx]);
		}
		
		String text=sb.toString();
		
		emailService.sendSimpleMessage(id, "[#GEHA] 비밀번호 찾기 인증코드 발신 메일입니다. ", "인증코드 : " + text);
	
		return text;
	}
	
	
	
	@RequestMapping("/checkMail")
	public String checkMail() {
		return "/modifyPw";
	}



	@RequestMapping("/signup")
	public String signup() {

		return "/signup";
	}
	
	@RequestMapping("/facebook/complete")
	public String facebookComplete() {
		OAuth2Authentication authentication = (OAuth2Authentication) SecurityContextHolder
				.getContext()
				.getAuthentication();
		
		Map<String, String> map = (HashMap<String, String>)authentication
				.getUserAuthentication().getDetails();
		
		System.out.println(map.get("gender"));
		
		return "/fbComplete";
	}
	
	@RequestMapping("/modifyPw")
	public String modifyPw() {

		return "/modifyPw";
	}



	@RequestMapping("/hello")
	public String hello(Authentication auth) {
		SecurityMember sc = (SecurityMember)auth.getPrincipal();
		System.out.println(sc.getMemberCode());
		return "/hello";
	}

	@PostMapping("/create")
	public String create(Member member,RedirectAttributes rttr) throws Exception {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		member.setPassword(passwordEncoder.encode(member.getPassword()));
		member.setAuthority("USER");
		service.save(member);
		return "redirect:/";

	}
	
	
	@RequestMapping(value = "/emailConfirming", method = RequestMethod.GET)
	public String emailConfirming(String id,String key,Model model) 
			throws Exception { // 이메일인증
		service.userAuth(id);
		model.addAttribute("user_email", id);
		

		return "/emailConfirm";
	}
	
	@RequestMapping("/emailConfirm")
	public void emailConfirm() {
	
	}

}
