package com.ruoyi.asset.mapper;

import java.util.List;
import com.ruoyi.asset.domain.AssetEntering;

/**
 * 入库管理Mapper接口
 * 
 * @author yepanpan
 * @date 2021-03-05
 */
public interface AssetEnteringMapper 
{
    /**
     * 查询入库管理
     * 
     * @param id 入库管理ID
     * @return 入库管理
     */
    public AssetEntering selectAssetEnteringById(Long id);

    /**
     * 查询入库管理列表
     * 
     * @param assetEntering 入库管理
     * @return 入库管理集合
     */
    public List<AssetEntering> selectAssetEnteringList(AssetEntering assetEntering);

    /**
     * 新增入库管理
     * 
     * @param assetEntering 入库管理
     * @return 结果
     */
    public int insertAssetEntering(AssetEntering assetEntering);

    /**
     * 修改入库管理
     * 
     * @param assetEntering 入库管理
     * @return 结果
     */
    public int updateAssetEntering(AssetEntering assetEntering);

    /**
     * 删除入库管理
     * 
     * @param id 入库管理ID
     * @return 结果
     */
    public int deleteAssetEnteringById(Long id);

    /**
     * 批量删除入库管理
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteAssetEnteringByIds(Long[] ids);
}
