package com.cplatform.mall.back.batch.web.validator;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.cplatform.mall.back.batch.entity.BatchTask;
import com.cplatform.mall.back.item.web.ItemManageController;
import com.cplatform.mall.back.sys.dao.UserDao;

@Component
public class BatchTaskValidator implements Validator {

	@Autowired
	UserDao userDao;
	private static final Logger log = Logger.getLogger(ItemManageController.class);

	@Override
	public boolean supports(Class<?> clazz) {
		return BatchTask.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		BatchTask task = (BatchTask) target;

		// 是否是创建帐号的验证
		String srcFrom = task.getSrcFrom();
		String IsNewTerminalId = task.getIsNewTerminalId();
		if (IsNewTerminalId.equals("1")) {
			if (!srcFrom.equals("1")) {
				validateTerminalId(task.getTerminalid(), errors,"terminalid");
				if (null != task.getBlackid() && !"".equals(task.getBlackid())) {
					validateTerminalId(task.getBlackid(), errors,"blackid");
				}
				if (null != task.getWhiteid() && !"".equals(task.getWhiteid())) {
					validateTerminalId(task.getWhiteid(), errors,"whiteid");

				}
			}
		}

		// TODO 其他必要验证

	}

	private void validateTerminalId(String terminalId, Errors errors,String id) {
		if (terminalId.replaceAll("，", ",").endsWith(",")) {
			terminalId = terminalId.substring(0, terminalId.length() - 1);
		}
		String terminalIds[] = terminalId.split(",");
		for (String ter : terminalIds) {
			if (ter.length() != 11 && !vidateIsNumber(ter)) {
				errors.rejectValue(id, null, ter+"号码无效！");
				break;
			}
		}
	}

	private boolean vidateIsNumber(String str) {
		try {
			Long.parseLong(str);
			return true;
		}
		catch (Exception e) {
			// TODO: handle exception
			log.error(e.getMessage());
			return false;
		}

	}

}