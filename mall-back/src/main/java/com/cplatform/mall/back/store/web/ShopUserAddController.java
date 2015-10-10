package com.cplatform.mall.back.store.web;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cplatform.mall.back.store.dao.ShopUserDao;
import com.cplatform.mall.back.store.entity.ShopUser;
import com.cplatform.mall.back.utils.JsonRespWrapper;
import com.cplatform.mall.back.utils.LogUtils;
import com.cplatform.util2.TimeStamp;
import com.cplatform.util2.security.MD5;

/**
 * 
 * @Title	添加商户账号
 * @Description
 * @Copyright: Copyright (c) 2013-9-18
 * @Company: 北京宽连十方数字技术有限公司
 * @Author xueqiang
 * @Version: 1.0
 *
 */
@Controller
@RequestMapping(value = "/storeuser")
public class ShopUserAddController {
	
	@Autowired
	private ShopUserDao shopUserDao;
	@Autowired
	LogUtils logUtils;
	
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String addGet(ShopUser user) {
		return "/store/user/user_add";
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	public Object addPost(ShopUser user, @RequestParam(value = "page", required = false, defaultValue = "1") int page, Model model) throws SQLException{
		
		ShopUser su = shopUserDao.findByCode(user.getCode());
		if(null != su){
			return JsonRespWrapper.successAlert("该账号已经注册过,请重新填写账号名！");
		}
		
		user.setPwd(MD5.digest2Str(user.getPwd()));
		user.setUpdateTime(TimeStamp.getTime(14));
		user.setStatus(1l);
		user.setType(1L);
		shopUserDao.save(user);
		logUtils.logModify("添加商户账号", "添加成功, 商户账号编号：" +user.getId());
		
		return JsonRespWrapper.successJump("/store/user/list");
	}

}
