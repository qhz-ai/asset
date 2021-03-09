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
import com.ruoyi.asset.domain.AssetDelivery;
import com.ruoyi.asset.service.IAssetDeliveryService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 出库管理Controller
 * 
 * @author yepanpan
 * @date 2021-03-05
 */
@RestController
@RequestMapping("/asset/delivery")
public class AssetDeliveryController extends BaseController
{
    @Autowired
    private IAssetDeliveryService assetDeliveryService;

    /**
     * 查询出库管理列表
     */
    @PreAuthorize("@ss.hasPermi('asset:delivery:list')")
    @GetMapping("/list")
    public TableDataInfo list(AssetDelivery assetDelivery)
    {
        startPage();
        List<AssetDelivery> list = assetDeliveryService.selectAssetDeliveryList(assetDelivery);
        return getDataTable(list);
    }

    /**
     * 导出出库管理列表
     */
    @PreAuthorize("@ss.hasPermi('asset:delivery:export')")
    @Log(title = "出库管理", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(AssetDelivery assetDelivery)
    {
        List<AssetDelivery> list = assetDeliveryService.selectAssetDeliveryList(assetDelivery);
        ExcelUtil<AssetDelivery> util = new ExcelUtil<AssetDelivery>(AssetDelivery.class);
        return util.exportExcel(list, "delivery");
    }

    /**
     * 获取出库管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('asset:delivery:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(assetDeliveryService.selectAssetDeliveryById(id));
    }

    /**
     * 新增出库管理
     */
    @PreAuthorize("@ss.hasPermi('asset:delivery:add')")
    @Log(title = "出库管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody AssetDelivery assetDelivery)
    {
        return toAjax(assetDeliveryService.insertAssetDelivery(assetDelivery));
    }

    /**
     * 修改出库管理
     */
    @PreAuthorize("@ss.hasPermi('asset:delivery:edit')")
    @Log(title = "出库管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody AssetDelivery assetDelivery)
    {
        return toAjax(assetDeliveryService.updateAssetDelivery(assetDelivery));
    }

    /**
     * 删除出库管理
     */
    @PreAuthorize("@ss.hasPermi('asset:delivery:remove')")
    @Log(title = "出库管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(assetDeliveryService.deleteAssetDeliveryByIds(ids));
    }
}
