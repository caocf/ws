package com.cplatform.mapper;

import com.cplatform.b2c.dto.kuaidi100_DTO;
import com.cplatform.b2c.util.JaxbMapper;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * User: cuikai
 * Date: 13-8-12
 * Time: 上午11:02
 */
public class kuaidi100_apiTest {

    private String xml = "<xml>" +
            "<message>ok</message>" +
            "<nu>210090644209</nu>" +
            "<ischeck>1</ischeck>" +
            "<com>huitongkuaidi</com>" +
            "<status>1</status>" +
            "<condition>F00</condition>" +
            "<data>" +
            "<time>2012-09-22 18:34:41</time>" +
            "<context>苏州市|签收|苏州市【常熟】，王瑞龙 已签收</context>" +
            "</data>" +
            "<data>" +
            "<time>2012-09-22 11:12:46</time>" +
            "<context>苏州市|发件|苏州市【常熟】，正发往【常熟东南分部】</context>" +
            "</data>" +
            "<data>" +
            "<time>2012-09-22 06:40:32</time>" +
            "<context>苏州市|到件|到苏州市【常熟】</context>" +
            "</data>" +
            "<data>" +
            "<time>2012-09-21 21:00:56</time>" +
            "<context>南京市|发件|南京市【南京分拨中心】，正发往【无锡分拨中心】</context>" +
            "</data>" +
            "<state>3</state>" +
            "</xml>";


    @Test
    public void kuai100MapperTest() {
        kuaidi100_DTO dto = JaxbMapper.fromXml(xml, kuaidi100_DTO.class);
        assertEquals("ok", dto.getMessage());
        assertEquals("210090644209", dto.getNumber());
        assertEquals("1", dto.getIscheck());
        assertEquals("huitongkuaidi", dto.getCom());
        assertEquals("1", dto.getStatus());
        assertEquals("F00", dto.getCondition());
        assertEquals(4, dto.getData().size());
        assertEquals("2012-09-21 21:00:56",dto.getData().get(3).getTime());
        assertEquals("南京市|发件|南京市【南京分拨中心】，正发往【无锡分拨中心】",dto.getData().get(3).getContext());
    }
}
