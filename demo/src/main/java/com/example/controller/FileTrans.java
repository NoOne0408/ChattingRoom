package com.example.controller;

import com.alibaba.fastjson.JSON;
import com.example.POJO.Websocketmessage;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@RestController
@RequestMapping("/filetrans")
public class FileTrans {
    public final static String UPLOAD_PATH_PREFIX = "static/File/";
    @RequestMapping(value = "/upload",method = RequestMethod.POST)
    public String upload(@RequestParam(value = "file") MultipartFile file) throws RuntimeException {

        if (file.isEmpty()) {
            Websocketmessage websocketmessage = new Websocketmessage();
            websocketmessage.setMessagetype("error");
            websocketmessage.setMessage("传输的文件为空");
            return JSON.toJSONString(websocketmessage);
        }
        String fileName = file.getOriginalFilename();  // 文件名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));  // 后缀名
        String filePath = System.getProperty("user.dir")+"/demo/src/main/resources/static//temp-rainy/"; // 上传后的路径
        fileName = UUID.randomUUID() + suffixName; // 新文件名
        File dest = new File(filePath + fileName);
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        try {
            file.transferTo(dest);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String filename = System.getProperty("user.dir")+"/demo/src/main/resources/static//temp-rainy/" + fileName;
        System.out.println(filename);

        return "success";
    }
}
