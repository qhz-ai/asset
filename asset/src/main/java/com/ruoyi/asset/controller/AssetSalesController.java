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
import com.ruoyi.asset.domain.AssetSales;
import com.ruoyi.asset.service.IAssetSalesService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 资产租售Controller
 * 
 * @author yepanpan
 * @date 2021-03-05
 */
@RestController
@RequestMapping("/asset/sales")
public class AssetSalesController extends BaseController
{
    @Autowired
    private IAssetSalesService assetSalesService;

    /**
     * 查询资产租售列表
     */
    @PreAuthorize("@ss.hasPermi('asset:sales:list')")
    @GetMapping("/list")
    public TableDataInfo list(AssetSales assetSales)
    {
        startPage();
        List<AssetSales> list = assetSalesService.selectAssetSalesList(assetSales);
        return getDataTable(list);
    }

    /**
     * 导出资产租售列表
     */
    @PreAuthorize("@ss.hasPermi('asset:sales:export')")
    @Log(title = "资产租售", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(AssetSales assetSales)
    {
        List<AssetSales> list = assetSalesService.selectAssetSalesList(assetSales);
        ExcelUtil<AssetSales> util = new ExcelUtil<AssetSales>(AssetSales.class);
        return util.exportExcel(list, "sales");
    }

    /**
     * 获取资产租售详细信息
     */
    @PreAuthorize("@ss.hasPermi('asset:sales:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(assetSalesService.selectAssetSalesById(id));
    }

    /**
     * 新增资产租售
     */
    @PreAuthorize("@ss.hasPermi('asset:sales:add')")
    @Log(title = "资产租售", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody AssetSales assetSales)
    {
        return toAjax(assetSalesService.insertAssetSales(assetSales));
    }

    /**
     * 修改资产租售
     */
    @PreAuthorize("@ss.hasPermi('asset:sales:edit')")
    @Log(title = "资产租售", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody AssetSales assetSales)
    {
        return toAjax(assetSalesService.updateAssetSales(assetSales));
    }

    /**
     * 删除资产租售
     */
    @PreAuthorize("@ss.hasPermi('asset:sales:remove')")
    @Log(title = "资产租售", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(assetSalesService.deleteAssetSalesByIds(ids));
    }
}
