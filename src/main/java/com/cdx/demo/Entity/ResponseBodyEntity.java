package com.cdx.demo.Entity;

import lombok.Data;
import org.json.JSONArray;

/**
 * Created by DX on 2018/9/17.
 */
@Data
public class ResponseBodyEntity {
    private String log_id;
    private String words_result_num;
    private String words_result;
    private String language;

    public ResponseBodyEntity() {
    }

    public ResponseBodyEntity(String log_id, String words_result_num, String words_result, int language_no) {
        this.log_id = log_id;
        this.words_result_num = words_result_num;
        this.words_result = words_result;
        switch (language_no) {
            case -1:
                this.language = "Chinese";
                break;
            case 0:
                this.language = "English";
                break;
            case 1:
                this.language = "Japanese";
                break;
            case 2:
                this.language = "Korean";
                break;
            default:
                this.language = "other";
                break;
        }

    }
}
