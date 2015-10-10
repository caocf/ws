package com.cplatform.b2c.web.order;

import com.cplatform.b2c.service.AreaService;
import com.cplatform.b2c.util.AppConfig;
import com.cplatform.b2c.util.PathUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Title. <br>
 * Description.
 * <p/>
 * Copyright: Copyright (c) 13-6-18 下午2:18
 * <p/>
 * Company: 北京宽连十方数字技术有限公司
 * <p/>
 * Author: nicky
 * <p/>
 * Version: 1.0
 * <p/>
 */
@Controller
@RequestMapping("/")
public class StaticJsController {

    @Autowired
    AppConfig appConfig;

    @Autowired
    PathUtil pathUtil;

    @Autowired
    AreaService areaService;

    /*
 * 用于js脚本的全局变量
 */
    @RequestMapping(value = "/js/static", produces = { "application/x-javascript", "text/javascript", "application/javascript" })
    @ResponseBody
    public String jsGlobal(WebRequest webRequest) {
        StringBuilder sb = new StringBuilder();
        sb.append("var G_CTX_ROOT = '").append(appConfig.getWebRoot()).append("';\n");
        sb.append("var G_IMG_ROOT = '").append(appConfig.getImgSvrHost()).append("';\n");
        return sb.toString();
    }

    @RequestMapping(value = "/js/area_data", produces = {
            "application/x-javascript;charset=UTF-8",
            "text/javascript;charset=UTF-8",
            "application/javascript;charset=UTF-8" })
    @ResponseBody
    public String areaData() throws SQLException {
        StringBuilder sb = new StringBuilder();
        sb.append("var AREA = ").append(areaService.getallJson()).append(";\n");
        return sb.toString();
    }


    @RequestMapping(value = "/fun/direct-{flag}-{id}")
    public String funDirect(@PathVariable String flag, @PathVariable String id, HttpServletResponse response) throws IOException {
        String ret = pathUtil.getPathById(flag, id);
        response.sendRedirect(ret);
        return null;
    }

    @RequestMapping(value = "/fun/itemimg-{id}")
    @ResponseBody
    public String funItemImage(@PathVariable Long id) {
        return pathUtil.getPathById(PathUtil.TYPE_ITEM_PIC, id);
    }

}
