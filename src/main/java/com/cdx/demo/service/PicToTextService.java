package com.cdx.demo.service;

import com.baidu.aip.ocr.AipOcr;
import com.cdx.demo.Entity.ResponseBodyEntity;
import com.cdx.demo.util.ClientInstance;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.HashMap;

/**
 * Created by DX on 2018/9/17.
 */
@Service
public class PicToTextService {

    public ResponseBodyEntity PicToText(AipOcr client, String path, HashMap<String, String> options) {

        JSONObject res = client.basicGeneral(path, options);
        //参数整理
        String log_id = res.get("log_id").toString();
        String words_result_num = res.get("words_result_num").toString();
        String resultjsonArray = res.get("words_result").toString();
        int language_no = Integer.parseInt(res.get("language").toString());

        ResponseBodyEntity responseBodyEntity =
                new ResponseBodyEntity(log_id, words_result_num, resultjsonArray, language_no);
        return responseBodyEntity;
    }

    public static void main(String[] args) {
        // 调用接口
        HashMap<String, String> options = new HashMap<String, String>();
        options.put("language_type", "CHN_ENG");
        options.put("detect_direction", "true");
        options.put("detect_language", "true");
        //options.put("probability", "true");
        ClientInstance clientInstance = ClientInstance.getInstance();

        PicToTextService picToTextService = new PicToTextService();
        System.out.println(picToTextService.PicToText(clientInstance.getClient(), "C:\\software\\workspace\\AipOcr\\picture/cdx_origin.jpg", options).toString());
    }
}
