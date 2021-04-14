package com.ruoyi.asset.mapper;

import java.util.List;
import com.ruoyi.asset.domain.AssetTaking;

/**
 * 资产盘点Mapper接口
 * 
 * @author yepanpan
 * @date 2021-04-13
 */
public interface AssetTakingMapper 
{
    /**
     * 查询资产盘点
     * 
     * @param id 资产盘点ID
     * @return 资产盘点
     */
    public AssetTaking selectAssetTakingById(Long id);

    /**
     * 查询资产盘点列表
     * 
     * @param assetTaking 资产盘点
     * @return 资产盘点集合
     */
    public List<AssetTaking> selectAssetTakingList(AssetTaking assetTaking);

    /**
     * 新增资产盘点
     * 
     * @param assetTaking 资产盘点
     * @return 结果
     */
    public int insertAssetTaking(AssetTaking assetTaking);

    /**
     * 修改资产盘点
     * 
     * @param assetTaking 资产盘点
     * @return 结果
     */
    public int updateAssetTaking(AssetTaking assetTaking);

    /**
     * 删除资产盘点
     * 
     * @param id 资产盘点ID
     * @return 结果
     */
    public int deleteAssetTakingById(Long id);

    /**
     * 批量删除资产盘点
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteAssetTakingByIds(Long[] ids);
}
