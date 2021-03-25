package com.ruoyi.yun.domain;

import java.util.ArrayList;
import java.util.List;

import com.ruoyi.common.annotation.Excel;
import lombok.Data;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 云盘目录对象 yun_folder
 * 
 * @author yepanpan
 * @date 2021-03-15
 */

@ApiModel("云盘目录实体")
@Data
public class YunFolder extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 自增长主键ID */
    @ApiModelProperty("${comment}")
    private Long id;

    /** 分类名称 */
    @Excel(name = "分类名称")
    @ApiModelProperty("分类名称")
    private String title;

    /** 上级 */
    @Excel(name = "上级")
    @ApiModelProperty("上级")
    private Long pid;

    /** 排序 */
    @Excel(name = "排序")
    @ApiModelProperty("排序")
    private Long listSort;

    /** 是否公开 */
    @Excel(name = "是否公开")
    @ApiModelProperty("是否公开")
    private String isPublic;

    private List<YunFolder> children = new ArrayList<>();
}
