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
 * 耗材领用对象 asset_pickup
 * 
 * @author yepanpan
 * @date 2021-03-05
 */

@ApiModel("耗材领用实体")
@Data
public class AssetPickup extends BaseEntity
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
        @Excel(name = "耗材名称", targetAttr = "title", type = Type.EXPORT)
    })
    private AssetMaterial material;

    /** 领用数量 */
    @Excel(name = "领用数量")
    @ApiModelProperty("领用数量")
    private BigDecimal amount;

    /** 申请人ID */
    @ApiModelProperty("申请人ID")
    private Long applyUserId;
    
    @Excels({
        @Excel(name = "申请人", targetAttr = "nickName", type = Type.EXPORT)
    })
    private SysUser applyUser;
    
    /** 申请原由 */
    @Excel(name = "申请原由")
    @ApiModelProperty("申请原由")
    private String reason;

    /** 申请说明 */
    @Excel(name = "申请说明")
    @ApiModelProperty("申请说明")
    private String comment;

    /** 审核人 */
    @ApiModelProperty("审核人")
    private Long checkUserId;

    @Excels({
        @Excel(name = "审核人", targetAttr = "nickName", type = Type.EXPORT)
    })
    private SysUser checkUser;
    
    /** 审核结果 */
    @Excel(name = "审核结果")
    @ApiModelProperty("审核结果")
    private String checkStatus;

    /** 审核时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "审核时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty("审核时间")
    private Date checkTime;

    /** 审核不通过原因 */
    @Excel(name = "审核不通过原因")
    @ApiModelProperty("审核不通过原因")
    private String checkReason;

    /** 是否出库 */
    @Excel(name = "是否出库", dictType = "sys_yes_no")
    @ApiModelProperty("是否出库")
    private String isDelivery;
    

    /** 申请时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "申请时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty("申请时间")
    private Date createTime;

}
