package com.learnmicroservices.Moviecatalogservice.controller;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import com.learnmicroservices.Moviecatalogservice.model.MovieCatalogItem;
import com.learnmicroservices.Moviecatalogservice.model.MovieinfoItem;
import com.learnmicroservices.Moviecatalogservice.model.UserRating;

@RestController
@RequestMapping("/catalog")
public class MoviecatalogController {
	
	
	@Autowired
    private RestTemplate restTemplate;
	
//	@Autowired
//	private WebClient.Builder webClientBuilder;
//	
	@RequestMapping("/{userid}") 
	public List<MovieCatalogItem> getCatalog(@PathVariable("userid") String userid)
	{
		//calling api or microservice using rest template
		UserRating ratings=restTemplate.getForObject("http://localhost:8082/rating/users/"+userid,UserRating.class);
		
		return ratings.getUserRating().stream().map(rating -> {
	        //calling microservice using rest template
           MovieinfoItem movie= restTemplate.getForObject("http://localhost:8081/movie/"+rating.getMovieId(),MovieinfoItem.class);
	
//			MovieinfoItem movie=webClientBuilder.build()
//			                        .get()
//			                        .uri("http://localhost:8081/movie/"+rating.getMovieId())
//			                        .retrieve()
//			                        .bodyToMono(MovieinfoItem.class)
//			                        .block();
			        
			return (new MovieCatalogItem(movie.getName(),"Desc",rating.getRating()));
		})
			.collect(Collectors.toList());
	}

}

