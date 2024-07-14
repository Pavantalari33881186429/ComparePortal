package com.comparator.compare.serviceImpl;
import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.comparator.compare.dao.comparePortalDao;
import com.comparator.compare.model.CProduct;
	

	@Service
	public class ComparePortalServiceImpl  {

		

		@Autowired
		comparePortalDao dao;

		
		public String addProductFromManufacturer(List<CProduct> products) {
			
			
			String response = null;
			try {
				for(CProduct prod : products) {
					
					
				    dao.save(prod);
				    response="Added Products Successfully!!";
				}
				
			}
			catch(Exception e){
				
				response="Exception: "+e;
			}
			return response;
			
			
		}


		
		public List<CProduct> getProductID() {
			
			List<CProduct> listOfProductid = new ArrayList<CProduct>();
			String statusmsg = "";
			try {
				List<CProduct> CompareProductList = dao.findAll();
				
				for (CProduct amazonProduct : CompareProductList) {
					
					CProduct to = new CProduct();
					//to.setId(amazonProduct.getId());
					to.setProductID(amazonProduct.getProductID());
					//to.setWproductname(amazonProduct.getProductname());
					//to.setWproductprice(amazonProduct.getProductprice());
					
					listOfProductid.add(to);
				}

			} catch (Exception e) {
				statusmsg = "Exception occured during getProductID()";
				System.out.println(statusmsg);
				
			}
			
			return listOfProductid;
		}

	

	
}
