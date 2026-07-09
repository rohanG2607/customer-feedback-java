package com.feedback.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.feedback.dto.FeedbackDTO;
import com.feedback.entity.Feedback;
import com.feedback.entity.User;
import com.feedback.security.CustomUserDetails;
import com.feedback.service.FeedbackService;

import jakarta.validation.Valid;

@Controller
public class FeedbackController {

    private final FeedbackService feedbackService;

    public FeedbackController(FeedbackService feedbackService) {
        this.feedbackService = feedbackService;
    }

    @GetMapping("/feedback/add")
    public String showAddFeedbackForm(Authentication authentication,
                                      Model model) {

        CustomUserDetails userDetails =
                (CustomUserDetails) authentication.getPrincipal();

        model.addAttribute("user", userDetails.getUser());
        model.addAttribute("feedback", new FeedbackDTO());
        model.addAttribute("isEdit", false);
        model.addAttribute("action", "/feedback/add");

        return "feedback-form";
    }

    @PostMapping("/feedback/add")
    public String saveFeedback(@Valid FeedbackDTO feedbackDTO,
                               BindingResult bindingResult,
                               Authentication authentication,
                               Model model, RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {

            model.addAttribute("isEdit", false);
            model.addAttribute("action", "/feedback/add");

            return "feedback-form";
        }

        CustomUserDetails userDetails =
                (CustomUserDetails) authentication.getPrincipal();

        User user = userDetails.getUser();

        feedbackService.saveFeedback(feedbackDTO, user);

        redirectAttributes.addFlashAttribute(
                "success",
                "Feedback submitted successfully.");

        return "redirect:/dashboard";
    }

    @GetMapping("/feedback/edit")
    public String showEditFeedbackForm(Authentication authentication,
                                       Model model) {

        CustomUserDetails userDetails =
                (CustomUserDetails) authentication.getPrincipal();

        User user = userDetails.getUser();

        Feedback feedback =
                feedbackService.getFeedbackByUserOrThrow(user);

        FeedbackDTO feedbackDTO = new FeedbackDTO();

        feedbackDTO.setTitle(feedback.getTitle());
        feedbackDTO.setDescription(feedback.getDescription());

        model.addAttribute("feedback", feedbackDTO);
        model.addAttribute("isEdit", true);
        model.addAttribute("action", "/feedback/edit");

        return "feedback-form";
    }

    @PostMapping("/feedback/edit")
    public String saveEditForm(@Valid FeedbackDTO feedbackDTO,
                               BindingResult bindingResult,
                               Authentication authentication,
                               Model model, RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {

            model.addAttribute("isEdit", true);
            model.addAttribute("action", "/feedback/edit");

            redirectAttributes.addFlashAttribute(
                    "success",
                    "Feedback updated successfully.");

            return "redirect:/dashboard";
        }

        CustomUserDetails userDetails =
                (CustomUserDetails) authentication.getPrincipal();

        feedbackService.updateFeedback(
                feedbackDTO,
                userDetails.getUser());

        return "redirect:/dashboard";
    }

}