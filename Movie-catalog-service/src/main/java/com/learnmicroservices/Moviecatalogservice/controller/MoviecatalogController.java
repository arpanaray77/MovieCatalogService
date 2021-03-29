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
import com.learnmicroservices.Moviecatalogservice.model.UserRating;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
@RequestMapping("/catalog")
public class MoviecatalogController {
	
	
	@Autowired
    private RestTemplate restTemplate;
	
	@RequestMapping("/{userid}") 
	@HystrixCommand(fallbackMethod ="getFallbackCatalog")
	public List<MovieCatalogItem> getCatalog(@PathVariable("userid") String userid)
	{
		//calling api or microservice using rest template
		UserRating ratings=restTemplate.getForObject("http://rating-movie-service/rating/users/"+userid,UserRating.class); 
		//replacing hard coded url to registered name of microservice on eureka server
		
		return ratings.getUserRating().stream().map(rating -> {
	        //calling microservice using rest template
           MovieinfoItem movie= restTemplate.getForObject("http://movie-info-service/movie/"+rating.getMovieId(),MovieinfoItem.class);
	
        
			return (new MovieCatalogItem(movie.getName(),movie.getDescription(),rating.getRating()));
		})
			.collect(Collectors.toList());
	}
	
	public List<MovieCatalogItem> getFallbackCatalog(@PathVariable("userid") String userid)
	{
		return Arrays.asList(new MovieCatalogItem("No movie","",0));
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
