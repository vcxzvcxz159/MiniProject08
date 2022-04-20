package com.model2.mvc.service.basket.impl;

import java.util.Map;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.basket.BasketService;
import com.model2.mvc.service.basket.dao.BasketDAO;
import com.model2.mvc.service.domain.Basket;

public class BasketServiceImpl implements BasketService{

	/// Field
	private BasketDAO basketDAO;
	
	/// Constructor
	public BasketServiceImpl() {
		basketDAO = new BasketDAO();
	}
	
	/// Method
	@Override
	public void addBasket(Basket basket) throws Exception {
		basketDAO.insertBasket(basket);
	}

	@Override
	public Map<String, Object> getBasketList(Search search, String userId) throws Exception {
		return basketDAO.getBasketList(search, userId);
	}

	@Override
	public void deleteBasket(Basket basket) throws Exception {
		
	}
	
	

}
