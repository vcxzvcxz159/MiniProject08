package com.model2.mvc.view.basket;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.basket.BasketService;
import com.model2.mvc.service.basket.impl.BasketServiceImpl;
import com.model2.mvc.service.domain.Basket;
import com.model2.mvc.service.domain.User;

public class AddBasketAction extends Action {
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		System.out.println("AddBasketAction start");
		
		int prodNo = Integer.parseInt(request.getParameter("productNo"));
		
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		
		Basket basket = new Basket();
		basket.setProdNo(prodNo);
		basket.setUserId(user.getUserId());
		
		BasketService service = new BasketServiceImpl();
		service.addBasket(basket);
		
		System.out.println("AddBasketAction end");
		
		return "forward:/getProduct.do?prodNo="+prodNo+"&menu=search";
	}

}
