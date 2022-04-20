package com.model2.mvc.service.user.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.user.UserDao;

@Repository("userDaoImpl")
public class UserDaoImpl implements UserDao{
	
	///Field
	@Autowired
	@Qualifier("sqlSessionTemplate")
	private SqlSession sqlSession;
	
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	///Constructor
	public UserDaoImpl() {
		System.out.println("::" + getClass() + "default 생성자");
	}

	///Method
	public int insertUser(User user) throws Exception {
		return sqlSession.insert("UserMapper.addUser", user);
	}

	public User findUser(String userId) throws Exception {
		return sqlSession.selectOne("UserMapper.getUser", userId);
	}
	
	public int updateUser(User user) throws Exception {
		return sqlSession.update("UserMapper.updateUser", user);
	}
	
	public int deleteUser(String userId) throws Exception{
		return sqlSession.delete("UserMapper.removeUser", userId);
	}

	public Map<String , Object> getUserList(Search search) throws Exception {
		
		Map<String , Object>  map = new HashMap<String, Object>();
		
		//==> TotalCount GET
		int totalCount = sqlSession.selectOne("UserMapper.getTotalCount", search);
		
		//==> CurrentPage 게시물만 받도록 Query 다시구성
		List<User> list = sqlSession.selectList("UserMapper.makeCurrentPageSql", search);
		
		//==> totalCount 정보 저장
		map.put("totalCount", new Integer(totalCount));
		//==> currentPage 의 게시물 정보 갖는 List 저장
		map.put("list", list);
		
		return map;
	}

	
	
}