package com.ruoyi.asset.service;

import java.util.List;
import com.ruoyi.asset.domain.AssetCategory;

/**
 * 资产分类Service接口
 * 
 * @author yepanpan
 * @date 2021-03-05
 */
public interface IAssetCategoryService 
{
    /**
     * 查询资产分类
     * 
     * @param id 资产分类ID
     * @return 资产分类
     */
    public AssetCategory selectAssetCategoryById(Long id);

    /**
     * 查询资产分类列表
     * 
     * @param assetCategory 资产分类
     * @return 资产分类集合
     */
    public List<AssetCategory> selectAssetCategoryList(AssetCategory assetCategory);

    /**
     * 新增资产分类
     * 
     * @param assetCategory 资产分类
     * @return 结果
     */
    public int insertAssetCategory(AssetCategory assetCategory);

    /**
     * 修改资产分类
     * 
     * @param assetCategory 资产分类
     * @return 结果
     */
    public int updateAssetCategory(AssetCategory assetCategory);

    /**
     * 批量删除资产分类
     * 
     * @param ids 需要删除的资产分类ID
     * @return 结果
     */
    public int deleteAssetCategoryByIds(Long[] ids);

    /**
     * 删除资产分类信息
     * 
     * @param id 资产分类ID
     * @return 结果
     */
    public int deleteAssetCategoryById(Long id);
}
