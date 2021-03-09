package com.ruoyi.asset.service;

import java.util.List;
import com.ruoyi.asset.domain.AssetDelivery;

/**
 * 出库管理Service接口
 * 
 * @author yepanpan
 * @date 2021-03-05
 */
public interface IAssetDeliveryService 
{
    /**
     * 查询出库管理
     * 
     * @param id 出库管理ID
     * @return 出库管理
     */
    public AssetDelivery selectAssetDeliveryById(Long id);

    /**
     * 查询出库管理列表
     * 
     * @param assetDelivery 出库管理
     * @return 出库管理集合
     */
    public List<AssetDelivery> selectAssetDeliveryList(AssetDelivery assetDelivery);

    /**
     * 新增出库管理
     * 
     * @param assetDelivery 出库管理
     * @return 结果
     */
    public int insertAssetDelivery(AssetDelivery assetDelivery);

    /**
     * 修改出库管理
     * 
     * @param assetDelivery 出库管理
     * @return 结果
     */
    public int updateAssetDelivery(AssetDelivery assetDelivery);

    /**
     * 批量删除出库管理
     * 
     * @param ids 需要删除的出库管理ID
     * @return 结果
     */
    public int deleteAssetDeliveryByIds(Long[] ids);

    /**
     * 删除出库管理信息
     * 
     * @param id 出库管理ID
     * @return 结果
     */
    public int deleteAssetDeliveryById(Long id);
}
