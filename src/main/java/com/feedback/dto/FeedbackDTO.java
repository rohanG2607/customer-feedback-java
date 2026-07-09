package com.feedback.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;


public class FeedbackDTO {

	@NotBlank(message = "Title is required.")
	@Size(max = 100, message = "Title cannot exceed 100 charachters")
	private String title;
	
	@NotBlank(message = "Description is required")
	@Size(max = 1000, message = "Description cannot exceed 1000 charachters")
	private String description;
	
	public FeedbackDTO() {
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	
	
}
