package com.xidong.orderFoodOnline.controller;

import java.io.UnsupportedEncodingException;
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

import com.xidong.orderFoodOnline.model.Product;
import com.xidong.orderFoodOnline.model.ProductType;
import com.xidong.orderFoodOnline.service.IProductTypeService;
import com.xidong.orderFoodOnline.util.JsonVo;
import com.xidong.orderFoodOnline.util.UUIDUtil;

import net.sf.json.JSONObject;

@Controller
@RequestMapping(value="/producttype")
public class ProductTypeController {
@Autowired
	private IProductTypeService productTypeService;
	
	/**
	 * 添加商品类型页面
	 * @param shopId
	 * @param model
	 * @param productId
	 * @return
	 */
	@RequestMapping(value="/addPage")
	public  String addProductTypePage(String shopId,Model model,@RequestParam(required=false) String  productTypeId) {
	    model.addAttribute("shopId",shopId);
	    if(productTypeId!=null) {
	    	 model.addAttribute("productTypeId", productTypeId);
	    }
		return "producttype/add";
	}
	
	@RequestMapping(value="/index")
	public String getProducttypeIndex(String shopId ,Model model ){
		model.addAttribute("shopId", shopId);
		return "producttype/index";
	}
	
	@RequestMapping(value="/list",produces="text/html;charset=UTF-8")
	public  @ResponseBody String getProducttypeList(ProductType producttype)
	{
		List<ProductType> producttypeList=null;
		Map<String ,Object>  map=new HashMap<String , Object>();
		try {
			if(producttype.getProductTypeName()!=null){
				producttype.setProductTypeName(new String(producttype.getProductTypeName().getBytes("ISO-8859-1"),"UTF-8"))	;	
			}

			producttypeList = productTypeService.getAll(producttype);
			map.put("total",productTypeService.getCountAll(producttype));
			map.put("rows", producttypeList);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return JSONObject.fromObject(map).toString();
	}
	
	@RequestMapping(value="/add")
	public  @ResponseBody String addProducttype(ProductType productType)
	{
		JsonVo json=new JsonVo();
		json.setSuccess(true);
		try{
	json.setMessage("新增商品类型成功");
	productType.setProductTypeId(UUIDUtil.getUUID());
    productTypeService.add(productType);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			json.setMessage("新增商品类型失败");
			json.setSuccess(false);
			e.printStackTrace();
		}
		return JSONObject.fromObject(json).toString();
	}
	
	@RequestMapping(value="/modify",method=RequestMethod.POST)
	public 	 @ResponseBody JsonVo  modifyProduct(ProductType productType){
			JsonVo json = new JsonVo();
			json.setSuccess(true);
			  try {
				productTypeService.modify(productType);
				json.setMessage("修改商品类型成功");
				json.setUrl("producttype/index.do");
				json.setSuccess(true);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				json.setSuccess(false);
				e.printStackTrace();
			}
			  return json;
		  }
	
	@RequestMapping(value="/del",method=RequestMethod.GET)
	public 	 @ResponseBody String  delProduct(ProductType productType){
			JsonVo json = new JsonVo();
			json.setSuccess(true);
			  try {
				productTypeService.delete(productType);
				json.setMessage("删除商品类型成功");
				json.setUrl("producttype/index.do");
				json.setSuccess(true);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				json.setSuccess(false);
				e.printStackTrace();
			}
			  return JSONObject.fromObject(json).toString();
		  }
	
	
	@RequestMapping(value="/getProductType",produces="text/html;charset=UTF-8")
	public @ResponseBody  String getproductById(String productTypeId) {
		try {
		ProductType  productType=productTypeService.getProductTypeById(productTypeId);
JSONObject json	=JSONObject.fromObject(productType);
	return	json.toString();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
