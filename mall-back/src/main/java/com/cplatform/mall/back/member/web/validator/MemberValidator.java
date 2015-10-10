package com.cplatform.mall.back.member.web.validator;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.cplatform.mall.back.member.dao.MemberDao;
import com.cplatform.mall.back.member.entity.Member;
/**
 * 
 * 会员添加提交校验. <br>
 * 类详细说明.
 * <p>
 * Copyright: Copyright (c) 2013-6-15 下午04:48:40
 * <p>
 * Company: 北京宽连十方数字技术有限公司
 * <p>
 * @author zhaowei@c-platform.com
 * @version 1.0.0
 */
@Component
public class MemberValidator implements Validator {

    @Autowired
    MemberDao memberDao;

    @Override
    public boolean supports(Class<?> clazz) {
        return Member.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
    	Member member = (Member) target;
    	
        validateUserName(member, errors);
        validatePass(member, errors);

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "realName", null, "请填写真实姓名");
        if (!errors.hasFieldErrors("realName")) {
            if (member.getRealName().length() > 10) {
                errors.rejectValue("realName", null, "真实姓名长度必须小于等于10位");
            }
        }

    }

    private void validateUserName(Member member, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userName", null, "请填写登录名");
        if (!errors.hasFieldErrors("userName")) {
            String userName = member.getUserName();
            Member obj = memberDao.findByUserName(userName);
            if (obj != null) {
                errors.rejectValue("userName", null, "登录名已存在");
            }
        }
    }

    private void validatePass(Member member, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userPass", null, "请设置密码");
        if (!errors.hasFieldErrors("userPass")) {
            if (member.getUserPass().length() < 6 || member.getUserPass().length() > 25) {
                errors.rejectValue("userPass", null, "密码长度必须大于等于6位小于等于25位");
            }
        }
        if (!StringUtils.equals(member.getUserPass(), member.getConfirmPass())) {
            errors.rejectValue("confirmPass", null, "确认密码不一致");
        }
    }
}