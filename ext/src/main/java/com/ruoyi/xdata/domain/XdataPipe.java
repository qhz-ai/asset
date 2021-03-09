package com.ruoyi.xdata.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import lombok.Getter;
import lombok.Setter;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 通道对象 xdata_pipe
 * 
 * @author yepanpan
 * @date 2020-12-29
 */
@ApiModel("通道实体")
@Getter
@Setter
public class XdataPipe extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 自增长主键ID */
    private Long id;

    /** 应用 */
    @Excel(name = "应用")
    @ApiModelProperty("应用")
    private Long clientId;
    private XdataClient client;

    /** 模型名称 */
    @Excel(name = "模型名称")
    @ApiModelProperty("模型名称")
    private Long modelId;
    private XdataModel model;

    /** 权限 */
    @Excel(name = "权限")
    @ApiModelProperty("权限")
    private String mode;

    /** 读取操作 */
    @Excel(name = "读取操作")
    @ApiModelProperty("读取操作")
    private String readSql;

    /** 新增操作 */
    @Excel(name = "新增操作")
    @ApiModelProperty("新增操作")
    private String insertSql;
    
    /** 更新操作 */
    @Excel(name = "更新操作")
    @ApiModelProperty("更新操作")
    private String updateSql;

    /** 删除操作 */
    @Excel(name = "删除操作")
    @ApiModelProperty("删除操作")
    private String delSql;

    /** 验证操作 */
    @Excel(name = "验证操作")
    @ApiModelProperty("验证操作")
    private String existSql;

    /** 上次同步时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "上次同步时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty("上次同步时间")
    private Date lastTime;

    /** 上次同步结果 */
    @Excel(name = "上次同步结果")
    @ApiModelProperty("上次同步结果")
    private Long lastResult;

    /** 优先级 */
    @Excel(name = "优先级")
    @ApiModelProperty("优先级")
    private Long listSort;

    /** 状态 */
    @Excel(name = "状态")
    @ApiModelProperty("状态")
    private String status;



    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("clientId", getClientId())
            .append("modelId", getModelId())
            .append("mode", getMode())
            .append("readSql", getReadSql())
            .append("insertSql", getInsertSql())
            .append("updateSql", getUpdateSql())
            .append("delSql", getDelSql())
            .append("existSql", getExistSql())
            .append("lastTime", getLastTime())
            .append("lastResult", getLastResult())
            .append("listSort", getListSort())
            .append("status", getStatus())
            .toString();
    }
}
