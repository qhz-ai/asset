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
import com.ruoyi.asset.domain.AssetAptitude;
import com.ruoyi.asset.service.IAssetAptitudeService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 无形资产Controller
 * 
 * @author yepanpan
 * @date 2021-03-05
 */
@RestController
@RequestMapping("/asset/aptitude")
public class AssetAptitudeController extends BaseController
{
    @Autowired
    private IAssetAptitudeService assetAptitudeService;

    /**
     * 查询无形资产列表
     */
    @PreAuthorize("@ss.hasPermi('asset:aptitude:list')")
    @GetMapping("/list")
    public TableDataInfo list(AssetAptitude assetAptitude)
    {
        startPage();
        List<AssetAptitude> list = assetAptitudeService.selectAssetAptitudeList(assetAptitude);
        return getDataTable(list);
    }

    /**
     * 导出无形资产列表
     */
    @PreAuthorize("@ss.hasPermi('asset:aptitude:export')")
    @Log(title = "无形资产", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(AssetAptitude assetAptitude)
    {
        List<AssetAptitude> list = assetAptitudeService.selectAssetAptitudeList(assetAptitude);
        ExcelUtil<AssetAptitude> util = new ExcelUtil<AssetAptitude>(AssetAptitude.class);
        return util.exportExcel(list, "aptitude");
    }

    /**
     * 获取无形资产详细信息
     */
    @PreAuthorize("@ss.hasPermi('asset:aptitude:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(assetAptitudeService.selectAssetAptitudeById(id));
    }

    /**
     * 新增无形资产
     */
    @PreAuthorize("@ss.hasPermi('asset:aptitude:add')")
    @Log(title = "无形资产", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody AssetAptitude assetAptitude)
    {
        return toAjax(assetAptitudeService.insertAssetAptitude(assetAptitude));
    }

    /**
     * 修改无形资产
     */
    @PreAuthorize("@ss.hasPermi('asset:aptitude:edit')")
    @Log(title = "无形资产", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody AssetAptitude assetAptitude)
    {
        return toAjax(assetAptitudeService.updateAssetAptitude(assetAptitude));
    }

    /**
     * 删除无形资产
     */
    @PreAuthorize("@ss.hasPermi('asset:aptitude:remove')")
    @Log(title = "无形资产", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(assetAptitudeService.deleteAssetAptitudeByIds(ids));
    }
}
