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
 * 资产维护对象 asset_maintain
 * 
 * @author yepanpan
 * @date 2021-03-09
 */

@ApiModel("资产维护实体")
@Data
public class AssetMaintain extends BaseEntity
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

    /** 资产ID */
    @ApiModelProperty("资产ID")
    private Long assetId;

    @Excels({
        @Excel(name = "资产名", targetAttr = "name", type = Type.EXPORT),
        @Excel(name = "资产编号", targetAttr = "num", type = Type.EXPORT)
    })
    private AssetInfo asset;

    @Excels({
        @Excel(name = "申请人", targetAttr = "nickName", type = Type.EXPORT)
    })
    private SysUser applyUser;
    
    /** 维护类型 */
    @Excel(name = "维护类型", dictType = "maintain_type")
    @ApiModelProperty("维护类型")
    private String type;

    /** 维护原由 */
    @Excel(name = "维护原由")
    @ApiModelProperty("维护原由")
    private String reason;

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
    @Excel(name = "审核不通过原因")
    @ApiModelProperty("审核不通过原因")
    private String checkReason;


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

    /** 维修说明 */
    @ApiModelProperty("维修说明")
    private String comment;

    /** 维修结果 */
    @Excel(name = "维修结果", dictType = "maintain_result")
    @ApiModelProperty("维修结果")
    private String result;


}
