package com.model2.mvc.view.purchase;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.purchase.impl.PurchaseServiceImpl;
import com.model2.mvc.service.domain.Purchase;

public class UpdatePurchaseViewAction extends Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("UpdatePurchaseViewAction 시작");
		
		Purchase purchase = new Purchase();
		
		PurchaseService service = new PurchaseServiceImpl();
		
		int tranNo = Integer.parseInt((String)request.getParameter("tranNo"));
		int quantityDif = 0;
		
		purchase = service.getPurchase(tranNo);
		
		String paymentOption = request.getParameter("paymentOption");
		String receiverName = request.getParameter("receiverName");
		String receiverPhone = request.getParameter("receiverPhone");
		String receiverAddr = request.getParameter("receiverAddr");
		String receiverRequest = request.getParameter("receiverRequest");
		String receiverDate = request.getParameter("receiverDate");
		int quantity = Integer.parseInt(request.getParameter("quantity"));
		
		purchase.setTranNo(tranNo);
		purchase.setPaymentOption(paymentOption);
		purchase.setReceiverName(receiverName);
		purchase.setReceiverPhone(receiverPhone);
		purchase.setDivyAddr(receiverAddr);
		purchase.setDivyRequest(receiverRequest);
		purchase.setDivyDate(receiverDate);
		purchase.setQuantity(quantity);
		
		service.updatePurchase(purchase);
		
		System.out.println("purchase : " + purchase);
		request.setAttribute("purchase", purchase);
		
		System.out.println("UpdatePurchaseViewAction 종료");
		return "forward:/purchase/updatePurchaseView.jsp";
	}

}
