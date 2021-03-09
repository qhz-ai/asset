package com.ruoyi.asset.mapper;

import java.util.List;
import com.ruoyi.asset.domain.AssetUse;

/**
 * 使用申请Mapper接口
 * 
 * @author yepanpan
 * @date 2021-03-05
 */
public interface AssetUseMapper 
{
    /**
     * 查询使用申请
     * 
     * @param id 使用申请ID
     * @return 使用申请
     */
    public AssetUse selectAssetUseById(Long id);

    /**
     * 查询使用申请列表
     * 
     * @param assetUse 使用申请
     * @return 使用申请集合
     */
    public List<AssetUse> selectAssetUseList(AssetUse assetUse);

    /**
     * 新增使用申请
     * 
     * @param assetUse 使用申请
     * @return 结果
     */
    public int insertAssetUse(AssetUse assetUse);

    /**
     * 修改使用申请
     * 
     * @param assetUse 使用申请
     * @return 结果
     */
    public int updateAssetUse(AssetUse assetUse);

    /**
     * 删除使用申请
     * 
     * @param id 使用申请ID
     * @return 结果
     */
    public int deleteAssetUseById(Long id);

    /**
     * 批量删除使用申请
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteAssetUseByIds(Long[] ids);
}
