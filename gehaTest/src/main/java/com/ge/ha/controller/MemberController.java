package com.ge.ha.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ge.ha.domain.Member;
import com.ge.ha.service.EmailServiceImpl;
import com.ge.ha.service.MemberService;

@Controller
public class MemberController {

	@Autowired
	MemberService service;

	@Autowired
	EmailServiceImpl emailService;

	@RequestMapping("/")
	public String auth(Model model) {
		/*
		 * if(authentication!=null) { LinkedHashMap<String, Object> properties =
		 * (LinkedHashMap<String, Object>) authentication
		 * .getUserAuthentication().getDetails();
		 * 
		 * model.addAttribute("email",properties.get("email")); }
		 */
		return "/main";
	}

	@RequestMapping("/findPw")
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
	
	@RequestMapping("/modifyPw")
	public String modifyPw() {

		return "/modifyPw";
	}

	@RequestMapping("/login")
	public String login() {

		return "/login";
	}

	@RequestMapping("/hello")
	public String hello() {

		return "/hello";
	}

	@PostMapping("/create")
	public String create(Member member) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		member.setPassword(passwordEncoder.encode(member.getPassword()));
		member.setAuthorityCode("USER");
		service.save(member);
		return "redirect:/";

	}

}
