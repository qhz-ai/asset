package com.ruoyi.asset.domain;

import java.math.BigDecimal;
import com.ruoyi.common.annotation.Excel;
import lombok.Data;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.ruoyi.common.core.domain.BaseEntity;
import com.ruoyi.sys.domain.SysFiles;

/**
 * 资产盘点对象 asset_taking
 * 
 * @author yepanpan
 * @date 2021-04-13
 */

@ApiModel("资产盘点实体")
@Data
public class AssetTaking extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 自增长主键ID */
    @ApiModelProperty("${comment}")
    private Long id;

    /** 资产分类 */
    @ApiModelProperty("资产分类")
    private Long cateId;

    @Excel(name = "资产分类")
    private String cateName;

    @Excel(name = "资产名称")
    private String assetName;

    /** 耗材ID */
    @ApiModelProperty("耗材ID")
    private Long materialId;

    /** 资产ID */
    @ApiModelProperty("资产ID")
    private Long assetId;

    /** 出库数量 */
    @Excel(name = "出库数量")
    @ApiModelProperty("出库数量")
    private BigDecimal amount;

    /** 仓库 */
    @ApiModelProperty("仓库")
    private Long houseId;
    
    @Excel(name = "仓库")
    private String houseName;

    /** 使用人 */
    @ApiModelProperty("使用人")
    private Long userId;
    @Excel(name = "使用人")
    private String userName;

    /** 使用部门 */
    @ApiModelProperty("使用部门")
    private Long deptId;
    @Excel(name = "使用部门")
    private String deptName;

    /** 照片 */
    @ApiModelProperty("照片")
    private Long imgId;
    private SysFiles img;

    /** 状态 */
    @Excel(name = "状态", dictType = "asset_status")
    @ApiModelProperty("状态")
    private String status;

    /** 盘点说明 */
    @Excel(name = "盘点说明")
    @ApiModelProperty("盘点说明")
    private String comment;


}
