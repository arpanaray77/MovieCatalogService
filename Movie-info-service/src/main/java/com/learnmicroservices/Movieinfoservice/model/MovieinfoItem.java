package com.learnmicroservices.Movieinfoservice.model;

public class MovieinfoItem {
	private String movieID;
	private String name;
	
	public MovieinfoItem() {
		
	}
	
	public MovieinfoItem(String movieID, String name) {
		super();
		this.movieID = movieID;
		this.name = name;
	}
	public String getMovieID() {
		return movieID;
	}
	public void setMovieID(String movieID) {
		this.movieID = movieID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	

}
