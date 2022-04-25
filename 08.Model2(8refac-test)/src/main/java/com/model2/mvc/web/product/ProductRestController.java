package com.model2.mvc.web.product;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.model2.mvc.common.Page;
import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.product.ProductService;


//==> 회원관리 RestController
@RestController
@RequestMapping("/product/*")
public class ProductRestController {
	
	///Field
	@Autowired
	@Qualifier("productServiceImpl")
	private ProductService productService;
	//setter Method 구현 않음
		
	public ProductRestController(){
		System.out.println(this.getClass());
	}
	
	@Value("#{commonProperties['pageUnit']}")
	//@Value("#{commonProperties['pageUnit'] ?: 3}")
	int pageUnit;
	
	@Value("#{commonProperties['pageSize']}")
	//@Value("#{commonProperties['pageSize'] ?: 2}")
	int pageSize;
	
	// image upload
	@Resource(name="uploadPath")
	String uploadPath;
	
	@RequestMapping(value="json/addProduct", method=RequestMethod.POST)
	public Product addProduct(@RequestBody Product product) throws Exception{
		
		System.out.println("/product/json/addProduct : POST");
		
		System.out.println("::" + product);
	
		int resultRow = productService.addProduct(product);
		
		System.out.println("resultRow : " + resultRow);
		
		return product;
	}
	
	@RequestMapping(value="json/getProduct/{menu}/{prodNo}", method=RequestMethod.GET)
	public Map<String, Object> getProduct(@PathVariable String menu, @PathVariable int prodNo) throws Exception {
		
		System.out.println("/product/json/getProduct : GET");
		
		Product product = productService.getProduct(prodNo);
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("product", product);
		map.put("menu", menu);
		
		return map;
	}
	
	@RequestMapping(value="json/listProduct/{menu}")
	public Map<String, Object> listProduct(@PathVariable String menu,
										   @RequestBody Search search) throws Exception{
		
		System.out.println("/product/json/listProduct : GET/POST");
		
		if(search.getCurrentPage() ==0 ){
			search.setCurrentPage(1);
		}
		search.setPageSize(pageSize);
		
		// Business Logic
		Map<String, Object> map = productService.getProductList(search);
		
		Page resultPage = new Page( search.getCurrentPage(), ((Integer)map.get("totalCount")).intValue(), pageUnit, pageSize);
		System.out.println(resultPage);
		
		map.put("resultPage", resultPage);
		map.put("search", search);
		map.put("menu", menu);
		
		return map;
	}
	
	@RequestMapping(value="json/updateProduct/{prodNo}", method=RequestMethod.GET)
	public Product updateProduct(@PathVariable int prodNo) throws Exception{
		
		System.out.println("/updateProduct method = GET");
		
		return productService.getProduct(prodNo);
	}
	
	@RequestMapping(value="json/updateProduct", method=RequestMethod.POST)
	public Map<String, Object> updateProduct(@RequestBody Product product) throws Exception{
		
		System.out.println("/updateProduct method = POST");
		
		int rowResult = productService.updateProduct(product);
		
		System.out.println("rowResult : " + rowResult);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("prodNo", product.getProdNo());
		map.put("menu", "manage");
		
		return map;
	}
	
}