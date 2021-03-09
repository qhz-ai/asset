package com.ruoyi.asset.service.impl;

import java.util.List;

import com.ruoyi.common.exception.CustomException;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ruoyi.asset.mapper.AssetInfoMapper;
import com.ruoyi.asset.mapper.AssetUseMapper;
import com.ruoyi.asset.domain.AssetInfo;
import com.ruoyi.asset.domain.AssetUse;
import com.ruoyi.asset.service.IAssetUseService;

/**
 * 使用申请Service业务层处理
 * 
 * @author yepanpan
 * @date 2021-03-05
 */
@Service
public class AssetUseServiceImpl implements IAssetUseService 
{
    @Autowired
    private AssetUseMapper assetUseMapper;
    @Autowired
    private AssetInfoMapper assetInfoMapper;

    /**
     * 查询使用申请
     * 
     * @param id 使用申请ID
     * @return 使用申请
     */
    @Override
    public AssetUse selectAssetUseById(Long id)
    {
        return assetUseMapper.selectAssetUseById(id);
    }

    /**
     * 查询使用申请列表
     * 
     * @param assetUse 使用申请
     * @return 使用申请
     */
    @Override
    public List<AssetUse> selectAssetUseList(AssetUse assetUse)
    {
        return assetUseMapper.selectAssetUseList(assetUse);
    }

    /**
     * 新增使用申请
     * 
     * @param assetUse 使用申请
     * @return 结果
     */
    @Override
    public int insertAssetUse(AssetUse assetUse)
    {
        assetUse.setCreateTime(DateUtils.getNowDate());
        assetUse.setCreateBy(SecurityUtils.getUsername());
        assetUse.setIsRevert("N");
        return assetUseMapper.insertAssetUse(assetUse);
    }

    /**
     * 修改使用申请
     * 
     * @param assetUse 使用申请
     * @return 结果
     */
    @Override
    public int updateAssetUse(AssetUse assetUse)
    {
        assetUse.setUpdateTime(DateUtils.getNowDate());
        assetUse.setUpdateBy(SecurityUtils.getUsername());
        return assetUseMapper.updateAssetUse(assetUse);
    }

    /**
     * 批量删除使用申请
     * 
     * @param ids 需要删除的使用申请ID
     * @return 结果
     */
    @Override
    public int deleteAssetUseByIds(Long[] ids)
    {
        return assetUseMapper.deleteAssetUseByIds(ids);
    }

    /**
     * 删除使用申请信息
     * 
     * @param id 使用申请ID
     * @return 结果
     */
    @Override
    public int deleteAssetUseById(Long id)
    {
        return assetUseMapper.deleteAssetUseById(id);
    }

    /**
     * 审核使用申请
     * 
     * @param assetUse 使用申请
     * @return 结果
     */
    @Override
    @Transactional
    public int checkAssetUse(AssetUse assetUse) {
    	assetUse.setCheckUserId(SecurityUtils.getLoginUser().getUser().getUserId());
    	assetUse.setCheckTime(DateUtils.getNowDate());
    	
    	//释放原来分配的资产
    	AssetUse old = assetUseMapper.selectAssetUseById(assetUse.getId());
    	if(old.getAssetId() != null) {
    		AssetInfo info = assetInfoMapper.selectAssetInfoById(old.getAssetId());
        	info.setStatus("1");
        	info.setFinishTime(DateUtils.getNowDate());
        	assetInfoMapper.updateAssetInfo(info);
    	}
    	
    	//锁定新分配的资产信息
    	if("1".equals(assetUse.getCheckStatus())) {
        	AssetInfo info = assetInfoMapper.selectAssetInfoById(assetUse.getAssetId());
        	info.setStatus("2");
        	info.setFinishTime(assetUse.getFinishTime());
        	assetInfoMapper.updateAssetInfo(info);
        	
        	assetUse.setIsDelivery("N");
    	}
    	
    	return assetUseMapper.updateAssetUse(assetUse);
    }
    

    /**
     * 归还使用
     * 
     * @param id 使用申请ID
     * @return 结果
     */
    @Override
    @Transactional
    public int revertAssetUse(Long id) {
    	AssetUse assetUse = assetUseMapper.selectAssetUseById(id);
    	if(assetUse == null || !"1".equals(assetUse.getCheckStatus())) {
    		throw new CustomException("使用申请不存在或者审核未通过");
    	}
    	
    	//释放资产信息
    	AssetInfo info = assetInfoMapper.selectAssetInfoById(assetUse.getAssetId());
    	info.setStatus("1");
    	info.setFinishTime(DateUtils.getNowDate());
    	assetInfoMapper.updateAssetInfo(info);
    	
    	assetUse.setIsRevert("Y");
    	assetUse.setRevertTime(DateUtils.getNowDate());
    	return assetUseMapper.updateAssetUse(assetUse);
    }
}
