package com.cplatform.mall.back.sys.web.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.cplatform.mall.back.sys.dao.UserDao;
import com.cplatform.mall.back.sys.entity.SysUser;

@Component
public class UserValidator implements Validator {

    @Autowired
    UserDao userDao;


    @Override
    public boolean supports(Class<?> clazz) {
        return SysUser.class.equals(clazz);
    }

    private Pattern emailPattern = Pattern.compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");

    @Override
    public void validate(Object target, Errors errors) {
    	SysUser user = (SysUser) target;

        // 是否是创建帐号的验证
        boolean isCreate = (user.getId() == null);

        if (isCreate) {
            validateUserCode(user, errors);
        }
        validatePass(user, errors);
        validateEmail(user, errors);

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userName", null, "请填写真实姓名");
        if (!errors.hasFieldErrors("userName")) {
            if (user.getUserName().length() > 10) {
                errors.rejectValue("userName", null, "真实姓名长度必须小于等于10位");
            }
        }

        // TODO 其他必要验证



    }


    private void validateUserCode(SysUser user, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userCode", null, "请填写帐号");
        if (!errors.hasFieldErrors("userCode")) {
            String userCode = user.getUserCode();
            SysUser obj = userDao.findByUserCode(userCode);
            if (obj != null) {
                errors.rejectValue("userCode", null, "帐号已存在");
            }
        }
    }

    private void validatePass(SysUser user, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userPwd", null, "请设置登录平台的密码");
        //修改user时密码可以不输入，所以修改时不需要对密码输入框进行长度校验
        if (!errors.hasFieldErrors("userPwd") && user.getId()==null) {
            if (user.getUserPwd().length() < 6 || user.getUserPwd().length() > 25) {
                errors.rejectValue("userPwd", null, "密码长度必须大于等于6位小于等于25位");
            }
        }
        // TODO 包含特殊字符及字母开头的验证

        if (!StringUtils.equals(user.getUserPwd(), user.getConfirmPass())) {
            errors.rejectValue("confirmPass", null, "确认密码不一致");
        }
    }


    private void validateEmail(SysUser user, Errors errors) {

        // 修改且已邮件未变更，则不进行验证
        if (user.getId() != null) {
            SysUser di = userDao.findOne(user.getId());
            if (di != null && StringUtils.equals(di.getEmail(), user.getEmail())) {
                return;
            }
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", null, "请输入邮箱地址");
        if (!errors.hasFieldErrors("email")) {
            Matcher matcher = emailPattern.matcher(user.getEmail());
            if (!matcher.matches()) {
                errors.rejectValue("email", null, "邮箱格式不正确");
            } else if (user.getEmail().length() > 50) {
                errors.rejectValue("email", null, "邮箱地址长度必须小于等于50位");
            } else {
                if (user.getId() != null) {
                    SysUser obj = userDao.findByIdNotAndEmail(user.getId(), user.getEmail());
                    if (obj != null) {
                        errors.rejectValue("email", null, "邮箱地址已被使用");
                    }
                } else {
                    SysUser obj = userDao.findByEmail(user.getEmail());
                    if (obj != null) {
                        errors.rejectValue("email", null, "邮箱地址已被使用");
                    }
                }
            }
        }
    }
}