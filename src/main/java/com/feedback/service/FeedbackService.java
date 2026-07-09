package com.feedback.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.feedback.dto.FeedbackDTO;
import com.feedback.entity.Feedback;
import com.feedback.entity.User;
import com.feedback.repository.FeedbackRepository;

@Service
public class FeedbackService {

	private final FeedbackRepository feedbackRepository;

	public FeedbackService(FeedbackRepository feedbackRepository) {
		this.feedbackRepository = feedbackRepository;
	}

	public Optional<Feedback> getFeedbackByUser(User user) {
		return feedbackRepository.findByUser(user);
	}

	public Feedback saveFeedback(FeedbackDTO feedbackDTO, User user) {
		Feedback feedback = new Feedback();

		feedback.setTitle(feedbackDTO.getTitle());
		feedback.setDescription(feedbackDTO.getDescription());

		feedback.setCreatedAt(LocalDateTime.now());
		feedback.setUpdatedAt(LocalDateTime.now());
		feedback.setUser(user);

		return feedbackRepository.save(feedback);
	}

	public Feedback getFeedbackByUserOrThrow(User user) {

		return feedbackRepository.findByUser(user).orElseThrow(() -> new RuntimeException("Feedback not found"));

	}

	public void updateFeedback(FeedbackDTO feedbackDTO, User user) {
		Feedback feedback = getFeedbackByUserOrThrow(user);

		feedback.setTitle(feedbackDTO.getTitle());
		feedback.setDescription(feedbackDTO.getDescription());

		feedback.setUpdatedAt(LocalDateTime.now());

		feedbackRepository.save(feedback);
	}
	
	public List<Feedback> getAllFeedback(){
		return feedbackRepository.findAll();
	}
	
	public Optional<Feedback> getFeedbackById(Long id) {
	    return feedbackRepository.findById(id);
	}

	public void updateFeedbackByAdmin(Long id, FeedbackDTO feedbackDTO) {

	    Feedback feedback = feedbackRepository.findById(id)
	            .orElseThrow(() -> new RuntimeException("Feedback not found."));

	    feedback.setTitle(feedbackDTO.getTitle());
	    feedback.setDescription(feedbackDTO.getDescription());
	    feedback.setUpdatedAt(LocalDateTime.now());

	    feedbackRepository.save(feedback);
	}

	public void deleteFeedback(Long id) {
	    feedbackRepository.deleteById(id);
	}

	
	public long countFeedback() {
	    return feedbackRepository.count();
	}
}
