package com.cookie.controller;

import com.cookie.dto.ProductCategoryDTO;
import com.cookie.dto.ProductInfoDTO;
import com.cookie.exception.SellException;
import com.cookie.form.ProductForm;
import com.cookie.pojo.ProductInfo;
import com.cookie.service.ProductCategoryService;
import com.cookie.service.ProductIcomService;
import com.cookie.service.ProductInfoService;
import com.cookie.utils.KeyUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by myseital  on 2017/11/22.
 */
@Controller
@Slf4j
@RequestMapping("/seller/product")
public class SellerProductController {

    @Autowired
    private ProductInfoService productInfoService;

    @Autowired
    private ProductCategoryService productCategoryService;

    @Autowired
    private ProductIcomService productIcomService;

    @GetMapping("/list")
    public ModelAndView List(@RequestParam(value = "page", defaultValue = "1") Integer page,
                             @RequestParam(value = "size", defaultValue = "10") Integer size) {
        Page request=new Page(page-1,size);
        PageInfo<ProductInfoDTO> productInfoPage = productInfoService.findAll(request);
        Map<String, Object> map = new HashMap();
        map.put("productInfoPage", productInfoPage);
        map.put("currentPage", page);
        map.put("size", size);

        return new ModelAndView("product/list", map);
    }

    @GetMapping("/off_sale")
    public ModelAndView off_sale(@RequestParam(value = "productId") String productId,
                                 Map<String, Object> map) {
        map.put("url", "/sell/seller/product/list");
        try {
            ProductInfo productInfo=new ProductInfo();
            productInfo.setProductId(productId);
            productInfoService.offSale(productInfo);
        } catch (SellException e) {
            map.put("msg", e.getMessage());

            return new ModelAndView("common/error", map);
        }

        return new ModelAndView("common/success", map);
    }

    @GetMapping("/on_sale")
    public ModelAndView on_sale(@RequestParam(value = "productId") String productId,
                                Map<String, Object> map) {
        map.put("url", "/sell/seller/product/list");
        try {
            ProductInfo productInfo=new ProductInfo();
            productInfo.setProductId(productId);
            productInfoService.onSale(productInfo);
        } catch (SellException e) {
            map.put("msg", e.getMessage());

            return new ModelAndView("common/error", map);
        }

        return new ModelAndView("common/success", map);
    }

    @GetMapping("/index")
    public ModelAndView index(@RequestParam(value = "productId", required = false) String productId,
                              Map<String, Object> map) {
        if (!StringUtils.isEmpty(productId)) {
            ProductInfo productInfo=new ProductInfo();
            productInfo.setProductId(productId);
            ProductInfoDTO productInfoDTO = productInfoService.findOne(productInfo);
            map.put("productInfo", productInfoDTO);
        }

        //查询所有的类目
        List<ProductCategoryDTO> productCategoryList = productCategoryService.findAll();
        map.put("categoryList", productCategoryList);

        return new ModelAndView("product/index", map);
    }

    @ResponseBody
    @PostMapping("/save")
    public ModelAndView save(@Valid ProductForm productForm, BindingResult bindingResult,
                             Map<String, Object> map) {
        if (bindingResult.hasErrors()) {
            map.put("msg", bindingResult.getFieldError().getDefaultMessage());
            map.put("url", "/sell/seller/product/index");

            return new ModelAndView("common/error", map);
        }
        ProductInfoDTO productInfoDTO = new ProductInfoDTO();
        try {
            ProductInfo productInfo=new ProductInfo();
            Date date=null;
            if (!StringUtils.isEmpty(productForm.getProductId())) {
                productInfo.setProductId(productForm.getProductId());
                productInfoDTO = productInfoService.findOne(productInfo);
                DateFormat fmt =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                date = fmt.parse(productInfoDTO.getCreateTime());
            } else {
                productForm.setProductId(KeyUtil.genUniqueKey());
            }

            BeanUtils.copyProperties(productForm,productInfoDTO);
            BeanUtils.copyProperties(productInfoDTO,productInfo);
            productInfo.setCreateTime(date);
            productInfoService.save(productInfo);
        } catch (Exception e) {
            map.put("msg", e.getMessage());
            map.put("url", "/sell/seller/product/index");
            return new ModelAndView("common/error", map);
        }
        map.put("url", "/sell/seller/product/list");

        return new ModelAndView("common/success",map);
    }

    @GetMapping("/delete")
    public ModelAndView delete(@RequestParam(value = "productId") String productId,
                               Map<String, Object> map){

        map.put("url", "/sell/seller/product/list");

        try {
            ProductInfo productInfo=new ProductInfo();
            productInfo.setProductId(productId);
            productInfoService.delete(productInfo);
        } catch (SellException e) {
            map.put("msg", e.getMessage());

            return new ModelAndView("common/error", map);
        }

        return new ModelAndView("common/success",map);
    }

    /**
     * 使用Ajax异步上传图片
     *
     * @param productIconUp 封装图片对象
     * @param request
     * @param response
     * @throws IOException
     * @throws IllegalStateException
     */
    @PostMapping("/uploadPic")
    public void uploadPic(MultipartFile productIconUp, HttpServletRequest request, HttpServletResponse response) throws IllegalStateException, IOException {

        if(!StringUtils.isEmpty(productIconUp)) {
            // 将相对路径写回（json格式）
            Map<String, Object> map=productIcomService.uploadPicture(productIconUp);
            // 设置响应数据的类型json
            response.setContentType("application/json; charset=utf-8");
            // 写回
            response.getWriter().write(new Gson().toJson(map));

        }
    }

}
