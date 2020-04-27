package com.tt.newsup.server;

import com.alibaba.fastjson.JSONObject;
import com.tt.newsup.dao.LineUserDao;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.net.URISyntaxException;

@Service
@Transactional
public class GetOpenIDService {

    @Autowired
    private LineUserDao lineUserDao;

    @Autowired
    private GetAccessTokenService getAccessTokenService;

    private static final int SUCCESS_CODE = 200;

    public String getAccessToken(String code,String accesstoken) throws IOException, URISyntaxException {
        JSONObject jsonObject = null;
        CloseableHttpClient client = null;
        CloseableHttpResponse response = null;
        if(accesstoken =="" || accesstoken == null){
            getAccessTokenService.InsertAccessToken();
            accesstoken = getAccessTokenService.getAccessToken();
        }
            try {
                String url = "https://qyapi.weixin.qq.com/cgi-bin/user/getuserinfo?access_token=" + accesstoken + "&code=" + code;
                client = HttpClients.createDefault();
                URIBuilder uriBuilder = new URIBuilder(url);
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
                        System.out.println(jsonObject);

                        lineUserDao.insert(jsonObject.getString("UserId"));
                    } catch (Exception e) {

                    }
                } else {

                }
            } catch (Exception e) {

            } finally {
                response.close();
                client.close();
            }

            if(jsonObject.getString("UserId") == ""||jsonObject.getString("UserId") == null){
                getAccessTokenService.InsertAccessToken();
                accesstoken = getAccessTokenService.getAccessToken();
                try {
                    String url = "https://qyapi.weixin.qq.com/cgi-bin/user/getuserinfo?access_token=" + accesstoken + "&code=" + code;
                    client = HttpClients.createDefault();
                    URIBuilder uriBuilder = new URIBuilder(url);
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
                            System.out.println(jsonObject);

                            lineUserDao.insert(jsonObject.getString("UserId"));
                        } catch (Exception e) {

                        }
                    } else {

                    }
                } catch (Exception e) {

                } finally {
                    response.close();
                    client.close();
                }
            }




            return jsonObject.getString("UserId");

    }





    public void inserlineuserclick(String userid){

        lineUserDao.inserclik(userid);
    }
}
