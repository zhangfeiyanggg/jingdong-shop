package com.jd.shop.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.jd.shop.mapper.CategoryMapper;
import com.jd.shop.base.BaseApiService;
import com.jd.shop.base.Result;
import com.jd.shop.entity.CategoryEntity;
import com.jd.shop.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RestController;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @ClassName CategoryServiceImpl
 * @Description: TODO
 * @Author zhangfeiyang
 * @Date 2020/8/27
 * @Version V1.0
 **/
@RestController
public class CategoryServiceImpl extends BaseApiService implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;


    @Override
    public Result<List<CategoryEntity>> getCategoryByPid(Integer pid) {

        CategoryEntity categoryEntity = new CategoryEntity();

        categoryEntity.setParentId(pid);

        List<CategoryEntity> list = categoryMapper.select(categoryEntity);

        return this.setResultSuccess(list);
    }

    @Override
    @Transactional
    public Result<JSONObject> saveCategory(CategoryEntity categoryEntity) {

        CategoryEntity parentEntity = new CategoryEntity();
        //新增时获取到父类目id为0  即不是父级目录
        parentEntity.setId(categoryEntity.getParentId());
        //把新增的父类目id的状态0转换成1,让他成为父级目录
        parentEntity.setIsParent(1);
        //修改父类目状态
        categoryMapper.updateByPrimaryKeySelective(parentEntity);
        categoryMapper.insertSelective(categoryEntity);
        return this.setResultSuccess();
    }

    @Override
    @Transactional
    public Result<JSONObject> updateCategory(CategoryEntity categoryEntity) {

        categoryMapper.updateByPrimaryKeySelective(categoryEntity);

        return this.setResultSuccess();
    }

    @Override
    @Transactional
    public Result<JSONObject> delCategory(Integer id) {


        //通过当前id查询分类信息
        CategoryEntity categoryEntity = categoryMapper.selectByPrimaryKey(id);
        //判断是否有数据(安全)
        if(categoryEntity.getId() == null){
            return this.setResultError("当前id不存在");
        }

        //判断当前节点是否为父节点
        if(categoryEntity.getIsParent() == 1){
            return this.setResultError("当前节点为父节点");
        }

        //构建条件查询 通过当前被删除节点的parentid查询数据
        Example example = new Example(CategoryEntity.class);
        example.createCriteria().andEqualTo("parentId",categoryEntity.getParentId());
        List<CategoryEntity> list = categoryMapper.selectByExample(example);
        //如果查询出来的数据只有一条
        if(list.size() == 1){
            CategoryEntity parentEntity = new CategoryEntity();
            parentEntity.setId(categoryEntity.getParentId());
            //将父节点的isParent状态改为0
            parentEntity.setIsParent(0);
            categoryMapper.updateByPrimaryKeySelective(parentEntity);
        }

        categoryMapper.deleteByPrimaryKey(id);

        return this.setResultSuccess();
    }


}
