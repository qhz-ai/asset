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
import com.ruoyi.sys.domain.SysFiles;

/**
 * 入库管理对象 asset_entering
 * 
 * @author yepanpan
 * @date 2021-03-05
 */

@ApiModel("入库管理实体")
@Data
public class AssetEntering extends BaseEntity
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

    /** 采购订单 */
    @ApiModelProperty("采购订单")
    private Long purchaseId;

    /** 仓库 */
    @ApiModelProperty("仓库")
    private Long houseId;
    
    @Excels({
        @Excel(name = "仓库", targetAttr = "title", type = Type.EXPORT)
    })
    private AssetWarehouse house;

    /** 产品名称 */
    @Excel(name = "产品名称")
    @ApiModelProperty("产品名称")
    private String name;

    /** 参数要求 */
    @Excel(name = "参数要求")
    @ApiModelProperty("参数要求")
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

    /** 接收人 */
    @ApiModelProperty("接收人")
    private Long recvUserId;
    
    @Excels({
        @Excel(name = "接收人", targetAttr = "nickName", type = Type.EXPORT)
    })
    private SysUser recvUser;

    /** 接收时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "接收时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty("接收时间")
    private Date recvTime;

    /** 收货单 */
    @ApiModelProperty("收货单")
    private Long imgId;
    private SysFiles img;


    /** 资产编号 */
    @Excel(name = "资产编号")
    @ApiModelProperty("资产编号")
    private String nums;
}
