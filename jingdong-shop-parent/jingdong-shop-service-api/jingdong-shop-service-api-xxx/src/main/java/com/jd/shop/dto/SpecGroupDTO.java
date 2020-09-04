package com.jd.shop.dto;

import com.jd.shop.base.BaseDTO;
import com.jd.shop.validate.group.JingdongOperation;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @ClassName SpecGroupDTO
 * @Description: TODO
 * @Author zhangfeiyang
 * @Date 2020/9/3
 * @Version V1.0
 **/
@ApiModel(value = "规格组数据传输DTO")
@Data
public class SpecGroupDTO {

    @ApiModelProperty(value = "主键",example = "1")
    @NotNull(message = "主键不能为空",groups = {JingdongOperation.Update.class})
    private Integer id;

    @ApiModelProperty(value = "类型id",example = "1")
    @NotNull(message = "类型id不能为空",groups = {JingdongOperation.Add.class})
    private Integer cid;

    @ApiModelProperty(value = "规格组名称")
    @NotEmpty(message = "规格组名称不能为空",groups = {JingdongOperation.Add.class})
    private String name;
}
