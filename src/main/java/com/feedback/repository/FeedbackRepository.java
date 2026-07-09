package com.feedback.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.feedback.entity.Feedback;
import com.feedback.entity.User;

@Repository
public interface FeedbackRepository extends JpaRepository<Feedback, Long>{
	
	Optional<Feedback>  findByUser(User user);
	
}
