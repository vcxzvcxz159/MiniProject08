package com.model2.mvc.view.purchase;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.domain.*;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.impl.ProductServiceImpl;

public class AddPurchaseAction extends Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		System.out.println("AddPurchaseAction 시작");
		
		Purchase purchase = new Purchase();
		Product product = new Product();
		
		// session true
		HttpSession session = request.getSession(true);
		
		// readProduct.jsp로 부터 Paramater 받기
		int prodNo = Integer.parseInt(request.getParameter("productNo"));
		product.setProdNo(prodNo);
		ProductService service = new ProductServiceImpl();
		product = service.getProduct(prodNo);
		
		purchase.setBuyer((User)session.getAttribute("user"));
		purchase.setPurchaseProd(product);
		
		request.setAttribute("purchase", purchase);
		request.setAttribute("product", product);
		
		System.out.println("AddPurchaseAction 종료");
		return "forward:/purchase/addPurchase.jsp";
	}
	
}
