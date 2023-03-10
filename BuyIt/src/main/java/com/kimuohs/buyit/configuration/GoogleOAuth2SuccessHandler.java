
package com.kimuohs.buyit.configuration;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.kimuohs.buyit.model.User;
import com.kimuohs.buyit.repository.RoleRepository;
import com.kimuohs.buyit.repository.UserRepository;

@Component
public class GoogleOAuth2SuccessHandler implements AuthenticationSuccessHandler {

	@Autowired
	UserRepository userRepository;
	@Autowired
	RoleRepository roleRepository;

	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		OAuth2AuthenticationToken token = (OAuth2AuthenticationToken) authentication;
		String email = token.getPrincipal().getAttributes().get("email").toString();
		if (!userRepository.findUserByEmail(email).isPresent()) {
			User user = User.builder()
					.email(email)
					.firstName(token.getPrincipal().getAttributes().get("given_name").toString())
					.lastName(token.getPrincipal().getAttributes().get("family_name").toString())
					.roles(Arrays.asList(roleRepository.findById(2).get()))
					.build();
			userRepository.save(user);
		}
		redirectStrategy.sendRedirect(request, response, "/");
	}

}
