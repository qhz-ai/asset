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
import com.ruoyi.asset.domain.AssetPurchase;
import com.ruoyi.asset.service.IAssetPurchaseService;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 采购申请Controller
 * 
 * @author yepanpan
 * @date 2021-03-05
 */
@RestController
@RequestMapping("/asset/purchase")
public class AssetPurchaseController extends BaseController
{
    @Autowired
    private IAssetPurchaseService assetPurchaseService;

    /**
     * 查询个人采购申请列表
     */
    @PreAuthorize("@ss.hasPermi('asset:purchase:my')")
    @GetMapping("/my")
    public TableDataInfo my(AssetPurchase assetPurchase)
    {
    	assetPurchase.setCreateBy(SecurityUtils.getUsername());
        startPage();
        List<AssetPurchase> list = assetPurchaseService.selectAssetPurchaseList(assetPurchase);
        return getDataTable(list);
    }

    /**
     * 查询个人采购任务列表
     */
    @PreAuthorize("@ss.hasPermi('asset:purchase:task')")
    @GetMapping("/task")
    public TableDataInfo task(AssetPurchase assetPurchase)
    {
    	assetPurchase.setMasterUserId(SecurityUtils.getLoginUser().getUser().getUserId());
    	assetPurchase.setCheckStatus("1");
        startPage();
        List<AssetPurchase> list = assetPurchaseService.selectAssetPurchaseList(assetPurchase);
        return getDataTable(list);
    }
    
    /**
     * 查询采购申请列表
     */
    @PreAuthorize("@ss.hasPermi('asset:purchase:list')")
    @GetMapping("/list")
    public TableDataInfo list(AssetPurchase assetPurchase)
    {
        startPage();
        List<AssetPurchase> list = assetPurchaseService.selectAssetPurchaseList(assetPurchase);
        return getDataTable(list);
    }

    /**
     * 导出采购申请列表
     */
    @PreAuthorize("@ss.hasPermi('asset:purchase:export')")
    @Log(title = "采购申请", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(AssetPurchase assetPurchase)
    {
        List<AssetPurchase> list = assetPurchaseService.selectAssetPurchaseList(assetPurchase);
        ExcelUtil<AssetPurchase> util = new ExcelUtil<AssetPurchase>(AssetPurchase.class);
        return util.exportExcel(list, "purchase");
    }

    /**
     * 获取采购申请详细信息
     */
    @PreAuthorize("@ss.hasPermi('asset:purchase:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(assetPurchaseService.selectAssetPurchaseById(id));
    }

    /**
     * 新增采购申请
     */
    @PreAuthorize("@ss.hasPermi('asset:purchase:add')")
    @Log(title = "采购申请", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody AssetPurchase assetPurchase)
    {
        return toAjax(assetPurchaseService.insertAssetPurchase(assetPurchase));
    }

    /**
     * 修改采购申请
     */
    @PreAuthorize("@ss.hasPermi('asset:purchase:edit')")
    @Log(title = "采购申请", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody AssetPurchase assetPurchase)
    {
        return toAjax(assetPurchaseService.updateAssetPurchase(assetPurchase));
    }
    

    /**
     * 审核采购申请
     */
    @PreAuthorize("@ss.hasPermi('asset:purchase:check')")
    @Log(title = "审核采购申请", businessType = BusinessType.UPDATE)
    @PutMapping("/check")
    public AjaxResult check(@RequestBody AssetPurchase assetPurchase)
    {
        return toAjax(assetPurchaseService.checkAssetPurchase(assetPurchase));
    }


    /**
     * 删除采购申请
     */
    @PreAuthorize("@ss.hasPermi('asset:purchase:remove')")
    @Log(title = "采购申请", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(assetPurchaseService.deleteAssetPurchaseByIds(ids));
    }
}
