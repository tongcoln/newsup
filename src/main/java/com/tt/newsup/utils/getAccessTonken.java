package com.tt.newsup.utils;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;

import javax.servlet.http.HttpSession;
import java.io.IOException;

public class getAccessTonken {
    private static final int SUCCESS_CODE =200 ;

     public static String  getAcess(HttpSession httpSession) throws IOException {
         String accesstoken = (String) httpSession.getAttribute("accesstoken");
         if (!StringUtils.isBlank(accesstoken)){
             System.out.println("在session中取值");
             return accesstoken;



         }else {
             JSONObject jsonObject = null;
             CloseableHttpClient client = null;
             CloseableHttpResponse response = null;
             final String corpid = "wwef896039f17ed93a";
             final String corpsecret = "QTC31rILx7u8PnqX2QCenUDSfZvgRdLRoiElwqIMQ8c";
             try {/**
              * 创建HttpClient对象
              */
                 client = HttpClients.createDefault();
                 /**
                  * 创建URIBuilder
                  */
                 String url = "https://qyapi.weixin.qq.com/cgi-bin/gettoken?corpid=" + corpid + "&corpsecret=" + corpsecret;
                 URIBuilder uriBuilder = new URIBuilder(url);
                 /**
                  * 设置参数
                  */

                 /**
                  * 创建HttpGet
                  */
                 HttpGet httpGet = new HttpGet(uriBuilder.build());
                 /**
                  * 设置请求头部编码
                  */
                 httpGet.setHeader(new BasicHeader("Content-Type", "application/x-www-form-urlencoded; charset=utf-8"));
                 /**
                  * 设置返回编码
                  */
                 httpGet.setHeader(new BasicHeader("Accept", "text/plain;charset=utf-8"));
                 /**
                  * 请求服务
                  */
                 response = client.execute(httpGet);
                 /**
                  * 获取响应吗
                  */
                 int statusCode = response.getStatusLine().getStatusCode();
                 if (SUCCESS_CODE == statusCode) {
                     /**
                      * 获取返回对象
                      */
                     HttpEntity entity = response.getEntity();
                     /**
                      * 通过EntityUitls获取返回内容
                      */
                     String result = EntityUtils.toString(entity, "UTF-8");

                     /**
                      * 转换成json,根据合法性返回json或者字符串
                      */
                     try {
                         jsonObject = JSONObject.parseObject(result);
                         System.out.println("从url上面取值");
                         httpSession.setAttribute("code", jsonObject.getString("access_token"));
                     } catch (Exception e) {
                         return result;
                     }
                 } else {

                 }
             } catch (Exception e) {

             } finally {
                 response.close();
                 client.close();
             }
             return jsonObject.getString("access_token");
         }

    }

}
