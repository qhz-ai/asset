package com.ruoyi.asset.service;

import java.util.List;

import com.ruoyi.asset.domain.AssetMaterial;

/**
 * 耗材信息Service接口
 * 
 * @author yepanpan
 * @date 2021-03-05
 */
public interface IAssetMaterialService 
{
    /**
     * 查询耗材信息
     * 
     * @param id 耗材信息ID
     * @return 耗材信息
     */
    public AssetMaterial selectAssetMaterialById(Long id);

    /**
     * 查询耗材信息列表
     * 
     * @param assetMaterial 耗材信息
     * @return 耗材信息集合
     */
    public List<AssetMaterial> selectAssetMaterialList(AssetMaterial assetMaterial);

    /**
     * 新增耗材信息
     * 
     * @param assetMaterial 耗材信息
     * @return 结果
     */
    public int insertAssetMaterial(AssetMaterial assetMaterial);

    /**
     * 修改耗材信息
     * 
     * @param assetMaterial 耗材信息
     * @return 结果
     */
    public int updateAssetMaterial(AssetMaterial assetMaterial);

    /**
     * 批量删除耗材信息
     * 
     * @param ids 需要删除的耗材信息ID
     * @return 结果
     */
    public int deleteAssetMaterialByIds(Long[] ids);

    /**
     * 删除耗材信息信息
     * 
     * @param id 耗材信息ID
     * @return 结果
     */
    public int deleteAssetMaterialById(Long id);
    

    /**
     * 导入耗材信息数据
     * 
     * @param list 耗材信息列表
     * @param isUpdateSupport 是否更新支持，如果已存在，则进行更新数据
     * @param operName 操作用户
     * @return 结果
     */
    public String imports(List<AssetMaterial> list, Boolean isUpdateSupport, String operName);
}
