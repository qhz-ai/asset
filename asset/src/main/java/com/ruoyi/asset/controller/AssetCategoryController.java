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
import com.ruoyi.asset.domain.AssetCategory;
import com.ruoyi.asset.service.IAssetCategoryService;
import com.ruoyi.common.utils.poi.ExcelUtil;

/**
 * 资产分类Controller
 * 
 * @author yepanpan
 * @date 2021-03-05
 */
@RestController
@RequestMapping("/asset/category")
public class AssetCategoryController extends BaseController
{
    @Autowired
    private IAssetCategoryService assetCategoryService;

    /**
     * 查询资产分类列表
     */
    @PreAuthorize("@ss.hasPermi('asset:category:list')")
    @GetMapping("/list")
    public AjaxResult list(AssetCategory assetCategory)
    {
        List<AssetCategory> list = assetCategoryService.selectAssetCategoryList(assetCategory);
        return AjaxResult.success(list);
    }

    /**
     * 导出资产分类列表
     */
    @PreAuthorize("@ss.hasPermi('asset:category:export')")
    @Log(title = "资产分类", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(AssetCategory assetCategory)
    {
        List<AssetCategory> list = assetCategoryService.selectAssetCategoryList(assetCategory);
        ExcelUtil<AssetCategory> util = new ExcelUtil<AssetCategory>(AssetCategory.class);
        return util.exportExcel(list, "category");
    }

    /**
     * 获取资产分类详细信息
     */
    @PreAuthorize("@ss.hasPermi('asset:category:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(assetCategoryService.selectAssetCategoryById(id));
    }

    /**
     * 新增资产分类
     */
    @PreAuthorize("@ss.hasPermi('asset:category:add')")
    @Log(title = "资产分类", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody AssetCategory assetCategory)
    {
        return toAjax(assetCategoryService.insertAssetCategory(assetCategory));
    }

    /**
     * 修改资产分类
     */
    @PreAuthorize("@ss.hasPermi('asset:category:edit')")
    @Log(title = "资产分类", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody AssetCategory assetCategory)
    {
        return toAjax(assetCategoryService.updateAssetCategory(assetCategory));
    }

    /**
     * 删除资产分类
     */
    @PreAuthorize("@ss.hasPermi('asset:category:remove')")
    @Log(title = "资产分类", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(assetCategoryService.deleteAssetCategoryByIds(ids));
    }
}
