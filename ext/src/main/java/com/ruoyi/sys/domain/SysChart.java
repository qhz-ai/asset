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
 * 报表管理对象 sys_chart
 * 
 * @author yepanpan
 * @date 2020-12-04
 */
@ApiModel("报表管理实体")
@Getter
@Setter
public class SysChart extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 自增长主键ID */
    private Long id;

    /** 代码 */
    @Excel(name = "代码")
    @ApiModelProperty("代码")
    private String code;

    /** 名称 */
    @Excel(name = "名称")
    @ApiModelProperty("名称")
    private String title;

    /** 数据源 */
    @Excel(name = "数据源")
    @ApiModelProperty("数据源")
    private Long dsId;
    private String dsName;

    /** 横向坐标 */
    @Excel(name = "横向坐标")
    @ApiModelProperty("横向坐标")
    private String xaxis;

    /** 纵向坐标 */
    @Excel(name = "纵向坐标")
    @ApiModelProperty("纵向坐标")
    private String yaxis;
    
    /** 类型 */
    @Excel(name = "类型")
    @ApiModelProperty("类型")
    private String type;

    /** 说明 */
    @Excel(name = "说明")
    @ApiModelProperty("说明")
    private String comment;

    /** 排序 */
    @Excel(name = "排序")
    @ApiModelProperty("排序")
    private Long listSort;

    /** 状态 */
    @Excel(name = "状态")
    @ApiModelProperty("状态")
    private String status;  

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("code", getCode())
            .append("title", getTitle())
            .append("dsId", getDsId())
            .append("type", getType())
            .append("comment", getComment())
            .append("listSort", getListSort())
            .append("status", getStatus())
            .toString();
    }
}
