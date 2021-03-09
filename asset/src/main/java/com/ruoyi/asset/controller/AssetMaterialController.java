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
import org.springframework.web.multipart.MultipartFile;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.asset.domain.AssetMaterial;
import com.ruoyi.asset.service.IAssetMaterialService;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 耗材信息Controller
 * 
 * @author yepanpan
 * @date 2021-03-05
 */
@RestController
@RequestMapping("/asset/material")
public class AssetMaterialController extends BaseController
{
    @Autowired
    private IAssetMaterialService assetMaterialService;

    /**
     * 查询耗材信息列表
     */
    @PreAuthorize("@ss.hasPermi('asset:material:list')")
    @GetMapping("/list")
    public TableDataInfo list(AssetMaterial assetMaterial)
    {
        startPage();
        List<AssetMaterial> list = assetMaterialService.selectAssetMaterialList(assetMaterial);
        return getDataTable(list);
    }

    /**
     * 导出耗材信息列表
     */
    @PreAuthorize("@ss.hasPermi('asset:material:export')")
    @Log(title = "耗材信息", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(AssetMaterial assetMaterial)
    {
        List<AssetMaterial> list = assetMaterialService.selectAssetMaterialList(assetMaterial);
        ExcelUtil<AssetMaterial> util = new ExcelUtil<AssetMaterial>(AssetMaterial.class);
        return util.exportExcel(list, "material");
    }

    /**
     * 获取耗材信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('asset:material:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(assetMaterialService.selectAssetMaterialById(id));
    }

    /**
     * 新增耗材信息
     */
    @PreAuthorize("@ss.hasPermi('asset:material:add')")
    @Log(title = "耗材信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody AssetMaterial assetMaterial)
    {
        return toAjax(assetMaterialService.insertAssetMaterial(assetMaterial));
    }

    /**
     * 修改耗材信息
     */
    @PreAuthorize("@ss.hasPermi('asset:material:edit')")
    @Log(title = "耗材信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody AssetMaterial assetMaterial)
    {
        return toAjax(assetMaterialService.updateAssetMaterial(assetMaterial));
    }

    /**
     * 删除耗材信息
     */
    @PreAuthorize("@ss.hasPermi('asset:material:remove')")
    @Log(title = "耗材信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(assetMaterialService.deleteAssetMaterialByIds(ids));
    }
    

    @Log(title = "导入耗材信息", businessType = BusinessType.IMPORT)
    @PreAuthorize("@ss.hasPermi('system:user:import')")
    @PostMapping("/importData")
    public AjaxResult importData(MultipartFile file, boolean updateSupport) throws Exception
    {
        ExcelUtil<AssetMaterial> util = new ExcelUtil<AssetMaterial>(AssetMaterial.class);
        List<AssetMaterial> list = util.importExcel(file.getInputStream());
        String operName = SecurityUtils.getUsername();
        String message = assetMaterialService.imports(list, updateSupport, operName);
        return AjaxResult.success(message);
    }

    @GetMapping("/importTemplate")
    public AjaxResult importTemplate()
    {
        ExcelUtil<AssetMaterial> util = new ExcelUtil<AssetMaterial>(AssetMaterial.class);
        return util.importTemplateExcel("耗材信息数据");
    }
}
