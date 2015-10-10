package com.cplatform.cache;

import com.cplatform.b2c.service.VerifyCodeService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import static junit.framework.Assert.assertEquals;

/**
 * User: cuikai
 * Date: 13-7-30
 * Time: 下午4:21
 */
@ContextConfiguration(locations = {"/spring-configuration/*.xml"})
public class VerifyCodeServiceTest extends SpringContextTest {

    @Autowired
    private VerifyCodeService verifyCodeService;

    @Test
    public void verifyCodeTest() {
        String key = "key";
        String code = verifyCodeService.generateVerifyCode(key);

        assertEquals(code, verifyCodeService.getVerifyCode(key));


    }
}
