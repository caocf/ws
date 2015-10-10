package com.cplatform.mall.storeuc.cas.captcha;

import org.springframework.webflow.action.AbstractAction;
import org.springframework.webflow.execution.Event;
import org.springframework.webflow.execution.RequestContext;
 
public final class CaptchaErrorCountAction extends AbstractAction {
 
   protected Event doExecute(final RequestContext context) {
      int count;
      try {
         count = context.getExternalContext().getGlobalSessionMap().getInteger("error_count");
      } catch (Exception e) {
         count=0;
      }
 
      count++;
 
      context.getExternalContext().getGlobalSessionMap().put("error_count", count);

       logger.debug("error count : " + count);
      return success();
   }

    public final String clearError(final RequestContext context) {
        context.getExternalContext().getGlobalSessionMap().remove("error_count");
        return "success";
    }
}