package com.cplatform.mall.back.utils;

import org.apache.log4j.Logger;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cplatform.mall.back.item.entity.ItemSale;
import com.cplatform.mall.back.store.entity.Store;
import com.cplatform.mall.back.sys.entity.SysType;

/**
 * 缓存重置功能 add by chensl
 */
@Aspect
@Component
public class AfterReturningAdviceRefreshUrl {

	private static final Logger log = Logger.getLogger(AfterReturningAdviceRefreshUrl.class);

	@Autowired
	private AppConfig appConfig;

	@Autowired
	private LogUtils logUtils;

	// 匹配商户service具体方法作为切入点
	@AfterReturning(returning = "store", pointcut = "execution(* com.cplatform.mall.back.store.service.StoreService.saveUpdateStore(..)) || execution(* com.cplatform.mall.back.store.service.StoreService.saveStore(..))")
	public void sotoreEdit(Store store) {
		try {
			RequestUrl.post(appConfig.getCacheRefreshUrl() + appConfig.getStoreUrl() + store.getId());
			log.info("商户缓存更新，调用缓存刷新接口成功。");
			logUtils.logOther("商户缓存更新", "商户" + store.getId() + "更新，调用缓存刷新接口成功");
		}
		catch (Exception e) {
			log.error("商户缓存重置异常：" + e.getMessage());
			logUtils.logOther("商户缓存重置异常", "商户" + store.getId() + "更新，调用缓存刷新接口失败");
		}
	}

	// 匹配 商品service具体方法作为切入点
	@AfterReturning(returning = "item", pointcut = "execution(* com.cplatform.mall.back.item.service.ItemManageService.addItemInfo(..)) || "
	        + " execution(* com.cplatform.mall.back.item.service.ItemManageService.saveItemSale(..)) || execution(* com.cplatform.mall.back.item.service.ItemManageService.auditItemSale(..)) "
	        + "|| execution(* com.cplatform.mall.back.item.virtual.service.VitualItemService.addItemInfo(..)) || execution(* com.cplatform.mall.back.item.virtual.service.VitualItemService.saveItemSale(..))")
	public void itemEdit(ItemSale item) {
		try {
			RequestUrl.post(appConfig.getCacheRefreshUrl() + appConfig.getItemUrl() + item.getId());
			log.info("商品更新，调用缓存刷新接口成功.");
			logUtils.logOther("商品缓存更新", "商品" + item.getId() + "更新，调用缓存刷新接口成功");
		}
		catch (Exception e) {
			log.error("商品缓存重置异常：" + e.getMessage());
			logUtils.logOther("商品缓存重置异常", "商品" + item.getId() + "更新，调用缓存刷新接口失败");
		}
	}

	// 匹配 商户分类service具体方法作为切入点
	@AfterReturning(returning = "type", pointcut = "execution(* com.cplatform.mall.back.sys.service.SysTypeService.add(..)) || execution(* com.cplatform.mall.back.sys.service.SysTypeService.delete(..))")
	public void classifyStoreEdit(SysType type) {
		try {
			RequestUrl.post(appConfig.getCacheRefreshUrl() + appConfig.getSysTypeUrl() + type.getId());
			log.info("商户分类或商品分类缓存更新，调用缓存刷新接口成功.");
			logUtils.logOther("商户分类或商品分类缓存更新", "分类" + type.getId() + "更新，调用缓存刷新接口成功");
		}
		catch (Exception e) {
			log.error("商户分类缓存重置异常：" + e.getMessage());
			logUtils.logOther("商户分类或商品分类重置异常", "分类" + type.getId() + "更新，调用缓存刷新接口失败");
		}
	}
}
