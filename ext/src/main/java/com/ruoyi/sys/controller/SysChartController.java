package com.ruoyi.sys.controller;

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
import com.ruoyi.sys.domain.SysChart;
import com.ruoyi.sys.service.ISysChartService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 报表管理Controller
 * 
 * @author yepanpan
 * @date 2020-12-04
 */
@Api("报表管理信息管理")
@RestController
@RequestMapping("/sys/chart")
public class SysChartController extends BaseController
{
    @Autowired
    private ISysChartService sysChartService;

    /**
     * 查询报表管理列表
     */
    @ApiOperation("获取报表管理列表")
    @PreAuthorize("@ss.hasPermi('sys:chart:list')")
    @GetMapping("/list")
    public TableDataInfo list(SysChart sysChart)
    {
        startPage();
        List<SysChart> list = sysChartService.selectSysChartList(sysChart);
        return getDataTable(list);
    }

    /**
     * 导出报表管理列表
     */
    @ApiOperation("导出报表管理列表")
    @PreAuthorize("@ss.hasPermi('sys:chart:export')")
    @Log(title = "报表管理", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(SysChart sysChart)
    {
        List<SysChart> list = sysChartService.selectSysChartList(sysChart);
        ExcelUtil<SysChart> util = new ExcelUtil<SysChart>(SysChart.class);
        return util.exportExcel(list, "chart");
    }

    /**
     * 获取报表管理详细信息
     */
    @ApiOperation("获取报表管理详细信息")
    @ApiImplicitParam(name = "id", value = "报表管理ID", required = true, dataType = "int", paramType = "path")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(sysChartService.selectSysChartById(id));
    }
    
    /**
     * 获取报表管理详细信息
     */
    @ApiOperation("获取报表管理详细信息")
    @ApiImplicitParam(name = "code", value = "报表代码", required = true, dataType = "string", paramType = "path")
    @GetMapping(value = "/code/{code}")
    public AjaxResult getByCode(@PathVariable("code") String id)
    {
        return AjaxResult.success(sysChartService.selectSysChartByCode(id));
    }

    /**
     * 新增报表管理
     */
    @ApiOperation("新增报表管理")
    @PreAuthorize("@ss.hasPermi('sys:chart:add')")
    @Log(title = "报表管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SysChart sysChart)
    {
        return toAjax(sysChartService.insertSysChart(sysChart));
    }

    /**
     * 修改报表管理
     */
    @ApiOperation("修改报表管理")
    @PreAuthorize("@ss.hasPermi('sys:chart:edit')")
    @Log(title = "报表管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysChart sysChart)
    {
        return toAjax(sysChartService.updateSysChart(sysChart));
    }

    /**
     * 删除报表管理
     */
    @ApiOperation("删除报表管理")
    @ApiImplicitParam(name = "id", value = "报表管理ID", required = true, dataType = "int", paramType = "path")
    @PreAuthorize("@ss.hasPermi('sys:chart:remove')")
    @Log(title = "报表管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(sysChartService.deleteSysChartByIds(ids));
    }
    
    
    /**
     * 加入报表菜单
     */
    @ApiOperation("加入报表菜单")
    @ApiImplicitParam(name = "id", value = "报表ID", required = true, dataType = "int", paramType = "path")
    @GetMapping(value = "/menu/{id}")
    public AjaxResult menu(@PathVariable("id") Long id)
    {
        return AjaxResult.success(sysChartService.addChartMenu(id));
    }
}
