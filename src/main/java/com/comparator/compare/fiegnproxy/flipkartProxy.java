package com.comparator.compare.fiegnproxy;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.comparator.compare.model.CProduct;

@FeignClient(name = "FlipkartApplication", url = "${flipkart.url}")
public interface flipkartProxy {
	
	@GetMapping("/getprodbyid/{id}")
	public CProduct getProductByIDInFlipkart(@PathVariable int id);

	@GetMapping("/getallprod")
	public  List<CProduct> getFlipkartProduct(); 
	

}
