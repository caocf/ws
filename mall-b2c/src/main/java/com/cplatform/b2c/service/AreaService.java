package com.cplatform.b2c.service;

import com.cplatform.dbhelp.DbHelper;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Title. <br>
 * Description.
 * <p/>
 * Copyright: Copyright (c) 13-6-14 下午3:21
 * <p/>
 * Company: 北京宽连十方数字技术有限公司
 * <p/>
 * Author: nicky
 * <p/>
 * Version: 1.0
 * <p/>
 */
@Service
public class AreaService {

    @Autowired
    DbHelper dbHelper;

    @Autowired
    CommonCacheService commonCacheService;

    /**
     * 获取地区中文全名
     * @param regionCode 地区编码
     * @return 全名
     * @throws SQLException
     */
    public String getFullName(String regionCode) {
        if (regionCode == null && regionCode.length() != 6) return "";
        StringBuilder sb = new StringBuilder(40);
        sb.append( commonCacheService.getRegionName(regionCode.substring(0, 2).concat("0000")) );
        if (!regionCode.endsWith("0000")) {
            sb.append(" ");
            sb.append( commonCacheService.getRegionName(regionCode.substring(0, 4).concat("00")) );
            if (!regionCode.endsWith("00")) {
                sb.append(" ");
                sb.append( commonCacheService.getRegionName(regionCode));
            }
        }
        return sb.toString();
    }

    @Cacheable(value="area_data")
    public String getallJson() throws SQLException {

        List<String[]> list = dbHelper.getArrayList("select region_code, region_name, region_level from t_sys_region order by region_code");
        Map<String, AreaProv> data = new LinkedHashMap<String, AreaProv>();
        for (String[] line : list) {
            String regionCode = line[0];
            String regionName = line[1];
            int level = Integer.valueOf(line[2]);

            if (level == 0) {

                if (!data.containsKey(regionCode)) data.put(regionCode, new AreaProv(regionName));

            }

            if (level == 1) {

                AreaProv ap = data.get(regionCode.substring(0, 2) + "0000");

                Map<String, AreaCity> citys = ap.getS();

                if (!citys.containsKey(regionCode)) citys.put(regionCode, new AreaCity(regionName));

            }

            if (level == 2) {

                AreaProv ap = data.get(regionCode.substring(0, 2) + "0000");

                Map<String, AreaCity> citys = ap.getS();

                AreaCity ac = citys.get(regionCode.substring(0, 4) + "00");

                Map<String, String> regions = ac.getS();

                if (!regions.containsKey(regionCode)) regions.put(regionCode, regionName);

            }

        }

        return JSONObject.fromObject(data).toString();
    }




    public static class AreaCity {

        public AreaCity(String name) {
            this.n = name;
            this.s = new LinkedHashMap<String, String>();
        }
        private String n;
        private Map<String, String> s;

        public String getN() {
            return n;
        }

        public void setN(String n) {
            this.n = n;
        }

        public Map<String, String> getS() {
            return s;
        }

        public void setS(Map<String, String> s) {
            this.s = s;
        }
    }

    public static class AreaProv {

        public AreaProv(String name) {
            this.n = name;
            this.s = new LinkedHashMap<String, AreaCity>();
        }

        private String n;

        private Map<String, AreaCity> s;

        public String getN() {
            return n;
        }

        public void setN(String n) {
            this.n = n;
        }

        public Map<String, AreaCity> getS() {
            return s;
        }

        public void setS(Map<String, AreaCity> s) {
            this.s = s;
        }
    }

}
