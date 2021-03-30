package com.learnmicroservices.Moviecatalogservice.controller;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import com.learnmicroservices.Moviecatalogservice.model.MovieCatalogItem;
import com.learnmicroservices.Moviecatalogservice.model.MovieinfoItem;
import com.learnmicroservices.Moviecatalogservice.model.RatingmovieItem;
import com.learnmicroservices.Moviecatalogservice.model.UserRating;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
@RequestMapping("/catalog")
public class MoviecatalogController {
	
	
	@Autowired
    private RestTemplate restTemplate;
	
	@RequestMapping("/{userid}") 
	public List<MovieCatalogItem> getCatalog(@PathVariable("userid") String userid)
	{
		//calling api or microservice using rest template
		UserRating userRating=getUserRating(userid);
		return userRating.getUserRating().stream()
				.map(rating ->getCatalogItem(rating))
		        .collect(Collectors.toList());
	}
	
	@HystrixCommand(fallbackMethod ="getFallbackCatalog")
	private MovieCatalogItem getCatalogItem(RatingmovieItem rating)
	{
		MovieinfoItem movie= restTemplate.getForObject("http://movie-info-service/movie/"+rating.getMovieId(),MovieinfoItem.class);
		return (new MovieCatalogItem(movie.getName(),movie.getDescription(),rating.getRating()));
	}
	
	@HystrixCommand(fallbackMethod ="getFallbackUserRating")
	private UserRating getUserRating(@PathVariable("userid") String userid)
	{
		return restTemplate.getForObject("http://rating-movie-service/rating/users/"+userid,UserRating.class); 
	}

	
	public MovieCatalogItem getFallbackCatalog(RatingmovieItem rating) {
		
	  return (new MovieCatalogItem("Movie name not found","",rating.getRating())); //hardcoded Movie catlog item
	}
	
	public UserRating getFallbackUserRating(@PathVariable("userid") String userid)
	{
		UserRating userRating = new UserRating();
		userRating.setUserId(userid);
		userRating.setUserRating(Arrays.asList(new RatingmovieItem("0",0))); //hardcoded rating object
		return userRating;
	}

}
/*Alternative way
 * @Autowired
	private WebClient.Builder webClientBuilder;
   MovieinfoItem movie=webClientBuilder.build()
                       .get()
                       .uri("http://localhost:8081/movie/"+rating.getMovieId())
                       .retrieve()
                       .bodyToMono(MovieinfoItem.class)
                       .block(); */
