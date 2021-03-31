package com.learnmicroservices.Moviecatalogservice.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.learnmicroservices.Moviecatalogservice.model.MovieCatalogItem;
import com.learnmicroservices.Moviecatalogservice.model.MovieinfoItem;
import com.learnmicroservices.Moviecatalogservice.model.RatingmovieItem;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

@Service
public class MovieInfo {
	
	@Autowired
    private RestTemplate restTemplate;
	
	
	@HystrixCommand(fallbackMethod ="getFallbackCatalog",
			threadPoolKey="movieInfoPool", //separating threadpool for movie info circuit (bulkhead pattern)
			threadPoolProperties = {
					@HystrixProperty(name="coreSize",value="20"),
					@HystrixProperty(name="maxQueueSize",value="10")					
			})
	public MovieCatalogItem getCatalogItem(RatingmovieItem rating)
	{
		MovieinfoItem movie= restTemplate.getForObject("http://movie-info-service/movie/"+rating.getMovieId(),MovieinfoItem.class);
		return (new MovieCatalogItem(movie.getName(),movie.getDescription(),rating.getRating()));
	}
	
	public MovieCatalogItem getFallbackCatalog(RatingmovieItem rating) {
		
	  return (new MovieCatalogItem("Movie name not found","",rating.getRating())); //hardcoded Movie catlog item
	}
}
