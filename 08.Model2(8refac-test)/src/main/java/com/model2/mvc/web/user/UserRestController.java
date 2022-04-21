package com.model2.mvc.web.user;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import com.model2.mvc.common.Page;
import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.user.UserService;


//==> 회원관리 RestController
@RestController
@RequestMapping("/user/*")
public class UserRestController {
	
	///Field
	@Autowired
	@Qualifier("userServiceImpl")
	private UserService userService;
	//setter Method 구현 않음
		
	public UserRestController(){
		System.out.println(this.getClass());
	}
	
	@Value("#{commonProperties['pageUnit']}")
	//@Value("#{commonProperties['pageUnit'] ?: 3}")
	int pageUnit;
	
	@Value("#{commonProperties['pageSize']}")
	//@Value("#{commonProperties['pageSize'] ?: 2}")
	int pageSize;
	
	@RequestMapping( value="json/login", method=RequestMethod.POST )
	public User login(	@RequestBody User user,
									HttpSession session ) throws Exception{
	
		System.out.println("/user/json/login : POST");
		//Business Logic
		System.out.println("::"+user);
		User dbUser=userService.getUser(user.getUserId());
		
		System.out.println(user.getPassword().equals(dbUser.getPassword()));
		
		if( user.getPassword().equals(dbUser.getPassword())){
			session.setAttribute("user", dbUser);
		}
		
		return dbUser;
	}
	
	@RequestMapping( value="json/logout", method=RequestMethod.GET )
	public void logout(HttpSession session ) throws Exception{
	
		System.out.println("/user/json/logout : GET");
		//Business Logic
		session.invalidate();
		System.out.println("after invalidate");

	}
	
	@RequestMapping( value="json/getUser/{userId}", method=RequestMethod.GET )
	public User getUser( @PathVariable String userId ) throws Exception{
		
		System.out.println("/user/json/getUser : GET");
		
		//Business Logic
		return userService.getUser(userId);
	}
	
	@RequestMapping(value="json/addUser", method=RequestMethod.POST)
	public User addUser(@RequestBody User user) throws Exception{
		
		System.out.println("/user/json/addUser : POST");
		
		System.out.println("::" + user);
		int resultRow = userService.addUser(user);
		
		System.out.println("resultRow : " + resultRow);
		
		return user;
	}
	
	@RequestMapping(value="json/updateUser", method=RequestMethod.POST)
	public User updateUser(@RequestBody User user, 
										HttpSession session) throws Exception{
		
		System.out.println("/user/json/updateUser : POST");
		
		System.out.println("::" + user);
		userService.updateUser(user);
		
		String sessionId = ((User)session.getAttribute("user")).getUserId();
		
		if(sessionId.equals(user.getUserId())) {
			session.setAttribute("user", user);
		}		
		
		return user;
	}
	
	@RequestMapping(value="json/checkDuplication", method=RequestMethod.POST)
	public Map<String, Object> checkDuplication(@RequestBody User user) throws Exception{
		
		System.out.println("/user/json/checkDuplication : POST");
		
		System.out.println("::" + user);
		
		boolean result = userService.checkDuplication(user.getUserId());
		
		Map map = new HashMap();
		
		map.put("result", new Boolean(result));
		map.put("userId", user.getUserId());
		
		return map;
	}
	
	@RequestMapping(value="json/listUser", method=RequestMethod.POST)
	public Map<String, Object> listUser(@RequestBody Search search) throws Exception{
		
		System.out.println("/user/json/listUser");
		
		if(search.getCurrentPage()==0) {
			search.setCurrentPage(1);
		}
		search.setPageSize(pageSize);
		
		// Business logic 수행
		Map<String, Object> map = userService.getUserList(search);
		
		Page resultPage = new Page(search.getCurrentPage(), ((Integer)map.get("totalCount")).intValue(), pageUnit, pageSize);
		
		map.put("resultPage", resultPage);
		map.put("search", search);
		
		map.remove("totalCount");
		
		return map;
	}
	
}