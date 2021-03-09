package com.ruoyi.asset.mapper;

import java.util.List;
import com.ruoyi.asset.domain.AssetSales;

/**
 * 资产租售Mapper接口
 * 
 * @author yepanpan
 * @date 2021-03-05
 */
public interface AssetSalesMapper 
{
    /**
     * 查询资产租售
     * 
     * @param id 资产租售ID
     * @return 资产租售
     */
    public AssetSales selectAssetSalesById(Long id);

    /**
     * 查询资产租售列表
     * 
     * @param assetSales 资产租售
     * @return 资产租售集合
     */
    public List<AssetSales> selectAssetSalesList(AssetSales assetSales);

    /**
     * 新增资产租售
     * 
     * @param assetSales 资产租售
     * @return 结果
     */
    public int insertAssetSales(AssetSales assetSales);

    /**
     * 修改资产租售
     * 
     * @param assetSales 资产租售
     * @return 结果
     */
    public int updateAssetSales(AssetSales assetSales);

    /**
     * 删除资产租售
     * 
     * @param id 资产租售ID
     * @return 结果
     */
    public int deleteAssetSalesById(Long id);

    /**
     * 批量删除资产租售
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteAssetSalesByIds(Long[] ids);
}
