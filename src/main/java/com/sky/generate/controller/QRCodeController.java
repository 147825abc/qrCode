package com.sky.generate.controller;

import com.sky.generate.service.QRCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.awt.*;

@RestController
public class QRCodeController {
    @Autowired
    private QRCodeService service;

    @GetMapping(value = "get",produces = MediaType.APPLICATION_JSON_VALUE)
    public String get(String content, @RequestParam(defaultValue = "300") int size){
        try{
            String base64=service.generateQRCodeBase64(content,size);
            return "data:image/png;base64,"+base64;
        }catch (Exception e){
            e.printStackTrace();
            return "出现错误,生成失败";
        }
    }
}
