package com.ruoyi.xdata.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import lombok.Getter;
import lombok.Setter;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 模型对象 xdata_model
 * 
 * @author yepanpan
 * @date 2020-12-29
 */
@ApiModel("模型实体")
@Getter
@Setter
public class XdataModel extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 自增长主键ID */
    private Long id;

    /** 模型名称 */
    @Excel(name = "模型名称")
    @ApiModelProperty("模型名称")
    private String title;

    /** 类型 */
    @Excel(name = "类型")
    @ApiModelProperty("类型")
    private String type;

    /** 数据表 */
    @Excel(name = "数据表")
    @ApiModelProperty("数据表")
    private String tables;

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
            .append("title", getTitle())
            .append("type", getType())
            .append("tables", getTables())
            .append("listSort", getListSort())
            .append("status", getStatus())
            .toString();
    }
}
