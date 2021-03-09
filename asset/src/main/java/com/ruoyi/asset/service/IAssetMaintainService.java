package com.ruoyi.asset.service;

import java.util.List;
import com.ruoyi.asset.domain.AssetMaintain;
import com.ruoyi.asset.domain.AssetPurchase;

/**
 * 资产维护Service接口
 * 
 * @author yepanpan
 * @date 2021-03-09
 */
public interface IAssetMaintainService 
{
    /**
     * 查询资产维护
     * 
     * @param id 资产维护ID
     * @return 资产维护
     */
    public AssetMaintain selectAssetMaintainById(Long id);

    /**
     * 查询资产维护列表
     * 
     * @param assetMaintain 资产维护
     * @return 资产维护集合
     */
    public List<AssetMaintain> selectAssetMaintainList(AssetMaintain assetMaintain);

    /**
     * 新增资产维护
     * 
     * @param assetMaintain 资产维护
     * @return 结果
     */
    public int insertAssetMaintain(AssetMaintain assetMaintain);

    /**
     * 修改资产维护
     * 
     * @param assetMaintain 资产维护
     * @return 结果
     */
    public int updateAssetMaintain(AssetMaintain assetMaintain);

    /**
     * 批量删除资产维护
     * 
     * @param ids 需要删除的资产维护ID
     * @return 结果
     */
    public int deleteAssetMaintainByIds(Long[] ids);

    /**
     * 删除资产维护信息
     * 
     * @param id 资产维护ID
     * @return 结果
     */
    public int deleteAssetMaintainById(Long id);
    
    /**
     * 审核资产维护申请
     * 
     * @param assetMaintain 资产维护申请
     * @return 结果
     */
    public int checkAssetMaintain(AssetMaintain assetMaintain);
}
