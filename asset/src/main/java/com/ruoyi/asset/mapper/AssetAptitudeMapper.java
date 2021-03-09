package com.ruoyi.asset.mapper;

import java.util.List;
import com.ruoyi.asset.domain.AssetAptitude;

/**
 * 无形资产Mapper接口
 * 
 * @author yepanpan
 * @date 2021-03-05
 */
public interface AssetAptitudeMapper 
{
    /**
     * 查询无形资产
     * 
     * @param id 无形资产ID
     * @return 无形资产
     */
    public AssetAptitude selectAssetAptitudeById(Long id);

    /**
     * 查询无形资产列表
     * 
     * @param assetAptitude 无形资产
     * @return 无形资产集合
     */
    public List<AssetAptitude> selectAssetAptitudeList(AssetAptitude assetAptitude);

    /**
     * 新增无形资产
     * 
     * @param assetAptitude 无形资产
     * @return 结果
     */
    public int insertAssetAptitude(AssetAptitude assetAptitude);

    /**
     * 修改无形资产
     * 
     * @param assetAptitude 无形资产
     * @return 结果
     */
    public int updateAssetAptitude(AssetAptitude assetAptitude);

    /**
     * 删除无形资产
     * 
     * @param id 无形资产ID
     * @return 结果
     */
    public int deleteAssetAptitudeById(Long id);

    /**
     * 批量删除无形资产
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteAssetAptitudeByIds(Long[] ids);
}
