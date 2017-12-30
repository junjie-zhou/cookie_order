package com.cookie.service.impl;

import com.cookie.common.config.FTPConfig;
import com.cookie.common.config.NginxConfig;
import com.cookie.service.ProductIcomService;
import com.cookie.utils.FTPUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by yr on 2017/12/30.
 */
@Service
@Slf4j
public class ProductIcomServiceImpl implements ProductIcomService {

    @Autowired
    private FTPConfig ftpConfig;

    @Autowired
    private NginxConfig nginxConfig;

    @Override
    public Map<String, Object> uploadPicture(MultipartFile uploadPricture) {


        Map<String,Object> resultMap=new HashMap<String,Object>();
        //生成一个文件名
        //获取原始文件名
        String originalFilename = uploadPricture.getOriginalFilename();
        //生成新文件名
        String name = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date())
                    +originalFilename.substring(originalFilename.lastIndexOf("."));
        boolean uploadFile=false;
        try {
            uploadFile = FTPUtil.uploadFile(ftpConfig, "/", name, uploadPricture.getInputStream());
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("服务器繁忙，上传图片失败");
        }
        if(!uploadFile){
            throw new RuntimeException("服务器繁忙，上传图片失败");
        }
        resultMap.put("url", nginxConfig.getUrl()+"/"+name);
        return resultMap;
    }
}
