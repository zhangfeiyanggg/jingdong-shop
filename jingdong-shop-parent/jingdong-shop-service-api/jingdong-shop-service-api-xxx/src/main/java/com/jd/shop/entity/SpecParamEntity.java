package com.jd.shop.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @ClassName SpecParamEntity
 * @Description: TODO
 * @Author zhangfeiyang
 * @Date 2020/9/3
 * @Version V1.0
 **/
@Table(name = "tb_spec_param")
@Data
public class SpecParamEntity {

    @Id
    private Integer id;

    private Integer cid;

    private Integer groupId;

    private String name;

    //numeric是mysql数据库的关键字,
    //加上``会当成普通字符串处理
    @Column(name = "`numeric`")
    private Boolean numeric;

    private String unit;

    private Boolean generic;

    private Boolean searching;

    private String segments;

}