package com.blockchain.controller;


import com.blockchain.config.uploadfile.UploadTodist;
import com.blockchain.pojo.ResBean;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Random;

@RestController
public class FileControl {

    @ApiOperation("上传文件")
    @PostMapping("/fileupload")
    public ResBean uploadFile(@RequestBody MultipartFile file) {
        UploadTodist uploadTodist = new UploadTodist();
        String upload = uploadTodist.upload(file);
         if(upload.equals("上传失败")){
            return ResBean.error("上传失败");
        }else if(upload.equals("图片为空")) {
            return ResBean.error("图片为空");
        }else{
             return ResBean.success("上传成功");
         }
    }
}
