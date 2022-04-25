package com.model2.mvc.web.purchase;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.model2.mvc.common.Page;
import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.user.UserService;


//==> 회원관리 Controller
@Controller
@RequestMapping("/purchase")
public class PurchaseController {
	
	///Field
	@Autowired
	@Qualifier("purchaseServiceImpl")
	private PurchaseService purchaseService;
	//setter Method 구현 않음
	
	@Autowired
	@Qualifier("productServiceImpl")
	private ProductService productService;
		
	public PurchaseController(){
		System.out.println(this.getClass());
	}
	
	//==> classpath:config/common.properties  ,  classpath:config/commonservice.xml 참조 할것
	//==> 아래의 두개를 주석을 풀어 의미를 확인 할것
	@Value("#{commonProperties['pageUnit']}")
	//@Value("#{commonProperties['pageUnit'] ?: 3}")
	int pageUnit;
	
	@Value("#{commonProperties['pageSize']}")
	//@Value("#{commonProperties['pageSize'] ?: 2}")
	int pageSize;
	
	
	@RequestMapping(value="/addPurchase", method=RequestMethod.GET)
	public ModelAndView addPurchase(@RequestParam("prodNo") int prodNo,
									HttpSession session) throws Exception {

		System.out.println("/addPurchase.do");
		
		Product product = productService.getProduct(prodNo);
		Purchase purchase = new Purchase(); 
		purchase.setPurchaseProd(product);
		
		purchase.setBuyer((User)session.getAttribute("user"));
		
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("purchase", purchase);
		modelAndView.setViewName("forward:/purchase/addPurchase.jsp");
		
		return modelAndView;
	}
	
	@RequestMapping(value="/addPurchase", method=RequestMethod.POST)
	public ModelAndView addPurchase( @ModelAttribute("purchase") Purchase purchase,
								 @ModelAttribute("product") Product product,
								 HttpSession session) throws Exception {

		System.out.println("/addUser.do");
		//Business Logic
		
		purchase.setBuyer((User)session.getAttribute("user"));
		purchase.setPurchaseProd(product);
		
		purchaseService.addPurchase(purchase);
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("purchase", purchase);
		modelAndView.setViewName("forward:/purchase/addPurchaseView.jsp");
		
		return modelAndView;
	}
	
	@RequestMapping(value="/getPurchase", method=RequestMethod.GET)
	public ModelAndView getUser( @RequestParam("tranNo") int tranNo) throws Exception {
		
		System.out.println("/getPurchase.do");
		//Business Logic
		Purchase purchase = purchaseService.getPurchase(tranNo);
		// Model 과 View 연결
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("purchase", purchase);
		modelAndView.setViewName("");
		
		return modelAndView;
	}
	
	@RequestMapping(value="/listPurchase")
	public ModelAndView listPurchase( @ModelAttribute("search") Search search,
									  HttpSession session) throws Exception{

		System.out.println("/listPurchase.do");
		
		if(search.getCurrentPage() ==0 ){
			search.setCurrentPage(1);
		}
		search.setPageSize(pageSize);
		
		//Business Logic
		Map<String, Object> map = purchaseService.getPurchaseList(search, ((User)session.getAttribute("user")).getUserId());
		
		Page resultPage = new Page(search.getCurrentPage(), ((Integer)map.get("totalCount")).intValue(), pageUnit, pageSize);
		
		// Model 과 View 연결
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("list", map.get("list"));
		modelAndView.addObject("resultPage", resultPage);
		modelAndView.addObject("search", search);
		
		modelAndView.setViewName("forward:/purchase/listPurchase.jsp");
		
		return modelAndView;
	}
	
	@RequestMapping(value="/listSale")
	public ModelAndView listSale( @ModelAttribute("search") Search search) throws Exception{

		System.out.println("/listSale");
		
		if(search.getCurrentPage() ==0 ){
			search.setCurrentPage(1);
		}
		search.setPageSize(pageSize);
		
		//Business Logic
		Map<String, Object> map = purchaseService.getSaleList(search);
		
		Page resultPage = new Page(search.getCurrentPage(), ((Integer)map.get("totalCount")).intValue(), pageUnit, pageSize);
		
		// Model 과 View 연결
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("list", map.get("list"));
		modelAndView.addObject("resultPage", resultPage);
		modelAndView.addObject("search", search);
		
		modelAndView.setViewName("forward:/purchase/listSale.jsp");
		
		return modelAndView;
	}
	
	@RequestMapping(value="/updatePurchase", method=RequestMethod.GET)
	public ModelAndView updatePurchase(@RequestParam("prodNo") int prodNo,
								 	   @RequestParam("tranNo") int tranNo,
								 	   HttpSession session) throws Exception{
		
		System.out.println("/updatePurchase.do");
		
		// B/L
		Product product = productService.getProduct(prodNo);
		Purchase purchase = purchaseService.getPurchase(tranNo);
		
		purchase.setPurchaseProd(product);
		purchase.setBuyer((User)session.getAttribute("user"));
		
		System.out.println("updatePurchase.do purchase : " + purchase);
		
		// Model 과 View 연결
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("purchase", purchase);
				
		modelAndView.setViewName("forward:/purchase/updatePurchase.jsp");
				
		return modelAndView;
	}
	
	@RequestMapping(value="/updatePurchase", method=RequestMethod.POST)
	public ModelAndView updatePurchase(@ModelAttribute("purchase") Purchase purchase,
						@ModelAttribute("product") Product prdocut,
							  HttpSession session) throws Exception{
		
		System.out.println("/updatePurchaseView.do");
		
		
		//Business Logic
		purchase.setBuyer((User)session.getAttribute("user"));
		purchase.setPurchaseProd(prdocut);
		
		System.out.println("updatePurchaseView purchase : " + purchase);
		
		purchase = purchaseService.updatePurchase(purchase);
		
		
		// Model 과 View 연결
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("purchase", purchase);
		
		modelAndView.setViewName("forward:/purchase/updatePurchaseView.jsp");
		
		return modelAndView;
	}
	
	@RequestMapping(value="/updateTranCode", method=RequestMethod.GET)
	public ModelAndView updateTranCode( @ModelAttribute("purchase") Purchase purchase,
									   @RequestParam("menu") String menu ) throws Exception{
		
		System.out.println("/updateTranCode");
		
		//Business Logic
		purchaseService.UpdateTranCode(purchase);
		
		System.out.println("updateTranCode menu : " + menu);
		
		// Model 과 View 연결
		ModelAndView modelAndView = new ModelAndView();		
		
		// Navigate
		String navString="forward:/purchase/listPurchase";
		if(menu.equals("manage")) {
			navString = "forward:/purchase/listSale";
		}
		modelAndView.setViewName(navString);
		
		return modelAndView;
	}
	
	@RequestMapping(value="/deletePurchase", method=RequestMethod.GET)
	public ModelAndView deletePurchsae(@RequestParam("tranNo") int tranNo) throws Exception {
		
		System.out.println("/deletePurchase method = GET");
		
		// B/L
		int rowResult = purchaseService.deletePurchase(tranNo);
		
		System.out.println("rowResult : " + rowResult);
		
		ModelAndView modelAndView = new ModelAndView();
		
		modelAndView.setViewName("forward:/purchase/listPurchase");
		
		return modelAndView;
	}
}