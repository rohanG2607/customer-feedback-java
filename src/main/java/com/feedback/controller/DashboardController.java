package com.feedback.controller;

import java.util.Optional;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.feedback.entity.Feedback;
import com.feedback.entity.User;
import com.feedback.security.CustomUserDetails;
import com.feedback.service.FeedbackService;

@Controller
public class DashboardController {

	private final FeedbackService feedbackService;

	public DashboardController(FeedbackService feedbackService) {
		this.feedbackService = feedbackService;
	}

	@GetMapping("/dashboard")
	public String dashboard(Authentication authentication, Model model) {

		CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();

		User user = userDetails.getUser();

		Optional<Feedback> feedback = feedbackService.getFeedbackByUser(user);

		model.addAttribute("user", user);
		model.addAttribute("hasFeedback", feedback.isPresent());

		return "dashboard";
	}

}
