package com.ge.ha.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ge.ha.domain.Member;
import com.ge.ha.service.MemberService;

@Controller
public class MemberController {

	@Autowired
	MemberService service;

	@RequestMapping("/")
	public String main() {

		return "/main";
	}

	@RequestMapping("/signup")
	public String signup() {

		return "/signup";
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
		member.setAuthorityCode("U");
		service.save(member);
		return "redirect:/";

	}

}
