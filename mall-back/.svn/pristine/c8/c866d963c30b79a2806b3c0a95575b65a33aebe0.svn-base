package com.cplatform.mall.back.store.web;

import java.sql.SQLException;

import org.eclipse.jetty.server.Authentication.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cplatform.dbhelp.page.Page;
import com.cplatform.mall.back.store.entity.ShopUser;
import com.cplatform.mall.back.store.service.ShopUserService;
import com.cplatform.mall.back.sys.web.validator.ShopUserValidator;
import com.cplatform.mall.back.sys.web.validator.UserValidator;
import com.cplatform.mall.back.utils.JsonRespWrapper;
import com.cplatform.mall.back.utils.LogUtils;
import com.cplatform.util2.security.MD5;

/**
 * 
 * @Title 商户账户管理控制层
 * @Description
 * @Copyright: Copyright (c) 2013-9-18
 * @Company: 北京宽连十方数字技术有限公司
 * @Author chencheng
 * @Version: 1.0
 *
 */
@Controller
@RequestMapping(value = "/store/user")
public class ShopUserController {

	@Autowired
	private LogUtils logUtils;

	@Autowired
	private ShopUserService shopUserService;

	
	@Autowired
	private ShopUserValidator userValidator;
	/**
	 * 账号管理
	 * @param user
	 * @param page
	 * @param model
	 * @return
	 * @throws SQLException
	 */
	@RequestMapping(value = "/list")
	public String list(ShopUser user, @RequestParam(value = "page", required = false, defaultValue = "1") int page, Model model) throws SQLException {
	
		Page<ShopUser> userPage = shopUserService.findShopUser(user, page);
		
		model.addAttribute("userPage", userPage);
		
		model.addAttribute("shopClassMap", ShopUser.getShopClassMap());
		model.addAttribute("statusMap", ShopUser.getStatusMap());
		return "store/user/user_list";
	}
	/**
	 * 账号冻结
	 * @param id
	 * @param shopUser
	 * @param model
	 * @return
	 */
	 @RequestMapping(value ="modifyUser")
	 @ResponseBody
	 public Object modifyUser(@RequestParam Long id,ShopUser shopUser,Model model){
		 
		  shopUser  = shopUserService.findShopUserById(id);
		  if(shopUser.getStatus()==1){
			  shopUser.setStatus(Long.valueOf(0));
		  }else{
			  shopUser.setStatus(Long.valueOf(1));
		  }
		  shopUserService.updateUserStatus(shopUser);
		if(shopUser.getStatus()==1)
			  return JsonRespWrapper.successReload("账号激活成功！");
		else
			  return JsonRespWrapper.successReload("账号冻结成功！");
		
	 }
	 /**
	  * 密码重置
	  * @param id
	  * @param shopUser
	  * @param model
	  * @return
	  */
	 @RequestMapping(value="modifyUserLogin")
	 @ResponseBody
	 public Object modifyUserLogin(@RequestParam Long id,ShopUser shopUser,Model model){
		 
		  shopUser  = shopUserService.findShopUserById(id);
		  String pwd  ="abc12#";
		  shopUser.setPwd(MD5.digest2Str(pwd));
		  shopUserService.updateUserStatus(shopUser);
		  return JsonRespWrapper.successReload("密码重置成功！");
	 }
	 
	 /**
	  * 账号修改
	  * @param id
	  * @param shopUser
	  * @param model
	  * @return
	  */
	 @RequestMapping(value="modifyUserPwd",method = RequestMethod.GET)

	 public String modifyUserPwd(@RequestParam Long id, Model model){
		 ShopUser shopUser = new ShopUser();
		  shopUser  = shopUserService.findShopUserById(id);
		  model.addAttribute("shopUser",shopUser);
		  model.addAttribute("code",shopUser.getCode());
		  return "store/user/user_modify";
	 }
	 
	 /**
	  * 验证账户和密码是否匹配
	  * @param id
	  * @param shopUser
	  * @param model
	  * @return
	  */
	 @RequestMapping(value="modifyUserPwd",method = RequestMethod.POST)
	 @ResponseBody
	 public Object checkUserPwd(ShopUser shopUser, Model model ,BindingResult result){
		 ShopUser  oldshopUser = shopUserService.findShopUserById(shopUser.getId());

//	
//		 
//		    String [] pwds = pwd.split(",");
//		    for (int i = 0; i < pwds.length; i++) {
//				System.out.println(pwds[1]);
//			}
		   

			//在user密码加密前对user进行验证
		    userValidator.validate(shopUser, result);
		    
			oldshopUser.setPwd(MD5.digest2Str(shopUser.getPwd()));
		  shopUserService.UpdateShopUserRegon(oldshopUser);
			logUtils.logModify("商户账户管理", "商户账户修改, 商户编号：" + oldshopUser.getId());
			
		  
		
		  
		  return JsonRespWrapper.success("密码修改成功！","/store/user/list");
	 }
	 
	 
	 
	 
}
