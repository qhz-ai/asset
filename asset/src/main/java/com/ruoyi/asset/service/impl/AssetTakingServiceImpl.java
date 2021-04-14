package com.ruoyi.asset.service.impl;

import java.util.List;

import com.ruoyi.common.exception.CustomException;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruoyi.asset.mapper.AssetInfoMapper;
import com.ruoyi.asset.mapper.AssetMaterialMapper;
import com.ruoyi.asset.mapper.AssetTakingMapper;
import com.ruoyi.asset.domain.AssetInfo;
import com.ruoyi.asset.domain.AssetMaterial;
import com.ruoyi.asset.domain.AssetTaking;
import com.ruoyi.asset.service.IAssetTakingService;

/**
 * 资产盘点Service业务层处理
 * 
 * @author yepanpan
 * @date 2021-04-13
 */
@Service
public class AssetTakingServiceImpl implements IAssetTakingService 
{
    @Autowired
    private AssetTakingMapper assetTakingMapper;
    @Autowired
    private AssetInfoMapper assetInfoMapper;
    @Autowired
    private AssetMaterialMapper assetMaterialMapper;

    /**
     * 查询资产盘点
     * 
     * @param id 资产盘点ID
     * @return 资产盘点
     */
    @Override
    public AssetTaking selectAssetTakingById(Long id)
    {
        return assetTakingMapper.selectAssetTakingById(id);
    }

    /**
     * 查询资产盘点列表
     * 
     * @param assetTaking 资产盘点
     * @return 资产盘点
     */
    @Override
    public List<AssetTaking> selectAssetTakingList(AssetTaking assetTaking)
    {
        return assetTakingMapper.selectAssetTakingList(assetTaking);
    }

    /**
     * 新增资产盘点
     * 
     * @param assetTaking 资产盘点
     * @return 结果
     */
    @Override
    public int insertAssetTaking(AssetTaking assetTaking)
    {
    	if(assetTaking.getAssetId() != null) {
    		AssetInfo info = assetInfoMapper.selectAssetInfoById(assetTaking.getAssetId());
    		if(info == null) {
    			throw new CustomException("资产不存在");
    		}
    		info.setHouseId(assetTaking.getHouseId());
    		info.setDeptId(assetTaking.getDeptId());
    		info.setUseUserId(assetTaking.getUserId());
    		info.setStatus(assetTaking.getStatus());
    		assetInfoMapper.updateAssetInfo(info);
    		
    		assetTaking.setAssetName(info.getName());
    	}
    	if(assetTaking.getMaterialId() != null) {
    		AssetMaterial material = assetMaterialMapper.selectAssetMaterialById(assetTaking.getMaterialId());
    		if(material == null) {
    			throw new CustomException("耗材信息不存在");
    		}
    		//减库存
    		material.setAmount(assetTaking.getAmount());
    		material.setHouseId(assetTaking.getHouseId());
    		assetMaterialMapper.updateAssetMaterial(material);
    		
    		assetTaking.setAssetName(material.getName());    		
    	}
    	
        assetTaking.setCreateTime(DateUtils.getNowDate());
        assetTaking.setCreateBy(SecurityUtils.getUsername());
        return assetTakingMapper.insertAssetTaking(assetTaking);
    }

    /**
     * 修改资产盘点
     * 
     * @param assetTaking 资产盘点
     * @return 结果
     */
    @Override
    public int updateAssetTaking(AssetTaking assetTaking)
    {
        assetTaking.setUpdateTime(DateUtils.getNowDate());
        assetTaking.setUpdateBy(SecurityUtils.getUsername());
        return assetTakingMapper.updateAssetTaking(assetTaking);
    }

    /**
     * 批量删除资产盘点
     * 
     * @param ids 需要删除的资产盘点ID
     * @return 结果
     */
    @Override
    public int deleteAssetTakingByIds(Long[] ids)
    {
        return assetTakingMapper.deleteAssetTakingByIds(ids);
    }

    /**
     * 删除资产盘点信息
     * 
     * @param id 资产盘点ID
     * @return 结果
     */
    @Override
    public int deleteAssetTakingById(Long id)
    {
        return assetTakingMapper.deleteAssetTakingById(id);
    }
}
