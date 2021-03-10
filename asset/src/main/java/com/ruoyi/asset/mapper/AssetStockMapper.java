package com.ruoyi.asset.mapper;

import java.util.List;
import com.ruoyi.asset.domain.AssetStock;

/**
 * 资产盘点Mapper接口
 * 
 * @author yepanpan
 * @date 2021-03-10
 */
public interface AssetStockMapper 
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
     * 删除资产盘点
     * 
     * @param id 资产盘点ID
     * @return 结果
     */
    public int deleteAssetStockById(Long id);

    /**
     * 批量删除资产盘点
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteAssetStockByIds(Long[] ids);
    

    /**
     * 盘点现在有资产总数
     * 
     * @param year 资产盘点年份
     * @return 结果
     */
    public int sumAssetStockAssetCount(String year);
    
    /**
     * 盘点现在有资产总值
     * 
     * @param year 资产盘点年份
     * @return 结果
     */
    public int sumAssetStockAssetWorth(String year);
    

    /**
     * 盘点现在有耗材总数
     * 
     * @param year 资产盘点年份
     * @return 结果
     */
    public int sumAssetStockMaterialCount(String year);
    
    /**
     * 盘点现在有耗材总值
     * 
     * @param year 资产盘点年份
     * @return 结果
     */
    public int sumAssetStockMaterialWorth(String year);
    
    /**
     * 盘点报废资产总数
     * 
     * @param year 资产盘点年份
     * @return 结果
     */
    public int sumAssetStockScrapCount(String year);
    
    /**
     * 盘点报废资产总值
     * 
     * @param year 资产盘点年份
     * @return 结果
     */
    public int sumAssetStockScrapWorth(String year);

    /**
     * 盘点采购总次数
     * 
     * @param year 资产盘点年份
     * @return 结果
     */
    public int sumAssetStockPurchaseCount(String year);
    
    /**
     * 盘点采购资产总值
     * 
     * @param year 资产盘点年份
     * @return 结果
     */
    public int sumAssetStockPurchaseWorth(String year);
    

    /**
     * 计算维修总次数
     * 
     * @param year 资产盘点年份
     * @return 结果
     */
    public int sumAssetStockMaintainCount(String year);
    
    /**
     * 盘点现有资产和耗材总值
     * 
     * @param year 资产盘点年份
     * @return 结果
     */
    public int sumAssetStockWorth(String year);

}
