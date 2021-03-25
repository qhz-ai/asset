package com.ruoyi.asset.service.impl;

import java.util.List;

import com.ruoyi.common.core.domain.entity.SysDept;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.exception.CustomException;
import com.ruoyi.common.utils.CollectionUtils;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.ValidationUtils;
import com.ruoyi.system.mapper.SysDeptMapper;
import com.ruoyi.system.mapper.SysUserMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ruoyi.asset.mapper.AssetCategoryMapper;
import com.ruoyi.asset.mapper.AssetInfoMapper;
import com.ruoyi.asset.mapper.AssetWarehouseMapper;
import com.ruoyi.asset.domain.AssetCategory;
import com.ruoyi.asset.domain.AssetInfo;
import com.ruoyi.asset.domain.AssetWarehouse;
import com.ruoyi.asset.domain.BatchInfo;
import com.ruoyi.asset.service.IAssetInfoService;

/**
 * 资产信息Service业务层处理
 * 
 * @author yepanpan
 * @date 2021-03-05
 */
@Service
public class AssetInfoServiceImpl implements IAssetInfoService 
{
    private static final Logger log = LoggerFactory.getLogger(AssetInfoServiceImpl.class);
    
    @Autowired
    private AssetCategoryMapper assetCategoryMapper;
    @Autowired
    private AssetWarehouseMapper assetWarehouseMapper;
    @Autowired
    private AssetInfoMapper assetInfoMapper;
    @Autowired
    private SysUserMapper sysUserMapper;
    @Autowired
    private SysDeptMapper sysDeptMapper;

    /**
     * 查询资产信息
     * 
     * @param id 资产信息ID
     * @return 资产信息
     */
    @Override
    public AssetInfo selectAssetInfoById(Long id)
    {
        return assetInfoMapper.selectAssetInfoById(id);
    }

    /**
     * 查询资产信息列表
     * 
     * @param assetInfo 资产信息
     * @return 资产信息
     */
    @Override
    public List<AssetInfo> selectAssetInfoList(AssetInfo assetInfo)
    {
        return assetInfoMapper.selectAssetInfoList(assetInfo);
    }

    /**
     * 新增资产信息
     * 
     * @param assetInfo 资产信息
     * @return 结果
     */
    @Override
    public int insertAssetInfo(AssetInfo assetInfo)
    {
        assetInfo.setCreateTime(DateUtils.getNowDate());
        assetInfo.setCreateBy(SecurityUtils.getUsername());
        return assetInfoMapper.insertAssetInfo(assetInfo);
    }

    /**
     * 修改资产信息
     * 
     * @param assetInfo 资产信息
     * @return 结果
     */
    @Override
    public int updateAssetInfo(AssetInfo assetInfo)
    {
        assetInfo.setUpdateTime(DateUtils.getNowDate());
        assetInfo.setUpdateBy(SecurityUtils.getUsername());
        return assetInfoMapper.updateAssetInfo(assetInfo);
    }

    /**
     * 批量删除资产信息
     * 
     * @param ids 需要删除的资产信息ID
     * @return 结果
     */
    @Override
    public int deleteAssetInfoByIds(Long[] ids)
    {
        return assetInfoMapper.deleteAssetInfoByIds(ids);
    }

    /**
     * 删除资产信息信息
     * 
     * @param id 资产信息ID
     * @return 结果
     */
    @Override
    public int deleteAssetInfoById(Long id)
    {
        return assetInfoMapper.deleteAssetInfoById(id);
    }
    

    /**
     * 导入资产信息数据
     * 
     * @param infoList 资产信息列表
     * @param isUpdateSupport 是否更新支持，如果已存在，则进行更新数据
     * @param operName 操作用户
     * @return 结果
     */
    @Transactional
    public String imports(List<AssetInfo> list, Boolean isUpdateSupport, String operName) {
    	if (StringUtils.isNull(list) || list.size() == 0)
        {
            throw new CustomException("导入数据不能为空！");
        }
        int successNum = 0;
        int failureNum = 0;
        StringBuilder successMsg = new StringBuilder();
        StringBuilder failureMsg = new StringBuilder();
        for (AssetInfo vo : list)
        {
        	List<String> vs = ValidationUtils.validate(vo);
        	if(vs != null) {
        		failureNum++;
                failureMsg.append("<br/>" + failureNum + "、资产 " + vo.getName() + " 数据不完整："+ CollectionUtils.join(vs, ","));
                continue;
        	}
            try
            {
            	AssetCategory ac = assetCategoryMapper.selectAssetCategoryByTitle(vo.getCateName());
            	if(ac == null) {
            		throw new CustomException("资产分类不存在！"+vo.getCateName());
            	}
            	
            	vo.setCateId(ac.getId());
            	

            	AssetWarehouse aw = assetWarehouseMapper.selectAssetWarehouseByTitle(vo.getHouseName());
            	if(aw == null) {
            		throw new CustomException("仓库不存在！"+vo.getHouseName());
            	}
            	
            	vo.setHouseId(aw.getId());        
            	
            	if(StringUtils.isNotBlank(vo.getDeptName())) {
            		SysDept dc = sysDeptMapper.selectDeptByName(vo.getDeptName());
                	if(dc == null) {
                		throw new CustomException("部门不存在:"+vo.getDeptName());
                	}
                	vo.setDeptId(dc.getDeptId());
            	}
            	
            	if(StringUtils.isNotBlank(vo.getUserName())) {
            		SysUser uc = new SysUser();
            		uc.setNickName(vo.getUserName());
                	List<SysUser> users = sysUserMapper.selectUser(uc);
                	if(users == null || users.size() == 0) {
                		throw new CustomException("用户不存在:"+vo.getUserName());
                	}
                	vo.setUseUserId(users.get(0).getUserId());
            	}
            	
            	//旧数据
            	AssetInfo old = assetInfoMapper.selectAssetInfoByNum(vo.getNum());
                if (StringUtils.isNull(old))
                {
                    this.insertAssetInfo(vo);
                    successNum++;
                    successMsg.append("<br/>" + successNum + "、资产 " + vo.getName() + " 导入成功");
                }
                else if (isUpdateSupport)
                {
                    vo.setId(old.getId());
                    this.updateAssetInfo(vo);
                    successNum++;
                    successMsg.append("<br/>" + successNum + "、资产 " + vo.getName() + " 更新成功");
                }
                else
                {
                    failureNum++;
                    failureMsg.append("<br/>" + failureNum + "、资产 " + vo.getName() + " 已存在");
                }
            }
            catch (Exception e)
            {
                failureNum++;
                String msg = "<br/>" + failureNum + "、资产 " + vo.getName() + " 导入失败：";
                failureMsg.append(msg + e.getMessage());
                log.error(msg, e);
            }
        }
        if (failureNum > 0)
        {
            failureMsg.insert(0, "很抱歉，导入失败！共 " + failureNum + " 条数据格式不正确，错误如下：");
            throw new CustomException(failureMsg.toString());
        }
        else
        {
            successMsg.insert(0, "恭喜您，数据已全部导入成功！共 " + successNum + " 条，数据如下：");
        }
        return successMsg.toString();
    }
    


    /**
     * 资产调拨
     * 
     * @param info BatchInfo 调拨信息
     * @return 结果
     */
    @Override
    public int moveInfo(BatchInfo info) {
    	return assetInfoMapper.moveInfo(info);
    }


    /**
     * 资产报废
     * 
     * @param info BatchInfo 资产信息
     * @return 结果
     */
    @Override
    public int scrapInfo(BatchInfo info) {
    	return assetInfoMapper.scrapInfo(info);
    }
}
