package com.ruoyi.asset.domain;

import java.math.BigDecimal;
import java.util.Date;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.annotation.Excels;
import com.ruoyi.common.annotation.Excel.Type;

import lombok.Data;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.ruoyi.common.core.domain.BaseEntity;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.sys.domain.SysFiles;

/**
 * 资产信息对象 asset_info
 * 
 * @author yepanpan
 * @date 2021-03-05
 */

@ApiModel("资产信息实体")
@Data
public class AssetInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 自增长主键ID */
    @ApiModelProperty("${comment}")
    private Long id;

    /** 资产分类 */
    @ApiModelProperty("资产分类")
    private Long cateId;

    @Excel(name = "资产分类")
    @NotNull(message="资产分类不能为空")
    private String cateName;
    private AssetCategory cate;

    /** 资产编号 */
    @Excel(name = "资产编号")
    @ApiModelProperty("资产编号")
    @NotNull(message="资产编号不能为空")
    private String num;

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

    /** 当前价值 */
    @Excel(name = "当前价值")
    @ApiModelProperty("当前价值")
    @NotNull(message="当前价值不能为空")
    private BigDecimal price;

    /** 市场价格 */
    @Excel(name = "市场价格")
    @ApiModelProperty("市场价格")
    private BigDecimal price1;

    /** 购买价格 */
    @Excel(name = "购买价格")
    @ApiModelProperty("购买价格")
    private BigDecimal price2;

    /** 仓库 */
    @ApiModelProperty("仓库")
    private Long houseId;

    @Excel(name = "仓库")
    @NotNull(message="仓库不能为空")
    private String houseName;
    private AssetWarehouse house;

    /** 图例 */
    @ApiModelProperty("图例")
    private Long imgId;
    private SysFiles img;

    /** 资产说明 */
    @Excel(name = "资产说明")
    @ApiModelProperty("资产说明")
    private String comment;

    /** 资产状态 */
    @Excel(name = "资产状态", dictType = "asset_status", combo = {"闲置","在用","损坏","维修中","报废","丢失"})
    @ApiModelProperty("资产状态")
    @NotNull(message="资产状态不能为空")
    private String status;

    /** 所属部门 */
    @ApiModelProperty("所属部门")
    private Long deptId;
    @Excel(name = "所属部门")
    private String deptName;
    
    /** 使用人 */
    @ApiModelProperty("使用人")
    private Long useUserId;
    @Excel(name = "使用人")
    private String userName;
    private SysUser useUser;

    /** 预计归还时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "预计归还时间", width = 30, dateFormat = "yyyy-MM-dd")
    @ApiModelProperty("预计归还时间")
    private Date finishTime;

    /** 报废时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "报废时间", width = 30, dateFormat = "yyyy-MM-dd")
    @ApiModelProperty("报废时间")
    private Date scrapTime;
    
}
