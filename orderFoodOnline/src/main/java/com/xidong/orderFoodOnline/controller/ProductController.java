package com.xidong.orderFoodOnline.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import com.xidong.orderFoodOnline.model.Product;
import com.xidong.orderFoodOnline.service.IProductService;
import com.xidong.orderFoodOnline.util.JsonVo;
import net.sf.json.JSONObject;


@Controller      
@RequestMapping(value="/product")
public class ProductController {
	@Autowired
	private IProductService productService;
	public void setProductService(IProductService productService) {
		this.productService = productService;
	}
	
	@RequestMapping(value="/addPage")
	public  String addProductPage(String shopId,Model model,@RequestParam(required=false) String  productId) {
	    model.addAttribute("shopId",shopId);
	    if(productId!=null) {
	    	 model.addAttribute("productId", productId);
	    }
		return "product/add";
	}
	
	/**
	 * 新增商品
	 * 
	 * @param product
	 * @param picture
	 * @return
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public @ResponseBody JsonVo addProduct(Product product, MultipartFile picture) {
		JsonVo json = new JsonVo();
		try {

			json.setSuccess(true);
			product.getPrice().setScale(2);
		    if(product.getSalePrice()!=null) {
		    	product.getSalePrice().setScale(2);
		    }
			productService.addProduct(product, picture);
			json.setMessage("增加商品成功");
			json.setUrl("product/index.do");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			json.setSuccess(false);
			e.printStackTrace();
		}
		return json;
	}
	
	@RequestMapping(value="/modify",method=RequestMethod.POST)
public 	 @ResponseBody JsonVo  modifyProduct(Product product,MultipartFile picture){
		JsonVo json = new JsonVo();
		  try {
			productService.modifyProduct(product,picture);
			json.setMessage("修改商品成功");
			json.setUrl("product/index.do");
			json.setSuccess(true);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  return json;
	  }
	
	@RequestMapping(value="/del",method=RequestMethod.POST)
	public void  delProduct(Product product){
		  try {
			productService.delProduct(product);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	  }
	
	@RequestMapping(value="/list",produces="text/html;charset=UTF-8")
	public	@ResponseBody String selectProduct(String shopId){
		  try {
			  List <Product> list=productService.selectAllProduct(shopId);
			  Map<String , Object> map=new HashMap<String , Object>();
			  map.put("rows", list);
			  map.put("total", list.size());
			  JSONObject json= JSONObject.fromObject(map);
		return	json.toString();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	  }
	
	@RequestMapping(value="/index")
	public String listPage() {
		return  "/product/index";
	}
	
	@RequestMapping(value="/getProduct",produces="text/html;charset=UTF-8")
	public @ResponseBody  String getproductById(String productId) {
		try {
		Product  product=productService.selectProductById(productId);
JSONObject json	=JSONObject.fromObject(product);
if(product.getPrice()!=null) {
	json.replace("price", product.getPrice().toString());
}

if(product.getSalePrice()!=null) {
	json.replace("salePrice", product.getSalePrice().toString());
}

	return	json.toString();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
