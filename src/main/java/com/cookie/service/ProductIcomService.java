package com.cookie.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * Created by yr on 2017/12/30.
 */
public interface ProductIcomService {

    /**
     * 图片上传
     * @param uploadPricture
     * @return
     */
    Map<String,Object> uploadPicture(MultipartFile uploadPricture);

}
