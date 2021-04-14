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

import com.ruoyi.common.annotation.DataScope;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.asset.domain.AssetPickup;
import com.ruoyi.asset.domain.AssetUse;
import com.ruoyi.asset.service.IAssetPickupService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 耗材领用Controller
 * 
 * @author yepanpan
 * @date 2021-03-05
 */
@RestController
@RequestMapping("/asset/pickup")
public class AssetPickupController extends BaseController
{
    @Autowired
    private IAssetPickupService assetPickupService;

    /**
     * 查询耗材领用列表
     */
    @PreAuthorize("@ss.hasPermi('asset:pickup:list')")
    @GetMapping("/list")
    @DataScope(userAlias = "au", deptAlias = "au")
    public TableDataInfo list(AssetPickup assetPickup)
    {
        startPage();
        List<AssetPickup> list = assetPickupService.selectAssetPickupList(assetPickup);
        return getDataTable(list);
    }

    /**
     * 导出耗材领用列表
     */
    @PreAuthorize("@ss.hasPermi('asset:pickup:export')")
    @Log(title = "耗材领用", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(AssetPickup assetPickup)
    {
        List<AssetPickup> list = assetPickupService.selectAssetPickupList(assetPickup);
        ExcelUtil<AssetPickup> util = new ExcelUtil<AssetPickup>(AssetPickup.class);
        return util.exportExcel(list, "pickup");
    }

    /**
     * 获取耗材领用详细信息
     */
    @PreAuthorize("@ss.hasPermi('asset:pickup:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(assetPickupService.selectAssetPickupById(id));
    }

    /**
     * 新增耗材领用
     */
    @PreAuthorize("@ss.hasPermi('asset:pickup:add')")
    @Log(title = "耗材领用", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody AssetPickup assetPickup)
    {
        return toAjax(assetPickupService.insertAssetPickup(assetPickup));
    }

    /**
     * 修改耗材领用
     */
    @PreAuthorize("@ss.hasPermi('asset:pickup:edit')")
    @Log(title = "耗材领用", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody AssetPickup assetPickup)
    {
        return toAjax(assetPickupService.updateAssetPickup(assetPickup));
    }

    /**
     * 删除耗材领用
     */
    @PreAuthorize("@ss.hasPermi('asset:pickup:remove')")
    @Log(title = "耗材领用", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(assetPickupService.deleteAssetPickupByIds(ids));
    }
    

    /**
     * 审核耗材领用
     */
    @PreAuthorize("@ss.hasPermi('asset:use:check')")
    @Log(title = "审核耗材领用", businessType = BusinessType.UPDATE)
    @PutMapping("/check")
    public AjaxResult check(@RequestBody AssetPickup assetPickup)
    {
        return toAjax(assetPickupService.checkAssetPickup(assetPickup));
    }  
}
