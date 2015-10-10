package com.cplatform.mall.storeuc.cas;

import com.cplatform.mall.storeuc.service.DataAccessService;
import org.apache.commons.codec.digest.DigestUtils;
import org.jasig.cas.authentication.handler.AuthenticationException;
import org.jasig.cas.authentication.handler.BadCredentialsAuthenticationException;
import org.jasig.cas.authentication.handler.support.AbstractUsernamePasswordAuthenticationHandler;
import org.jasig.cas.authentication.principal.UsernamePasswordCredentials;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.SQLException;
import java.util.Map;

/**
 * 验证用户
 * 
 * @author chengyao
 */
public final class UcAuthenticationHandler extends AbstractUsernamePasswordAuthenticationHandler {

    @Autowired
    DataAccessService dataAccessService;

    public boolean authenticateUsernamePasswordInternal(final UsernamePasswordCredentials credentials) throws AuthenticationException {

        final String userCode = credentials.getUsername();
        final String password = credentials.getPassword();

        try {
            Map<String, String> map = dataAccessService.findByCode(userCode);

            if (map == null) {
                return false;
            }

            final String encryptedPassword = encode(password);
            if (encryptedPassword.equals(map.get("pwd"))) {
                return true;
            }
        } catch (SQLException e) {
            this.log.error(e.getMessage(), e);
            throw new BadCredentialsAuthenticationException("error.authentication.db.error");
        }

        return false;
    }

    private String encode(String password) {
        return DigestUtils.md5Hex(password);
    }
}