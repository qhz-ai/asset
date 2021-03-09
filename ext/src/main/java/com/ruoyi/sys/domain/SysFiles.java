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
 * 文件对象 sys_files
 * 
 * @author yepanpan
 * @date 2020-11-20
 */
@ApiModel("文件实体")
@Getter
@Setter
public class SysFiles extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 自增长主键 */
    private Long id;

    /** 文件名 */
    @Excel(name = "文件名")
    @ApiModelProperty("文件名")
    private String title;

    /** 路径 */
    @Excel(name = "路径")
    @ApiModelProperty("路径")
    private String path;

    /** MD5值 */
    @Excel(name = "MD5值")
    @ApiModelProperty("MD5值")
    private String md5;

  
    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("title", getTitle())
            .append("path", getPath())
            .append("md5", getMd5())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .toString();
    }
}
