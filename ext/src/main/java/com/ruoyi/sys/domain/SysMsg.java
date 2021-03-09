package com.ruoyi.sys.domain;

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
 * 消息对象 oa_msg
 * 
 * @author yepanpan
 * @date 2020-12-11
 */
@ApiModel("消息实体")
@Getter
@Setter
public class SysMsg extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 自增长主键ID */
    private Long id;
    private Long users[];

    /** 标题 */
    @Excel(name = "标题")
    @ApiModelProperty("标题")
    private String title;

    /** 内容 */
    @Excel(name = "内容")
    @ApiModelProperty("内容")
    private String content;

    /** 链接 */
    @Excel(name = "链接")
    @ApiModelProperty("链接")
    private String url;

    /** 消息类型 */
    @Excel(name = "消息类型")
    @ApiModelProperty("消息类型")
    private String type;

    /** 添加时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "添加时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty("添加时间")
    private Date addTime;

    /** 添加人 */
    @Excel(name = "添加人")
    @ApiModelProperty("添加人")
    private Long addUser;

    /** 发信人姓名 */
    @Excel(name = "发信人姓名")
    @ApiModelProperty("发信人姓名")
    private String addUserName;

    /** 收信人 */
    @Excel(name = "收信人")
    @ApiModelProperty("收信人")
    private Long toUser;

    /** 收信人姓名 */
    @Excel(name = "收信人姓名")
    @ApiModelProperty("收信人姓名")
    private String toUserName;

    /** 读取时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "读取时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty("读取时间")
    private Date readTime;



    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("title", getTitle())
            .append("content", getContent())
            .append("url", getUrl())
            .append("type", getType())
            .append("addTime", getAddTime())
            .append("addUser", getAddUser())
            .append("addUserName", getAddUserName())
            .append("toUser", getToUser())
            .append("toUserName", getToUserName())
            .append("readTime", getReadTime())
            .toString();
    }
}
