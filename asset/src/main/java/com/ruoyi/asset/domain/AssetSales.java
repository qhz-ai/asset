package com.ruoyi.asset.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.annotation.Excels;
import com.ruoyi.common.annotation.Excel.Type;

import lombok.Data;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.ruoyi.common.core.domain.BaseEntity;
import com.ruoyi.common.core.domain.entity.SysUser;

/**
 * 资产租售对象 asset_sales
 * 
 * @author yepanpan
 * @date 2021-03-05
 */

@ApiModel("资产租售实体")
@Data
public class AssetSales extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 自增长主键ID */
    @ApiModelProperty("${comment}")
    private Long id;

    /** 资产分类 */
    @ApiModelProperty("资产分类")
    private Long cateId;
    
    @Excels({
        @Excel(name = "资产分类", targetAttr = "title", type = Type.EXPORT)
    })
    private AssetCategory cate;

    /** 耗材ID */
    @ApiModelProperty("耗材ID")
    private Long materialId;
    

    @Excels({
        @Excel(name = "耗材名称", targetAttr = "name", type = Type.EXPORT)
    })
    private AssetMaterial material;

    /** 资产ID */
    @ApiModelProperty("资产ID")
    private Long assetId;
    

    @Excels({
        @Excel(name = "资产名", targetAttr = "name", type = Type.EXPORT),
        @Excel(name = "资产编号", targetAttr = "num", type = Type.EXPORT)
    })
    private AssetInfo asset;

    /** 无形资产ID */
    @ApiModelProperty("无形资产ID")
    private Long aptitudeId;

    @Excels({
        @Excel(name = "无形资产", targetAttr = "name", type = Type.EXPORT)
    })
    private AssetAptitude aptitude;
    

    /** 租售类型 */
    @Excel(name = "租售类型", dictType = "sales_type")
    @ApiModelProperty("租售类型")
    private String type;

    /** 数量 */
    @Excel(name = "数量")
    @ApiModelProperty("数量")
    private BigDecimal amount;

    /** 总价 */
    @Excel(name = "总价")
    @ApiModelProperty("总价")
    private BigDecimal price;

    /** 租售原由 */
    @ApiModelProperty("总价")
    private String reason;

    /** 负责人 */
    @ApiModelProperty("负责人")
    private Long masterUserId;
    

    @Excels({
        @Excel(name = "负责人", targetAttr = "nickName", type = Type.EXPORT)
    })
    private SysUser masterUser;

    /** 开始时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "开始时间", width = 30, dateFormat = "yyyy-MM-dd")
    @ApiModelProperty("开始时间")
    private Date startTime;

    /** 完成时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "完成时间", width = 30, dateFormat = "yyyy-MM-dd")
    @ApiModelProperty("完成时间")
    private Date finishTime;

    /** 是否完成 */
    @Excel(name = "是否完成", dictType = "sys_yes_no")
    @ApiModelProperty("是否完成")
    private String isComplete;


}
