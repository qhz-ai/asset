package com.ruoyi.asset.service.impl;

import java.util.List;

import com.ruoyi.common.exception.CustomException;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.asset.mapper.AssetPickupMapper;
import com.ruoyi.asset.domain.AssetPickup;
import com.ruoyi.asset.service.IAssetPickupService;

/**
 * 耗材领用Service业务层处理
 * 
 * @author yepanpan
 * @date 2021-03-05
 */
@Service
public class AssetPickupServiceImpl implements IAssetPickupService 
{
    @Autowired
    private AssetPickupMapper assetPickupMapper;

    /**
     * 查询耗材领用
     * 
     * @param id 耗材领用ID
     * @return 耗材领用
     */
    @Override
    public AssetPickup selectAssetPickupById(Long id)
    {
        return assetPickupMapper.selectAssetPickupById(id);
    }

    /**
     * 查询耗材领用列表
     * 
     * @param assetPickup 耗材领用
     * @return 耗材领用
     */
    @Override
    public List<AssetPickup> selectAssetPickupList(AssetPickup assetPickup)
    {
        return assetPickupMapper.selectAssetPickupList(assetPickup);
    }

    /**
     * 新增耗材领用
     * 
     * @param assetPickup 耗材领用
     * @return 结果
     */
    @Override
    public int insertAssetPickup(AssetPickup assetPickup)
    {
    	if(assetPickup.getAmount().floatValue() <= 0) {
    		throw new CustomException("数量不能小于0");
    	}
    	if(assetPickup.getApplyUserId() == null) {
    		assetPickup.setApplyUserId(SecurityUtils.getLoginUser().getUser().getUserId());
    	}
    	if(!"0".equals(assetPickup.getCheckStatus())) {
        	assetPickup.setCheckUserId(SecurityUtils.getLoginUser().getUser().getUserId());
        	assetPickup.setCheckTime(DateUtils.getNowDate());    		
    	}
        assetPickup.setCreateTime(DateUtils.getNowDate());
        assetPickup.setCreateBy(SecurityUtils.getUsername());
        return assetPickupMapper.insertAssetPickup(assetPickup);
    }

    /**
     * 修改耗材领用
     * 
     * @param assetPickup 耗材领用
     * @return 结果
     */
    @Override
    public int updateAssetPickup(AssetPickup assetPickup)
    {
    	if(assetPickup.getAmount().floatValue() <= 0) {
    		throw new CustomException("数量不能小于0");
    	}
    	
        assetPickup.setUpdateTime(DateUtils.getNowDate());
        assetPickup.setUpdateBy(SecurityUtils.getUsername());
        return assetPickupMapper.updateAssetPickup(assetPickup);
    }

    /**
     * 批量删除耗材领用
     * 
     * @param ids 需要删除的耗材领用ID
     * @return 结果
     */
    @Override
    public int deleteAssetPickupByIds(Long[] ids)
    {
        return assetPickupMapper.deleteAssetPickupByIds(ids);
    }

    /**
     * 删除耗材领用信息
     * 
     * @param id 耗材领用ID
     * @return 结果
     */
    @Override
    public int deleteAssetPickupById(Long id)
    {
        return assetPickupMapper.deleteAssetPickupById(id);
    }
    

    /**
     * 审核耗材领用
     * 
     * @param assetPickup 耗材领用
     * @return 结果
     */
    @Override
    public int checkAssetPickup(AssetPickup assetPickup) {
    	assetPickup.setCheckUserId(SecurityUtils.getLoginUser().getUser().getUserId());
    	assetPickup.setCheckTime(DateUtils.getNowDate());
    	if("1".equals(assetPickup.getCheckStatus())) {
        	assetPickup.setIsDelivery("N");
    	}
        return assetPickupMapper.updateAssetPickup(assetPickup);
    }
}
