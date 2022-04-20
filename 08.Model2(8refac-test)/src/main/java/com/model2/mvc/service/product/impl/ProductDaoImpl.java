package com.model2.mvc.service.product.impl;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;



import com.model2.mvc.common.Search;
import com.model2.mvc.common.util.DBUtil;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.product.ProductDao;

@Repository("productDaoImpl")
public class ProductDaoImpl implements ProductDao{
	
	///Field
	@Autowired
	@Qualifier("sqlSessionTemplate")
	private SqlSession sqlSession;
	
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	// Constructor
	public ProductDaoImpl() {
		System.out.println("::" + getClass() + "default Constructor..");
	}
	
	
	/// Method
	public int insertProduct(Product product) throws Exception {
		return sqlSession.insert("ProductMapper.addProduct", product);		
	}
	
	public Product findProduct(int prodNo) throws Exception {
		return sqlSession.selectOne("ProductMapper.getProduct", prodNo);
	}
	
	public int updateProduct(Product product) throws Exception {
		return sqlSession.update("ProductMapper.updateProduct", product);
	}
	
	public Map<String, Object> getProductList(Search search) throws Exception{
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		//==>TotalCount GET
		int totalCount = sqlSession.selectOne("ProductMapper.getTotalCount", search);
		
		//==> CurrentPage 게시물만 받도록 Query 다시구성
		List<Product> list = sqlSession.selectList("ProductMapper.makeCurrentPageSql", search);
		
		//==> totalCount 정보 저장
		map.put("totalCount", new Integer(totalCount));
		//==> currentPage의 게시물 정보 갖는 List 저장
		map.put("list", list);
		
		return map;
	}
	
	
}
