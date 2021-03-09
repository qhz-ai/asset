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
import com.ruoyi.asset.domain.AssetMaintain;
import com.ruoyi.asset.service.IAssetMaintainService;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 资产维护Controller
 * 
 * @author yepanpan
 * @date 2021-03-09
 */
@RestController
@RequestMapping("/asset/maintain")
public class AssetMaintainController extends BaseController
{
    @Autowired
    private IAssetMaintainService assetMaintainService;

    /**
     * 查询资产维护列表
     */
    @PreAuthorize("@ss.hasPermi('asset:maintain:my')")
    @GetMapping("/my")
    public TableDataInfo my(AssetMaintain assetMaintain)
    {
    	assetMaintain.setCreateBy(SecurityUtils.getUsername());
        startPage();
        List<AssetMaintain> list = assetMaintainService.selectAssetMaintainList(assetMaintain);
        return getDataTable(list);
    }

    /**
     * 查询资产维护列表
     */
    @PreAuthorize("@ss.hasPermi('asset:maintain:task')")
    @GetMapping("/task")
    public TableDataInfo task(AssetMaintain assetMaintain)
    {
    	assetMaintain.setMasterUserId(SecurityUtils.getLoginUser().getUser().getUserId());
    	assetMaintain.setCheckStatus("1");
        startPage();
        List<AssetMaintain> list = assetMaintainService.selectAssetMaintainList(assetMaintain);
        return getDataTable(list);
    }

    
    /**
     * 查询资产维护列表
     */
    @PreAuthorize("@ss.hasPermi('asset:maintain:list')")
    @GetMapping("/list")
    public TableDataInfo list(AssetMaintain assetMaintain)
    {
        startPage();
        List<AssetMaintain> list = assetMaintainService.selectAssetMaintainList(assetMaintain);
        return getDataTable(list);
    }

    /**
     * 导出资产维护列表
     */
    @PreAuthorize("@ss.hasPermi('asset:maintain:export')")
    @Log(title = "资产维护", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(AssetMaintain assetMaintain)
    {
        List<AssetMaintain> list = assetMaintainService.selectAssetMaintainList(assetMaintain);
        ExcelUtil<AssetMaintain> util = new ExcelUtil<AssetMaintain>(AssetMaintain.class);
        return util.exportExcel(list, "maintain");
    }

    /**
     * 获取资产维护详细信息
     */
    @PreAuthorize("@ss.hasPermi('asset:maintain:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(assetMaintainService.selectAssetMaintainById(id));
    }

    /**
     * 新增资产维护
     */
    @PreAuthorize("@ss.hasPermi('asset:maintain:add')")
    @Log(title = "资产维护", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody AssetMaintain assetMaintain)
    {
        return toAjax(assetMaintainService.insertAssetMaintain(assetMaintain));
    }

    /**
     * 修改资产维护
     */
    @PreAuthorize("@ss.hasPermi('asset:maintain:edit')")
    @Log(title = "资产维护", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody AssetMaintain assetMaintain)
    {
        return toAjax(assetMaintainService.updateAssetMaintain(assetMaintain));
    }

    /**
     * 删除资产维护
     */
    @PreAuthorize("@ss.hasPermi('asset:maintain:remove')")
    @Log(title = "资产维护", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(assetMaintainService.deleteAssetMaintainByIds(ids));
    }

    /**
     * 审核资产维护申请
     */
    @PreAuthorize("@ss.hasPermi('asset:maintain:check')")
    @Log(title = "审核资产维护申请", businessType = BusinessType.UPDATE)
    @PutMapping("/check")
    public AjaxResult check(@RequestBody AssetMaintain assetMaintain)
    {
        return toAjax(assetMaintainService.checkAssetMaintain(assetMaintain));
    }
}
