package com.ruoyi.asset.service.impl;

import java.util.List;

import com.ruoyi.common.exception.CustomException;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.sys.service.ISysMsgService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.asset.mapper.AssetPurchaseMapper;
import com.ruoyi.asset.domain.AssetPurchase;
import com.ruoyi.asset.service.IAssetPurchaseService;

/**
 * 采购申请Service业务层处理
 * 
 * @author yepanpan
 * @date 2021-03-05
 */
@Service
public class AssetPurchaseServiceImpl implements IAssetPurchaseService 
{
    @Autowired
    private AssetPurchaseMapper assetPurchaseMapper;
    @Autowired
    private ISysMsgService sysMsgService;

    /**
     * 查询采购申请
     * 
     * @param id 采购申请ID
     * @return 采购申请
     */
    @Override
    public AssetPurchase selectAssetPurchaseById(Long id)
    {
        return assetPurchaseMapper.selectAssetPurchaseById(id);
    }

    /**
     * 查询采购申请列表
     * 
     * @param assetPurchase 采购申请
     * @return 采购申请
     */
    @Override
    public List<AssetPurchase> selectAssetPurchaseList(AssetPurchase assetPurchase)
    {
        return assetPurchaseMapper.selectAssetPurchaseList(assetPurchase);
    }

    /**
     * 新增采购申请
     * 
     * @param assetPurchase 采购申请
     * @return 结果
     */
    @Override
    public int insertAssetPurchase(AssetPurchase assetPurchase)
    {
        assetPurchase.setCreateTime(DateUtils.getNowDate());
        assetPurchase.setCreateBy(SecurityUtils.getUsername());
        return assetPurchaseMapper.insertAssetPurchase(assetPurchase);
    }

    /**
     * 修改采购申请
     * 
     * @param assetPurchase 采购申请
     * @return 结果
     */
    @Override
    public int updateAssetPurchase(AssetPurchase assetPurchase)
    {
        assetPurchase.setUpdateTime(DateUtils.getNowDate());
        assetPurchase.setUpdateBy(SecurityUtils.getUsername());
        return assetPurchaseMapper.updateAssetPurchase(assetPurchase);
    }

    /**
     * 批量删除采购申请
     * 
     * @param ids 需要删除的采购申请ID
     * @return 结果
     */
    @Override
    public int deleteAssetPurchaseByIds(Long[] ids)
    {
        return assetPurchaseMapper.deleteAssetPurchaseByIds(ids);
    }

    /**
     * 删除采购申请信息
     * 
     * @param id 采购申请ID
     * @return 结果
     */
    @Override
    public int deleteAssetPurchaseById(Long id)
    {
        return assetPurchaseMapper.deleteAssetPurchaseById(id);
    }
    
    /**
     * 审核采购申请
     * 
     * @param assetPurchase 采购申请
     * @return 结果
     */
    public int checkAssetPurchase(AssetPurchase assetPurchase) {
    	if(assetPurchase.getMasterUserId() == null) {
    		throw new CustomException("必须选择采购负责人");
    	}
    	
    	long users[] = {assetPurchase.getMasterUserId()};
    	sysMsgService.sendMsg("采购通知", "2", "您有新的采购任务，要求在"+DateUtils.dateTime(assetPurchase.getFinishTime())+"前完成", users, "asset/purchase/task");
    	
    	assetPurchase.setCheckUserId(SecurityUtils.getLoginUser().getUser().getUserId());
    	assetPurchase.setCheckTime(DateUtils.getNowDate());
    	assetPurchase.setIsComplete("N");
    	return assetPurchaseMapper.updateAssetPurchase(assetPurchase);
    }
}
