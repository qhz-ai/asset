package com.ruoyi.asset.domain;

import java.math.BigDecimal;

import javax.validation.constraints.NotNull;

import com.ruoyi.common.annotation.Excel;
import lombok.Data;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 耗材信息对象 asset_material
 * 
 * @author yepanpan
 * @date 2021-03-05
 */

@ApiModel("耗材信息实体")
@Data
public class AssetMaterial extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 自增长主键ID */
    @ApiModelProperty("${comment}")
    private Long id;

    /** 资产分类 */
    @ApiModelProperty("资产分类")
    private Long cateId;
    private AssetCategory cate;

    @Excel(name = "资产分类")
    @NotNull(message="当前价值不能为空")
    private String cateName;

    
    /** 产品名称 */
    @Excel(name = "产品名称")
    @ApiModelProperty("产品名称")
    @NotNull(message="产品名称不能为空")
    private String name;

    /** 参数 */
    @ApiModelProperty("产品参数")
    private String param;

    /** 厂商 */
    @Excel(name = "厂商")
    @ApiModelProperty("厂商")
    private String factory;

    /** 购买渠道 */
    @Excel(name = "购买渠道")
    @ApiModelProperty("购买渠道")
    private String proxy;

    /** 计量单位 */
    @Excel(name = "计量单位")
    @ApiModelProperty("计量单位")
    @NotNull(message="计量单位不能为空")
    private String unit;

    /** 库存数量 */
    @Excel(name = "库存数量")
    @ApiModelProperty("库存数量")
    @NotNull(message="库存数量不能为空")
    private BigDecimal amount;

    /** 市场价格 */
    @Excel(name = "市场价格")
    @ApiModelProperty("市场价格")
    private BigDecimal price1;

    /** 购买价格 */
    @Excel(name = "购买价格")
    @ApiModelProperty("购买价格")
    @NotNull(message="购买价格不能为空")
    private BigDecimal price2;

    /** 仓库 */
    @ApiModelProperty("仓库")
    private Long houseId;
    private AssetWarehouse house;

    @Excel(name = "仓库")
    @NotNull(message="仓库不能为空")
    private String houseName;
    
    /** 耗材说明 */
    @Excel(name = "耗材说明")
    @ApiModelProperty("耗材说明")
    private String comment;

    /** 耗材状态 */
    @Excel(name = "耗材状态", dictType = "material_status", combo = {"正常","库存不足","空仓","报废"})
    @ApiModelProperty("耗材状态")
    @NotNull(message="耗材状态不能为空")
    private String status;


}
