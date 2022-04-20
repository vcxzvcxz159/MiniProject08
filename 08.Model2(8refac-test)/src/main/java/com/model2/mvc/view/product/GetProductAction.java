package com.model2.mvc.view.product;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.impl.ProductServiceImpl;
import com.model2.mvc.service.domain.Product;

public class GetProductAction extends Action{
	
		@Override
		public String execute(	HttpServletRequest request,
													HttpServletResponse response) throws Exception {
			System.out.println("GetProductAction 실행");
			
			String menu = request.getParameter("menu");
			
			if(menu == null || menu.equals("")) {
				menu = "afterManage";
			}
			if(menu.equals("manage")) {
				System.out.println("menu : " + menu);
				return "forward:/updateProductView.do";
			}
			
			String productNo = request.getParameter("prodNo");
			System.out.println("productNo : " + productNo);
			
			ProductService service = new ProductServiceImpl();
			Product product = service.getProduct(Integer.parseInt(productNo));
			
			Cookie[] cookies = request.getCookies();
			String cookieString = null;
			boolean flag = false;
			
			if (cookies!=null && cookies.length > 0) {
				for (int i = 0; i < cookies.length; i++) {
					Cookie cookie = cookies[i];
					if (cookie.getName().equals("history")) {
						System.out.println("cookie value : " + cookie.getValue());
						cookieString = cookie.getValue();
						cookieString = cookieString+"/"+productNo;
						cookie = new Cookie("history", cookieString);
						response.addCookie(cookie);
						flag = true;
					}
					if(i==cookies.length-1 && flag == false) {
						Cookie newCookie = new Cookie("history", productNo);
						response.addCookie(newCookie);
					}
				}
			}
			
			System.out.println("product : "+product);
			
			request.setAttribute("menu", menu);
			request.setAttribute("product", product);
				
			System.out.println("GetProductAction 종료");
			return "forward:/product/getProduct.jsp?menu="+menu;
			
		}
}
