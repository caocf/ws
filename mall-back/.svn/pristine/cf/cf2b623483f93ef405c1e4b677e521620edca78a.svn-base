package com.cplatform.mall.back.item.web.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.cplatform.mall.back.item.entity.ItemOrg;
import com.cplatform.mall.back.item.entity.ItemSale;

@Component
public class ItemOrgValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return ItemOrg.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ItemSale itemOrg = (ItemSale) target;
		validateTag(itemOrg, errors, "tag");
		validateItemPirce(itemOrg,errors,"shopPrice");
	}

	/**
	 * 验证标签长度
	 * 
	 * @param itemOrg
	 * @param errors
	 * @param id
	 */
	private void validateTag(ItemSale itemOrg, Errors errors, String id) {
		if (null != itemOrg.getTag() && !"".equals(itemOrg.getTag())) {
			String itemOrgTag = itemOrg.getTag().replaceAll("；", ";");
			if (itemOrgTag.endsWith(";")) {
				itemOrgTag = itemOrgTag.substring(0, itemOrgTag.lastIndexOf(";"));
			}
			String[] itemTagNames = itemOrgTag.split(";");
			for (String str : itemTagNames) {
				if (str.length() > 10) {
					errors.rejectValue(id, null, "标签“" + str + "”，长度为10个汉字！");
					break;
				}
			}

		}
	}
	
	private void validateItemPirce(ItemSale itemSale,Errors errors,String id){
		if(itemSale.getPrice() == null){
			return;
		}
		String[] priceArry = itemSale.getPrice().split(",");
		Double itemPrice ;
		for(String p : priceArry){
			itemPrice = Double.parseDouble(p.trim());
			if(itemPrice>itemSale.getShopPrice()){
				errors.rejectValue(id,null,"支付价格不能大于商城价！");
				break;
			}
		}
	}
}
