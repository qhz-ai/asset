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
import com.ruoyi.asset.domain.AssetPurchase;
import com.ruoyi.asset.domain.AssetUse;
import com.ruoyi.asset.service.IAssetUseService;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 使用申请Controller
 * 
 * @author yepanpan
 * @date 2021-03-05
 */
@RestController
@RequestMapping("/asset/use")
public class AssetUseController extends BaseController
{
    @Autowired
    private IAssetUseService assetUseService;


    /**
     * 查询使用申请列表
     */
    @PreAuthorize("@ss.hasPermi('asset:use:my')")
    @GetMapping("/my")
    @DataScope(userAlias = "au")
    public TableDataInfo my(AssetUse assetUse)
    {
    	assetUse.setCreateBy(SecurityUtils.getUsername());
        startPage();
        List<AssetUse> list = assetUseService.selectAssetUseList(assetUse);
        return getDataTable(list);
    }

    
    /**
     * 查询使用申请列表
     */
    @PreAuthorize("@ss.hasPermi('asset:use:list')")
    @GetMapping("/list")
    @DataScope(deptAlias = "au")
    public TableDataInfo list(AssetUse assetUse)
    {
        startPage();
        List<AssetUse> list = assetUseService.selectAssetUseList(assetUse);
        return getDataTable(list);
    }

    /**
     * 导出使用申请列表
     */
    @PreAuthorize("@ss.hasPermi('asset:use:export')")
    @Log(title = "使用申请", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(AssetUse assetUse)
    {
        List<AssetUse> list = assetUseService.selectAssetUseList(assetUse);
        ExcelUtil<AssetUse> util = new ExcelUtil<AssetUse>(AssetUse.class);
        return util.exportExcel(list, "use");
    }

    /**
     * 获取使用申请详细信息
     */
    @PreAuthorize("@ss.hasPermi('asset:use:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(assetUseService.selectAssetUseById(id));
    }

    /**
     * 新增使用申请
     */
    @PreAuthorize("@ss.hasPermi('asset:use:add')")
    @Log(title = "使用申请", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody AssetUse assetUse)
    {
        return toAjax(assetUseService.insertAssetUse(assetUse));
    }

    /**
     * 修改使用申请
     */
    @PreAuthorize("@ss.hasPermi('asset:use:edit')")
    @Log(title = "使用申请", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody AssetUse assetUse)
    {
        return toAjax(assetUseService.updateAssetUse(assetUse));
    }

    /**
     * 删除使用申请
     */
    @PreAuthorize("@ss.hasPermi('asset:use:remove')")
    @Log(title = "使用申请", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(assetUseService.deleteAssetUseByIds(ids));
    }
    

    /**
     * 审核使用申请
     */
    @PreAuthorize("@ss.hasPermi('asset:use:check')")
    @Log(title = "审核使用申请", businessType = BusinessType.UPDATE)
    @PutMapping("/check")
    public AjaxResult check(@RequestBody AssetUse assetUse)
    {
        return toAjax(assetUseService.checkAssetUse(assetUse));
    }  

    /**
     * 归还使用
     */
    @PreAuthorize("@ss.hasPermi('asset:use:edit')")
    @Log(title = "归还使用", businessType = BusinessType.UPDATE)
    @GetMapping("/revert/{id}")
    public AjaxResult revert(@PathVariable Long id)
    {
        return toAjax(assetUseService.revertAssetUse(id));
    }
}
