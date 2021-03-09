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
 * 应用对象 xdata_client
 * 
 * @author yepanpan
 * @date 2020-12-29
 */
@ApiModel("应用实体")
@Getter
@Setter
public class XdataClient extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 自增长主键ID */
    private Long id;

    /** 应用名称 */
    @Excel(name = "应用名称")
    @ApiModelProperty("应用名称")
    private String title;

    /** 类型 */
    @Excel(name = "类型")
    @ApiModelProperty("类型")
    private String type;

    /** 应用代码 */
    @Excel(name = "应用代码")
    @ApiModelProperty("应用代码")
    private String code;

    /** 应用密钥 */
    @Excel(name = "应用密钥")
    @ApiModelProperty("应用密钥")
    private String secret;

    /** 媒体附件 */
    @Excel(name = "媒体附件")
    @ApiModelProperty("媒体附件")
    private String url;

    /** 图标 */
    @Excel(name = "图标")
    @ApiModelProperty("图标")
    private String icon;

    /** 数据源类型 */
    @Excel(name = "数据源类型")
    @ApiModelProperty("数据源类型")
    private String dbType;
    
    /** 数据连接 */
    @Excel(name = "数据连接")
    @ApiModelProperty("数据连接")
    private String jdbc;

    /** 数据库账号 */
    @Excel(name = "数据库账号")
    @ApiModelProperty("数据库账号")
    private String dbUser;

    /** 数据库密码 */
    @Excel(name = "数据库密码")
    @ApiModelProperty("数据库密码")
    private String dbPass;

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
            .append("code", getCode())
            .append("secret", getSecret())
            .append("url", getUrl())
            .append("icon", getIcon())
            .append("jdbc", getJdbc())
            .append("dbUser", getDbUser())
            .append("dbPass", getDbPass())
            .append("status", getStatus())
            .toString();
    }
}
