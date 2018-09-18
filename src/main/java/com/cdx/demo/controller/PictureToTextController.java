package com.cdx.demo.controller;

import com.cdx.demo.Entity.ResponseBodyEntity;
import com.cdx.demo.service.DeleteFileService;
import com.cdx.demo.service.PicToTextService;
import com.cdx.demo.service.RedisFileService;
import com.cdx.demo.service.UploadFileService;
import com.cdx.demo.util.ClientInstance;

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

    @GetMapping("/")
    public String main1() {
        return "main";
    }

    @RequestMapping(value = "/main", method = {RequestMethod.GET, RequestMethod.POST})
    public String main() {
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
     * 上传图片
     *
     * @param file 文件
     */
    @PostMapping(value = "/uploadFile")
    public String uploadFile(MultipartFile file,String PictureKindList, @RequestParam(value = "username", required = true) String username) throws Exception {
        if ("".equals(username) || username.isEmpty()) {
            return "main";
        }
        String name = file.getOriginalFilename();
        /*这里只列出了一些常见格式，理论上图片格式都可以*/
        if (name.contains(".jpg") || name.contains(".jpeg") || name.contains(".png") || name.contains(".gif") || name.contains(".bmp")) {
            String[] Name = name.split("\\.");
            int i = Name.length - 1;
            String newName = username + PictureKindList + Name[i];//原图片 例= cdx_basicGeneral.jpg
            String dirPath = "C:\\software\\workspace\\AipOcr\\picture/";//====================================
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

        ClientInstance clientInstance = ClientInstance.getInstance();
        String PictureFilePath = redisFileService.getFilePathFromRedis(username + "PictureFilePath");
        System.out.println("start:");
        ResponseBodyEntity responseBodyEntity = picToTextService.PicToText(clientInstance.getClient(), PictureFilePath);
        System.out.println("complete!");
        deleteFileService.delTempChild(PictureFilePath);
        return responseBodyEntity;
    }
}

