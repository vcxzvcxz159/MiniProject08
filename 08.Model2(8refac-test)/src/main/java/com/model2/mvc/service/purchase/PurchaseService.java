package com.model2.mvc.service.purchase;

import java.util.Map;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Purchase;

public interface PurchaseService {
	
	public int addPurchase(Purchase purchase)throws Exception;
	
	public Purchase getPurchase(int tranNo) throws Exception;
	
	public Map<String, Object> getPurchaseList(Search search, String buyerId)throws Exception;
	
	public Map<String, Object> getSaleList(Search search)throws Exception;
	
	public Purchase updatePurchase(Purchase purchase)throws Exception;
	
	public void UpdateTranCode(Purchase purchase)throws Exception;
	
	public int deletePurchase(int tranNo) throws Exception;
}
