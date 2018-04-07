package com.xidong.orderFoodOnline.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.xidong.orderFoodOnline.model.User;
import com.xidong.orderFoodOnline.service.IShoppingCartService;
import com.xidong.orderFoodOnline.service.IUserService;
import com.xidong.orderFoodOnline.util.JsonVo;

@Controller
@RequestMapping(value = "/user")
public class UserController {
	@Resource(name = "userService")
	private IUserService userService;

	public IUserService getUserService() {
		return userService;
	}

	public void setUserService(IUserService userService) {
		this.userService = userService;
	}

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
	public JsonVo login(HttpServletRequest request, String username, String password, String userType) {
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
					jsonVo.setUrl("seller/index.do");
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
	 * 用户注销
	 * 
	 * @param request
	 * @param username
	 * @return
	 */
	@RequestMapping(value = "/logout", method = RequestMethod.POST)
	public String logout(HttpServletRequest request, String username) {
		HttpSession session = request.getSession();
		if (username.equals(session.getAttribute("username"))) {
			session.invalidate();
			return "index";
		}
		return "index";
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
				mav.setViewName("user/seller/index");
				return mav;
			}
		}
		mav.setViewName("index");
		return mav;
	}
}
