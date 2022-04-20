package com.model2.mvc.view.purchase;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.purchase.impl.PurchaseServiceImpl;


public class GetPurchaseAction extends Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		System.out.println("GetPurchaseAction 실행");
		
		String prodNo = request.getParameter("prodNo");
		System.out.println("prodNO : " + prodNo);
		
		PurchaseService service = new PurchaseServiceImpl();
		Purchase purchase = service.getPurchase(Integer.parseInt(prodNo));
		
		request.setAttribute("purchase", purchase);
		
		System.out.println("GetPurchaseAction 종료");
		
		return "forword:/purchase/transCodeProduct.jsp";
	}

}
