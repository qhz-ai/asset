package com.ruoyi.asset.service.impl;

import java.util.List;

import com.ruoyi.common.exception.CustomException;
import com.ruoyi.common.utils.CollectionUtils;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.ValidationUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ruoyi.asset.mapper.AssetCategoryMapper;
import com.ruoyi.asset.mapper.AssetMaterialMapper;
import com.ruoyi.asset.mapper.AssetTakingMapper;
import com.ruoyi.asset.mapper.AssetWarehouseMapper;
import com.ruoyi.asset.domain.AssetCategory;
import com.ruoyi.asset.domain.AssetMaterial;
import com.ruoyi.asset.domain.AssetTaking;
import com.ruoyi.asset.domain.AssetWarehouse;
import com.ruoyi.asset.service.IAssetMaterialService;

/**
 * 耗材信息Service业务层处理
 * 
 * @author yepanpan
 * @date 2021-03-05
 */
@Service
public class AssetMaterialServiceImpl implements IAssetMaterialService 
{
    private static final Logger log = LoggerFactory.getLogger(AssetMaterialServiceImpl.class);
    
    @Autowired
    private AssetTakingMapper assetTakingMapper;
    @Autowired
    private AssetCategoryMapper assetCategoryMapper;
    @Autowired
    private AssetWarehouseMapper assetWarehouseMapper;
    @Autowired
    private AssetMaterialMapper assetMaterialMapper;

    /**
     * 查询耗材信息
     * 
     * @param id 耗材信息ID
     * @return 耗材信息
     */
    @Override
    public AssetMaterial selectAssetMaterialById(Long id)
    {
        return assetMaterialMapper.selectAssetMaterialById(id);
    }

    /**
     * 查询耗材信息列表
     * 
     * @param assetMaterial 耗材信息
     * @return 耗材信息
     */
    @Override
    public List<AssetMaterial> selectAssetMaterialList(AssetMaterial assetMaterial)
    {
        return assetMaterialMapper.selectAssetMaterialList(assetMaterial);
    }

    /**
     * 新增耗材信息
     * 
     * @param assetMaterial 耗材信息
     * @return 结果
     */
    @Override
    public int insertAssetMaterial(AssetMaterial assetMaterial)
    {
        assetMaterial.setCreateTime(DateUtils.getNowDate());
        assetMaterial.setCreateBy(SecurityUtils.getUsername());
        return assetMaterialMapper.insertAssetMaterial(assetMaterial);
    }

    /**
     * 修改耗材信息
     * 
     * @param assetMaterial 耗材信息
     * @return 结果
     */
    @Override
    @Transactional
    public int updateAssetMaterial(AssetMaterial assetMaterial)
    {
    	if(assetMaterial.getParams().containsKey("taking") || Boolean.parseBoolean(assetMaterial.getParams().get("taking").toString())) {
    		AssetTaking assetTaking = new AssetTaking();
    		assetTaking.setCateId(assetMaterial.getCateId());
    		assetTaking.setMaterialId(assetMaterial.getId());
    		assetTaking.setAssetName(assetMaterial.getName());
    		assetTaking.setHouseId(assetMaterial.getHouseId());
    		assetTaking.setAmount(assetMaterial.getAmount());
    		assetTaking.setCreateTime(DateUtils.getNowDate());
    		assetTaking.setCreateBy(SecurityUtils.getUsername());
    		assetTakingMapper.insertAssetTaking(assetTaking);
    	}
    	
        assetMaterial.setUpdateTime(DateUtils.getNowDate());
        assetMaterial.setUpdateBy(SecurityUtils.getUsername());
        return assetMaterialMapper.updateAssetMaterial(assetMaterial);
    }

    /**
     * 批量删除耗材信息
     * 
     * @param ids 需要删除的耗材信息ID
     * @return 结果
     */
    @Override
    public int deleteAssetMaterialByIds(Long[] ids)
    {
        return assetMaterialMapper.deleteAssetMaterialByIds(ids);
    }

    /**
     * 删除耗材信息信息
     * 
     * @param id 耗材信息ID
     * @return 结果
     */
    @Override
    public int deleteAssetMaterialById(Long id)
    {
        return assetMaterialMapper.deleteAssetMaterialById(id);
    }
    

    /**
     * 导入耗材信息数据
     * 
     * @param list 耗材信息列表
     * @param isUpdateSupport 是否更新支持，如果已存在，则进行更新数据
     * @param operName 操作用户
     * @return 结果
     */
    @Transactional
    public String imports(List<AssetMaterial> list, Boolean isUpdateSupport, String operName) {
    	if (StringUtils.isNull(list) || list.size() == 0)
        {
            throw new CustomException("导入数据不能为空！");
        }
        int successNum = 0;
        int failureNum = 0;
        StringBuilder successMsg = new StringBuilder();
        StringBuilder failureMsg = new StringBuilder();
        for (AssetMaterial vo : list)
        {
        	List<String> vs = ValidationUtils.validate(vo);
        	if(vs != null) {
        		failureNum++;
                failureMsg.append("<br/>" + failureNum + "、耗材 " + vo.getName() + " 数据不完整："+ CollectionUtils.join(vs, ","));
                continue;
        	}
            try
            {
            	AssetCategory ac = assetCategoryMapper.selectAssetCategoryByTitle(vo.getCateName());
            	if(ac == null) {
            		throw new CustomException("耗材分类不存在！"+vo.getCateName());
            	}
            	
            	vo.setCateId(ac.getId());
            	

            	AssetWarehouse aw = assetWarehouseMapper.selectAssetWarehouseByTitle(vo.getHouseName());
            	if(aw == null) {
            		throw new CustomException("仓库不存在！"+vo.getHouseName());
            	}
            	
            	vo.setHouseId(aw.getId());        
            	
            	
            	//旧数据
            	AssetMaterial old = assetMaterialMapper.selectAssetMaterialByName(vo.getName());
                if (StringUtils.isNull(old))
                {
                    this.insertAssetMaterial(vo);
                    successNum++;
                    successMsg.append("<br/>" + successNum + "、耗材 " + vo.getName() + " 导入成功");
                }
                else if (isUpdateSupport)
                {
                    vo.setId(old.getId());
                    this.updateAssetMaterial(vo);
                    successNum++;
                    successMsg.append("<br/>" + successNum + "、耗材 " + vo.getName() + " 更新成功");
                }
                else
                {
                    failureNum++;
                    failureMsg.append("<br/>" + failureNum + "、耗材 " + vo.getName() + " 已存在");
                }
            }
            catch (Exception e)
            {
                failureNum++;
                String msg = "<br/>" + failureNum + "、耗材 " + vo.getName() + " 导入失败：";
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
}
