package com.xidong.orderFoodOnline.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
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
import com.xidong.orderFoodOnline.model.ProductType;
import com.xidong.orderFoodOnline.service.IProductService;
import com.xidong.orderFoodOnline.service.IProductTypeService;
import com.xidong.orderFoodOnline.util.JsonVo;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


@Controller      
@RequestMapping(value="/product")
public class ProductController {
	@Autowired
	private IProductService productService;
	public void setProductService(IProductService productService) {
		this.productService = productService;
	}
	@Autowired
	private IProductTypeService productTypeService;
	
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
	public @ResponseBody JsonVo addProduct(Product product, MultipartFile picture,HttpServletRequest request) {
		JsonVo json = new JsonVo();
		try {
			json.setSuccess(true);
		    if(product.getSalePrice()!=null) {
		    	product.getSalePrice().setScale(2);
		    }
		   String imagePath= request.getServletContext().getRealPath("/resources/picture/shop_default.jpg");
		    product.setImage(imagePath);
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
	/**
	 * 修改商品
	 * @param product
	 * @param picture
	 * @return
	 */
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
	
	/**
	 * 删除商品
	 * @param product
	 */
	@RequestMapping(value="/del",method=RequestMethod.GET)
	public @ResponseBody String  delProduct(Product product){
		JsonVo json=new JsonVo();
		  try {
			  json.setSuccess(true);
			productService.delProduct(product);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			json.setSuccess(false);
			e.printStackTrace();
		}
		  return JSONObject.fromObject(json).toString();
	  }
	
	/**
	 * 卖家商品列表
	 * @param product
	 * @return
	 */
	@RequestMapping(value="/list",method=RequestMethod.POST)
	public	@ResponseBody String selectProduct(Product product){
		JSONObject json=null;
		  try {
			  product.setProductName(new String (product.getProductName().getBytes("ISO-8859-1"),"UTF-8"));
			  List <Product> list=productService.selectProducts(product);
			  JSONArray jsonProduct=JSONArray.fromObject(list);
			  for(int index=0;index<list.size();index++){
				 JSONObject productJson= jsonProduct.getJSONObject(index);
				String productTypeId= productJson.getString("productTypeId");
				ProductType productType= productTypeService.getProductTypeById(productTypeId);
				productJson.accumulate("productTypeName", productType.getProductTypeName());
			  }
			  Map<String , Object> map=new HashMap<String , Object>();
			  map.put("rows", jsonProduct);
			  map.put("total", productService.getCountAll(product));
			  json= JSONObject.fromObject(map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

			return	json.toString();
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
			  model.addAttribute("shopId", product.getShopId());
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
		byte []	buffer=new byte[1024];
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
