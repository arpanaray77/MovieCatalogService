package com.learnmicroservices.Moviecatalogservice.Services;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;
import com.learnmicroservices.Moviecatalogservice.model.RatingmovieItem;
import com.learnmicroservices.Moviecatalogservice.model.UserRating;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

@Service
public class UserInfo {
	
	@Autowired
    private RestTemplate restTemplate;
	
	@HystrixCommand(fallbackMethod ="getFallbackUserRating",
			commandProperties = {                   
					@HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value="2000"),
					@HystrixProperty(name="circuitBreaker.requestVolumeThreshold",value="5"),
					@HystrixProperty(name="circuitBreaker.errorThresholdPercentage",value="50"),
					@HystrixProperty(name="circuitBreaker.sleepWindowInMilliseconds",value="5000")
			})
	public UserRating getUserRating(@PathVariable("userid") String userid)
	{
		return restTemplate.getForObject("http://rating-movie-service/rating/users/"+userid,UserRating.class); 
	}
	
	public UserRating getFallbackUserRating(@PathVariable("userid") String userid)
	{
		UserRating userRating = new UserRating();
		userRating.setUserId(userid);
		userRating.setUserRating(Arrays.asList(new RatingmovieItem("0",0))); //hardcoded rating object
		return userRating;
	}

}
