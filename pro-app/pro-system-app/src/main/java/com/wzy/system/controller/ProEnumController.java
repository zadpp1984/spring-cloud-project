package com.wzy.system.controller;
import com.wzy.common.method.ProParameter;
import com.wzy.common.util.DateUtil;
import com.wzy.common.util.ServiceResponse;
import com.wzy.redis.RedisService;
import com.wzy.system.IProEnumService;
import com.wzy.system.dto.ProEnum;
import com.wzy.system.request.ProEnumRequest;
import com.wzy.system.vo.ProEnumVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
    * 枚举表 
    * </p>
 *
 * @author 王振宇
 * @since 2020-05-02
 */
@RestController
@RequestMapping(value = "proEnum")
@Api(value = "ProEnumController", description = "枚举表 ")
public class ProEnumController {

    @Autowired
    IProEnumService proEnumService;

    @Autowired
    RedisService redisService;

    @PostMapping(value = "/getPageList")
    @ApiOperation(value = "分页查询列表")
    public ServiceResponse<List<ProEnumVo>> getPageList(@RequestBody ProEnumRequest request){
        return new ServiceResponse<List<ProEnumVo>>()
                .run(serviceResponse -> {

                    // 获取调用服务返回结果 通过返回结果 进行业务判断 以及 手动控制 分布式事务回滚
                    ServiceResponse<List<ProEnum>> response = proEnumService.getPageList(new ProParameter<>(request)
                            .setRequestPage(request));

                    // 获取调用服务状态
                    response.checkState();

                    // 获取返回的分页信息
                    response.copyPage(serviceResponse);

                    // 获取服务返回的结果
                    List<ProEnum> resultList = response.getObj();

                    // 组装vo 返回数据 也可以不组装直接返回原始数据
                    List<ProEnumVo> returnList = resultList.stream()
                            .map(proEnum -> {
                                ProEnumVo proEnumvo = new ProEnumVo();
                                BeanUtils.copyProperties(proEnum,proEnumvo);
                                proEnumvo.setCreateTime(DateUtil.getyyMMddHHmmss(proEnum.getCreateTime()));
                                // vo.set 格式化一些特定的字段比如时间类型 自定义多种返回类型 应对视图层的需要
                                return proEnumvo;
                            })
                            .collect(Collectors.toList());

                    return returnList;
                })
                .exec();
    }

    @PostMapping(value = "/get")
    @ApiOperation(value = "获取单条信息")
    public ServiceResponse<ProEnumVo> get(@RequestBody ProEnumRequest request){
        return new ServiceResponse<ProEnumVo>()
                .run(serviceResponse -> {

                    // 获取调用服务返回结果 通过返回结果 进行业务判断 以及 手动控制 分布式事务回滚
                    ServiceResponse<ProEnum> response = proEnumService.get(new ProParameter<>(request));

                    // 获取调用服务状态
                    response.checkState();

                    // 组装返回的vo
                    ProEnum proEnum = response.getObj();
                    ProEnumVo proEnumVo = new ProEnumVo();
                    BeanUtils.copyProperties(proEnum,proEnumVo);
                    return proEnumVo;
                })
                .exec();
    }

    @PostMapping(value = "/getList")
    @ApiOperation(value = "获取单条信息")
    public ServiceResponse<List<ProEnumVo> > getList(@RequestBody ProEnumRequest request){
        return new ServiceResponse<List<ProEnumVo> >()
                .run(serviceResponse -> {

                    // 获取调用服务返回结果 通过返回结果 进行业务判断 以及 手动控制 分布式事务回滚
                    ServiceResponse<List<ProEnum>> response = proEnumService.getList(new ProParameter<>(request));

                    // 获取调用服务状态
                    response.checkState();

                    // 获取服务返回的结果
                    List<ProEnum> resultList = response.getObj();

                    // 组装vo 返回数据 也可以不组装直接返回原始数据
                    List<ProEnumVo> returnList = resultList.stream()
                            .map(proEnum -> {
                                ProEnumVo proEnumvo = new ProEnumVo();
                                BeanUtils.copyProperties(proEnum,proEnumvo);
                                proEnumvo.setCreateTime(DateUtil.getyyMMddHHmmss(proEnum.getCreateTime()));
                                // vo.set 格式化一些特定的字段比如时间类型 自定义多种返回类型 应对视图层的需要
                                return proEnumvo;
                            })
                            .collect(Collectors.toList());

                    return returnList;
                })
                .exec();
    }

    @PostMapping(value = "/save")
    @ApiOperation(value = "保存")
    public ServiceResponse<ProEnumVo> save(@RequestBody ProEnumRequest request){
        return new ServiceResponse<ProEnumVo>()
                .run(serviceResponse -> {

                    // 获取返回结果 包括数据库插入id
                    ProEnum proEnum = proEnumService.save(new ProParameter<>(request)).getObj();
                    ProEnumVo proEnumVo = new ProEnumVo();
                    BeanUtils.copyProperties(proEnum,proEnumVo);
                    return proEnumVo;
                })
                .exec();
    }

    @PostMapping(value = "/idsDelete")
    @ApiOperation(value = "批量删除")
    public ServiceResponse<Integer> idsDelete(@RequestBody ProEnumRequest request){
        return new ServiceResponse<Integer>()
                .run(serviceResponse -> {

                    // 标记通过enumid删除
                    request.setEnumId(1);

                    // 获取调用服务返回结果 通过返回结果 进行业务判断 以及 手动控制 分布式事务回滚
                    ServiceResponse<Integer> response = proEnumService.idsDelete(new ProParameter<>(request));

                    // 获取调用服务状态
                    response.checkState();

                    return response.getObj();
                })
                .exec();
    }

    @PostMapping(value = "/delete")
    @ApiOperation(value = "批量删除")
    public ServiceResponse<Integer> delete(@RequestBody ProEnumRequest request){
        return new ServiceResponse<Integer>()
                .run(serviceResponse -> {

                    // 获取调用服务返回结果 通过返回结果 进行业务判断 以及 手动控制 分布式事务回滚
                    ServiceResponse<Integer> response = proEnumService.delete(new ProParameter<>(request));

                    // 获取调用服务状态
                    response.checkState();

                    return response.getObj();
                })
                .exec();
    }

    @PostMapping(value = "/update")
    @ApiOperation(value = "修改")
    public ServiceResponse<Integer> update(@RequestBody ProEnumRequest request){
        return new ServiceResponse<Integer>()
                .run(serviceResponse -> {

                    // 获取调用服务返回结果 通过返回结果 进行业务判断 以及 手动控制 分布式事务回滚
                    ServiceResponse<Integer> response = proEnumService.update(new ProParameter<>(request));

                    // 获取调用服务状态
                    response.checkState();

                    return response.getObj();
                })
                .exec();
    }
}
