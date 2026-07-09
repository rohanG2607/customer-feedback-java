package com.feedback.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.feedback.dto.FeedbackDTO;
import com.feedback.entity.Feedback;
import com.feedback.service.FeedbackService;
import com.feedback.service.UserService;

import jakarta.validation.Valid;

@Controller
public class AdminController {

    private final FeedbackService feedbackService;
    private final UserService userService;

    public AdminController(FeedbackService feedbackService,
                           UserService userService) {

        this.feedbackService = feedbackService;
        this.userService = userService;
    }

    @GetMapping("/admin/dashboard")
    public String adminDashboard(Model model) {

        model.addAttribute("feedbackCount",
                feedbackService.countFeedback());

        model.addAttribute("userCount",
                userService.countUsers());

        return "admin-dashboard";
    }

    @GetMapping("/admin/feedbacks")
    public String viewAllFeedback(Model model) {

        model.addAttribute("feedbacks",
                feedbackService.getAllFeedback());

        return "admin-feedbacks";
    }

    @GetMapping("/admin/feedback/edit/{id}")
    public String showEditFeedbackForm(@PathVariable Long id,
                                       Model model) {

        Feedback feedback = feedbackService.getFeedbackById(id)
                .orElseThrow(() ->
                        new RuntimeException("Feedback not found."));

        FeedbackDTO feedbackDTO = new FeedbackDTO();

        feedbackDTO.setTitle(feedback.getTitle());
        feedbackDTO.setDescription(feedback.getDescription());

        model.addAttribute("feedback", feedbackDTO);
        model.addAttribute("isEdit", true);
        model.addAttribute("action",
                "/admin/feedback/edit/" + id);

        return "feedback-form";
    }

    @PostMapping("/admin/feedback/edit/{id}")
    public String updateFeedback(@PathVariable Long id,
                                 @Valid FeedbackDTO feedbackDTO,
                                 BindingResult bindingResult,
                                 Model model, RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {

            model.addAttribute("isEdit", true);
            model.addAttribute("action",
                    "/admin/feedback/edit/" + id);

            return "feedback-form";
        }

        feedbackService.updateFeedbackByAdmin(id,
                feedbackDTO);

        redirectAttributes.addFlashAttribute(
                "success",
                "Feedback updated successfully.");

        return "redirect:/admin/feedbacks";
    }

    @PostMapping("/admin/feedback/delete/{id}")
    public String deleteFeedback(@PathVariable Long id, RedirectAttributes redirectAttributes) {

        feedbackService.deleteFeedback(id);

        redirectAttributes.addFlashAttribute(
                "success",
                "Feedback deleted successfully.");

        return "redirect:/admin/feedbacks";
    }

}