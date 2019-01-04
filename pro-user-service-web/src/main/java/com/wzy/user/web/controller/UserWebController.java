package com.wzy.user.web.controller;

import com.alibaba.fastjson.JSON;
import com.wzy.redis.RedisService;
import com.wzy.user.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserWebController {

       @Autowired
    UserService userService;

    @Autowired
    RedisService redisService;

    @ApiOperation(value = "测试订单接口", notes = "测试订单的web接口")
    @RequestMapping(value = "/hi", method = RequestMethod.GET)
    public String getOrder() {
        redisService.set("hhhk", "234");
        System.out.println(redisService.get("hhhk"));
        return userService.hi();
    }

    @ApiOperation(value = "查询所有用户信息", notes = "测试订单的web接口")
    @RequestMapping(value = "/admin/getUserList", method = RequestMethod.GET)
    public String getUserList() {
        return JSON.toJSONString(userService.getUserList());
    }
}
