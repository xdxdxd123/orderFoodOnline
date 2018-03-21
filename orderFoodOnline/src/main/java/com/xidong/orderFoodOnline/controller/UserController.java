package com.xidong.orderFoodOnline.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.xidong.orderFoodOnline.model.User;
import com.xidong.orderFoodOnline.service.IUserService;

@Controller
@RequestMapping(value="/user")
public class UserController {
	@Resource(name = "userService")
	private IUserService userService;

	public IUserService getUserService() {
		return userService;
	}

	public void setUserService(IUserService userService) {
		this.userService = userService;
	}
	
	/**
	 * 用户注册
	 * @param user
	 * @return
	 */
	@RequestMapping( value="/register",method=RequestMethod.POST)
	public String  addUser( User user){
		try {
			userService.addUser(user);
		return 	 "user/registerSuccess";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return  null;
	}
	
	/**
	 *  用户登录
	 * @param username
	 * @param password
	 * @return
	 */
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public String  login(HttpServletRequest  request,String  username,String password)
	{
		if(!"".equals(username)&&!"".equals(password))
		{
		HttpSession   session=	request.getSession();
		session.setAttribute("username", username);
		return  "user/index.html";
		}else{
			
		}
		return null;
		
	}
	
	/**
	 * 用户注销
	 * @param request
	 * @param username
	 * @return
	 */
	@RequestMapping(value="/logout",method=RequestMethod.POST)
   public String logout(HttpServletRequest request,String  username){
		   HttpSession   session=	request.getSession();
		if(username.equals(session.getAttribute("username")))  {
			session.invalidate();
			return "index";
		}  
	   return  "index";
   }
	
	/**
	 * 检验用户名的唯一性
	 * @param username
	 * @return
	 */

	@RequestMapping(value="/checkUsername",method=RequestMethod.GET)
	
	public @ResponseBody  Map<String,Object> checkUserNameExist(String username){
	try {
		boolean  	flag=userService.checkUsernameExist(username);
		HashMap<String, Object> map=new HashMap<String,Object>();
		if(flag==true){
			map.put("valid", false);
			return  map;
		}else{
			map.put("valid", true);
			return  map;
		}
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return null;
	}
}
