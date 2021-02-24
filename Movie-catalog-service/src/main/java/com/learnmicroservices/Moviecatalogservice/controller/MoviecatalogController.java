package com.learnmicroservices.Moviecatalogservice.controller;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.learnmicroservices.Moviecatalogservice.model.MovieCatalogItem;
import com.learnmicroservices.Moviecatalogservice.model.MovieinfoItem;
import com.learnmicroservices.Moviecatalogservice.model.RatingmovieItem;

@RestController
@RequestMapping("/catalog")
public class MoviecatalogController {
	
	@RequestMapping("/{userid}")
	public List<MovieCatalogItem> getCatalog(@PathVariable("userid") String userid)
	{
		//calling microservice using rest template
		RestTemplate resttemplate =new RestTemplate();
		
		
		List<RatingmovieItem> ratings = Arrays.asList(
			new RatingmovieItem("1234",4),
			new RatingmovieItem("5678",3)
		);
		
		return ratings.stream().map(rating -> {
		            MovieinfoItem movie= resttemplate.getForObject("http://localhost:8081/movie/"+rating.getMovieId(),MovieinfoItem.class);
					return (new MovieCatalogItem(movie.getName(),"Desc",rating.getRating()));
		})
			.collect(Collectors.toList());
	}

}

