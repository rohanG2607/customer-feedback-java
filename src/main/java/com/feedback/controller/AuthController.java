package com.feedback.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.feedback.dto.UserRegisterationDTO;
import com.feedback.service.UserService;

import jakarta.validation.Valid;

@Controller
public class AuthController {

	private final UserService userService;

	public AuthController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping("/register")
	public String showRegisterationForm(Model model) {

		model.addAttribute("user", new UserRegisterationDTO());

		return "register";

	}
	
	@GetMapping("/login")
	public String showLoginPage() {
		return "login";
	}

	@PostMapping("/register")
	public String registerUser(@Valid UserRegisterationDTO userRegisterationDTO, BindingResult bindingResult,
			Model model, RedirectAttributes redirectAttributes) {
		if (bindingResult.hasErrors()) {
			return "register";
		}

		userService.registerUser(userRegisterationDTO);

		redirectAttributes.addFlashAttribute(
	            "success",
	            "Registration completed successfully. Please login.");

	    return "redirect:/login";
	}

}
