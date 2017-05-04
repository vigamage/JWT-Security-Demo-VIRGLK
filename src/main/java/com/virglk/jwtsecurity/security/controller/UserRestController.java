package com.virglk.jwtsecurity.security.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.virglk.jwtsecurity.security.JwtTokenUtil;
import com.virglk.jwtsecurity.security.JwtUser;

@RestController
public class UserRestController {

	@Value("${jwt.auth.header}")
	private String tokenHeader;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private UserDetailsService userDetailsService;

	@RequestMapping(value="user", method=RequestMethod.GET)
	public JwtUser getAuthenticatedUser(HttpServletRequest request){

		String token = request.getHeader(tokenHeader);
		System.out.println("VIRGLK INFO : getting the user name from the token");
		String username = jwtTokenUtil.getUsernameFromToken(token);
		JwtUser user = (JwtUser) userDetailsService.loadUserByUsername(username);
		return user;

	}


}
