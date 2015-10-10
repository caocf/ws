package com.cplatform.mall.backuc.cas.captcha;

import nl.captcha.Captcha;
import org.springframework.binding.message.MessageBuilder;
import org.springframework.binding.message.MessageContext;
import org.springframework.webflow.action.AbstractAction;
import org.springframework.webflow.execution.Event;
import org.springframework.webflow.execution.RequestContext;

public final class CaptchaValidateAction extends AbstractAction {


    private String captchaValidationParameter = "j_captcha_response";


    protected Event doExecute(final RequestContext context) {

        String captchaResponse = context.getRequestParameters().get(captchaValidationParameter);
        boolean valid = false;

        if (captchaResponse != null) {
            Captcha captcha = (Captcha) context.getExternalContext().getSessionMap().get(Captcha.NAME);
            if (captcha != null) {
                valid = captcha.isCorrect(captchaResponse);
            }
        }

        if (valid) {
            return success();
        }

        MessageContext messageContext = context.getMessageContext();
        MessageBuilder msgBuilder=new MessageBuilder();
        msgBuilder.code("error.authentication.vcode.error");
        msgBuilder.defaultText("验证码有误！");
        messageContext.addMessage(msgBuilder.error().build());
        return error();
    }

    public void setCaptchaValidationParameter(String captchaValidationParameter) {
        this.captchaValidationParameter = captchaValidationParameter;
    }
}