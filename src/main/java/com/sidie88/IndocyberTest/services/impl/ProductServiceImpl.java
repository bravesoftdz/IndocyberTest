package com.sidie88.IndocyberTest.services.impl;


import com.sidie88.IndocyberTest.entity.Product;
import com.sidie88.IndocyberTest.services.ProductService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

@Service("productService")
@Scope(value = "singleton", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ProductServiceImpl implements ProductService {

	
	@Autowired
	ProductDao prodDao;
	
	public List<Product> getListProduct(){
		return prodDao.queryAll();
	}
	
	public List<Product> getListSearchProduct(String param){
		param = "%"+param+"%";
		return prodDao.queryByName(param);
	}


}
