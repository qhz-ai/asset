package com.ruoyi.asset.service;

import java.util.List;
import com.ruoyi.asset.domain.AssetWarehouse;

/**
 * 仓库Service接口
 * 
 * @author yepanpan
 * @date 2021-03-05
 */
public interface IAssetWarehouseService 
{
    /**
     * 查询仓库
     * 
     * @param id 仓库ID
     * @return 仓库
     */
    public AssetWarehouse selectAssetWarehouseById(Long id);

    /**
     * 查询仓库列表
     * 
     * @param assetWarehouse 仓库
     * @return 仓库集合
     */
    public List<AssetWarehouse> selectAssetWarehouseList(AssetWarehouse assetWarehouse);

    /**
     * 新增仓库
     * 
     * @param assetWarehouse 仓库
     * @return 结果
     */
    public int insertAssetWarehouse(AssetWarehouse assetWarehouse);

    /**
     * 修改仓库
     * 
     * @param assetWarehouse 仓库
     * @return 结果
     */
    public int updateAssetWarehouse(AssetWarehouse assetWarehouse);

    /**
     * 批量删除仓库
     * 
     * @param ids 需要删除的仓库ID
     * @return 结果
     */
    public int deleteAssetWarehouseByIds(Long[] ids);

    /**
     * 删除仓库信息
     * 
     * @param id 仓库ID
     * @return 结果
     */
    public int deleteAssetWarehouseById(Long id);
}
