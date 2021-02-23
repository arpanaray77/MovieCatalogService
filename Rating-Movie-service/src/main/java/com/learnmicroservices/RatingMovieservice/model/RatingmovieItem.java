package com.learnmicroservices.RatingMovieservice.model;

public class RatingmovieItem {

	private String movieId;
	private int rating;
	
	public RatingmovieItem(String movieId, int rating) {
		super();
		this.movieId = movieId;
		this.rating = rating;
	}
	
	
	public String getMovieId() {
		return movieId;
	}
	public void setMovieId(String movieId) {
		this.movieId = movieId;
	}
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
	
	
	
}
