package com.model2.mvc.service.product.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.product.ProductDao;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.domain.Product;

@Service("productServiceImpl")
public class ProductServiceImpl implements ProductService{

	/// Field
	@Autowired
	@Qualifier("productDaoImpl")
	private ProductDao productDao;
	
	public void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
	}
	
	/// Constructor
	public ProductServiceImpl() {
		System.out.println("::" + getClass() + " default Constructor..");
	}

	
	/// Method
	
	public int addProduct(Product product) throws Exception {
		return productDao.insertProduct(product);
	}

	public Product getProduct(int prodNo) throws Exception {
		return productDao.findProduct(prodNo);
	}
	
	public int updateProduct(Product product) throws Exception {
		return productDao.updateProduct(product);
	}

	public Map<String, Object> getProductList(Search search) throws Exception{
		return productDao.getProductList(search);
	}

}
