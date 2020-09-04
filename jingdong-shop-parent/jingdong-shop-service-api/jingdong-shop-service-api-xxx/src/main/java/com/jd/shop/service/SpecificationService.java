package com.jd.shop.service;

import com.alibaba.fastjson.JSONObject;
import com.jd.shop.base.Result;
import com.jd.shop.dto.SpecGroupDTO;
import com.jd.shop.dto.SpecParamDTO;
import com.jd.shop.entity.SpecGroupEntity;
import com.jd.shop.entity.SpecParamEntity;
import com.jd.shop.validate.group.JingdongOperation;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "规格接口")
public interface SpecificationService {

    @ApiOperation(value = "通过条件查询规格组")
    @GetMapping(value = "specgroup/getSpecGroupList")
    Result<List<SpecGroupEntity>> getSpecGroupList(SpecGroupDTO groupDTO);


    @ApiOperation(value = "新增规格组")
    @PostMapping(value = "specgroup/getSpecGroupSave")
    Result<JSONObject> getSpecGroupSave(@Validated({JingdongOperation.Add.class}) @RequestBody SpecGroupDTO groupDTO);


    @ApiOperation(value = "修改规格组")
    @PutMapping(value = "specgroup/getSpecGroupSave")
    Result<JSONObject> getSpecGroupEdit(@Validated({JingdongOperation.Update.class}) @RequestBody SpecGroupDTO groupDTO);


    @ApiOperation(value = "删除规格组")
    @DeleteMapping(value = "specgroup/getSpecGroupDelete")
    Result<JSONObject> getSpecGroupDelete(Integer id);



    @ApiOperation(value = "查询规格参数")
    @GetMapping(value = "specparam/getSpecParamList")
    Result<List<SpecParamEntity>> getSpecParamList(SpecParamDTO specParamDTO);


    @ApiOperation(value = "新增规格参数")
    @PostMapping(value = "specparam/getSpecParamSave")
    Result<JSONObject> getSpecParamSave(@Validated({JingdongOperation.Add.class}) @RequestBody SpecParamDTO specParamDTO);

    @ApiOperation(value = "修改规格参数")
    @PutMapping(value = "specparam/getSpecParamSave")
    Result<JSONObject> getSpecParamEdit(@Validated({JingdongOperation.Update.class}) @RequestBody SpecParamDTO specParamDTO);


    @ApiOperation(value = "删除规格参数")
    @DeleteMapping(value = "specparam/getSpecParamDel")
    Result<JSONObject> getSpecParamDel(Integer id);
}
