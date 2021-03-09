package com.ruoyi.asset.service.impl;

import java.util.List;

import com.ruoyi.common.exception.CustomException;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ruoyi.asset.mapper.AssetDeliveryMapper;
import com.ruoyi.asset.mapper.AssetInfoMapper;
import com.ruoyi.asset.mapper.AssetMaterialMapper;
import com.ruoyi.asset.mapper.AssetPickupMapper;
import com.ruoyi.asset.mapper.AssetUseMapper;
import com.ruoyi.asset.domain.AssetDelivery;
import com.ruoyi.asset.domain.AssetInfo;
import com.ruoyi.asset.domain.AssetMaterial;
import com.ruoyi.asset.domain.AssetPickup;
import com.ruoyi.asset.domain.AssetUse;
import com.ruoyi.asset.service.IAssetDeliveryService;

/**
 * 出库管理Service业务层处理
 * 
 * @author yepanpan
 * @date 2021-03-05
 */
@Service
public class AssetDeliveryServiceImpl implements IAssetDeliveryService 
{
    @Autowired
    private AssetUseMapper assetUseMapper;
    @Autowired
    private AssetPickupMapper assetPickupMapper;
    @Autowired
    private AssetDeliveryMapper assetDeliveryMapper;
    @Autowired
    private AssetInfoMapper assetInfoMapper;
    @Autowired
    private AssetMaterialMapper assetMaterialMapper;

    /**
     * 查询出库管理
     * 
     * @param id 出库管理ID
     * @return 出库管理
     */
    @Override
    public AssetDelivery selectAssetDeliveryById(Long id)
    {
        return assetDeliveryMapper.selectAssetDeliveryById(id);
    }

    /**
     * 查询出库管理列表
     * 
     * @param assetDelivery 出库管理
     * @return 出库管理
     */
    @Override
    public List<AssetDelivery> selectAssetDeliveryList(AssetDelivery assetDelivery)
    {
        return assetDeliveryMapper.selectAssetDeliveryList(assetDelivery);
    }

    /**
     * 新增出库管理
     * 
     * @param assetDelivery 出库管理
     * @return 结果
     */
    @Override
    @Transactional
    public int insertAssetDelivery(AssetDelivery assetDelivery)
    {
    	if(assetDelivery.getAssetId() != null) {
    		AssetInfo info = assetInfoMapper.selectAssetInfoById(assetDelivery.getAssetId());
    		if(info == null || !"1".equals(info.getStatus())) {
    			throw new CustomException("资产不存在或者已经被领用");
    		}
    		assetDelivery.setHouseId(info.getHouseId());
    		
    		AssetUse use = assetUseMapper.selectAssetUseById(assetDelivery.getApplyId());
    		if(use == null) {
    			throw new CustomException("没有使用申请信息");
    		}
    		if("Y".equalsIgnoreCase(use.getIsDelivery())) {
    			throw new CustomException("已经出库的不能重复出库");
    		}else {
    			use.setIsDelivery("Y");
    			assetUseMapper.updateAssetUse(use);
    		}
    	}
    	if(assetDelivery.getMaterialId() != null) {
    		AssetMaterial material = assetMaterialMapper.selectAssetMaterialById(assetDelivery.getMaterialId());
    		if(material == null || material.getAmount().floatValue() < assetDelivery.getAmount().floatValue()) {
    			throw new CustomException("耗材不不存在或者库存不足");
    		}
    		//减库存
    		material.setAmount(material.getAmount().subtract(assetDelivery.getAmount()));
    		assetMaterialMapper.updateAssetMaterial(material);
    		
    		assetDelivery.setHouseId(material.getHouseId());
    		
    		//更新出库状态
    		AssetPickup pickup = assetPickupMapper.selectAssetPickupById(assetDelivery.getApplyId());
    		if(pickup == null) {
    			throw new CustomException("没有领用申请信息");
    		}
    		if("Y".equalsIgnoreCase(pickup.getIsDelivery())) {
    			throw new CustomException("已经出库的不能重复出库");
    		}else {
    			pickup.setIsDelivery("Y");
    			assetPickupMapper.updateAssetPickup(pickup);
    		}
    	}
    	
        assetDelivery.setCreateTime(DateUtils.getNowDate());
        assetDelivery.setCreateBy(SecurityUtils.getUsername());
        return assetDeliveryMapper.insertAssetDelivery(assetDelivery);
    }

    /**
     * 修改出库管理
     * 
     * @param assetDelivery 出库管理
     * @return 结果
     */
    @Override
    public int updateAssetDelivery(AssetDelivery assetDelivery)
    {
        assetDelivery.setUpdateTime(DateUtils.getNowDate());
        assetDelivery.setUpdateBy(SecurityUtils.getUsername());
        return assetDeliveryMapper.updateAssetDelivery(assetDelivery);
    }

    /**
     * 批量删除出库管理
     * 
     * @param ids 需要删除的出库管理ID
     * @return 结果
     */
    @Override
    public int deleteAssetDeliveryByIds(Long[] ids)
    {
        return assetDeliveryMapper.deleteAssetDeliveryByIds(ids);
    }

    /**
     * 删除出库管理信息
     * 
     * @param id 出库管理ID
     * @return 结果
     */
    @Override
    public int deleteAssetDeliveryById(Long id)
    {
        return assetDeliveryMapper.deleteAssetDeliveryById(id);
    }
}
