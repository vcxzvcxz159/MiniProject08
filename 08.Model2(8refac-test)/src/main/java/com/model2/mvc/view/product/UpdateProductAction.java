package com.model2.mvc.view.product;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.impl.ProductServiceImpl;
import com.model2.mvc.service.domain.Product;

public class UpdateProductAction extends Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception{
		int prodNo = Integer.parseInt(request.getParameter("prodNo"));
		
		System.out.println("updateAction prodNo : " + prodNo);
		Product product = new Product();
		product.setProdNo(prodNo);
		product.setProdName(request.getParameter("prodName"));
		product.setProdDetail(request.getParameter("prodDetail"));
		product.setPrice(Integer.parseInt(request.getParameter("price")));
		product.setManuDate(request.getParameter("manuDate"));
		product.setFileName(request.getParameter("fileName"));
		product.setQuantity(Integer.parseInt(request.getParameter("quantity")));
		
		ProductService service = new ProductServiceImpl();
		service.updateProduct(product);
		
		//request.setAttribute("product", productVO);
		
		return "redirect:/getProduct.do?prodNo="+prodNo;
		
	}
}
