package com.tt.newsup.server;

import com.alibaba.fastjson.JSONObject;
import com.tt.newsup.dao.AccessTokenDao;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.net.URISyntaxException;

@Service
@Transactional
public class GetAccessTokenService {

    private static final int SUCCESS_CODE = 200;

    @Autowired
    private AccessTokenDao accessTokenDao;

    @Scheduled(cron = "0 0 * * * ?")
    public void InsertAccessToken() throws IOException, URISyntaxException {
        JSONObject jsonObject = null;
        CloseableHttpClient client =null;
        CloseableHttpResponse response = null;
        final String corpid = "wwef896039f17ed93a";
        final String corpsecret = "NHKF4CTlOnzcigQ9RkGDy6Mug6_ssD21xI8abBw17Oo";
        //创建HTTPClient对象
        client = HttpClients.createDefault();
        String url = "https://qyapi.weixin.qq.com/cgi-bin/gettoken?corpid=" + corpid + "&corpsecret=" + corpsecret;
        URIBuilder uriBuilder = new URIBuilder(url);
        //创建HTTPGET请求
        HttpGet httpGet = new HttpGet(uriBuilder.build());
        //设置请求头不编码
        httpGet.setHeader(new BasicHeader("Content-type","application/x-www-form-urlencoded; charset=utf-8"));
        //设置返回编码
        httpGet.setHeader(new BasicHeader("Accept", "text/plain;charset=utf-8"));
        //请求服务
        response = client.execute(httpGet);
        int statusCode = response.getStatusLine().getStatusCode();
        if(SUCCESS_CODE == statusCode) {
            //获取返回对象
            HttpEntity entity = response.getEntity();
            //获取返回内容
            String result = EntityUtils.toString(entity, "UTF-8");
            //转换成JSON
            try {
                jsonObject = JSONObject.parseObject(result);

            } catch (Exception e) {
                System.out.println(e);
            } finally {
                response.close();
                client.close();
            }

        }

        accessTokenDao.insertAccess(jsonObject.getString("access_token"));

    }



    public String getAccessToken(){

        String accessToken =  accessTokenDao.selectAccess();
        return accessToken;
    }
}
