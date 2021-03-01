package com.learnmicroservices.Moviecatalogservice.controller;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import com.learnmicroservices.Moviecatalogservice.model.MovieCatalogItem;
import com.learnmicroservices.Moviecatalogservice.model.MovieinfoItem;
import com.learnmicroservices.Moviecatalogservice.model.RatingmovieItem;

@RestController
@RequestMapping("/catalog")
public class MoviecatalogController {
	
	
	@Autowired
    private RestTemplate restTemplate;
	
	@Autowired
	private WebClient.Builder webClientBuilder;
	
	@RequestMapping("/{userid}") 
	public List<MovieCatalogItem> getCatalog(@PathVariable("userid") String userid)
	{
		
		List<RatingmovieItem> ratings = Arrays.asList(
			new RatingmovieItem("1234",4),
			new RatingmovieItem("5678",5)
		);
		
		return ratings.stream().map(rating -> {
			        //calling microservice using rest template
		           // MovieinfoItem movie= restTemplate.getForObject("http://localhost:8081/movie/"+rating.getMovieId(),MovieinfoItem.class);
			
			MovieinfoItem movie=webClientBuilder.build()
			                        .get()
			                        .uri("http://localhost:8081/movie/"+rating.getMovieId())
			                        .retrieve()
			                        .bodyToMono(MovieinfoItem.class)
			                        .block();
			        
					return (new MovieCatalogItem(movie.getName(),"Desc",rating.getRating()));
		})
			.collect(Collectors.toList());
	}

}

