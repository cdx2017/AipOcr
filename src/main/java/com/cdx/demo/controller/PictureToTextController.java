package com.cdx.demo.controller;

import com.cdx.demo.Entity.ResponseBodyEntity;
import com.cdx.demo.service.DeleteFileService;
import com.cdx.demo.service.PicToTextService;
import com.cdx.demo.service.RedisFileService;
import com.cdx.demo.service.UploadFileService;
import com.cdx.demo.util.ClientInstance;

import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;

/**
 * Created by DX on 2018/9/17.
 */
@Controller
public class PictureToTextController {
    @Autowired
    private UploadFileService uploadFileService;
    @Autowired
    private RedisFileService redisFileService;
    @Autowired
    private PicToTextService picToTextService;
    @Autowired
    private DeleteFileService deleteFileService;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @RequestMapping(value = "/main", method = {RequestMethod.GET, RequestMethod.POST})
    public String main(@RequestParam(value = "username", required = true) String username) {
        if ("".equals(username) || username.isEmpty()) {
            return "login";
        }
        return "main";
    }

    @GetMapping("/upload")
    public String upload() {
        return "upload";
    }

    @GetMapping("/fail")
    public String fail() {
        return "fail";
    }

    @GetMapping("/startChangeToText")
    public String startChangeToText() {
        return "startChangeToText";
    }

    /**
     * 上传音频
     *
     * @param file 文件
     */
    @PostMapping(value = "/uploadFile")
    public String uploadFile(MultipartFile file, @RequestParam(value = "username", required = true) String username) throws Exception {
        if ("".equals(username) || username.isEmpty()) {
            return "login";
        }
        String name = file.getOriginalFilename();
        /*这里只列出了一些常见格式，理论上音频视频格式都可以*/
        if (name.contains(".jpg") || name.contains(".jpeg") || name.contains(".png") || name.contains(".gif") || name.contains(".bmp")) {
            String[] Name = name.split("\\.");
            int i = Name.length - 1;
            String newName = username + "_origin." + Name[i];//原音频 例= cdx_origin.jpg
            String dirPath = "C:\\software\\workspace\\AipOcr\\picture/";
            if (uploadFileService.uploadFile(file.getInputStream(), newName, dirPath)) {
                String PictureFilePath = dirPath + newName;//原图片路径
                redisFileService.saveFilePathToRedis(username + "PictureFilePath", PictureFilePath);/*记录文件位置*/
            }
            return "startChangeToText";
        }
        return "/fail";
    }

    @PostMapping(value = "/PictureToText")
    @ResponseBody
    public Object PictureToText(String username) {

        HashMap<String, String> options = new HashMap<String, String>();
        options.put("language_type", "CHN_ENG");
        options.put("detect_direction", "true");
        options.put("detect_language", "true");
        //options.put("probability", "true");

        ClientInstance clientInstance = ClientInstance.getInstance();
        String PictureFilePath = redisFileService.getFilePathFromRedis(username + "PictureFilePath");
        System.out.println("start:");
        ResponseBodyEntity responseBodyEntity = picToTextService.PicToText(clientInstance.getClient(), PictureFilePath, options);
        System.out.println("complete!");
        deleteFileService.delTempChild(PictureFilePath);
        return responseBodyEntity;
    }
}

