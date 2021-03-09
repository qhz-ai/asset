package com.ruoyi.asset.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.asset.mapper.AssetAptitudeMapper;
import com.ruoyi.asset.domain.AssetAptitude;
import com.ruoyi.asset.service.IAssetAptitudeService;

/**
 * 无形资产Service业务层处理
 * 
 * @author yepanpan
 * @date 2021-03-05
 */
@Service
public class AssetAptitudeServiceImpl implements IAssetAptitudeService 
{
    @Autowired
    private AssetAptitudeMapper assetAptitudeMapper;

    /**
     * 查询无形资产
     * 
     * @param id 无形资产ID
     * @return 无形资产
     */
    @Override
    public AssetAptitude selectAssetAptitudeById(Long id)
    {
        return assetAptitudeMapper.selectAssetAptitudeById(id);
    }

    /**
     * 查询无形资产列表
     * 
     * @param assetAptitude 无形资产
     * @return 无形资产
     */
    @Override
    public List<AssetAptitude> selectAssetAptitudeList(AssetAptitude assetAptitude)
    {
        return assetAptitudeMapper.selectAssetAptitudeList(assetAptitude);
    }

    /**
     * 新增无形资产
     * 
     * @param assetAptitude 无形资产
     * @return 结果
     */
    @Override
    public int insertAssetAptitude(AssetAptitude assetAptitude)
    {
        assetAptitude.setCreateTime(DateUtils.getNowDate());
        assetAptitude.setCreateBy(SecurityUtils.getUsername());
        return assetAptitudeMapper.insertAssetAptitude(assetAptitude);
    }

    /**
     * 修改无形资产
     * 
     * @param assetAptitude 无形资产
     * @return 结果
     */
    @Override
    public int updateAssetAptitude(AssetAptitude assetAptitude)
    {
        assetAptitude.setUpdateTime(DateUtils.getNowDate());
        assetAptitude.setUpdateBy(SecurityUtils.getUsername());
        return assetAptitudeMapper.updateAssetAptitude(assetAptitude);
    }

    /**
     * 批量删除无形资产
     * 
     * @param ids 需要删除的无形资产ID
     * @return 结果
     */
    @Override
    public int deleteAssetAptitudeByIds(Long[] ids)
    {
        return assetAptitudeMapper.deleteAssetAptitudeByIds(ids);
    }

    /**
     * 删除无形资产信息
     * 
     * @param id 无形资产ID
     * @return 结果
     */
    @Override
    public int deleteAssetAptitudeById(Long id)
    {
        return assetAptitudeMapper.deleteAssetAptitudeById(id);
    }
}
