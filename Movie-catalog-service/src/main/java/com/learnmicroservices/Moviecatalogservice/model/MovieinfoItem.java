package com.learnmicroservices.Moviecatalogservice.model;

public class MovieinfoItem {
	 private String movieId;
	    private String name;
	    private String description;

	    public MovieinfoItem() {
	    }

		public MovieinfoItem(String movieId, String name, String description) {
	        this.movieId = movieId;
	        this.name = name;
	        this.description = description;
	    }

	    public String getMovieId() {
	        return movieId;
	    }

	    public void setMovieId(String movieId) {
	        this.movieId = movieId;
	    }

	    public String getName() {
	        return name;
	    }

	    public void setName(String name) {
	        this.name = name;
	    }

	    public String getDescription() {
	        return description;
	    }

	    public void setDescription(String description) {
	        this.description = description;
	    }
}
