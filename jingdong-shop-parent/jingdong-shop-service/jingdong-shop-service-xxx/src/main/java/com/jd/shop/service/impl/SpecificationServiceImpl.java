package com.jd.shop.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.JsonObject;
import com.jd.shop.base.BaseApiService;
import com.jd.shop.base.Result;
import com.jd.shop.dto.SpecGroupDTO;
import com.jd.shop.dto.SpecParamDTO;
import com.jd.shop.entity.SpecGroupEntity;
import com.jd.shop.entity.SpecParamEntity;
import com.jd.shop.mapper.SpecGroupMapper;
import com.jd.shop.mapper.SpecParamMapper;
import com.jd.shop.service.SpecificationService;
import com.jd.shop.utils.JingDongBeanUtil;
import com.jd.shop.utils.ObjectUtil;
import com.jd.shop.utils.StringUtil;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RestController;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName SpecificationServiceImpl
 * @Description: TODO
 * @Author zhangfeiyang
 * @Date 2020/9/3
 * @Version V1.0
 **/
@RestController
public class SpecificationServiceImpl extends BaseApiService implements SpecificationService {

    @Resource
    private SpecGroupMapper specGroupMapper;

    @Resource
    private SpecParamMapper specParamMapper;

    @Override
    public Result<List<SpecGroupEntity>> getSpecGroupList(SpecGroupDTO groupDTO) {

        if(ObjectUtil.isNull(groupDTO.getCid()))return this.setResultError("商品分类id不能为空");
        Example example = new Example(SpecGroupEntity.class);
        example.createCriteria().andEqualTo("cid",groupDTO.getCid());
        List<SpecGroupEntity> list = specGroupMapper.selectByExample(example);
        return this.setResultSuccess(list);

    }

    @Override
    @Transactional
    public Result<JSONObject> getSpecGroupSave(SpecGroupDTO groupDTO) {

        SpecGroupEntity specGroupEntity = JingDongBeanUtil.copyProperties(groupDTO, SpecGroupEntity.class);
        specGroupMapper.insertSelective(specGroupEntity);

        return this.setResultSuccess();
    }

    @Override
    @Transactional
    public Result<JSONObject> getSpecGroupEdit(SpecGroupDTO groupDTO) {

        SpecGroupEntity specGroupEntity = JingDongBeanUtil.copyProperties(groupDTO, SpecGroupEntity.class);

        specGroupMapper.updateByPrimaryKey(specGroupEntity);

        return this.setResultSuccess();
    }

    @Override
    @Transactional
    public Result<JSONObject> getSpecGroupDelete(Integer id) {

        if(ObjectUtil.isNull(id)) return this.setResultError("id不能为空");

        Example example = new Example(SpecParamEntity.class);
        example.createCriteria().andEqualTo("groupId",id);
        List<SpecParamEntity> list = specParamMapper.selectByExample(example);
        if(list.size()>0){
            return this.setResultError("该规格组被绑定不能被删除");
        }

        specGroupMapper.deleteByPrimaryKey(id);

        return this.setResultSuccess();
    }

    @Override
    public Result<List<SpecParamEntity>> getSpecParamList(SpecParamDTO specParamDTO) {

        if(ObjectUtil.isNull(specParamDTO.getGroupId())) return this.setResultError("规格组id不能为空");
        Example example = new Example(SpecParamEntity.class);
        example.createCriteria().andEqualTo("groupId",specParamDTO.getGroupId());
        List<SpecParamEntity> list = specParamMapper.selectByExample(example);

        return this.setResultSuccess(list);
    }

    @Override
    @Transactional
    public Result<JSONObject> getSpecParamSave(SpecParamDTO specParamDTO) {

        SpecParamEntity specParamEntity = JingDongBeanUtil.copyProperties(specParamDTO, SpecParamEntity.class);
        specParamMapper.insertSelective(specParamEntity);

        return this.setResultSuccess();
    }

    @Override
    @Transactional
    public Result<JSONObject> getSpecParamEdit(SpecParamDTO specParamDTO) {

        SpecParamEntity specParamEntity = JingDongBeanUtil.copyProperties(specParamDTO, SpecParamEntity.class);
        specParamMapper.updateByPrimaryKey(specParamEntity);

        return this.setResultSuccess();
    }

    @Override
    @Transactional
    public Result<JSONObject> getSpecParamDel(Integer id) {

        if(ObjectUtil.isNull(id)) return this.setResultError("id不能为空");


        specParamMapper.deleteByPrimaryKey(id);

        return this.setResultSuccess();
    }


}
