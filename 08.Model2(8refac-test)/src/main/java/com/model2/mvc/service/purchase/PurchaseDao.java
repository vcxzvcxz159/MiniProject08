package com.model2.mvc.service.purchase;

import java.util.Map;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Purchase;

public interface PurchaseDao {
	public int insertPurchase(Purchase purchase) throws Exception;
	
	public Purchase findPurchase(int tranNo) throws Exception;
	
	public int updatePurchase(Purchase purchase) throws Exception;
	
	public Map<String, Object> getPurchaseList(Search search, String buyerId) throws Exception;
	
	public Map<String, Object> getSaleList(Search search) throws Exception;
	
	public void updateTranCode(Purchase purchase) throws Exception;

	public int deletePurchase(int tranNo) throws Exception;
}
