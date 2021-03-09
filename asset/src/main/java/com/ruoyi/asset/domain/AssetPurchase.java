package com.ruoyi.asset.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.annotation.Excel.Type;
import com.ruoyi.common.annotation.Excels;

import lombok.Data;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.ruoyi.common.core.domain.BaseEntity;
import com.ruoyi.common.core.domain.entity.SysUser;

/**
 * 采购申请对象 asset_purchase
 * 
 * @author yepanpan
 * @date 2021-03-05
 */

@ApiModel("采购申请实体")
@Data
public class AssetPurchase extends BaseEntity
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
    
    @Excels({
        @Excel(name = "申请人", targetAttr = "nickName", type = Type.EXPORT)
    })
    private SysUser applyUser;

    /** 产品名称 */
    @Excel(name = "产品名称")
    @ApiModelProperty("产品名称")
    private String name;

    /** 参数要求 */
    @ApiModelProperty("产品名称")
    private String param;

    /** 厂商 */
    @Excel(name = "厂商")
    @ApiModelProperty("厂商")
    private String factory;

    /** 购买渠道 */
    @Excel(name = "购买渠道")
    @ApiModelProperty("购买渠道")
    private String proxy;

    /** 购买数量 */
    @Excel(name = "购买数量")
    @ApiModelProperty("购买数量")
    private BigDecimal amount;

    /** 市场价格 */
    @Excel(name = "市场价格")
    @ApiModelProperty("市场价格")
    private BigDecimal price1;

    /** 购买价格 */
    @Excel(name = "购买价格")
    @ApiModelProperty("购买价格")
    private BigDecimal price2;

    /** 申请原由 */
    @ApiModelProperty("购买价格")
    private String reason;

    /** 申请说明 */
    @ApiModelProperty("购买价格")
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
    @ApiModelProperty("审核时间")
    private String checkReason;

    /** 采购负责人 */
    @ApiModelProperty("采购负责人")
    private Long masterUserId;


    @Excels({
        @Excel(name = "负责人", targetAttr = "nickName", type = Type.EXPORT)
    })
    private SysUser masterUser;

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
