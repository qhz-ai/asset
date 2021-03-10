package com.ruoyi.asset.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ruoyi.asset.mapper.AssetStockMapper;
import com.ruoyi.asset.domain.AssetStock;
import com.ruoyi.asset.service.IAssetStockService;

/**
 * 资产盘点Service业务层处理
 * 
 * @author yepanpan
 * @date 2021-03-10
 */
@Service
public class AssetStockServiceImpl implements IAssetStockService 
{
    @Autowired
    private AssetStockMapper assetStockMapper;

    /**
     * 查询资产盘点
     * 
     * @param id 资产盘点ID
     * @return 资产盘点
     */
    @Override
    public AssetStock selectAssetStockById(Long id)
    {
        return assetStockMapper.selectAssetStockById(id);
    }

    /**
     * 查询资产盘点列表
     * 
     * @param assetStock 资产盘点
     * @return 资产盘点
     */
    @Override
    public List<AssetStock> selectAssetStockList(AssetStock assetStock)
    {
        return assetStockMapper.selectAssetStockList(assetStock);
    }

    /**
     * 新增资产盘点
     * 
     * @param assetStock 资产盘点
     * @return 结果
     */
    @Override
    @Transactional
    public int insertAssetStock(AssetStock assetStock)
    {
        assetStock.setCreateTime(DateUtils.getNowDate());
        assetStock.setCreateBy(SecurityUtils.getUsername());
        assetStockMapper.insertAssetStock(assetStock);
        
        return this.countAssetStock(assetStock);
    }

    /**
     * 修改资产盘点
     * 
     * @param assetStock 资产盘点
     * @return 结果
     */
    @Override
    public int updateAssetStock(AssetStock assetStock)
    {
        assetStock.setUpdateTime(DateUtils.getNowDate());
        assetStock.setUpdateBy(SecurityUtils.getUsername());
        return assetStockMapper.updateAssetStock(assetStock);
    }

    /**
     * 批量删除资产盘点
     * 
     * @param ids 需要删除的资产盘点ID
     * @return 结果
     */
    @Override
    public int deleteAssetStockByIds(Long[] ids)
    {
        return assetStockMapper.deleteAssetStockByIds(ids);
    }

    /**
     * 删除资产盘点信息
     * 
     * @param id 资产盘点ID
     * @return 结果
     */
    @Override
    public int deleteAssetStockById(Long id)
    {
        return assetStockMapper.deleteAssetStockById(id);
    }
    

    /**
     * 盘点资产信息
     * 
     * @param stock AssetStock
     * @return 结果
     */
    @Override
    @Transactional
    public int countAssetStock(AssetStock stock) {
    	assetStockMapper.sumAssetStockAssetCount(stock.getYear());
    	assetStockMapper.sumAssetStockAssetWorth(stock.getYear());
    	assetStockMapper.sumAssetStockMaintainCount(stock.getYear());
    	assetStockMapper.sumAssetStockMaterialCount(stock.getYear());
    	assetStockMapper.sumAssetStockMaterialWorth(stock.getYear());
    	assetStockMapper.sumAssetStockPurchaseCount(stock.getYear());
    	assetStockMapper.sumAssetStockPurchaseWorth(stock.getYear());
    	assetStockMapper.sumAssetStockScrapCount(stock.getYear());
    	assetStockMapper.sumAssetStockScrapWorth(stock.getYear());
    	assetStockMapper.sumAssetStockWorth(stock.getYear());
    	return 1;
    }
}
