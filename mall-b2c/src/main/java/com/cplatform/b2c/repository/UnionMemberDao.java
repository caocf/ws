package com.cplatform.b2c.repository;

import com.cplatform.b2c.util.*;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * User: cuikai
 * Date: 13-11-8
 * Time: 下午3:07
 */
@Component
public class UnionMemberDao {

    private Logger businessLogger = Logger.getLogger("business");

    private static final String SHOP_CODE = "12580777";
    private static final String VERSION = "1.0";

    @Autowired
    private AppConfig appConfig;

    public UnionMember getUnionMemberInfo(String terminalId) {
        JsonMapper mapper = JsonMapper.buildNormalMapper();
        try {
            String time = TimeUtil.now();
            String headerValue = time + "#" + Encodes.encodeMd5(SHOP_CODE + StringUtils.substring(time, 0, 8)
                    + VERSION + StringUtils.substring(time, 8));

            String queryString = "{\"terminalId\":\"" + terminalId + "\"}";

            queryString = Encodes.encodeHex(Encodes.encodeBase64(queryString.getBytes()).getBytes());
            String hexString = HttpClientUtils.httpPost(appConfig.getUnionMemberUri(), queryString, headerValue);
            String json = new String(Encodes.decodeBase64(new String(Encodes.decodeHex(hexString))));

            businessLogger.info("商盟会员" + terminalId + "查询响应：" + json);
            return mapper.fromJson(json, UnionMember.class);
        } catch (Exception e) {
//            throw new RuntimeException("网络异常，[" + terminalId + "]商盟会员查询失败");
            return new UnionMember();
        }
    }

    public static class UnionMember {
        private String flag;
        private String msg;
        @JsonProperty("isMember")
        private String member;
        private String bossSet;
        private String level;

        public String getFlag() {
            return flag;
        }

        public void setFlag(String flag) {
            this.flag = flag;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public String getMember() {
            return member;
        }

        public void setMember(String member) {
            this.member = member;
        }

        public String getBossSet() {
            return bossSet;
        }

        public void setBossSet(String bossSet) {
            this.bossSet = bossSet;
        }

        public String getLevel() {
            return level;
        }

        public void setLevel(String level) {
            this.level = level;
        }
    }
}
