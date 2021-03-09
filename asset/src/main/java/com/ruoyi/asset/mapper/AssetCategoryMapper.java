package com.ruoyi.asset.mapper;

import java.util.List;
import com.ruoyi.asset.domain.AssetCategory;

/**
 * 资产分类Mapper接口
 * 
 * @author yepanpan
 * @date 2021-03-05
 */
public interface AssetCategoryMapper 
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
     * @param name 资产分类名称
     * @return 资产分类集合
     */
    public AssetCategory selectAssetCategoryByTitle(String title);
    

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
     * 删除资产分类
     * 
     * @param id 资产分类ID
     * @return 结果
     */
    public int deleteAssetCategoryById(Long id);

    /**
     * 批量删除资产分类
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteAssetCategoryByIds(Long[] ids);
}
