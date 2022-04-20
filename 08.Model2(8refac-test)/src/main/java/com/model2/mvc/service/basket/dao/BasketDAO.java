package com.model2.mvc.service.basket.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.model2.mvc.common.Search;
import com.model2.mvc.common.util.DBUtil;
import com.model2.mvc.service.domain.Basket;

public class BasketDAO {
	
	/// Constructor
	public BasketDAO() {
	}
	
	public void insertBasket(Basket basket) throws Exception{
		
		Connection con = DBUtil.getConnection();
		
		String sql = "INSERT INTO basket values(seq_basket_basket_no.NEXTVAL, ?, ?, sysdate) ";
		
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setInt(1, basket.getProdNo());
		stmt.setString(2, basket.getUserId());
		stmt.executeUpdate();
		
		con.close();
	}
	
	public Map<String, Object> getBasketList(Search search, String userId) throws Exception{
		Map<String, Object> map = new HashMap<String, Object>();
		
		Connection con = DBUtil.getConnection();
		
		String sql = "SELECT * FROM basket "
				+ 	 "WHERE user_id = '" + userId +"' "
				+	 "ORDER BY basket_no ";
		
		int totalCount = this.getTotalCount(sql);
		
		sql = makeCurrentPageSql(sql, search);
		PreparedStatement pStmt = con.prepareStatement(sql);
		ResultSet rs = pStmt.executeQuery();
		
		ArrayList<Basket> list = new ArrayList<Basket>();
		
		while(rs.next()) {
			Basket basket = new Basket();
			basket.setBasketNo(rs.getInt("basket_no"));
			basket.setProdNo(rs.getInt("prod_no"));
			basket.setUserId(rs.getString("userId"));
			basket.setRegDate(rs.getDate("reg_date"));
			
			list.add(basket);
		}
		
		map.put("totalCount", new Integer(totalCount));
		map.put("list", list);
		
		rs.close();
		con.close();
		pStmt.close();
		
		return map;
		
	}
	
	
	private int getTotalCount(String sql) throws Exception {
		sql = "SELECT COUNT(*) "
			+ "FROM ( " + sql + ") countTable ";
		
		Connection con = DBUtil.getConnection();
		PreparedStatement pStmt = con.prepareStatement(sql);
		ResultSet rs = pStmt.executeQuery();
		
		int totalCount = 0;
		if(rs.next()) {
			totalCount = rs.getInt(1);
		}
		
		pStmt.close();
		con.close();
		rs.close();
		
		return totalCount;
	}
	
	private String makeCurrentPageSql(String sql, Search search) {
		sql = 	"SELECT * "+ 
				"FROM (		SELECT inner_table. * ,  ROWNUM AS row_seq " +
								" 	FROM (	"+sql+" ) inner_table "+
								"	WHERE ROWNUM <="+search.getCurrentPage()*search.getPageSize()+" ) " +
				"WHERE row_seq BETWEEN "+((search.getCurrentPage()-1)*search.getPageSize()+1) +" AND "+search.getCurrentPage()*search.getPageSize();
	
	System.out.println("UserDAO :: make SQL :: "+ sql);	
	
	return sql;
	}

}
