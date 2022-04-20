package com.model2.mvc.view.purchase;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.purchase.impl.PurchaseServiceImpl;


public class UpdateTranCodeAction extends Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		int prodNo = Integer.parseInt(request.getParameter("prodNo"));
		String tranCode = request.getParameter("tranCode");
		String menu = request.getParameter("menu");
		
		System.out.println("UpdateTranCodeAction prodNo : "+prodNo);
		System.out.println("UpdateTranCodeAction tranCode : "+tranCode);
		
		Product product = new Product();
		
		Purchase purchase = new Purchase();
		
		product.setProdNo(prodNo);
		
		purchase.setPurchaseProd(product);
		purchase.setTranCode(tranCode);
		
		PurchaseService service = new PurchaseServiceImpl();
		purchase.setTranCode(tranCode);
		
		service.UpdateTranCode(purchase);
		request.setAttribute("menu", menu);
		
		return "forward:/listProduct.do";
	}

}
