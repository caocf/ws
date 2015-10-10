package com.cplatform.mall.back.store.web.validator;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.cplatform.mall.back.store.entity.StoreFee;
import com.cplatform.mall.back.store.service.StoreFeeService;
import com.cplatform.mall.back.store.service.StoreService;
import com.cplatform.util2.TimeStamp;

@Component
public class StoreFeeValidator implements Validator {

	@Autowired
	private StoreService storeService;

	@Autowired
	private StoreFeeService storeFeeService;

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		StoreFee vo = (StoreFee) target;
		if (vo.getCapitalType1() == null && vo.getCapitalType2() == null && vo.getCapitalType3() == null && vo.getCapitalType4() == null) {
			errors.rejectValue("capitalType", null, "最少选择一种资金类型");
		}

		try {
			if (vo.getId() == null) {
				String time = TimeStamp.getTime(TimeStamp.YYYYMMDD);
				if (Integer.valueOf(vo.getEffortdate()) < Integer.valueOf(time)) {
					errors.rejectValue("effortdate", null, "生效时间不能小于当前时间");
				} else {
					boolean flag = true;
					if (vo.getCapitalType1() != null) {
						String filtersql = " and capital_type1 = " + vo.getCapitalType1();
						if (!storeFeeService.dateIsValid(vo, filtersql)) {
							errors.rejectValue("capitalType", null, "请检查资金类型是否冲突");
							errors.rejectValue("effortdate", null, "请检查生效时间是否冲突");
							errors.rejectValue("expirydate", null, "请检查失效时间是否冲突");
							errors.rejectValue("productionType", null, "请检查费率类别是否冲突");
							flag = false;
						}
					}
					if (vo.getCapitalType2() != null && flag) {
						String filtersql = " and capital_type2 = " + vo.getCapitalType2();
						if (!storeFeeService.dateIsValid(vo, filtersql)) {
							errors.rejectValue("capitalType", null, "请检查资金类型是否冲突");
							errors.rejectValue("effortdate", null, "请检查生效时间是否冲突");
							errors.rejectValue("expirydate", null, "请检查失效时间是否冲突");
							errors.rejectValue("productionType", null, "请检查费率类别是否冲突");
							flag = false;
						}
					}
					if (vo.getCapitalType3() != null && flag) {
						String filtersql = " and capital_type3 = " + vo.getCapitalType3();
						if (!storeFeeService.dateIsValid(vo, filtersql)) {
							errors.rejectValue("capitalType", null, "请检查资金类型是否冲突");
							errors.rejectValue("effortdate", null, "请检查生效时间是否冲突");
							errors.rejectValue("expirydate", null, "请检查失效时间是否冲突");
							errors.rejectValue("productionType", null, "请检查费率类别是否冲突");
							flag = false;
						}
					}
				}

			} else {
				boolean flag = true;
				if (vo.getCapitalType1() != null) {
					String filtersql = " and capital_type1 = " + vo.getCapitalType1();
					if (!storeFeeService.dateIsValid(vo, filtersql)) {
						errors.rejectValue("capitalType", null, "请检查资金类型是否冲突");
						errors.rejectValue("effortdate", null, "请检查生效时间是否冲突");
						errors.rejectValue("expirydate", null, "请检查失效时间是否冲突");
						errors.rejectValue("productionType", null, "请检查费率类别是否冲突");
						flag = false;
					}
				}
				if (vo.getCapitalType2() != null && flag) {
					String filtersql = " and capital_type2 = " + vo.getCapitalType2();
					if (!storeFeeService.dateIsValid(vo, filtersql)) {
						errors.rejectValue("capitalType", null, "请检查资金类型是否冲突");
						errors.rejectValue("effortdate", null, "请检查生效时间是否冲突");
						errors.rejectValue("expirydate", null, "请检查失效时间是否冲突");
						errors.rejectValue("productionType", null, "请检查费率类别是否冲突");
						flag = false;
					}
				}
				if (vo.getCapitalType3() != null && flag) {
					String filtersql = " and capital_type3 = " + vo.getCapitalType3();
					if (!storeFeeService.dateIsValid(vo, filtersql)) {
						errors.rejectValue("capitalType", null, "请检查资金类型是否冲突");
						errors.rejectValue("effortdate", null, "请检查生效时间是否冲突");
						errors.rejectValue("expirydate", null, "请检查失效时间是否冲突");
						errors.rejectValue("productionType", null, "请检查费率类别是否冲突");
						flag = false;
					}
				}
			}
			if (vo.getFeemode() != 0L && vo.getFeemothod() != 3L) {
				if (vo.getUpreference1() == null || "".equals(vo.getUpreference1())) {
					if (vo.getFixfeeamount1() != null && !"".equals(vo.getFixfeeamount1())) {
						errors.rejectValue("fixfeeamount1", null, "计费参考为空时，固定金额必须为空。");
					}
					if (vo.getFeerate1() != null && !"".equals(vo.getFeerate1())) {
						errors.rejectValue("feerate1", null, "计费参考为空时，费率必须为空。");
					}
				} else {
					if (vo.getFixfeeamount1() == null || "".equals(vo.getFixfeeamount1())) {
						if (vo.getFeerate1() == null || "".equals(vo.getFeerate1())) {
							errors.rejectValue("feerate1", null, "请输入费率。");
						}

					}
					if (vo.getFeerate1() == null || "".equals(vo.getFeerate1())) {
						if (vo.getFixfeeamount1() == null || "".equals(vo.getFixfeeamount1())) {
							errors.rejectValue("fixfeeamount1", null, "请输入固定金额。");
						}

					}

				}
				if (vo.getUpreference2() == null || "".equals(vo.getUpreference2())) {
					if (vo.getFixfeeamount2() != null && !"".equals(vo.getFixfeeamount2())) {
						errors.rejectValue("fixfeeamount2", null, "计费参考为空时，固定金额必须为空。");
					}
					if (vo.getFeerate2() != null && !"".equals(vo.getFeerate2())) {
						errors.rejectValue("feerate2", null, "计费参考为空时，费率必须为空。");
					}
				} else {
					if (vo.getFixfeeamount2() == null || "".equals(vo.getFixfeeamount2())) {
						if (vo.getFeerate2() == null || "".equals(vo.getFeerate2())) {
							errors.rejectValue("feerate2", null, "请输入费率。");
						}

					}
					if (vo.getFeerate2() == null || "".equals(vo.getFeerate2())) {
						if (vo.getFixfeeamount2() == null || "".equals(vo.getFixfeeamount2())) {
							errors.rejectValue("fixfeeamount2", null, "请输入固定金额。");
						}

					}

				}
				if (vo.getUpreference3() == null || "".equals(vo.getUpreference3())) {
					if (vo.getFixfeeamount3() != null && !"".equals(vo.getFixfeeamount3())) {
						errors.rejectValue("fixfeeamount3", null, "计费参考为空时，固定金额必须为空。");
					}
					if (vo.getFeerate3() != null && !"".equals(vo.getFeerate3())) {
						errors.rejectValue("feerate3", null, "计费参考为空时，费率必须为空。");
					}
				} else {
					if (vo.getFixfeeamount3() == null || "".equals(vo.getFixfeeamount3())) {
						if (vo.getFeerate3() == null || "".equals(vo.getFeerate3())) {
							errors.rejectValue("feerate3", null, "请输入费率。");
						}

					}
					if (vo.getFeerate3() == null || "".equals(vo.getFeerate3())) {
						if (vo.getFixfeeamount3() == null || "".equals(vo.getFixfeeamount3())) {
							errors.rejectValue("fixfeeamount3", null, "请输入固定金额。");
						}

					}

				}
				if (vo.getUpreference4() == null || "".equals(vo.getUpreference4())) {
					if (vo.getFixfeeamount4() != null && !"".equals(vo.getFixfeeamount4())) {
						errors.rejectValue("fixfeeamount4", null, "计费参考为空时，固定金额必须为空。");
					}
					if (vo.getFeerate4() != null && !"".equals(vo.getFeerate4())) {
						errors.rejectValue("feerate4", null, "计费参考为空时，费率必须为空。");
					}
				} else {
					if (vo.getFixfeeamount4() == null || "".equals(vo.getFixfeeamount4())) {
						if (vo.getFeerate4() == null || "".equals(vo.getFeerate4())) {
							errors.rejectValue("feerate4", null, "请输入费率。");
						}

					}
					if (vo.getFeerate4() == null || "".equals(vo.getFeerate4())) {
						if (vo.getFixfeeamount4() == null || "".equals(vo.getFixfeeamount4())) {
							errors.rejectValue("fixfeeamount4", null, "请输入固定金额。");
						}

					}

				}
				if (vo.getUpreference5() == null || "".equals(vo.getUpreference5())) {
					if (vo.getFixfeeamount5() != null && !"".equals(vo.getFixfeeamount5())) {
						errors.rejectValue("fixfeeamount5", null, "计费参考为空时，固定金额必须为空。");
					}
					if (vo.getFeerate5() != null && !"".equals(vo.getFeerate5())) {
						errors.rejectValue("feerate5", null, "计费参考为空时，费率必须为空。");
					}
				} else {
					if (vo.getFixfeeamount5() == null || "".equals(vo.getFixfeeamount5())) {
						if (vo.getFeerate5() == null || "".equals(vo.getFeerate5())) {
							errors.rejectValue("feerate5", null, "请输入费率。");
						}

					}
					if (vo.getFeerate5() == null || "".equals(vo.getFeerate5())) {
						if (vo.getFixfeeamount5() == null || "".equals(vo.getFixfeeamount5())) {
							errors.rejectValue("fixfeeamount5", null, "请输入固定金额。");
						}

					}
				}

			}
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
