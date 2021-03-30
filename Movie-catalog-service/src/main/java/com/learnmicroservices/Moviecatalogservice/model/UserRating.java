package com.learnmicroservices.Moviecatalogservice.model;

import java.util.List;

public class UserRating {
	
	private String userId;
	private List<RatingmovieItem> userRating;
	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public List<RatingmovieItem> getUserRating() {
		return userRating;
	}

	public void setUserRating(List<RatingmovieItem> userRating) {
		this.userRating = userRating;
	}
	
	
}
