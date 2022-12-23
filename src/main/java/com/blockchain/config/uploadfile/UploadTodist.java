package com.blockchain.config.uploadfile;


import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Random;

public class UploadTodist {
    public String upload(MultipartFile file){
        if (file.isEmpty()) {
            return("图片为空");
        }
        String originalFilename = file.getOriginalFilename();
        String filename = ""+System.currentTimeMillis()+""+ new Random().nextInt()+"."+originalFilename.substring(originalFilename.lastIndexOf(".")+1);
        String path = "D:\\上传图片\\";
        File destination = new File(path+filename);
        if(!destination.getParentFile().exists())
            destination.mkdirs();
        try {
            file.transferTo(destination);
        }catch (Exception e){
            e.printStackTrace();
        }
        if (destination.exists()) {
            return  filename;
        }else{
            return ("上传失败");
        }
    }
}
