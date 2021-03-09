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
import com.ruoyi.asset.domain.AssetEntering;
import com.ruoyi.asset.service.IAssetEnteringService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 入库管理Controller
 * 
 * @author yepanpan
 * @date 2021-03-05
 */
@RestController
@RequestMapping("/asset/entering")
public class AssetEnteringController extends BaseController
{
    @Autowired
    private IAssetEnteringService assetEnteringService;

    /**
     * 查询入库管理列表
     */
    @PreAuthorize("@ss.hasPermi('asset:entering:list')")
    @GetMapping("/list")
    public TableDataInfo list(AssetEntering assetEntering)
    {
        startPage();
        List<AssetEntering> list = assetEnteringService.selectAssetEnteringList(assetEntering);
        return getDataTable(list);
    }

    /**
     * 导出入库管理列表
     */
    @PreAuthorize("@ss.hasPermi('asset:entering:export')")
    @Log(title = "入库管理", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(AssetEntering assetEntering)
    {
        List<AssetEntering> list = assetEnteringService.selectAssetEnteringList(assetEntering);
        ExcelUtil<AssetEntering> util = new ExcelUtil<AssetEntering>(AssetEntering.class);
        return util.exportExcel(list, "entering");
    }

    /**
     * 获取入库管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('asset:entering:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(assetEnteringService.selectAssetEnteringById(id));
    }

    /**
     * 新增入库管理
     */
    @PreAuthorize("@ss.hasPermi('asset:entering:add')")
    @Log(title = "入库管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody AssetEntering assetEntering)
    {
        return toAjax(assetEnteringService.insertAssetEntering(assetEntering));
    }

    /**
     * 修改入库管理
     */
    @PreAuthorize("@ss.hasPermi('asset:entering:edit')")
    @Log(title = "入库管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody AssetEntering assetEntering)
    {
        return toAjax(assetEnteringService.updateAssetEntering(assetEntering));
    }

    /**
     * 删除入库管理
     */
    @PreAuthorize("@ss.hasPermi('asset:entering:remove')")
    @Log(title = "入库管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(assetEnteringService.deleteAssetEnteringByIds(ids));
    }
}
