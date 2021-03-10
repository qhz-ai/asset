package com.ruoyi.asset.controller;

import java.util.List;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.asset.domain.AssetStock;
import com.ruoyi.asset.service.IAssetStockService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 资产盘点Controller
 * 
 * @author yepanpan
 * @date 2021-03-10
 */
@RestController
@RequestMapping("/asset/stock")
public class AssetStockController extends BaseController
{
    @Autowired
    private IAssetStockService assetStockService;

    /**
     * 查询资产盘点列表
     */
    @PreAuthorize("@ss.hasPermi('asset:stock:list')")
    @GetMapping("/list")
    public TableDataInfo list(AssetStock assetStock)
    {
        startPage();
        List<AssetStock> list = assetStockService.selectAssetStockList(assetStock);
        return getDataTable(list);
    }

    /**
     * 导出资产盘点列表
     */
    @PreAuthorize("@ss.hasPermi('asset:stock:export')")
    @Log(title = "资产盘点", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(AssetStock assetStock)
    {
        List<AssetStock> list = assetStockService.selectAssetStockList(assetStock);
        ExcelUtil<AssetStock> util = new ExcelUtil<AssetStock>(AssetStock.class);
        return util.exportExcel(list, "stock");
    }

    /**
     * 获取资产盘点详细信息
     */
    @PreAuthorize("@ss.hasPermi('asset:stock:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(assetStockService.selectAssetStockById(id));
    }

    /**
     * 新增资产盘点
     */
    @PreAuthorize("@ss.hasPermi('asset:stock:add')")
    @Log(title = "资产盘点", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody AssetStock assetStock)
    {
        return toAjax(assetStockService.insertAssetStock(assetStock));
    }

    /**
     * 修改资产盘点
     */
    @PreAuthorize("@ss.hasPermi('asset:stock:edit')")
    @Log(title = "资产盘点", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody AssetStock assetStock)
    {
        return toAjax(assetStockService.updateAssetStock(assetStock));
    }

    /**
     * 删除资产盘点
     */
    @PreAuthorize("@ss.hasPermi('asset:stock:remove')")
    @Log(title = "资产盘点", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(assetStockService.deleteAssetStockByIds(ids));
    }
    
    /**
     * 自动盘点资产信息
     */
    @PreAuthorize("@ss.hasPermi('asset:stock:edit')")
    @GetMapping(value = "/count/{id}")
    public AjaxResult count(@PathVariable("id") Long id)
    {
    	AssetStock assetStock = assetStockService.selectAssetStockById(id);
        return toAjax(assetStockService.countAssetStock(assetStock));
    }
}
