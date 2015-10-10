package com.cplatform.mall.back.sys.web.validator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.cplatform.mall.back.store.entity.ShopUser;
import com.cplatform.mall.back.sys.dao.UserDao;
import com.cplatform.mall.back.sys.entity.SysUser;

@Component
public class ShopUserValidator implements Validator {

    @Autowired
    UserDao userDao;


    @Override
    public boolean supports(Class<?> clazz) {
        return SysUser.class.equals(clazz);
    }



    @Override
    public void validate(Object target, Errors errors) {
    	ShopUser user = (ShopUser) target;
        validatePass(user, errors);
    }
    private void validatePass(ShopUser user, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "pwd", null, "请设置登录平台的密码");
        //修改user时密码可以不输入，所以修改时不需要对密码输入框进行长度校验
        if (!errors.hasFieldErrors("pwd") && user.getId()==null) {
            if (user.getPwd().length() < 6 || user.getPwd().length() > 25) {
                errors.rejectValue("pwd", null, "密码长度必须大于等于6位小于等于25位");
            }
        }
        // TODO 包含特殊字符及字母开头的验证

        if (!StringUtils.equals(user.getPwd(), user.getConfirmPass())) {
            errors.rejectValue("confirmPass", null, "确认密码不一致");
        }
    }
}