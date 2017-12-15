package com.cookie.controller;

import com.mao.common.converter.OrderForm2OrderDTOConverter;
import com.mao.common.enums.ResultEnum;
import com.mao.common.utils.Result;
import com.mao.common.utils.ResultUtil;
import com.mao.dto.OrderDTO;
import com.mao.exception.SellException;
import com.mao.form.OrderForm;
import com.mao.service.BuyerService;
import com.mao.service.OrderServer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by myseital  on 2017/11/20.
 */
@RestController
@RequestMapping(value = "/buyer/order")
@Slf4j
public class BuyerOrderController {

    @Autowired
    private OrderServer orderServer;

    @Autowired
    private BuyerService buyerService;

    @PostMapping(value = "/create")
    public Result create(@Valid OrderForm orderForm, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            log.error("[创建订单] 参数不正确，orderForm={}", orderForm);
            throw new SellException(ResultEnum.PARAM_ERROR.getCode(), bindingResult.getFieldError().getDefaultMessage());
        }

        OrderDTO orderDTO = OrderForm2OrderDTOConverter.convert(orderForm);
        if (CollectionUtils.isEmpty(orderDTO.getOrderDetailList())) {
            log.error("[创建订单] 购物车不能为空");
            throw new SellException(ResultEnum.CART_EMPTY);
        }

        OrderDTO createResult = orderServer.create(orderDTO);

        Map<String, String> map = new HashMap<>();
        map.put("orderId", createResult.getOrderId());

        return ResultUtil.success(map);
    }

    @GetMapping(value = "/list")
    public Result list(@RequestParam("openid") String openid, @RequestParam(value = "page", defaultValue = "0") Integer page,
                       @RequestParam(value = "size", defaultValue = "10") Integer size) {
        if (StringUtils.isEmpty(openid)) {
            log.error("[查询订单列表] openid为空");
            throw new SellException(ResultEnum.PARAM_ERROR);
        }
        PageRequest request = new PageRequest(page, size);
        Page<OrderDTO> orderDTOPage = orderServer.findList(openid, request);

        // 修改返回的时间值 由毫秒到秒 date对应的字段添加@JsonSerialize(using = Date2LongSerializer.class)
        // 返回的值为null 则不返回 @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)被废弃
        // 推荐使用 @JsonInclude(JsonInclude.Include.NON_NULL)

        return ResultUtil.success(orderDTOPage.getContent());
    }

    @GetMapping(value = "/detail")
    public Result detail(@RequestParam("openid") String openid, @RequestParam("orderId") String orderId) {
        /* 不安全做法,改进
        OrderDTO orderDTO = orderServer.findOne(orderId);
        if (!orderDTO.getBuyerOpenid().equals(openid)) {

        }
        return ResultUtil.success(orderDTO);*/

        return ResultUtil.success(buyerService.findOrderOne(openid, orderId));
    }

    @PostMapping(value = "/cancel")
    public Result cancel(@RequestParam("openid") String openid, @RequestParam("orderId") String orderId){
        /* 不安全做法,改进
        OrderDTO orderDTO = orderServer.findOne(orderId);
        orderServer.cancel(orderDTO);
        return ResultUtil.success(orderDTO);*/

        buyerService.cancelOrder(openid, orderId);
        return ResultUtil.success();
    }
}
