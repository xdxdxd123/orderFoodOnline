package com.xidong.orderFoodOnline.controller;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.xidong.orderFoodOnline.model.User;
import com.xidong.orderFoodOnline.service.IShopService;
import com.xidong.orderFoodOnline.service.IShoppingCartService;
import com.xidong.orderFoodOnline.service.IUserService;
import com.xidong.orderFoodOnline.util.JsonVo;

@Controller
@RequestMapping(value = "/user")
public class UserController {
	@Resource(name = "userService")
	private IUserService userService;
	@Resource(name = "shopService")
	private IShopService shopService;
	
	

	@Autowired
	IShoppingCartService shoppingCartService;

	public void setShoppingCartService(IShoppingCartService shoppingCartService) {
		this.shoppingCartService = shoppingCartService;
	}

	
	
	/**
	 * 用户注册
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	@ResponseBody
	public JsonVo addUser(User user) {
	JsonVo  json=	new JsonVo();
	json.setSuccess(false);
		try {
			userService.addUser(user);
			json.setSuccess(true);
			json.setUrl("index.jsp");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return json;
	}

	/**
	 * 登录
	 * 
	 * @param request
	 * @param username
	 * @param password
	 * @param userType
	 * @return
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseBody
	public JsonVo login(HttpServletRequest request, String username, String password, String userType,Model model) {
		JsonVo jsonVo = new JsonVo();
		jsonVo.setSuccess(false);
		if (!"".equals(username) && !"".equals(password)) {
			try {
				User user = userService.checkIdentity(username, password, userType);
				if (user != null) {
					HashMap<String, Object> obj = new HashMap<String, Object>();
					HttpSession session = request.getSession();
					session.setAttribute("userId", user.getUserid());
					jsonVo.setSuccess(true);
					if("卖家".equals(userType)) {
						jsonVo.setUrl("/user/seller/index.do");
					
					}else if("买家".equals(userType)){
						jsonVo.setUrl("/user/buyer/index.do");
					}
					model.addAttribute("userId", user.getUserid());
					obj.put("userId", user.getUserid());
					jsonVo.setReturnJson(obj);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}  
		return jsonVo;

	}

	
	
	/**
	 * 请求登录页面
	 * @return
	 */
	@RequestMapping("loginPage")
	public  String loginPage() {
		return "user/login2";
	}
	
	@RequestMapping("registerPage")
	public  String registerPage() {
		return "user/register2";
	}
	
	
	/**
	 * 用户注销
	 * 
	 * @param request
	 * @param username
	 * @return
	 */
	@RequestMapping(value = "/logout")
	public String logout(HttpServletRequest request, String userId) {
		HttpSession session = request.getSession();
		ModelAndView mav=new ModelAndView();
		if (userId.equals(session.getAttribute("userId"))) {
			session.invalidate();
		}
		return "redirect:/user/loginPage.do";
	}

	/**
	 * 检验用户名的唯一性
	 * 
	 * @param username
	 * @return
	 */

	@RequestMapping(value = "/checkUsername", method = RequestMethod.GET)

	public @ResponseBody Map<String, Object> checkUserNameExist(String username) {
		try {
			boolean flag = userService.checkUsernameExist(username);
			HashMap<String, Object> map = new HashMap<String, Object>();
			if (flag == true) {
				map.put("valid", false);
				return map;
			} else {
				map.put("valid", true);
				return map;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 请求卖家店铺首页
	 * 
	 * @param userId
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/seller/index")
	public ModelAndView sellerIndex(String userId, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		HttpSession session = request.getSession();
		if (session.getAttribute("userId") != null && userId != null) {
			if (userId.equals(session.getAttribute("userId"))) {
			User user=null;
			try {
				user = userService.findUserById(userId);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				mav.setViewName("user/seller/index");
				mav.addObject("userId", userId);
				mav.addObject("userName",user.getUsername());
				return mav;
			}
		}
		return mav;
	}
	
	@RequestMapping(value = "findById")
	@ResponseBody
	public JsonVo findById(String userId, HttpServletRequest request) {
		HttpSession session = request.getSession();
		  JsonVo  jsonVo=  new JsonVo();
		if (session.getAttribute("userId") != null && userId != null) {
			if (userId.equals(session.getAttribute("userId"))) {
			User user=null;
			try {
				user = userService.findUserById(userId);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			  jsonVo.setReturnJson(user.getUsername());
				return jsonVo;
			}
		}
		return jsonVo;
	}
	
	@RequestMapping(value = "/buyer/index")
	public String  userIndex(HttpServletRequest request,Model model){
	try {
		shopService.selectAllShop();
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		return "index";
	}

}
