package com.jd.shop.service;

import com.alibaba.fastjson.JSONObject;
import com.jd.shop.base.Result;
import com.jd.shop.entity.CategoryEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName CategoryService
 * @Description: TODO
 * @Author zhangfeiyang
 * @Date 2020/8/27
 * @Version V1.0
 **/
@Api(tags = "商品分类接口")
public interface CategoryService {

    @ApiOperation(value = "通过查询商品分类")
    @GetMapping(value = "category/list")
    Result<List<CategoryEntity>> getCategoryByPid(Integer pid);

    @ApiOperation(value = "新增商品分类")
    @PostMapping(value = "category/save")
    Result<JSONObject> saveCategory(@RequestBody CategoryEntity categoryEntity);

    @ApiOperation(value = "修改商品分类")
    @PutMapping(value = "category/edit")
    Result<JSONObject> updateCategory(@RequestBody CategoryEntity categoryEntity);

    @ApiOperation(value = "删除商品分类")
    @DeleteMapping(value = "category/delCategory")
    Result<JSONObject> delCategory(Integer id);
}
