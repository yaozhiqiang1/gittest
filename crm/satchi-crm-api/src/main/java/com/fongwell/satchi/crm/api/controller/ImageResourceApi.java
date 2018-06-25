package com.fongwell.satchi.crm.api.controller;

import com.fongwell.base.rest.Payload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.UnsupportedEncodingException;

/**
 * Created by roman on 18-3-30.
 */
@RestController
@RequestMapping("/resource")
public class ImageResourceApi {

    Logger LOGGER = LoggerFactory.getLogger(ImageResourceApi.class);

    public static final String IMAGE_URL = "http://i1.sinaimg.cn/fashion/cr/2014/0311/786348701.jpg";

    @PostMapping("/image")
    public Payload upload(@RequestParam MultipartFile file) throws UnsupportedEncodingException {
        String fileName;
        if(file != null){
            fileName = file.getOriginalFilename();
        }else{
            throw new IllegalArgumentException("file upload exception !");
        }
        LOGGER.debug("upload image :"+new String(fileName.getBytes("ISO8859-1"),"UTF-8"));
        return new Payload(IMAGE_URL);
    }

    @PostMapping("")
    public Payload batchUpload(@RequestParam MultipartFile file) throws UnsupportedEncodingException {
        String fileName;
        if(file != null){
            fileName = file.getOriginalFilename();
        }else{
            throw new IllegalArgumentException("file upload exception !");
        }
        LOGGER.debug("upload image :"+new String(fileName.getBytes("ISO8859-1"),"UTF-8"));
        return new Payload(IMAGE_URL);
    }
}
