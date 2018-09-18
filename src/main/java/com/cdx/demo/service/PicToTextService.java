package com.cdx.demo.service;

import com.baidu.aip.ocr.AipOcr;
import com.cdx.demo.Entity.ResponseBodyEntity;
import com.cdx.demo.util.ClientInstance;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

/**
 * Created by DX on 2018/9/17.
 */
@Service
public class PicToTextService {

    public ResponseBodyEntity PicToText(AipOcr client, String path) {
        String log_id;
        String words_result_num;
        String words_result;
        int language_no;
        String image_status;
        String idcard_type;
        String edit_tool;

        JSONObject res;
        ResponseBodyEntity responseBodyEntity = new ResponseBodyEntity();
        HashMap<String, String> options = new HashMap<String, String>();


        if (path.contains("basicGeneral")) {//=======================================
            // 传入可选参数调用接口
            options.put("language_type", "CHN_ENG");
            options.put("detect_direction", "true");
            options.put("detect_language", "true");
            //options.put("probability", "true");
            res = client.basicGeneral(path, options);

            log_id = res.get("log_id").toString();
            words_result_num = res.get("words_result_num").toString();
            words_result = res.get("words_result").toString();
            language_no = Integer.parseInt(res.get("language").toString());

            responseBodyEntity.setAll(log_id, words_result_num, words_result, language_no);
        } else if (path.contains("webImage")) {//===================================
            // 传入可选参数调用接口
            options.put("detect_direction", "true");
            options.put("detect_language", "true");
            res = client.webImage(path, options);

            log_id = res.get("log_id").toString();
            words_result_num = res.get("words_result_num").toString();
            words_result = res.get("words_result").toString();
            language_no = Integer.parseInt(res.get("language").toString());

            responseBodyEntity.setAll(log_id, words_result_num, words_result, language_no);
        } else if (path.contains("idcard")) {//=========================================
            // 传入可选参数调用接口
            options.put("detect_direction", "true");
            options.put("detect_risk", "false");
            String idCardSide;
            if (path.contains("back")) {
                idCardSide = "back";
            } else {
                idCardSide = "front";
            }
            res = client.idcard(path, idCardSide, options);

            log_id = res.get("log_id").toString();
            words_result_num = res.get("words_result_num").toString();
            words_result = res.get("words_result").toString();
            language_no = -1;
            image_status = res.get("image_status").toString();
            idcard_type = res.get("idcard_type").toString();
            edit_tool = res.get("edit_tool").toString();

            responseBodyEntity.setAll(log_id, words_result_num, words_result, language_no, image_status, idcard_type, edit_tool);
        } else if (path.contains("bankcard")) {//===============================
            // 传入可选参数调用接口
            res = client.bankcard(path, options);

            log_id = res.get("log_id").toString();
            words_result_num = "1";
            words_result = res.get("result").toString();
            language_no = -1;

            responseBodyEntity.setAll(log_id, words_result_num, words_result, language_no);
        } else if (path.contains("drivingLicense")) {//==============================================
            // 传入可选参数调用接口
            options.put("detect_direction", "true");
            res = client.drivingLicense(path, options);

            log_id = res.get("log_id").toString();
            res = new JSONObject(res.get("data"));
            words_result_num = res.get("words_result_num").toString();
            words_result = res.get("words_result").toString();
            language_no = -1;

            responseBodyEntity.setAll(log_id, words_result_num, words_result, language_no);
        } else if (path.contains("vehicleLicense")) {//==================================================
            // 传入可选参数调用接口
            options.put("detect_direction", "true");
            options.put("accuracy", "normal");
            res = client.vehicleLicense(path, options);

            log_id = res.get("log_id").toString();
            res = new JSONObject(res.get("data"));
            words_result_num = res.get("words_result_num").toString();
            words_result = res.get("words_result").toString();
            language_no = -1;

            responseBodyEntity.setAll(log_id, words_result_num, words_result, language_no);
        } else if (path.contains("plateLicense")) {//==========================================
            // 传入可选参数调用接口
            options.put("multi_detect", "true");
            res = client.plateLicense(path, options);

            log_id = res.get("log_id").toString();
            words_result_num = "1";
            words_result = res.get("words_result").toString();
            language_no = -1;

            responseBodyEntity.setAll(log_id, words_result_num, words_result, language_no);
        } else if (path.contains("businessLicense")) {//=============================================
            // 传入可选参数调用接口
            res = client.businessLicense(path, options);

            log_id = res.get("log_id").toString();
            words_result_num = res.get("words_result_num").toString();
            words_result = res.get("words_result").toString();
            language_no = -1;

            responseBodyEntity.setAll(log_id, words_result_num, words_result, language_no);
        } else if (path.contains("receipt")) {//==================================================
            // 传入可选参数调用接口
            options.put("recognize_granularity", "big");
            options.put("probability", "true");
            options.put("accuracy", "normal");
            options.put("detect_direction", "true");
            res = client.receipt(path, options);

            log_id = res.get("log_id").toString();
            words_result_num = res.get("words_result_num").toString();
            words_result = res.get("words_result").toString();
            language_no = -1;

            responseBodyEntity.setAll(log_id, words_result_num, words_result, language_no);
        } else if (path.contains("form")) {//===========================================================
            // 传入可选参数调用接口
            res = client.form(path, options);

            log_id = res.get("log_id").toString();
            words_result_num = res.get("forms_result_num").toString();
            words_result = res.get("forms_result").toString();
            language_no = -1;

            responseBodyEntity.setAll(log_id, words_result_num, words_result, language_no);
        } else {//===============================================================================
            // 传入可选参数调用接口
            options.put("detect_direction", "true");
            options.put("probability", "true");
            res = client.basicGeneral(path, options);

            log_id = res.get("log_id").toString();
            words_result_num = res.get("words_result_num").toString();
            words_result = res.get("words_result").toString();
            language_no = Integer.parseInt(res.get("language").toString());

            responseBodyEntity.setAll(log_id, words_result_num, words_result, language_no);
        }

        return responseBodyEntity;
    }

    public static void main(String[] args) {
        // 调用接口
        PicToTextService picToTextService = new PicToTextService();
        ClientInstance clientInstance = ClientInstance.getInstance();
        AipOcr client = clientInstance.getClient();
        System.out.println(picToTextService.PicToText(client, "C:\\Users\\DX\\Desktop\\picture/1.jpg").toString());
    }
}
