package com.ruoyi.asset.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.asset.mapper.AssetWarehouseMapper;
import com.ruoyi.asset.domain.AssetWarehouse;
import com.ruoyi.asset.service.IAssetWarehouseService;

/**
 * 仓库Service业务层处理
 * 
 * @author yepanpan
 * @date 2021-03-05
 */
@Service
public class AssetWarehouseServiceImpl implements IAssetWarehouseService 
{
    @Autowired
    private AssetWarehouseMapper assetWarehouseMapper;

    /**
     * 查询仓库
     * 
     * @param id 仓库ID
     * @return 仓库
     */
    @Override
    public AssetWarehouse selectAssetWarehouseById(Long id)
    {
        return assetWarehouseMapper.selectAssetWarehouseById(id);
    }

    /**
     * 查询仓库列表
     * 
     * @param assetWarehouse 仓库
     * @return 仓库
     */
    @Override
    public List<AssetWarehouse> selectAssetWarehouseList(AssetWarehouse assetWarehouse)
    {
        return assetWarehouseMapper.selectAssetWarehouseList(assetWarehouse);
    }

    /**
     * 新增仓库
     * 
     * @param assetWarehouse 仓库
     * @return 结果
     */
    @Override
    public int insertAssetWarehouse(AssetWarehouse assetWarehouse)
    {
        assetWarehouse.setCreateTime(DateUtils.getNowDate());
        assetWarehouse.setCreateBy(SecurityUtils.getUsername());
        return assetWarehouseMapper.insertAssetWarehouse(assetWarehouse);
    }

    /**
     * 修改仓库
     * 
     * @param assetWarehouse 仓库
     * @return 结果
     */
    @Override
    public int updateAssetWarehouse(AssetWarehouse assetWarehouse)
    {
        assetWarehouse.setUpdateTime(DateUtils.getNowDate());
        assetWarehouse.setUpdateBy(SecurityUtils.getUsername());
        return assetWarehouseMapper.updateAssetWarehouse(assetWarehouse);
    }

    /**
     * 批量删除仓库
     * 
     * @param ids 需要删除的仓库ID
     * @return 结果
     */
    @Override
    public int deleteAssetWarehouseByIds(Long[] ids)
    {
        return assetWarehouseMapper.deleteAssetWarehouseByIds(ids);
    }

    /**
     * 删除仓库信息
     * 
     * @param id 仓库ID
     * @return 结果
     */
    @Override
    public int deleteAssetWarehouseById(Long id)
    {
        return assetWarehouseMapper.deleteAssetWarehouseById(id);
    }
}
