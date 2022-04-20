package com.model2.mvc.view.purchase;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.domain.*;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.purchase.impl.PurchaseServiceImpl;


public class AddPurchaseViewAction extends Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		System.out.println("AddPurchaseViewAction 시작");
		
		Purchase purchase = new Purchase();
		Product product = new Product();
		User user = new User();
		
		String prodNo = (String)request.getParameter("prodNo");
		System.out.println("prodNo : " + prodNo);
		String buyerId = request.getParameter("buyerId");
		
		String paymentOption = request.getParameter("paymentOption");
		String receiverName = request.getParameter("receiverName");
		String receiverPhone = request.getParameter("receiverPhone");
		String receiverAddr = request.getParameter("receiverAddr");
		String receiverRequest = request.getParameter("receiverRequest");
		String receiverDate = request.getParameter("receiverDate");
		int quantity = Integer.parseInt(request.getParameter("quantity")); 
		
		System.out.println("receiverName : " + receiverName);
		System.out.println();
		
		product.setProdNo(Integer.parseInt(prodNo));
		user.setUserId(buyerId);
		
		purchase.setPurchaseProd(product);
		purchase.setPaymentOption(paymentOption);
		purchase.setBuyer(user);
		purchase.setReceiverName(receiverName);
		purchase.setReceiverPhone(receiverPhone);
		purchase.setDivyAddr(receiverAddr);
		purchase.setDivyRequest(receiverRequest);
		purchase.setDivyDate(receiverDate);
		purchase.setQuantity(quantity);
		
		PurchaseService service = new PurchaseServiceImpl();
		service.addPurchase(purchase);
		
		System.out.println("purchase : " + purchase);
		request.setAttribute("purchase", purchase);
		
		System.out.println("AddPurchaseViewAction 종료");
		return "forward:/purchase/addPurchaseView.jsp";
	}

}
