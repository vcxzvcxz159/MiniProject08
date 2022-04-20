package com.model2.mvc.view.purchase;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.purchase.impl.PurchaseServiceImpl;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.impl.ProductServiceImpl;

public class UpdatePurchaseAction extends Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		System.out.println("UpdatePurchaseAction 시작");
		
		Purchase purchase = new Purchase();
		Product product = new Product();
		
		String tranNo = request.getParameter("tranNo");
		int prodNo = Integer.parseInt(request.getParameter("prodNo"));
		System.out.println("UpdatePurchaseAction prodNo : " + prodNo);
		
		PurchaseService service = new PurchaseServiceImpl();
		purchase = service.getPurchase(Integer.parseInt(tranNo));
		
		ProductService productService = new ProductServiceImpl();
		product = productService.getProduct(prodNo);
		
		System.out.println("UpdatePurchaseAction product : " + product);
		
		request.setAttribute("purchase", purchase);
		request.setAttribute("product", product);
		
		System.out.println("UpdatePurchaseAction 종료");
		return "forward:/purchase/updatePurchase.jsp";
	}

}
