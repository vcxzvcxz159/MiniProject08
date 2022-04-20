package com.model2.mvc.view.product;


import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.common.Page;
import com.model2.mvc.common.Search;
import com.model2.mvc.framework.Action;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.impl.ProductServiceImpl;

public class ListProductAction extends Action{
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		Search search = new Search();
		
		String menu = (String)request.getAttribute("menu");
		if(menu==null || menu.equals("")) {
			menu = request.getParameter("menu");
		}
		System.out.println("Menu : " + menu);
		
		int currentPage = 1;
		
		if(request.getParameter("currentPage") != null) {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}
		System.out.println("CurrentPage : " + currentPage);
		
		if(request.getParameter("priceCondition") != null) {
			search.setPriceCondition(Integer.parseInt(request.getParameter("priceCondition")));
		}
		
		search.setCurrentPage(currentPage);
		search.setSearchCondition(request.getParameter("searchCondition"));
		search.setSearchKeyword(request.getParameter("searchKeyword"));
		System.out.println("searchCondition : " + search.getSearchCondition());
		System.out.println("searchKeyWord : " + search.getSearchKeyword());
		
		search.setMinCharge(request.getParameter("minCharge"));
		search.setMaxCharge(request.getParameter("maxCharge"));
		System.out.println("minCharge : " + search.getMinCharge());
		System.out.println("maxCharge : " + search.getMaxCharge());
		
		// web.xml meta-data로 부터 상수 추출
		int pageSize = Integer.parseInt( getServletContext().getInitParameter("pageSize"));
		int pageUnit  =  Integer.parseInt(getServletContext().getInitParameter("pageUnit"));
		search.setPageSize(pageSize);
		
		// Business logic 수행
		ProductService service = new ProductServiceImpl();
		Map<String, Object> map = service.getProductList(search);
		
		Page resultPage	= 
				new Page( currentPage, ((Integer)map.get("totalCount")).intValue(), pageUnit, pageSize);
		System.out.println("ListProductAction ::"+resultPage);
		
		// Model과 View 연결
		request.setAttribute("list", map.get("list"));
		request.setAttribute("resultPage", resultPage);
		request.setAttribute("search", search);
		request.setAttribute("menu", menu);
		
		return "forward:/product/listProduct.jsp";
	}
}
