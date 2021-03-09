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
import com.ruoyi.xdata.domain.XdataClient;
import com.ruoyi.xdata.service.IXdataClientService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 应用Controller
 * 
 * @author yepanpan
 * @date 2020-12-29
 */
@Api("应用信息管理")
@RestController
@RequestMapping("/xdata/client")
public class XdataClientController extends BaseController
{
    @Autowired
    private IXdataClientService xdataClientService;

    /**
     * 查询应用列表
     */
    @ApiOperation("获取应用列表")
    @PreAuthorize("@ss.hasPermi('xdata:client:list')")
    @GetMapping("/list")
    public TableDataInfo list(XdataClient xdataClient)
    {
        startPage();
        List<XdataClient> list = xdataClientService.selectXdataClientList(xdataClient);
        return getDataTable(list);
    }
    

    /**
     * 查询应用列表
     */
    @ApiOperation("获取应用列表")
    @GetMapping("/select")
    public TableDataInfo select(XdataClient xdataClient)
    {
        List<XdataClient> list = xdataClientService.selectXdataClientList(xdataClient);
        return getDataTable(list);
    }

    /**
     * 导出应用列表
     */
    @ApiOperation("导出应用列表")
    @PreAuthorize("@ss.hasPermi('xdata:client:export')")
    @Log(title = "应用", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(XdataClient xdataClient)
    {
        List<XdataClient> list = xdataClientService.selectXdataClientList(xdataClient);
        ExcelUtil<XdataClient> util = new ExcelUtil<XdataClient>(XdataClient.class);
        return util.exportExcel(list, "client");
    }

    /**
     * 获取应用详细信息
     */
    @ApiOperation("获取应用详细信息")
    @ApiImplicitParam(name = "id", value = "应用ID", required = true, dataType = "int", paramType = "path")
    @PreAuthorize("@ss.hasPermi('xdata:client:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(xdataClientService.selectXdataClientById(id));
    }

    /**
     * 新增应用
     */
    @ApiOperation("新增应用")
    @PreAuthorize("@ss.hasPermi('xdata:client:add')")
    @Log(title = "应用", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody XdataClient xdataClient)
    {
        return toAjax(xdataClientService.insertXdataClient(xdataClient));
    }

    /**
     * 修改应用
     */
    @ApiOperation("修改应用")
    @PreAuthorize("@ss.hasPermi('xdata:client:edit')")
    @Log(title = "应用", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody XdataClient xdataClient)
    {
        return toAjax(xdataClientService.updateXdataClient(xdataClient));
    }

    /**
     * 删除应用
     */
    @ApiOperation("删除应用")
    @ApiImplicitParam(name = "id", value = "应用ID", required = true, dataType = "int", paramType = "path")
    @PreAuthorize("@ss.hasPermi('xdata:client:remove')")
    @Log(title = "应用", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(xdataClientService.deleteXdataClientByIds(ids));
    }
}
