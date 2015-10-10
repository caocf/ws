package com.cplatform.mall.back.order.entity;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

/**
 * 从REQUEST对象中获取与组装信息的静态方法类
 * 
 * @author wyfooo
 * @Create-Date: 2010-1-11
 */
public class RequestUtils {


    /**
     * 从当前请求出解析出基本URL,主要是将POST和GET传递过来的参数全部以URL附加参数方式生成新URL
     * 
     * @param request
     *            一个请求对象实例
     * @param paraName
     *            要过滤掉的单个参数
     * @return 把所有参数拼装后形成的新uri
     */
    public static String assembleOneUri(HttpServletRequest request,
            String paraName) {
     	// 获取所有请求参数名
        StringBuilder sb = new StringBuilder(request.getRequestURI());
        sb.append("?");
        String val = request.getParameter(paraName);
        sb.append(val);
        return sb.toString();
    }
    
    /**
     * 从当前请求出解析出基本URL,主要是将POST和GET传递过来的参数全部以URL附加参数方式生成新URL
     * 
     * @param request
     *            一个请求对象实例
     * @param filterParameterNames
     *            要过滤掉的参数
     * @return 把所有参数拼装后形成的新uri
     */
    @SuppressWarnings("unchecked")
    public static String assembleUri(HttpServletRequest request,
            String[] filterParameterNames) {
//    	String a = request.getQueryString();
        StringBuilder sb = new StringBuilder(request.getRequestURI());
        sb.append("?");

        // 获取所有请求参数名
        Enumeration<String> paraNames = request.getParameterNames();

        while (paraNames.hasMoreElements()) {
            String paraName = paraNames.nextElement();
            if (!isIgnoreParameter(paraName, filterParameterNames)) {
                sb.append(paraName).append("=");
                try {
//                	sb.append(
//                            request.getParameter(paraName)
//                                    +"&");
                    String val = request.getParameter(paraName);
                    System.out.println("val "+val);
                    if(val.startsWith("%"))
                        sb.append(val);
                    else
                    sb.append(URLEncoder.encode(val,"utf-8")).append("&");
                } catch (Exception e) {
                }
            }
            break;
        }
        int i = sb.lastIndexOf("&");
        if(i != -1){
        	sb.deleteCharAt(i);
        }
        return sb.toString();
    }
    

    /**
     * 
     * 重载方法:
     * 从当前请求出解析出基本URL,主要是将POST和GET传递过来的参数全部以URL附加参数方式生成新URL
     * 
     * @param request
     *            一个请求对象实例
     * @param filterParameterNames
     *            要过滤掉的参数
     * @param ParameterNames
     *            需要重新计算ParameterValues值
     * @return 把所有参数拼装后形成的新uri
     */
    @SuppressWarnings("unchecked")
    public static String assembleUri(HttpServletRequest request,
            String[] filterParameterNames, String[] ParameterNames) {
        StringBuilder sb = new StringBuilder(request.getRequestURI());
        sb.append("?");

        // 获取所有请求参数名
        Enumeration<String> paraNames = request.getParameterNames();

        while (paraNames.hasMoreElements()) {
            String paraName = paraNames.nextElement();
            if (!isIgnoreParameter(paraName, filterParameterNames)) {
                sb.append(paraName).append("=");

                if (isParameter(paraName, ParameterNames)) {
                    String[] values = request.getParameterValues(paraName);
                    int value = 0;
                    if (values != null && values.length > 0) {
                        for (String v : values) {
                            value += Integer.valueOf(v);
                        }
                    }
                    sb.append(value + "&");

                } else {
                    try {
                        sb.append(
                                URLEncoder.encode(request
                                        .getParameter(paraName), "utf-8"))
                                .append("&");
                    } catch (UnsupportedEncodingException e) {
                    }

                }

            }

        }

        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }

    /**
     * 从当前请求出解析出基本URL,主要是将POST和GET传递过来的参数全部以URL附加参数方式生成新URL（无过滤参数功能）
     * 
     * @param request
     * @return
     */
    public static String assembleUri(HttpServletRequest request) {
        return assembleUri(request, null);
    }

    /**
     * assembleUri的辅助方法，判断当前参数名是否需要过滤掉
     * 
     * @param parameterName
     * @param filterParameterNames
     * @return
     */
    private static boolean isIgnoreParameter(String parameterName,
            String[] filterParameterNames) {

        if (filterParameterNames == null || filterParameterNames.length == 0) {
            // 过滤列表为空，则所有参数均不忽略
            return false;
        }

        for (String pname : filterParameterNames) {
            if (pname.trim().equalsIgnoreCase(parameterName)) {
                return true;
            }
        }

        return false;
    }

    /**
     * assembleUri的辅助方法，判断当前参数名是否需要重新计算Value的值. true:需要重新计算 false：不需要重新计算
     * 
     * @param parameterName
     * @param ParameterNames
     * @return
     */
    private static boolean isParameter(String parameterName,
            String[] ParameterNames) {
        if (ParameterNames == null || ParameterNames.length == 0) {
            return false;
        }
        for (String pname : ParameterNames) {
            if (pname.trim().equalsIgnoreCase(parameterName)) {
                return true;
            }
        }
        return false;
    }
}
