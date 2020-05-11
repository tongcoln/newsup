package com.tt.newsup.utils;

import com.fasterxml.jackson.databind.util.JSONPObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;


import java.io.IOException;

public class getUrl {
    public static JSONObject dogetStr(String url) throws IOException {

        String url1 = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=wwef896039f17ed93a&redirect_uri=https://dw30485303.wicp.vip/&response_type=code&scope=snsapi_base&state=www#wechat_redirect";

        return null;
    }
}
