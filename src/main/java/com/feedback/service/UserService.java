package com.feedback.service;

import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.feedback.dto.UserRegisterationDTO;
import com.feedback.entity.User;
import com.feedback.enums.Role;
import com.feedback.repository.UserRepository;

@Service
public class UserService {

	private final UserRepository userRepository;

	private final PasswordEncoder passwordEncoder;

	public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
	}

	public User registerUser(UserRegisterationDTO registerationDTO) {
		if (userRepository.existsByUsername(registerationDTO.getUsername())) {
			throw new RuntimeException("Username Already Exists");
		}

		if (userRepository.existsByEmail(registerationDTO.getEmail())) {
			throw new RuntimeException("Email Already Exists");
		}

		User user = new User();
		user.setFullName(registerationDTO.getFullName());
		user.setUserName(registerationDTO.getUsername());
		user.setEmail(registerationDTO.getEmail());
		user.setPassword(passwordEncoder.encode(registerationDTO.getPassword()));
		user.setRole(Role.USER);

		return userRepository.save(user);
	}

	public Optional<User> findByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	public Optional<User> findByEmial(String email) {
		return userRepository.findByEmail(email);
	}
	
	public long countUsers() {
	    return userRepository.count();
	}

}
