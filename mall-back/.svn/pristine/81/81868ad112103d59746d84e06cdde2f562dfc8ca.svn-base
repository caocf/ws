package com.cplatform.mall.back.store.web;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cplatform.mall.back.store.entity.GoodsShelf;
import com.cplatform.mall.back.store.service.GoodShelfService;

@Controller
@RequestMapping("/store/shelf/")
public class GoodsShelfController {

	@Autowired
	GoodShelfService goodShelfService;

	@RequestMapping("/select")
	@ResponseBody
	public List<GoodsShelf> selectStoreShelf(Long shopId, @RequestParam(required = false, defaultValue = "0") Long pid) throws SQLException {
		return goodShelfService.getTreeGoodShelf(shopId, pid);
	}
}
