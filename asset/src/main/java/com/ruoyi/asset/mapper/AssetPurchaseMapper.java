package com.ruoyi.asset.mapper;

import java.util.List;
import com.ruoyi.asset.domain.AssetPurchase;

/**
 * 采购申请Mapper接口
 * 
 * @author yepanpan
 * @date 2021-03-05
 */
public interface AssetPurchaseMapper 
{
    /**
     * 查询采购申请
     * 
     * @param id 采购申请ID
     * @return 采购申请
     */
    public AssetPurchase selectAssetPurchaseById(Long id);

    /**
     * 查询采购申请列表
     * 
     * @param assetPurchase 采购申请
     * @return 采购申请集合
     */
    public List<AssetPurchase> selectAssetPurchaseList(AssetPurchase assetPurchase);

    /**
     * 新增采购申请
     * 
     * @param assetPurchase 采购申请
     * @return 结果
     */
    public int insertAssetPurchase(AssetPurchase assetPurchase);

    /**
     * 修改采购申请
     * 
     * @param assetPurchase 采购申请
     * @return 结果
     */
    public int updateAssetPurchase(AssetPurchase assetPurchase);

     /**
     * 自动更新采购申请的完成状态
     * 
     * @param id 采购申请ID
     * @return 结果
     */
    public int updateAssetPurchaseComplete(Long id);
    
    /**
     * 删除采购申请
     * 
     * @param id 采购申请ID
     * @return 结果
     */
    public int deleteAssetPurchaseById(Long id);

    /**
     * 批量删除采购申请
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteAssetPurchaseByIds(Long[] ids);
}
