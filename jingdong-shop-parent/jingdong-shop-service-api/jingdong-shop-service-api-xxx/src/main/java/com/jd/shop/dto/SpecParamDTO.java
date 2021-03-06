package com.jd.shop.dto;

import com.jd.shop.validate.group.JingdongOperation;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @ClassName SpecParamDTO
 * @Description: TODO
 * @Author zhangfeiyang
 * @Date 2020/9/3
 * @Version V1.0
 **/
@ApiModel(value = "规格参数数据传输DTO")
@Data
public class SpecParamDTO {

    @ApiModelProperty(value = "主键",example = "1")
    @NotNull(message = "主键不能为空",groups = {JingdongOperation.Update.class})
    private Integer id;

    @ApiModelProperty(value = "商品分类id",example = "1")
    private Integer cid;

    @ApiModelProperty(value = "规格组id",example = "1")
    private Integer groupId;

    @ApiModelProperty(value = "规格参数名称")
    @NotEmpty(message = "规格参数名称不能为空",groups = {JingdongOperation.Update.class,JingdongOperation.Add.class})
    private String name;


    @ApiModelProperty(value = "是否是数字类型参数，1->true或0->false",example = "0")
    @NotNull(message = "数字类型参数不能为空",groups = {JingdongOperation.Update.class,JingdongOperation.Add.class})
    private Boolean numeric;

    @ApiModelProperty(value = "数字类型参数的单位，非数字类型可以为空")
    private String unit;

    @ApiModelProperty(value = "是否是sku通用属性，1->true或0->false", example = "0")
    @NotNull(message = "sku通用属性不能为空",groups = {JingdongOperation.Add.class,JingdongOperation.Update.class})
    private Boolean generic;

    @ApiModelProperty(value = "是否用于搜索过滤，1->true或0->false", example = "0")
    @NotNull(message = "用于搜索过滤不能为空",groups = {JingdongOperation.Add.class,JingdongOperation.Update.class})
    private Boolean searching;

    @ApiModelProperty(value = "数值类型参数，如果需要搜索，则添加分段间隔值，如CPU频率间隔：0.5-1.0")
    private String segments;

}
