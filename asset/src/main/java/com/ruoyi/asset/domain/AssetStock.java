package com.ruoyi.asset.domain;

import java.math.BigDecimal;
import com.ruoyi.common.annotation.Excel;
import lombok.Data;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 资产盘点对象 asset_stock
 * 
 * @author yepanpan
 * @date 2021-03-10
 */

@ApiModel("资产盘点实体")
@Data
public class AssetStock extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 自增长主键ID */
    @ApiModelProperty("${comment}")
    private Long id;

    /** 年度 */
    @Excel(name = "年度")
    @ApiModelProperty("年度")
    private String year;

    /** 资产总值 */
    @Excel(name = "资产总值")
    @ApiModelProperty("资产总值")
    private BigDecimal worth;

    /** 固定资产总数 */
    @Excel(name = "固定资产总数")
    @ApiModelProperty("固定资产总数")
    private Long assetCount;

    /** 固定资产总值 */
    @Excel(name = "固定资产总值")
    @ApiModelProperty("固定资产总值")
    private BigDecimal assetWorth;

    /** 耗材总类 */
    @Excel(name = "耗材总类")
    @ApiModelProperty("耗材总类")
    private Long materialCount;

    /** 耗材资产总值 */
    @Excel(name = "耗材资产总值")
    @ApiModelProperty("耗材资产总值")
    private BigDecimal materialWorth;

    /** 报废资产总数 */
    @Excel(name = "报废资产总数")
    @ApiModelProperty("报废资产总数")
    private Long scrapCount;

    /** 报废资产总值 */
    @Excel(name = "报废资产总值")
    @ApiModelProperty("报废资产总值")
    private Long scrapWorth;

    /** 资产维护次数 */
    @Excel(name = "资产维护次数")
    @ApiModelProperty("资产维护次数")
    private Long maintainCount;

    /** 采购次数 */
    @Excel(name = "采购次数")
    @ApiModelProperty("采购次数")
    private Long purchaseCount;

    /** 采购总值 */
    @Excel(name = "采购总值")
    @ApiModelProperty("采购总值")
    private BigDecimal purchaseWorth;

    /** 盘点说明 */
    @Excel(name = "盘点说明")
    @ApiModelProperty("盘点说明")
    private String comment;


}
