package com.ruoyi.asset.service;

import java.util.List;

import com.ruoyi.asset.domain.AssetPurchase;
import com.ruoyi.asset.domain.AssetUse;

/**
 * 使用申请Service接口
 * 
 * @author yepanpan
 * @date 2021-03-05
 */
public interface IAssetUseService 
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
     * 批量删除使用申请
     * 
     * @param ids 需要删除的使用申请ID
     * @return 结果
     */
    public int deleteAssetUseByIds(Long[] ids);

    /**
     * 删除使用申请信息
     * 
     * @param id 使用申请ID
     * @return 结果
     */
    public int deleteAssetUseById(Long id);
    
    /**
     * 审核使用申请
     * 
     * @param assetUse 使用申请
     * @return 结果
     */
    public int checkAssetUse(AssetUse assetUse);
    

    /**
     * 归还使用
     * 
     * @param id 使用申请ID
     * @return 结果
     */
    public int revertAssetUse(Long id);
}
