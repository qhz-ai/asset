package com.ruoyi.asset.mapper;

import java.util.List;
import com.ruoyi.asset.domain.AssetMaintain;

/**
 * 资产维护Mapper接口
 * 
 * @author yepanpan
 * @date 2021-03-09
 */
public interface AssetMaintainMapper 
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
     * 删除资产维护
     * 
     * @param id 资产维护ID
     * @return 结果
     */
    public int deleteAssetMaintainById(Long id);

    /**
     * 批量删除资产维护
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteAssetMaintainByIds(Long[] ids);
}
