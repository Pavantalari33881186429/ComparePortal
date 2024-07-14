package com.comparator.compare.fiegnproxy;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;

import org.springframework.web.bind.annotation.GetMapping;
import com.comparator.compare.model.CProduct;

@FeignClient(name="productmanufacturer",url="${productmanufacturer.url}")

public interface CProductManufacturerProxy {

	@GetMapping("/getallprod")
	public List<CProduct>	getProductDetailsFromProductManufacturer();
	
}
