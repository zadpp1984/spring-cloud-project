package com.wzy.user.service;

import com.codingapi.txlcn.tc.annotation.DTXPropagation;
import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import com.codingapi.txlcn.tc.annotation.TxcTransaction;
import com.wzy.common.util.ServiceResponse;
import com.wzy.mapper.UserMapper;
import com.wzy.pojo.User;
import com.wzy.redis.RedisService;
import com.wzy.user.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;
import java.math.BigDecimal;

@RestController
@Api(value = "订单接口")
@RefreshScope
public class UserServiceImpl implements UserService {

    @Resource
    UserMapper userMapper;

    @Autowired
    RedisService redisService;

    @Override
    @ApiOperation(value = "测试订单信息", notes = "就是测试接口")
    public ServiceResponse hi() {
        redisService.set("hhha1", "haha");
        redisService.get("hhha1");
        return ServiceResponse.getSUCCESS();
    }

    @Override
    @ApiOperation(value = "修改用户信息 测试分布式事务", notes = "就是测试接口")
    @TxcTransaction(propagation = DTXPropagation.SUPPORTS)
    @Transactional
    public ServiceResponse updateUser() {
        User user = new User();
        user.setId(1);
        user.setBalance(new BigDecimal("20"));
        user.setBalance(user.getBalance().add(new BigDecimal(("1"))));
        userMapper.updateByPrimaryKeySelective(user);
        return ServiceResponse.getSUCCESS();
    }
}
