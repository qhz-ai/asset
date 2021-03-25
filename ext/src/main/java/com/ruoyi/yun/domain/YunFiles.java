package com.ruoyi.yun.domain;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;
import lombok.Data;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.ruoyi.common.core.domain.BaseEntity;
import com.ruoyi.common.core.domain.entity.SysUser;
/**
 * 云盘文件对象 yun_files
 * 
 * @author yepanpan
 * @date 2021-03-16
 */

@ApiModel("云盘文件实体")
@Data
public class YunFiles extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 自增长主键ID */
    @ApiModelProperty("${comment}")
    private Long id;

    /** 目录 */
    @Excel(name = "目录")
    @ApiModelProperty("目录")
    private Long folderId;

    /** 文件 */
    @Excel(name = "文件")
    @ApiModelProperty("文件")
    private Long fileId;

    /** 文件名 */
    @Excel(name = "文件名")
    @ApiModelProperty("文件名")
    private String title;

    /** 类型 */
    @Excel(name = "类型")
    @ApiModelProperty("类型")
    private String type;

    /** 是否共享 */
    @Excel(name = "是否共享")
    @ApiModelProperty("是否共享")
    private String isPublic;

    private SysUser user;
}