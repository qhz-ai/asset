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
import com.ruoyi.sys.domain.SysFiles;

/**
 * 出库管理对象 asset_delivery
 * 
 * @author yepanpan
 * @date 2021-03-05
 */

@ApiModel("出库管理实体")
@Data
public class AssetDelivery extends BaseEntity
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

    /** 申请ID */
    @ApiModelProperty("申请ID")
    private Long applyId;

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

    /** 出库数量 */
    @Excel(name = "出库数量")
    @ApiModelProperty("出库数量")
    private BigDecimal amount;

    /** 仓库 */
    @ApiModelProperty("仓库")
    private Long houseId;

    @Excels({
        @Excel(name = "仓库", targetAttr = "title", type = Type.EXPORT)
    })
    private AssetWarehouse house;

    /** 接收人 */
    @ApiModelProperty("接收人")
    private Long recvUserId;
    @Excels({
        @Excel(name = "接收人", targetAttr = "nickName", type = Type.EXPORT)
    })
    private SysUser recvUser;


    /** 确认单 */
    @ApiModelProperty("确认单")
    private Long imgId;

    private SysFiles img;

    @Excels({
        @Excel(name = "操作人", targetAttr = "nickName", type = Type.EXPORT)
    })
    private SysUser user;
    

    /** 出库时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "出库时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty("出库时间")
    private Date createTime;

}
