package com.learnmicroservices.Moviecatalogservice.controller;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learnmicroservices.Moviecatalogservice.model.MovieCatalogItem;
import com.learnmicroservices.Moviecatalogservice.model.RatingmovieItem;

@RestController
@RequestMapping("/catalog")
public class MoviecatalogController {
	
	@RequestMapping("/{userid}")
	public List<MovieCatalogItem> getCatalog(@PathVariable("userid") String userid)
	{
		List<RatingmovieItem> ratings = Arrays.asList(
			new RatingmovieItem("TRF",4),
			new RatingmovieItem("XMEN",3)
		);
		
		return ratings.stream().map(rating-> new MovieCatalogItem("XMENN","SCI-fi",3))
		            .collect(Collectors.toList());
	}

}

