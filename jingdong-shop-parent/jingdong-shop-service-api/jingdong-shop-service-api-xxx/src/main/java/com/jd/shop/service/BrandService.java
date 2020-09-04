package com.jd.shop.service;

import com.github.pagehelper.PageInfo;
import com.google.gson.JsonObject;
import com.jd.shop.base.Result;
import com.jd.shop.dto.BrandDTO;
import com.jd.shop.entity.BrandEntity;
import com.jd.shop.validate.group.JingdongOperation;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


/**
 * @ClassName BrandService
 * @Description: TODO
 * @Author zhangfeiyang
 * @Date 2020/8/31
 * @Version V1.0
 **/
@Api(tags = "品牌接口")
public interface BrandService {

    @ApiOperation(value = "获取品牌")
    @GetMapping(value = "brand/list")
    Result<PageInfo<BrandEntity>> getBrandInfo(BrandDTO brandDTO);

    @ApiOperation(value = "新增品牌")
    @PostMapping(value = "brand/save")
    Result<JsonObject> getBrandSave(@Validated({JingdongOperation.Add.class}) @RequestBody BrandDTO brandDTO);


    @ApiOperation(value = "修改品牌")
    @PutMapping(value = "brand/save")
    Result<JsonObject> getBrandUp(@Validated({JingdongOperation.Update.class}) @RequestBody BrandDTO brandDTO);

    @ApiOperation(value="删除品牌")
    @DeleteMapping(value = "brand/delete")
    Result<JsonObject> getDelete(Integer id);
}
