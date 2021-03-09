package com.ruoyi.asset.service.impl;

import java.util.List;

import com.ruoyi.common.exception.CustomException;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.sys.service.ISysMsgService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ruoyi.asset.mapper.AssetMaintainMapper;
import com.ruoyi.asset.domain.AssetMaintain;
import com.ruoyi.asset.service.IAssetMaintainService;

/**
 * 资产维护Service业务层处理
 * 
 * @author yepanpan
 * @date 2021-03-09
 */
@Service
public class AssetMaintainServiceImpl implements IAssetMaintainService 
{
    @Autowired
    private AssetMaintainMapper assetMaintainMapper;
    @Autowired
    private ISysMsgService sysMsgService;

    /**
     * 查询资产维护
     * 
     * @param id 资产维护ID
     * @return 资产维护
     */
    @Override
    public AssetMaintain selectAssetMaintainById(Long id)
    {
        return assetMaintainMapper.selectAssetMaintainById(id);
    }

    /**
     * 查询资产维护列表
     * 
     * @param assetMaintain 资产维护
     * @return 资产维护
     */
    @Override
    public List<AssetMaintain> selectAssetMaintainList(AssetMaintain assetMaintain)
    {
        return assetMaintainMapper.selectAssetMaintainList(assetMaintain);
    }

    /**
     * 新增资产维护
     * 
     * @param assetMaintain 资产维护
     * @return 结果
     */
    @Override
    public int insertAssetMaintain(AssetMaintain assetMaintain)
    {
        assetMaintain.setCreateTime(DateUtils.getNowDate());
        assetMaintain.setCreateBy(SecurityUtils.getUsername());
        return assetMaintainMapper.insertAssetMaintain(assetMaintain);
    }

    /**
     * 修改资产维护
     * 
     * @param assetMaintain 资产维护
     * @return 结果
     */
    @Override
    public int updateAssetMaintain(AssetMaintain assetMaintain)
    {
        assetMaintain.setUpdateTime(DateUtils.getNowDate());
        assetMaintain.setUpdateBy(SecurityUtils.getUsername());
        return assetMaintainMapper.updateAssetMaintain(assetMaintain);
    }

    /**
     * 批量删除资产维护
     * 
     * @param ids 需要删除的资产维护ID
     * @return 结果
     */
    @Override
    public int deleteAssetMaintainByIds(Long[] ids)
    {
        return assetMaintainMapper.deleteAssetMaintainByIds(ids);
    }

    /**
     * 删除资产维护信息
     * 
     * @param id 资产维护ID
     * @return 结果
     */
    @Override
    public int deleteAssetMaintainById(Long id)
    {
        return assetMaintainMapper.deleteAssetMaintainById(id);
    }

    /**
     * 审核资产维护申请
     * 
     * @param assetMaintain 资产维护申请
     * @return 结果
     */
    @Override
    @Transactional
    public int checkAssetMaintain(AssetMaintain assetMaintain) {
    	if(assetMaintain.getMasterUserId() == null) {
    		throw new CustomException("必须选择维护负责人");
    	}
    	
    	long users[] = {assetMaintain.getMasterUserId()};
    	sysMsgService.sendMsg("资产维护通知", "2", "您有新的资产维护任务，要求在"+DateUtils.dateTime(assetMaintain.getFinishTime())+"前完成", users, "asset/maintain/task");
    	
    	assetMaintain.setCheckUserId(SecurityUtils.getLoginUser().getUser().getUserId());
    	assetMaintain.setCheckTime(DateUtils.getNowDate());
    	assetMaintain.setIsComplete("N");
        return assetMaintainMapper.updateAssetMaintain(assetMaintain);
    }
}
