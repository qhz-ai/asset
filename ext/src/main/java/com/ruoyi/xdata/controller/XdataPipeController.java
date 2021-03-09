package com.ruoyi.xdata.controller;

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
import com.ruoyi.xdata.domain.XdataPipe;
import com.ruoyi.xdata.service.IXdataPipeService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 通道Controller
 * 
 * @author yepanpan
 * @date 2020-12-29
 */
@Api("通道信息管理")
@RestController
@RequestMapping("/xdata/pipe")
public class XdataPipeController extends BaseController
{
    @Autowired
    private IXdataPipeService xdataPipeService;

    /**
     * 查询通道列表
     */
    @ApiOperation("获取通道列表")
    @PreAuthorize("@ss.hasPermi('xdata:pipe:list')")
    @GetMapping("/list")
    public TableDataInfo list(XdataPipe xdataPipe)
    {
        startPage();
        List<XdataPipe> list = xdataPipeService.selectXdataPipeList(xdataPipe);
        return getDataTable(list);
    }

    /**
     * 导出通道列表
     */
    @ApiOperation("导出通道列表")
    @PreAuthorize("@ss.hasPermi('xdata:pipe:export')")
    @Log(title = "通道", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(XdataPipe xdataPipe)
    {
        List<XdataPipe> list = xdataPipeService.selectXdataPipeList(xdataPipe);
        ExcelUtil<XdataPipe> util = new ExcelUtil<XdataPipe>(XdataPipe.class);
        return util.exportExcel(list, "pipe");
    }

    /**
     * 获取通道详细信息
     */
    @ApiOperation("获取通道详细信息")
    @ApiImplicitParam(name = "id", value = "通道ID", required = true, dataType = "int", paramType = "path")
    @PreAuthorize("@ss.hasPermi('xdata:pipe:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(xdataPipeService.selectXdataPipeById(id));
    }

    /**
     * 新增通道
     */
    @ApiOperation("新增通道")
    @PreAuthorize("@ss.hasPermi('xdata:pipe:add')")
    @Log(title = "通道", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody XdataPipe xdataPipe)
    {
        return toAjax(xdataPipeService.insertXdataPipe(xdataPipe));
    }

    /**
     * 修改通道
     */
    @ApiOperation("修改通道")
    @PreAuthorize("@ss.hasPermi('xdata:pipe:edit')")
    @Log(title = "通道", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody XdataPipe xdataPipe)
    {
        return toAjax(xdataPipeService.updateXdataPipe(xdataPipe));
    }

    /**
     * 删除通道
     */
    @ApiOperation("删除通道")
    @ApiImplicitParam(name = "id", value = "通道ID", required = true, dataType = "int", paramType = "path")
    @PreAuthorize("@ss.hasPermi('xdata:pipe:remove')")
    @Log(title = "通道", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(xdataPipeService.deleteXdataPipeByIds(ids));
    }
    
    /**
     * 执行通道
     */
    @ApiOperation("执行通道")
    @ApiImplicitParam(name = "id", value = "通道ID", required = true, dataType = "int", paramType = "path")
    @Log(title = "通道", businessType = BusinessType.UPDATE)
	@GetMapping("/run/{id}")
    public AjaxResult run(@PathVariable Long id)
    {
        return toAjax(xdataPipeService.run(id));
    }
}
