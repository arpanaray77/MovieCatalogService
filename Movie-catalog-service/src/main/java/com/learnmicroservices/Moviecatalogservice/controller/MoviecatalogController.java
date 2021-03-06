package com.learnmicroservices.Moviecatalogservice.controller;


import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.learnmicroservices.Moviecatalogservice.Services.MovieInfo;
import com.learnmicroservices.Moviecatalogservice.Services.UserInfo;
import com.learnmicroservices.Moviecatalogservice.model.MovieCatalogItem;
import com.learnmicroservices.Moviecatalogservice.model.UserRating;


@RestController
@RequestMapping("/catalog")
public class MoviecatalogController {
	
	@Autowired
	MovieInfo movieInfo;
	
	@Autowired
	UserInfo userInfo;
	
	
	@RequestMapping("/{userid}") 
	public List<MovieCatalogItem> getCatalog(@PathVariable("userid") String userid)
	{
		//calling api or microservice using rest template
		
		UserRating userRating=userInfo.getUserRating(userid);
		return userRating.getUserRating().stream()
				.map(rating ->movieInfo.getCatalogItem(rating))
		        .collect(Collectors.toList());
	}
	
}

	
	@RefreshScope
	@RestController
	class MessageRestController {
	 
	    @Value("${msg:Config Server is not working. Please check...}")
	    private String msg;
	 
	    @GetMapping("/msg")
	    public String getMsg() {
	        return this.msg;
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
