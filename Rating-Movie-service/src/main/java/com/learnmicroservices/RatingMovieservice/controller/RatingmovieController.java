package com.learnmicroservices.RatingMovieservice.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learnmicroservices.RatingMovieservice.model.RatingmovieItem;
import com.learnmicroservices.RatingMovieservice.model.UserRating;

@RestController
@RequestMapping("/rating")
@EnableEurekaClient
public class RatingmovieController {

	@RequestMapping("/{movieid}")
	public RatingmovieItem getMovie(@PathVariable("movieid") String movieid)
	{
		return new  RatingmovieItem(movieid,4);
		
	}
	
	//to return username along with rating list
	@RequestMapping("users/{userid}")
	public UserRating getUserRating(@PathVariable("userid") String userid)
	
	{
	List<RatingmovieItem> ratings = Arrays.asList(
			new RatingmovieItem("1234",4),
			new RatingmovieItem("5678",5)
		);
		
	UserRating userRating=new UserRating();
	userRating.setUserRating(ratings);
	return userRating;
	}
}
