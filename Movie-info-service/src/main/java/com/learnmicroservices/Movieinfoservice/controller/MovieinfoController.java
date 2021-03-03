package com.learnmicroservices.Movieinfoservice.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learnmicroservices.Movieinfoservice.model.MovieinfoItem;

@RestController
@RequestMapping("/movie")
public class MovieinfoController {

	@RequestMapping("/{movieid}")
	public MovieinfoItem getMovie(@PathVariable("movieid") String movieid)
	{
		return new MovieinfoItem(movieid,"Avengers");
		
	}
}