package com.ruoyi.asset.service.impl;

import java.math.BigDecimal;
import java.util.List;

import com.ruoyi.common.exception.CustomException;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ruoyi.asset.mapper.AssetCategoryMapper;
import com.ruoyi.asset.mapper.AssetInfoMapper;
import com.ruoyi.asset.mapper.AssetMaterialMapper;
import com.ruoyi.asset.mapper.AssetSalesMapper;
import com.ruoyi.asset.domain.AssetCategory;
import com.ruoyi.asset.domain.AssetInfo;
import com.ruoyi.asset.domain.AssetMaterial;
import com.ruoyi.asset.domain.AssetSales;
import com.ruoyi.asset.service.IAssetSalesService;

/**
 * 资产租售Service业务层处理
 * 
 * @author yepanpan
 * @date 2021-03-05
 */
@Service
public class AssetSalesServiceImpl implements IAssetSalesService 
{
    @Autowired
    private AssetSalesMapper assetSalesMapper;
    @Autowired
    private AssetInfoMapper assetInfoMapper;
    @Autowired
    private AssetMaterialMapper assetMaterialMapper;
    @Autowired
    private AssetCategoryMapper assetCategoryMapper;

    /**
     * 查询资产租售
     * 
     * @param id 资产租售ID
     * @return 资产租售
     */
    @Override
    public AssetSales selectAssetSalesById(Long id)
    {
        return assetSalesMapper.selectAssetSalesById(id);
    }

    /**
     * 查询资产租售列表
     * 
     * @param assetSales 资产租售
     * @return 资产租售
     */
    @Override
    public List<AssetSales> selectAssetSalesList(AssetSales assetSales)
    {
        return assetSalesMapper.selectAssetSalesList(assetSales);
    }

    /**
     * 新增资产租售
     * 
     * @param assetSales 资产租售
     * @return 结果
     */
    @Override
    @Transactional
    public int insertAssetSales(AssetSales assetSales)
    {
    	if(assetSales.getCateId() == null) {
    		throw new  CustomException("必须选择分类");
    	}
    	
    	AssetCategory cate = assetCategoryMapper.selectAssetCategoryById(assetSales.getCateId());
    	if(cate == null) {
    		throw new  CustomException("分类信息不存在");
    	}
    	
    	//资产类可以租售
    	if("1".equals(cate.getType())) {
    		if(assetSales.getAssetId() == null) {
    			throw new  CustomException("必须选择具体资产");
    		}
    		
    		AssetInfo info = assetInfoMapper.selectAssetInfoById(assetSales.getAssetId());
    		if(info == null) {
    			throw new  CustomException("资产信息不存在");
    		}
    		if("2".equals(info.getStatus())) {
    			throw new  CustomException("在用的资产不能租售");
    		}
    		
    		if("1".equals(assetSales.getType())) {//出租
    			info.setStatus("7");
    		}else {//出售
    			info.setStatus("6");
    		}
    		assetInfoMapper.updateAssetInfo(info);
    		
    		assetSales.setAmount(new BigDecimal("1"));
    	}else if("2".equals(cate.getType())) {//耗材只能出售
    		if(assetSales.getMaterialId() == null) {
    			throw new  CustomException("必须选择具体耗材");
    		}
    		AssetMaterial material = assetMaterialMapper.selectAssetMaterialById(assetSales.getMaterialId());
    		if(material == null) {
    			throw new  CustomException("耗材信息不存在");
    		}
    		if(material.getAmount().floatValue() < assetSales.getAmount().floatValue()) {
    			throw new  CustomException("库存不足");
    		}
    		
    		//减库存
    		material.setAmount(material.getAmount().subtract(assetSales.getAmount()));
    		assetMaterialMapper.updateAssetMaterial(material);
    		
    		assetSales.setType("2");
    	}else {//无形资产
    		if(assetSales.getAptitudeId() == null) {
    			throw new  CustomException("必须选择无形资产");
    		}
    		assetSales.setAmount(new BigDecimal("1"));
    	}
        assetSales.setCreateTime(DateUtils.getNowDate());
        assetSales.setCreateBy(SecurityUtils.getUsername());
        return assetSalesMapper.insertAssetSales(assetSales);
    }

    /**
     * 修改资产租售
     * 
     * @param assetSales 资产租售
     * @return 结果
     */
    @Override
    public int updateAssetSales(AssetSales assetSales)
    {
        assetSales.setUpdateTime(DateUtils.getNowDate());
        assetSales.setUpdateBy(SecurityUtils.getUsername());
        return assetSalesMapper.updateAssetSales(assetSales);
    }

    /**
     * 批量删除资产租售
     * 
     * @param ids 需要删除的资产租售ID
     * @return 结果
     */
    @Override
    public int deleteAssetSalesByIds(Long[] ids)
    {
        return assetSalesMapper.deleteAssetSalesByIds(ids);
    }

    /**
     * 删除资产租售信息
     * 
     * @param id 资产租售ID
     * @return 结果
     */
    @Override
    public int deleteAssetSalesById(Long id)
    {
        return assetSalesMapper.deleteAssetSalesById(id);
    }
}
