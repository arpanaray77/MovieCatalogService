package com.learnmicroservices.RatingMovieservice.model;

import java.util.List;

public class UserRating {
	
	private List<RatingmovieItem> userRating;

	public List<RatingmovieItem> getUserRating() {
		return userRating;
	}

	public void setUserRating(List<RatingmovieItem> userRating) {
		this.userRating = userRating;
	}
	
}
