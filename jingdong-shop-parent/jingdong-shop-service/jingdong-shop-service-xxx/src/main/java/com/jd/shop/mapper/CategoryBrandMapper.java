package com.jd.shop.mapper;

import com.jd.shop.entity.CategoryBrandEntity;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.special.InsertListMapper;

public interface CategoryBrandMapper extends Mapper<CategoryBrandEntity>, InsertListMapper<CategoryBrandEntity> {
}
