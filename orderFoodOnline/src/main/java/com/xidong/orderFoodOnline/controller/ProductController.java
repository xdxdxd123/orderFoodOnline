package com.xidong.orderFoodOnline.controller;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

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
	
	/**
	 * 添加商品页面
	 * @param shopId
	 * @param model
	 * @param productId
	 * @return
	 */
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
	
	/**
	 * 卖家商品列表
	 * @param product
	 * @return
	 */
	@RequestMapping(value="/list",produces="text/html;charset=UTF-8")
	public	@ResponseBody String selectProduct(Product product){
		  try {
			  List <Product> list=productService.selectProducts(product);
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
	
	/**
	 * 编辑商品数据回显
	 * @param productId
	 * @return
	 */
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
	
	@RequestMapping(value="/getDetail",produces="text/html;charset=UTF-8")
	public	String getProductDetail(Product product,Model model){
		  try {
			  List <Product> products=productService.selectProducts(product);
			  model.addAttribute("productList", products);
			  model.addAttribute("test", products);
		return	"product/list";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	  }
	
	@RequestMapping(value="/getImage")
	public  void readImage(String imagePath,HttpServletResponse response){
		try {
		byte []	buffer=new byte[1014];
		ServletOutputStream  out=	response.getOutputStream();
	InputStream  is=	new FileInputStream(new File(imagePath));
	while(is.read(buffer)!=-1){
		out.write(buffer);
	}
	out.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
