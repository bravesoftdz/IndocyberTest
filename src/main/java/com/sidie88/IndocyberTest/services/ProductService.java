package com.sidie88.IndocyberTest.services;


import com.sidie88.IndocyberTest.entity.Product;

import java.util.List;

public interface ProductService {


	List<Product> getListProduct();
	
	List<Product> getListSearchProduct(String param);


}
