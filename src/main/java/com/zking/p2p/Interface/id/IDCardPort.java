package com.zking.p2p.Interface.id;


import com.zking.p2p.Interface.HttpUtils;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;

import java.util.HashMap;
import java.util.Map;

/**
 *身份证接口调用调试
 * https://apigateway.console.aliyun.com/inner/?spm=5176.730006-56956004-57000002-cmapi025518.content.9.4dc9268fQCJKjZ#/cn-shanghai/apps/testApi/fbd15a6a9ff54832b4536e60500eba64/71a4d49ddd254515abd7b4ad6247110c/RELEASE/203757585/CloudMarket
 * 二要素 身份证号码 名字
 *测试ok
 */
public class IDCardPort {
    public static void main(String[] args) {
        String host = "https://idenauthen.market.alicloudapi.com";
        String path = "/idenAuthentication";
        String method = "POST";
        String appcode = "eb4e6e46ea96429993ef126322abb3f1";
        Map<String, String> headers = new HashMap<String, String>();
        //最后在header中的格式(中间是英文空格)为Authorization:APPCODE eb4e6e46ea96429993ef126322abb3f1
        headers.put("Authorization", "APPCODE " + appcode);
        //根据API的要求，定义相对应的Content-Type
        headers.put("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
        Map<String, String> querys = new HashMap<String, String>();
        Map<String, String> bodys = new HashMap<String, String>();
        bodys.put("idNo", "430603199912061219");
        bodys.put("name", "刘帅");


        try {
            /**
             * 重要提示如下:
             * HttpUtils请从
             * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/src/main/java/com/aliyun/api/gateway/demo/util/HttpUtils.java
             * 下载
             *
             * 相应的依赖请参照
             * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/pom.xml
             */
            HttpResponse response = HttpUtils.doPost(host, path, method, headers, querys, bodys);
            System.out.println(response.toString());
            //获取response的body
            System.out.println(EntityUtils.toString(response.getEntity()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
