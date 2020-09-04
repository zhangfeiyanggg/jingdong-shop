package com.jd.shop.dto;

import com.jd.shop.base.BaseDTO;
import com.jd.shop.validate.group.JingdongOperation;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @ClassName BrandDTO
 * @Description: TODO
 * @Author zhangfeiyang
 * @Date 2020/8/31
 * @Version V1.0
 **/
@ApiModel(value = "品牌DTO")
@Data
public class BrandDTO extends BaseDTO {

    @Id
    @ApiModelProperty(value = "品牌主键",example = "1")
    @NotNull(message = "主键不能为空",groups = {JingdongOperation.Update.class})
    private Integer id;

    @ApiModelProperty(value = "品牌名称",example = "1")
    @NotEmpty(message = "品牌名称不能为空",groups = {JingdongOperation.Add.class,JingdongOperation.Update.class})
    private String name;

    @ApiModelProperty(value = "品牌图片")
    private String image;

    @ApiModelProperty(value = "品牌首字母")
    private Character letter;

    @ApiModelProperty(value = "品牌分类信息")
    @NotEmpty(message = "品牌分类信息不能为空",groups = {JingdongOperation.Add.class})
    private String category;
}
