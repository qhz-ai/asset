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
import com.ruoyi.asset.domain.AssetWarehouse;
import com.ruoyi.asset.service.IAssetWarehouseService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 仓库Controller
 * 
 * @author yepanpan
 * @date 2021-03-05
 */
@RestController
@RequestMapping("/asset/warehouse")
public class AssetWarehouseController extends BaseController
{
    @Autowired
    private IAssetWarehouseService assetWarehouseService;

    /**
     * 查询仓库列表
     */
    @GetMapping("/select")
    public TableDataInfo select(AssetWarehouse assetWarehouse)
    {
        startPage();
        List<AssetWarehouse> list = assetWarehouseService.selectAssetWarehouseList(assetWarehouse);
        return getDataTable(list);
    }
    /**
     * 查询仓库列表
     */
    @PreAuthorize("@ss.hasPermi('asset:warehouse:list')")
    @GetMapping("/list")
    public TableDataInfo list(AssetWarehouse assetWarehouse)
    {
        startPage();
        List<AssetWarehouse> list = assetWarehouseService.selectAssetWarehouseList(assetWarehouse);
        return getDataTable(list);
    }

    /**
     * 导出仓库列表
     */
    @PreAuthorize("@ss.hasPermi('asset:warehouse:export')")
    @Log(title = "仓库", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(AssetWarehouse assetWarehouse)
    {
        List<AssetWarehouse> list = assetWarehouseService.selectAssetWarehouseList(assetWarehouse);
        ExcelUtil<AssetWarehouse> util = new ExcelUtil<AssetWarehouse>(AssetWarehouse.class);
        return util.exportExcel(list, "warehouse");
    }

    /**
     * 获取仓库详细信息
     */
    @PreAuthorize("@ss.hasPermi('asset:warehouse:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(assetWarehouseService.selectAssetWarehouseById(id));
    }

    /**
     * 新增仓库
     */
    @PreAuthorize("@ss.hasPermi('asset:warehouse:add')")
    @Log(title = "仓库", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody AssetWarehouse assetWarehouse)
    {
        return toAjax(assetWarehouseService.insertAssetWarehouse(assetWarehouse));
    }

    /**
     * 修改仓库
     */
    @PreAuthorize("@ss.hasPermi('asset:warehouse:edit')")
    @Log(title = "仓库", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody AssetWarehouse assetWarehouse)
    {
        return toAjax(assetWarehouseService.updateAssetWarehouse(assetWarehouse));
    }

    /**
     * 删除仓库
     */
    @PreAuthorize("@ss.hasPermi('asset:warehouse:remove')")
    @Log(title = "仓库", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(assetWarehouseService.deleteAssetWarehouseByIds(ids));
    }
}
