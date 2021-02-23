package com.learnmicroservices.RatingMovieservice.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learnmicroservices.RatingMovieservice.model.RatingmovieItem;

@RestController
@RequestMapping("/rating")
public class RatingmovieController {

	@RequestMapping("/{movieid}")
	public RatingmovieItem getMovie(@PathVariable("movieid") String movieid)
	{
		return new  RatingmovieItem(movieid,4);
		
	}
}
