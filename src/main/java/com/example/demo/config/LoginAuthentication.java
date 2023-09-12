package com.example.demo.config;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.example.demo.repository.HelperMemberRepository;
import com.example.demo.repository.StoreMemberRepository;
import com.example.demo.model.HelperMember;
import com.example.demo.model.StoreMember;

@Component
public class LoginAuthentication implements AuthenticationProvider {
	@Autowired
	private HelperMemberRepository helperMemberDao;

	@Autowired
	private StoreMemberRepository dao;

	@Autowired
	private PasswordEncoder encoder;

	private static final Logger log = LoggerFactory.getLogger(LoginAuthentication.class);

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {

		String account = authentication.getName();
		String pwd = authentication.getCredentials().toString();
		HelperMember helperMember = helperMemberDao.findHelperMemberByAccount(account);
		StoreMember storeMember = dao.findStoreMemberByAccount(account);
		if (helperMember != null) {
			if (encoder.matches(pwd, helperMember.getPassword())) {
				log.warn("failure in step2");
				List<GrantedAuthority> authorities = new ArrayList<>();
				authorities.add(new SimpleGrantedAuthority(helperMember.getRole()));
				log.warn("failure in step3");
				return new UsernamePasswordAuthenticationToken(account, pwd, authorities);
			} else {
				log.warn("failure In step4");
				throw new BadCredentialsException("Invalid password!");
			}
		} else if (storeMember != null) {
			if (encoder.matches(pwd, storeMember.getPassword())) {
				log.warn("failure in step2");
				List<GrantedAuthority> authorities = new ArrayList<>();
				authorities.add(new SimpleGrantedAuthority(storeMember.getRole()));
				log.warn("failure in step3");
				return new UsernamePasswordAuthenticationToken(account, pwd, authorities);
			} else {
				log.warn("failure In step4");
				throw new BadCredentialsException("Invalid password!");
			}

		} else {
			log.warn("failure in step5");
			throw new BadCredentialsException("No user registered with this details!");
		}
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
	}
}
