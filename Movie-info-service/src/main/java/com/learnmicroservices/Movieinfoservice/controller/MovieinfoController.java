package com.learnmicroservices.Movieinfoservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.learnmicroservices.Movieinfoservice.model.MovieSummary;
import com.learnmicroservices.Movieinfoservice.model.MovieinfoItem;

@RestController
@RequestMapping("/movie")
public class MovieinfoController {
	
	@Value("${api.key}")
    public String apiKey;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@RequestMapping("/{movieId}")
	public MovieinfoItem getMovieInfo(@PathVariable("movieId") String movieId) {
        MovieSummary movieSummary = restTemplate.getForObject("https://api.themoviedb.org/3/movie/" + movieId + "?api_key=" +  apiKey, MovieSummary.class);
        return new MovieinfoItem(movieId, movieSummary.getTitle(), movieSummary.getOverview());

    }
}