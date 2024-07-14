package com.comparator.compare.fiegnproxy;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


import com.comparator.compare.model.CProduct;

@FeignClient(name = "AmazonApplication", url = "${amazon.url}")
public interface amazonProxy {
	
	@GetMapping("/getprodbyid/{id}")
	public CProduct getProductByIDInAmazon(@PathVariable int id);

	@GetMapping("/getallprod")
	public  List<CProduct> getAmazonProduct(); 

}
