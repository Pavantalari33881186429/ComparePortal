package com.comparator.compare.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.comparator.compare.fiegnproxy.CProductManufacturerProxy;
import com.comparator.compare.fiegnproxy.amazonProxy;
import com.comparator.compare.fiegnproxy.flipkartProxy;


import com.comparator.compare.model.CProduct;
import com.comparator.compare.serviceImpl.ComparePortalServiceImpl;


@RestController
public class comparePortalController {
	
	@Autowired
	CProductManufacturerProxy productManufacturerProxy;

	@Autowired
	amazonProxy amazonProxy;

	@Autowired
	flipkartProxy flipkartProxy;

	@Autowired
	ComparePortalServiceImpl service;
	
	
	
	@GetMapping("/addAllProductFromManufacturer")
	public String addAllProductFromManufacturer() {
		String statusMsg = "";
		
		List<CProduct> response = productManufacturerProxy.getProductDetailsFromProductManufacturer();
		
		
		try {
			
			statusMsg=service.addProductFromManufacturer(response);
			

		} catch (Exception e) {
			statusMsg = "Exception Occured during data insertion" + e;
			
		}

		
		return statusMsg;
	}
	
	
	/*
	 * Get the Best price comparing FLIPKART and AMAZON 
	 */
	@GetMapping("/getBestPrice/{id}")
	public  String  getManufacturerProductID(@PathVariable("id") long prodID) {

		
		List<CProduct> listOfAmazonProduct = new ArrayList<CProduct>();
		List<CProduct> listOfFlipkartProduct = new ArrayList<CProduct>();

		Map<Long, Long> amazonProductidPrice = new HashMap<Long, Long>();
		Map<Long, Long> flipkartProductidPrice = new HashMap<Long, Long>();

		
		

		String statusMsg = "";
		long amazonProductPrice = 0L;
		long flipkartProductPrice = 0L;

		try {

			// List of Product ID from Distributer
			List<CProduct> productidlist = service.getProductID();

			/* Flipkart Product Details */
			List<CProduct> fproduct = flipkartProxy.getFlipkartProduct();
			
			
			/* Amazon Product Details */
			List<CProduct> aproduct= amazonProxy.getAmazonProduct();
			
			
			for (CProduct amazonProduct : aproduct) {
				amazonProductidPrice.put(amazonProduct.getProductID(), amazonProduct.getProductPrice());
			}

			for (CProduct flipkartProduct : fproduct) {
				flipkartProductidPrice.put(flipkartProduct.getProductID(), flipkartProduct.getProductPrice());
			}

			for (CProduct productid : productidlist) {

				
				
				if (productid.getProductID() == prodID) {

					if (amazonProductidPrice.containsKey(prodID) && flipkartProductidPrice.containsKey(prodID)) {

						amazonProductPrice = amazonProductidPrice.get(prodID);
						flipkartProductPrice = flipkartProductidPrice.get(prodID);

						if (amazonProductPrice > flipkartProductPrice) {
							
							// recommend Flipkart Price
							
							
							statusMsg = "Congratulationas !!!  FLIPKART offering you best price ";
							
							
						} else if(amazonProductPrice == flipkartProductPrice){
							
							
							statusMsg="Congratulationas !!!  FLIPKART & AMAZON both offering you best price ";
							
						}else if(amazonProductPrice < flipkartProductPrice){
							// recommend Amazon Price
							
							statusMsg="Congratulationas !!!  AMAZON offering you best price ";
							
						}

					} else {
						System.out.println("Product ID " + productid + " not Exist in to Amazon or Flipkart");
						String str = "Product ID" + productid + " not Exist in to Amazon or Flipkart";
						
					}

				} 
			}

			System.out.println("amazonProductidProce MAP-***" + amazonProductidPrice.get(prodID));
			System.out.println("flipkartProductidProce MAP-***" + flipkartProductidPrice.get(prodID));

		} catch (Exception e) {
			statusMsg = "Exception Occured during Gross Margin CALCULATION" + e;
			
		}

		// 1. Get the list of PRODUCT ID from "compareportal portal"
		// 2. Get the price from FLIPKART against the PRODUCT ID from "compareportal
		// portal"
		// 3. Get the price from AMAZON against the PRODUCT ID from "compareportal
		// portal"
		// 4. Logic to compare the price

		return statusMsg;
	}


}
