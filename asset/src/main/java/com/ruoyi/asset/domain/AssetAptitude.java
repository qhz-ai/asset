package com.ruoyi.asset.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;
import lombok.Data;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.ruoyi.common.core.domain.BaseEntity;
import com.ruoyi.sys.domain.SysFiles;

/**
 * 无形资产对象 asset_aptitude
 * 
 * @author yepanpan
 * @date 2021-03-05
 */

@ApiModel("无形资产实体")
@Data
public class AssetAptitude extends BaseEntity
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
    private String cateName;

    /** 资产名 */
    @ApiModelProperty("资产名")
    @Excel(name = "资产名")
    private String name;
    
    /** 图片 */
    @ApiModelProperty("图片")
    private Long imgId;
    private SysFiles img;


    /** 获取时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "获取时间", width = 30, dateFormat = "yyyy-MM-dd")
    @ApiModelProperty("获取时间")
    private Date getTime;

    /** 资产说明 */
    @Excel(name = "资产说明")
    @ApiModelProperty("资产说明")
    private String comment;


}
