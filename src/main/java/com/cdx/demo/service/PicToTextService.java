package com.cdx.demo.service;

import com.baidu.aip.ocr.AipOcr;
import com.cdx.demo.Entity.ResponseBodyEntity;
import com.cdx.demo.util.ClientInstance;
import org.json.JSONObject;
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


        if (path.contains("basicGeneral")) {//================通用文字识别==========================================
            // 传入可选参数调用接口
            options.put("language_type", "CHN_ENG");
            options.put("detect_direction", "true");
            options.put("detect_language", "true");
            //options.put("probability", "true");
            res = client.basicGeneral(path, options);

            if (!res.isNull("error_code")) {
                responseBodyEntity.setAll("通用文字识别", "error:" + res.get("error_code").toString(), "1", res.get("error_msg").toString(), -1);
            } else {
                log_id = res.get("log_id").toString();
                words_result_num = res.get("words_result_num").toString();
                words_result = res.get("words_result").toString();
                language_no = Integer.parseInt(res.get("language").toString());

                responseBodyEntity.setAll("通用文字识别", log_id, words_result_num, words_result, language_no);
            }
        } else if (path.contains("webImage")) {//=============网络图片文字识别======================================
            // 传入可选参数调用接口
            options.put("detect_direction", "true");
            options.put("detect_language", "true");
            res = client.webImage(path, options);

            if (!res.isNull("error_code")) {
                responseBodyEntity.setAll("网络图片文字识别", "error:" + res.get("error_code").toString(), "1", res.get("error_msg").toString(), -1);
            } else {
                log_id = res.get("log_id").toString();
                words_result_num = res.get("words_result_num").toString();
                words_result = res.get("words_result").toString();
                language_no = Integer.parseInt(res.get("language").toString());

                responseBodyEntity.setAll("网络图片文字识别", log_id, words_result_num, words_result, language_no);
            }
        } else if (path.contains("idcard")) {//===============身份证识别============================================
            // 传入可选参数调用接口
            options.put("detect_direction", "true");
            options.put("detect_risk", "true");
            String idCardSide;
            if (path.contains("back")) {
                idCardSide = "back";
            } else {
                idCardSide = "front";
            }
            res = client.idcard(path, idCardSide, options);

            if (!res.isNull("error_code")) {
                responseBodyEntity.setAll("身份证识别", "error:" + res.get("error_code").toString(), "1", res.get("error_msg").toString(), -1);
            } else {
                log_id = res.get("log_id").toString();
                words_result_num = res.get("words_result_num").toString();
                words_result = res.get("words_result").toString();
                language_no = -1;
                image_status = res.get("image_status").toString();
                idcard_type = res.get("risk_type").toString();
                if (res.isNull("edit_tool")) {
                    edit_tool = "null";
                } else {
                    edit_tool = res.get("edit_tool").toString();
                }

                responseBodyEntity.setAll("身份证识别", log_id, words_result_num, words_result, language_no, image_status, idcard_type, edit_tool);
            }
        } else if (path.contains("bankcard")) {//=============银行卡识别============================================
            // 传入可选参数调用接口
            res = client.bankcard(path, options);

            if (!res.isNull("error_code")) {
                responseBodyEntity.setAll("银行卡识别", "error:" + res.get("error_code").toString(), "1", res.get("error_msg").toString(), -1);
            } else {
                log_id = res.get("log_id").toString();
                words_result_num = "1";
                words_result = res.get("result").toString();
                language_no = -1;

                responseBodyEntity.setAll("银行卡识别", log_id, words_result_num, words_result, language_no);
            }
        } else if (path.contains("drivingLicense")) {//=======驾驶证识别============================================
            // 传入可选参数调用接口
            options.put("detect_direction", "true");
            res = client.drivingLicense(path, options);

            if (!res.isNull("error_code")) {
                responseBodyEntity.setAll("驾驶证识别", "error:" + res.get("error_code").toString(), "1", res.get("error_msg").toString(), -1);
            } else {
                log_id = res.get("log_id").toString();
                words_result_num = res.get("words_result_num").toString();
                words_result = res.get("words_result").toString();
                language_no = -1;

                responseBodyEntity.setAll("驾驶证识别", log_id, words_result_num, words_result, language_no);
            }
        } else if (path.contains("vehicleLicense")) {//=======行驶证识别============================================
            // 传入可选参数调用接口
            options.put("detect_direction", "true");
            options.put("accuracy", "normal");
            res = client.vehicleLicense(path, options);

            if (!res.isNull("error_code")) {
                responseBodyEntity.setAll("行驶证识别", "error:" + res.get("error_code").toString(), "1", res.get("error_msg").toString(), -1);
            } else {
                log_id = res.get("log_id").toString();
                words_result_num = res.get("words_result_num").toString();
                words_result = res.get("words_result").toString();
                language_no = -1;

                responseBodyEntity.setAll("行驶证识别", log_id, words_result_num, words_result, language_no);
            }
        } else if (path.contains("plateLicense")) {//=========车牌号识别============================================
            // 传入可选参数调用接口
            options.put("multi_detect", "true");
            res = client.plateLicense(path, options);

            if (!res.isNull("error_code")) {
                responseBodyEntity.setAll("车牌号识别", "error:" + res.get("error_code").toString(), "1", res.get("error_msg").toString(), -1);
            } else {
                log_id = res.get("log_id").toString();
                words_result_num = "1";
                words_result = res.get("words_result").toString();
                language_no = -1;

                responseBodyEntity.setAll("车牌号识别", log_id, words_result_num, words_result, language_no);
            }
        } else if (path.contains("businessLicense")) {//======营业执照识别==========================================
            // 传入可选参数调用接口
            res = client.businessLicense(path, options);

            if (!res.isNull("error_code")) {
                responseBodyEntity.setAll("营业执照识别", "error:" + res.get("error_code").toString(), "1", res.get("error_msg").toString(), -1);
            } else {
                log_id = res.get("log_id").toString();
                words_result_num = res.get("words_result_num").toString();
                words_result = res.get("words_result").toString();
                language_no = -1;

                responseBodyEntity.setAll("营业执照识别", log_id, words_result_num, words_result, language_no);
            }
        } else if (path.contains("receipt")) {//==============通用票据识别==========================================
            // 传入可选参数调用接口
            options.put("recognize_granularity", "big");
            options.put("probability", "true");
            options.put("accuracy", "normal");
            options.put("detect_direction", "true");
            res = client.receipt(path, options);

            if (!res.isNull("error_code")) {
                responseBodyEntity.setAll("通用票据识别", "error:" + res.get("error_code").toString(), "1", res.get("error_msg").toString(), -1);
            } else {
                log_id = res.get("log_id").toString();
                words_result_num = res.get("words_result_num").toString();
                words_result = res.get("words_result").toString();
                language_no = -1;

                responseBodyEntity.setAll("通用票据识别", log_id, words_result_num, words_result, language_no);
            }
        } else if (path.contains("form")) {//=================表格文字识别同步接口==================================
            // 传入可选参数调用接口
            res = client.form(path, options);

            if (!res.isNull("error_code")) {
                responseBodyEntity.setAll("表格文字识别", "error:" + res.get("error_code").toString(), "1", res.get("error_msg").toString(), -1);
            } else {
                log_id = res.get("log_id").toString();
                words_result_num = res.get("forms_result_num").toString();
                words_result = res.get("forms_result").toString();
                language_no = -1;

                responseBodyEntity.setAll("表格文字识别", log_id, words_result_num, words_result, language_no);
            }
        } else {//============================================通用文字识别（高精度版）==============================
            // 传入可选参数调用接口
            options.put("language_type", "CHN_ENG");
            options.put("detect_direction", "true");
            options.put("detect_language", "true");
            //options.put("probability", "true");
            res = client.basicAccurateGeneral(path, options);

            if (!res.isNull("error_code")) {
                responseBodyEntity.setAll("其他", "error:" + res.get("error_code").toString(), "1", res.get("error_msg").toString(), -1);
            } else {
                log_id = res.get("log_id").toString();
                words_result_num = res.get("words_result_num").toString();
                words_result = res.get("words_result").toString();
                language_no = 5;

                responseBodyEntity.setAll("其他", log_id, words_result_num, words_result, language_no);
            }
        }

        return responseBodyEntity;
    }

   /* public static void main(String[] args) {
        // 调用接口
        PicToTextService picToTextService = new PicToTextService();
        ClientInstance clientInstance = ClientInstance.getInstance();
        AipOcr client = clientInstance.getClient();
        System.out.println(picToTextService.PicToText(client, "C:\\Users\\DX\\Desktop\\picture/form.jpg").toString());
    }*/
}
