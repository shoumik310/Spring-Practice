package com.kimuohs.buyit.controller;

import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.kimuohs.buyit.model.User;
import com.kimuohs.buyit.repository.RoleRepository;
import com.kimuohs.buyit.repository.UserRepository;

@Controller
public class LoginController {
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;
	@Autowired
	UserRepository userRepository;
	@Autowired
	RoleRepository roleRepository;

	@GetMapping("/login")
	String getLogin() {
		return "login";
	}

	@GetMapping("/register")
	String getRegister() {
		return "register";
	}

	@PostMapping("/register")
	String postRegister(@ModelAttribute("user") User user, HttpServletRequest request) throws ServletException {
		String password = user.getPassword();
		user.setPassword(bCryptPasswordEncoder.encode(password));
		user.setRoles(Arrays.asList(roleRepository.findById(2).get()));
		userRepository.save(user);
		request.login(user.getEmail(), password);
		return "redirect:/";
	}
}
