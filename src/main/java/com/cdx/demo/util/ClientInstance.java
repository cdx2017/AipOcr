package com.cdx.demo.util;

import com.baidu.aip.ocr.AipOcr;
import org.springframework.stereotype.Component;

/**
 * Created by DX on 2018/9/17.
 */
@Component
public class ClientInstance {
    //设置APPID/AK/SK
    public static final String APP_ID = "14131614";
    public static final String API_KEY = "E2v1kAeQ07NZWRnKmmdSZiOB";
    public static final String SECRET_KEY = "qYLRXGEwAvzSf6OrOHD72c4lgZwa16Ix";
    //单例
    private static volatile ClientInstance instance = null;

    private ClientInstance() {
    }

    //同步锁构造单例模式
    public static ClientInstance getInstance() {
        if (instance == null) {
            synchronized (ClientInstance.class) {
                if (instance == null) {
                    instance = new ClientInstance();
                }
            }
        }
        return instance;
    }

    //构造 Aip客户
    public AipOcr getClient() {
        // 初始化一个AipOcr
        AipOcr client = new AipOcr(APP_ID, API_KEY, SECRET_KEY);

        // 可选：设置网络连接参数
        client.setConnectionTimeoutInMillis(2000);
        client.setSocketTimeoutInMillis(60000);

        // 可选：设置代理服务器地址, http和socket二选一，或者均不设置
        //client.setHttpProxy("proxy_host", proxy_port);  // 设置http代理
        //client.setSocketProxy("proxy_host", proxy_port);  // 设置socket代理

        // 可选：设置log4j日志输出格式，若不设置，则使用默认配置
        // 也可以直接通过jvm启动参数设置此环境变量
        //System.setProperty("aip.log4j.conf", "path/to/your/log4j.properties");
        return client;
    }

}
