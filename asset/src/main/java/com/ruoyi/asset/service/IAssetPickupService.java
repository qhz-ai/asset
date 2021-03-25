package com.ruoyi.asset.service;

import java.util.List;
import com.ruoyi.asset.domain.AssetPickup;
import com.ruoyi.asset.domain.BatchInfo;

/**
 * 耗材领用Service接口
 * 
 * @author yepanpan
 * @date 2021-03-05
 */
public interface IAssetPickupService 
{
    /**
     * 查询耗材领用
     * 
     * @param id 耗材领用ID
     * @return 耗材领用
     */
    public AssetPickup selectAssetPickupById(Long id);

    /**
     * 查询耗材领用列表
     * 
     * @param assetPickup 耗材领用
     * @return 耗材领用集合
     */
    public List<AssetPickup> selectAssetPickupList(AssetPickup assetPickup);

    /**
     * 新增耗材领用
     * 
     * @param assetPickup 耗材领用
     * @return 结果
     */
    public int insertAssetPickup(AssetPickup assetPickup);

    /**
     * 修改耗材领用
     * 
     * @param assetPickup 耗材领用
     * @return 结果
     */
    public int updateAssetPickup(AssetPickup assetPickup);

    /**
     * 批量删除耗材领用
     * 
     * @param ids 需要删除的耗材领用ID
     * @return 结果
     */
    public int deleteAssetPickupByIds(Long[] ids);

    /**
     * 删除耗材领用信息
     * 
     * @param id 耗材领用ID
     * @return 结果
     */
    public int deleteAssetPickupById(Long id);
    
    /**
     * 审核耗材领用
     * 
     * @param assetPickup 耗材领用
     * @return 结果
     */
    public int checkAssetPickup(AssetPickup assetPickup);
    
}
