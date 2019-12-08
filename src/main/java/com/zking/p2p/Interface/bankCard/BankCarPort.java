package com.zking.p2p.Interface.bankCard;






import com.zking.p2p.Interface.HttpUtils;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;

import java.util.HashMap;
import java.util.Map;


/**
 * 银行卡接口调用调试
 * https://market.aliyun.com/products/57000002/cmapi031454.html?spm=5176.2020520132.101.8.24b67218HfWdWH#sku=yuncode2545400001
 *二要素：姓名 银行卡号
 *测试ok
 */
public class BankCarPort {
    public static void main(String[] args) {
        String host = "https://yhkeys.market.alicloudapi.com";
        String path = "/bankcard2/check";
        String method = "GET";
        String appcode = "eb4e6e46ea96429993ef126322abb3f1";
        Map<String, String> headers = new HashMap<String, String>();
        //最后在header中的格式(中间是英文空格)为Authorization:APPCODE eb4e6e46ea96429993ef126322abb3f1
        headers.put("Authorization", "APPCODE " + appcode);
        Map<String, String> querys = new HashMap<String, String>();
        querys.put("bankcard", "6217995570008501069");
        querys.put("name", "石杨");


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
            HttpResponse response = HttpUtils.doGet(host, path, method, headers, querys);
            System.out.println(response.toString());
            //获取response的body
            System.out.println(EntityUtils.toString(response.getEntity()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
