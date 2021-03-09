package com.ruoyi.asset.mapper;

import java.util.List;
import com.ruoyi.asset.domain.AssetMaterial;

/**
 * 耗材信息Mapper接口
 * 
 * @author yepanpan
 * @date 2021-03-05
 */
public interface AssetMaterialMapper 
{
    /**
     * 查询耗材信息
     * 
     * @param id 耗材信息ID
     * @return 耗材信息
     */
    public AssetMaterial selectAssetMaterialById(Long id);

    /**
     * 查询耗材信息
     * 
     * @param name 耗材名
     * @return 耗材信息
     */
    public AssetMaterial selectAssetMaterialByName(String name);

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
     * 删除耗材信息
     * 
     * @param id 耗材信息ID
     * @return 结果
     */
    public int deleteAssetMaterialById(Long id);

    /**
     * 批量删除耗材信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteAssetMaterialByIds(Long[] ids);
}
