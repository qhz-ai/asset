package com.ruoyi.asset.domain;

import com.ruoyi.common.annotation.Excel;
import lombok.Data;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 仓库对象 asset_warehouse
 * 
 * @author yepanpan
 * @date 2021-03-05
 */

@ApiModel("仓库实体")
@Data
public class AssetWarehouse extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 自增长主键ID */
    @ApiModelProperty("${comment}")
    private Long id;

    /** 个仓库名称 */
    @Excel(name = "个仓库名称")
    @ApiModelProperty("个仓库名称")
    private String title;

    /** 地址 */
    @Excel(name = "地址")
    @ApiModelProperty("地址")
    private String address;

    /** 负责人 */
    @Excel(name = "负责人")
    @ApiModelProperty("负责人")
    private Long masterUserId;

    /** 联系方式 */
    @Excel(name = "联系方式")
    @ApiModelProperty("联系方式")
    private String links;

    /** 状态 */
    @Excel(name = "状态")
    @ApiModelProperty("状态")
    private String status;

    /** 仓库说明 */
    @Excel(name = "仓库说明")
    @ApiModelProperty("仓库说明")
    private String comment;


}
