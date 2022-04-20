package com.model2.mvc.service.purchase.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.*;
import com.model2.mvc.service.purchase.PurchaseDao;

@Repository("purchaseDaoImpl")
public class PurchaseDaoImpl implements PurchaseDao{
	
	/// Field
	@Autowired
	@Qualifier("sqlSessionTemplate")
	private SqlSession sqlSession;
	
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	public int insertPurchase(Purchase purchase) throws Exception {
		return sqlSession.insert("PurchaseMapper.addPurchase", purchase);
	}
	
	public Purchase findPurchase(int tranNo) throws Exception {
		return sqlSession.selectOne("PurchaseMapper.getPurchase", tranNo);
	}
	
	public int updatePurchase(Purchase purchase) throws Exception {
		return sqlSession.update("PurchaseMapper.updatePurchase", purchase);
	}
	
	public Map<String, Object> getPurchaseList(Search search, String buyerId) throws Exception{
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("search", search);
		map.put("buyerId", buyerId);
		int totalCount = sqlSession.selectOne("PurchaseMapper.PurchaseListGetTotalCount", map);
		
		List<Purchase> list = sqlSession.selectList("PurchaseMapper.PurchaseListMakeCurrentPageSql", map);
		
		map.put("totalCount", totalCount);
		map.put("list", list);
		
		return map;
	}
	
	public Map<String, Object> getSaleList(Search search) throws Exception{
		Map<String, Object> map = new HashMap<String, Object>();
		
		int totalCount = sqlSession.selectOne("PurchaseMapper.SaleListGetTotalCount");
		
		List<Purchase> list = sqlSession.selectList("PurchaseMapper.SaleListMakeCurrentPageSql", search);
		
		map.put("totalCount",totalCount);
		map.put("list", list);
		
		return map;
	}
	
	public void updateTranCode(Purchase purchase) throws Exception {
		sqlSession.update("PurchaseMapper.updateTranCode", purchase);
	}
	
	public int deletePurchase(int tranNo) throws Exception{
		return sqlSession.delete("PurchaseMapper.deletePurchase", tranNo);
	}
}
