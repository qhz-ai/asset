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
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiImplicitParam;
import com.ruoyi.asset.domain.AssetTaking;
import com.ruoyi.asset.service.IAssetTakingService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 资产盘点Controller
 * 
 * @author yepanpan
 * @date 2021-04-13
 */
@Api("资产盘点管理")
@RestController
@RequestMapping("/assets/taking")
public class AssetTakingController extends BaseController
{
    @Autowired
    private IAssetTakingService assetTakingService;

    /**
     * 查询资产盘点列表
     */
    @ApiOperation("查询资产盘点列表")   
    @PreAuthorize("@ss.hasPermi('assets:taking:list')")
    @GetMapping("/list")
    public TableDataInfo list(AssetTaking assetTaking)
    {
        startPage();
        List<AssetTaking> list = assetTakingService.selectAssetTakingList(assetTaking);
        return getDataTable(list);
    }

    /**
     * 导出资产盘点列表
     */
    @ApiOperation("导出资产盘点列表")    
    @PreAuthorize("@ss.hasPermi('assets:taking:export')")
    @Log(title = "资产盘点", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(AssetTaking assetTaking)
    {
        List<AssetTaking> list = assetTakingService.selectAssetTakingList(assetTaking);
        ExcelUtil<AssetTaking> util = new ExcelUtil<AssetTaking>(AssetTaking.class);
        return util.exportExcel(list, "taking");
    }

    /**
     * 获取资产盘点详细信息
     */
    @ApiOperation("获取资产盘点详细信息")
    @ApiImplicitParam(name = "id", value = "资产盘点ID", required = true, dataType = "int", paramType = "path") 
    @PreAuthorize("@ss.hasPermi('assets:taking:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(assetTakingService.selectAssetTakingById(id));
    }

    /**
     * 新增资产盘点
     */
    @ApiOperation("新增资产盘点")
    @PreAuthorize("@ss.hasPermi('assets:taking:add')")
    @Log(title = "资产盘点", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody AssetTaking assetTaking)
    {
        return toAjax(assetTakingService.insertAssetTaking(assetTaking));
    }

    /**
     * 修改资产盘点
     */
    @ApiOperation("修改资产盘点")  
    @PreAuthorize("@ss.hasPermi('assets:taking:edit')")
    @Log(title = "资产盘点", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody AssetTaking assetTaking)
    {
        return toAjax(assetTakingService.updateAssetTaking(assetTaking));
    }

    /**
     * 删除资产盘点
     */
    @ApiOperation("删除资产盘点")
    @ApiImplicitParam(name = "id", value = "资产盘点ID", required = true, dataType = "int", paramType = "path") 
    @PreAuthorize("@ss.hasPermi('assets:taking:remove')")
    @Log(title = "资产盘点", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(assetTakingService.deleteAssetTakingByIds(ids));
    }
}
