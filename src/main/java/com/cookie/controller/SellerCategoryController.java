package com.cookie.controller;

import com.cookie.dto.ProductCategoryDTO;
import com.cookie.exception.SellException;
import com.cookie.form.CategoryForm;
import com.cookie.pojo.ProductCategory;
import com.cookie.service.ProductCategoryService;
import com.cookie.utils.KeyUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by myseital  on 2017/11/23.
 */
@Controller
@Slf4j
@RequestMapping("/seller/category")
public class SellerCategoryController {

    @Autowired
    private ProductCategoryService productCategoryService;

    @GetMapping("/list")
    public ModelAndView list(Map<String, Object> map) {
        List<ProductCategoryDTO> categoryList = productCategoryService.findAll();
        map.put("categoryList", categoryList);

        return new ModelAndView("category/list", map);
    }

    @GetMapping("/index")
    public ModelAndView index(@RequestParam(value = "categoryId", required = false) Integer categoryId,
                              Map<String, Object> map) {
        if (categoryId != null) {
            ProductCategory productCategory=new ProductCategory();
            productCategory.setCategoryId(categoryId);
            ProductCategoryDTO category = productCategoryService.findOne(productCategory);
            map.put("category", category);
        }

        return new ModelAndView("category/index", map);
    }


    @PostMapping("/save")
    public ModelAndView save(@Valid CategoryForm categoryForm, BindingResult bindingResult,
                             Map<String, Object> map) {
        if (bindingResult.hasErrors()) {
            map.put("msg", bindingResult.getFieldError().getDefaultMessage());
            map.put("url", "/sell/seller/category/list");
            return new ModelAndView("common/error", map);
        }

        try {
            ProductCategoryDTO productCategoryDTO = new ProductCategoryDTO();
            ProductCategory productCategory=new ProductCategory();
            Date date=null;
            if (!StringUtils.isEmpty(categoryForm.getCategoryId())) {
                productCategory=new ProductCategory();
                productCategory.setCategoryId(categoryForm.getCategoryId());
                productCategoryDTO = productCategoryService.findOne(productCategory);
                DateFormat fmt =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                date = fmt.parse(productCategoryDTO.getCreateTime());
            }
            BeanUtils.copyProperties(categoryForm, productCategoryDTO);
            BeanUtils.copyProperties(productCategoryDTO, productCategory);
            productCategory.setCreateTime(date);
            productCategoryService.save(productCategory);
        } catch (Exception e) {
            map.put("msg", e.getMessage());
            map.put("url", "/sell/seller/category/list");
            System.out.println(e.getMessage());
            return new ModelAndView("common/error", map);
        }

        map.put("url", "/sell/seller/category/list");

        return new ModelAndView("common/success",map);
    }
}
