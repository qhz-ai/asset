package com.ruoyi.asset.service;

import java.util.List;
import com.ruoyi.asset.domain.AssetStock;

/**
 * 资产盘点Service接口
 * 
 * @author yepanpan
 * @date 2021-03-10
 */
public interface IAssetStockService 
{
    /**
     * 查询资产盘点
     * 
     * @param id 资产盘点ID
     * @return 资产盘点
     */
    public AssetStock selectAssetStockById(Long id);

    /**
     * 查询资产盘点列表
     * 
     * @param assetStock 资产盘点
     * @return 资产盘点集合
     */
    public List<AssetStock> selectAssetStockList(AssetStock assetStock);

    /**
     * 新增资产盘点
     * 
     * @param assetStock 资产盘点
     * @return 结果
     */
    public int insertAssetStock(AssetStock assetStock);

    /**
     * 修改资产盘点
     * 
     * @param assetStock 资产盘点
     * @return 结果
     */
    public int updateAssetStock(AssetStock assetStock);

    /**
     * 批量删除资产盘点
     * 
     * @param ids 需要删除的资产盘点ID
     * @return 结果
     */
    public int deleteAssetStockByIds(Long[] ids);

    /**
     * 删除资产盘点信息
     * 
     * @param id 资产盘点ID
     * @return 结果
     */
    public int deleteAssetStockById(Long id);
    

    /**
     * 盘点资产信息
     * 
     * @param stock AssetStock
     * @return 结果
     */
    public int countAssetStock(AssetStock stock);
}
