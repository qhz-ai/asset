package com.ruoyi.asset.domain;

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
 * 使用申请对象 asset_use
 * 
 * @author yepanpan
 * @date 2021-03-05
 */

@ApiModel("使用申请实体")
@Data
public class AssetUse extends BaseEntity
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

    /** 参数要求 */
    @ApiModelProperty("资产参数")
    @Excel(name = "资产参数")
    private String param;

    @Excels({
        @Excel(name = "申请人", targetAttr = "nickName", type = Type.EXPORT)
    })
    private SysUser applyUser;
    
    /** 申请原由 */
    @ApiModelProperty("申请原由")
    @Excel(name = "申请原由")
    private String reason;

    /** 申请说明 */
    @ApiModelProperty("申请说明")
    @Excel(name = "申请说明")
    private String comment;

    /** 审核人 */
    @ApiModelProperty("审核人")
    private Long checkUserId; 

    @Excels({
        @Excel(name = "审核人", targetAttr = "nickName", type = Type.EXPORT)
    })
    private SysUser checkUser;

    /** 审核结果 */
    @Excel(name = "审核结果", dictType = "check_status")
    @ApiModelProperty("审核结果")
    private String checkStatus;

    /** 审核时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "审核时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty("审核时间")
    private Date checkTime;

    /** 审核不通过原因 */
    @ApiModelProperty("审核不通过原因")
    @Excel(name = "审核不通过原因")
    private String checkReason;

    /** 开始时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "开始使用时间", width = 30, dateFormat = "yyyy-MM-dd")
    @ApiModelProperty("开始时间")
    private Date startTime;

    /** 结束时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "预计归还时间", width = 30, dateFormat = "yyyy-MM-dd")
    @ApiModelProperty("结束时间")
    private Date finishTime;

    /** 资产ID */
    @ApiModelProperty("资产ID")
    private Long assetId;
    
    @Excels({
        @Excel(name = "资产名", targetAttr = "name", type = Type.EXPORT),
        @Excel(name = "资产编号", targetAttr = "num", type = Type.EXPORT)
    })
    private AssetInfo asset;

    /** 是否出库 */
    @Excel(name = "是否出库", dictType = "sys_yes_no")
    @ApiModelProperty("是否出库")
    private String isDelivery;
    
    /** 是否归还 */
    @Excel(name = "是否归还", dictType = "sys_yes_no")
    @ApiModelProperty("是否归还")
    private String isRevert;

    /** 归还时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "归还时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty("归还时间")
    private Date revertTime;


}
