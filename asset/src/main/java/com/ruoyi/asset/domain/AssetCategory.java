package com.ruoyi.asset.domain;

import com.ruoyi.common.annotation.Excel;
import lombok.Data;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.ruoyi.common.core.domain.TreeEntity;

/**
 * 资产分类对象 asset_category
 * 
 * @author yepanpan
 * @date 2021-03-05
 */

@ApiModel("资产分类实体")
@Data
public class AssetCategory extends TreeEntity
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

    /** 分类类别 */
    @Excel(name = "分类类别")
    @ApiModelProperty("分类类别")
    private String type;

    /** 排序 */
    @Excel(name = "排序")
    @ApiModelProperty("排序")
    private Long listSort;

    /** 状态 */
    @Excel(name = "状态")
    @ApiModelProperty("状态")
    private String status;


}
