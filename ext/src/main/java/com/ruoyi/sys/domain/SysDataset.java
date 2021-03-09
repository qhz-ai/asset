package com.ruoyi.sys.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 数据集对象 sys_dataset
 * 
 * @author yepanpan
 * @date 2020-12-04
 */
@ApiModel("数据集实体")
@Getter
@Setter
public class SysDataset extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 自增长主键ID */
    private Long id;

    /** 部门代码 */
    @Excel(name = "部门代码")
    @ApiModelProperty("部门代码")
    private String code;

    /** 类型 */
    @Excel(name = "类型")
    @ApiModelProperty("类型")
    private String type;

    /** 部门名称 */
    @Excel(name = "部门名称")
    @ApiModelProperty("部门名称")
    private String title;

    /** 查询SQL */
    @Excel(name = "查询SQL")
    @ApiModelProperty("查询SQL")
    private String sqls;

    /** 支持参数 */
    @Excel(name = "支持参数")
    @ApiModelProperty("支持参数")
    private String param;

    /** 排序 */
    @Excel(name = "排序")
    @ApiModelProperty("排序")
    private Long listSort;

    /** 说明 */
    @Excel(name = "说明")
    @ApiModelProperty("说明")
    private String comment;

    /** 状态 */
    @Excel(name = "状态")
    @ApiModelProperty("状态")
    private String status;
    
    private String where;

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("code", getCode())
            .append("type", getType())
            .append("title", getTitle())
            .append("sqls", getSqls())
            .append("param", getParam())
            .append("listSort", getListSort())
            .append("comment", getComment())
            .append("status", getStatus())
            .toString();
    }
}
