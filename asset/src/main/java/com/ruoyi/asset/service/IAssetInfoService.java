package com.ruoyi.asset.service;

import java.util.List;
import com.ruoyi.asset.domain.AssetInfo;
import com.ruoyi.asset.domain.BatchInfo;

/**
 * 资产信息Service接口
 * 
 * @author yepanpan
 * @date 2021-03-05
 */
public interface IAssetInfoService 
{
    /**
     * 查询资产信息
     * 
     * @param id 资产信息ID
     * @return 资产信息
     */
    public AssetInfo selectAssetInfoById(Long id);

    /**
     * 查询资产信息列表
     * 
     * @param assetInfo 资产信息
     * @return 资产信息集合
     */
    public List<AssetInfo> selectAssetInfoList(AssetInfo assetInfo);

    /**
     * 新增资产信息
     * 
     * @param assetInfo 资产信息
     * @return 结果
     */
    public int insertAssetInfo(AssetInfo assetInfo);

    /**
     * 修改资产信息
     * 
     * @param assetInfo 资产信息
     * @return 结果
     */
    public int updateAssetInfo(AssetInfo assetInfo);

    /**
     * 批量删除资产信息
     * 
     * @param ids 需要删除的资产信息ID
     * @return 结果
     */
    public int deleteAssetInfoByIds(Long[] ids);

    /**
     * 删除资产信息信息
     * 
     * @param id 资产信息ID
     * @return 结果
     */
    public int deleteAssetInfoById(Long id);
    

    /**
     * 导入资产信息数据
     * 
     * @param infoList 资产信息列表
     * @param isUpdateSupport 是否更新支持，如果已存在，则进行更新数据
     * @param operName 操作用户
     * @return 结果
     */
    public String imports(List<AssetInfo> list, Boolean isUpdateSupport, String operName);
    


    /**
     * 资产调拨
     * 
     * @param info BatchInfo 调拨信息
     * @return 结果
     */
    public int moveInfo(BatchInfo info);


    /**
     * 资产报废
     * 
     * @param info BatchInfo 资产信息
     * @return 结果
     */
    public int scrapInfo(BatchInfo info);
}
