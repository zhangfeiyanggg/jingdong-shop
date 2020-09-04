package com.jd.shop.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.gson.JsonObject;
import com.jd.shop.base.BaseApiService;
import com.jd.shop.base.Result;
import com.jd.shop.dto.BrandDTO;
import com.jd.shop.entity.BrandEntity;
import com.jd.shop.entity.CategoryBrandEntity;
import com.jd.shop.mapper.BrandMapper;
import com.jd.shop.mapper.CategoryBrandMapper;
import com.jd.shop.service.BrandService;
import com.jd.shop.utils.JingDongBeanUtil;
import com.jd.shop.utils.PinyinUtil;
import com.jd.shop.utils.StringUtil;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RestController;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @ClassName BrandServiceMapper
 * @Description: TODO
 * @Author zhangfeiyang
 * @Date 2020/8/31
 * @Version V1.0
 **/
@RestController
public class BrandServiceImpl extends BaseApiService implements BrandService {

    @Resource
    private BrandMapper brandMapper;

    @Resource
    private CategoryBrandMapper categoryBrandMapper;

    @Override
    public Result<PageInfo<BrandEntity>> getBrandInfo(BrandDTO brandDTO) {

        //分页
        PageHelper.startPage(brandDTO.getPage(),brandDTO.getRows());

        Example example = new Example(BrandEntity.class);

        //排序
        if(StringUtil.isNotEmpty(brandDTO.getSort())) example.setOrderByClause(brandDTO.getOrderByClause());

        //条件查询
        if(StringUtil.isNotEmpty(brandDTO.getName()))example
                .createCriteria()
                .andLike("name","%"+brandDTO.getName()+"%");

        List<BrandEntity> list = brandMapper.selectByExample(example);


        PageInfo<BrandEntity> pageInfo = new PageInfo<>(list);

        return this.setResultSuccess(pageInfo);
    }

    @Override
    @Transactional
    public Result<JsonObject> getBrandSave(BrandDTO brandDto) {

        //str.charAt(0)获取当前字符串第一个字符
        //String.valueOf()将对象转换为String类型的字符串

        /*char c = brandDTO.getName().charAt(0);
        String s = String.valueOf(c);
        String upperCase = PinyinUtil.getUpperCase(s, PinyinUtil.TO_FIRST_CHAR_PINYIN);
        char c1 = upperCase.charAt(0);
        brandDTO.setLetter(c1);*/

        char c1 = PinyinUtil.getUpperCase(String.valueOf(brandDto.getName().charAt(0)), PinyinUtil.TO_FIRST_CHAR_PINYIN)
                .charAt(0);
        brandDto.setLetter(c1);

        BrandEntity brandEntity = JingDongBeanUtil.copyProperties(brandDto, BrandEntity.class);
        brandMapper.insertSelective(brandEntity);

        this.insertCategoryAndBrand(brandDto,brandEntity);

        return this.setResultSuccess();
    }

    @Override
    @Transactional
    public Result<JsonObject> getBrandUp(BrandDTO brandDto) {

        char c1 = PinyinUtil.getUpperCase(String.valueOf(brandDto.getName().charAt(0)), PinyinUtil.TO_FIRST_CHAR_PINYIN)
                .charAt(0);
        brandDto.setLetter(c1);

        //因为通用mapper里面接收的参数是entity实体类,所以要把brandDTO转换成brandEntity
        BrandEntity brandEntity = JingDongBeanUtil.copyProperties(brandDto, BrandEntity.class);
        brandMapper.updateByPrimaryKeySelective(brandEntity);


        //通过brandId去中间表删除数据
        this.deleteCategoryAndBrand(brandEntity.getId());

        //新增新的数据
        this.insertCategoryAndBrand(brandDto,brandEntity);

        return this.setResultSuccess();
    }

    @Override
    @Transactional
    public Result<JsonObject> getDelete(Integer id) {
        //删除数据
        brandMapper.deleteByPrimaryKey(id);

        //去关系表删除数据
        this.deleteCategoryAndBrand(id);

        return this.setResultSuccess();
    }

    private void insertCategoryAndBrand(BrandDTO brandDto,BrandEntity brandEntity){
        //新增新的数据
        if(brandDto.getCategory().contains(",")){

            List<CategoryBrandEntity> categoryBrandEntities = new ArrayList<>();

            /*String[] split = brandDTO.getCategory().split(",");
            List<String> asList = Arrays.asList(split);
            List<CategoryBrandEntity> categoryBrandEntities = new ArrayList<>();*/

            Arrays.asList(brandDto.getCategory().split(",")).stream().forEach(categoryId->{
                CategoryBrandEntity categoryBrandEntity = new CategoryBrandEntity();
                categoryBrandEntity.setCategoryId(StringUtil.toInteger(categoryId));
                categoryBrandEntity.setBrandId(brandEntity.getId());

                categoryBrandEntities.add(categoryBrandEntity);
            });
            categoryBrandMapper.insertList(categoryBrandEntities);

        }else{
            CategoryBrandEntity categoryBrandEntity = new CategoryBrandEntity();
            categoryBrandEntity.setCategoryId(StringUtil.toInteger(brandDto.getCategory()));
            categoryBrandEntity.setBrandId(brandEntity.getId());

            categoryBrandMapper.insertSelective(categoryBrandEntity);
        }
    }


    private void deleteCategoryAndBrand(Integer id){

        Example example = new Example(CategoryBrandEntity.class);
        example.createCriteria().andEqualTo("brandId",id);
        categoryBrandMapper.deleteByExample(example);

    }
}
