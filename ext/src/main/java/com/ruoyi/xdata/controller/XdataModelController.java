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
import com.ruoyi.xdata.domain.XdataModel;
import com.ruoyi.xdata.service.IXdataModelService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 模型Controller
 * 
 * @author yepanpan
 * @date 2020-12-29
 */
@Api("模型信息管理")
@RestController
@RequestMapping("/xdata/model")
public class XdataModelController extends BaseController
{
    @Autowired
    private IXdataModelService xdataModelService;

    /**
     * 查询模型列表
     */
    @ApiOperation("获取模型列表")
    @PreAuthorize("@ss.hasPermi('xdata:model:list')")
    @GetMapping("/list")
    public TableDataInfo list(XdataModel xdataModel)
    {
        startPage();
        List<XdataModel> list = xdataModelService.selectXdataModelList(xdataModel);
        return getDataTable(list);
    }
    
    /**
     * 查询模型列表
     */
    @ApiOperation("获取模型列表")
    @GetMapping("/select")
    public TableDataInfo select(XdataModel xdataModel)
    {
        List<XdataModel> list = xdataModelService.selectXdataModelList(xdataModel);
        return getDataTable(list);
    }

    /**
     * 导出模型列表
     */
    @ApiOperation("导出模型列表")
    @PreAuthorize("@ss.hasPermi('xdata:model:export')")
    @Log(title = "模型", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(XdataModel xdataModel)
    {
        List<XdataModel> list = xdataModelService.selectXdataModelList(xdataModel);
        ExcelUtil<XdataModel> util = new ExcelUtil<XdataModel>(XdataModel.class);
        return util.exportExcel(list, "model");
    }

    /**
     * 获取模型详细信息
     */
    @ApiOperation("获取模型详细信息")
    @ApiImplicitParam(name = "id", value = "模型ID", required = true, dataType = "int", paramType = "path")
    @PreAuthorize("@ss.hasPermi('xdata:model:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(xdataModelService.selectXdataModelById(id));
    }

    /**
     * 新增模型
     */
    @ApiOperation("新增模型")
    @PreAuthorize("@ss.hasPermi('xdata:model:add')")
    @Log(title = "模型", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody XdataModel xdataModel)
    {
        return toAjax(xdataModelService.insertXdataModel(xdataModel));
    }

    /**
     * 修改模型
     */
    @ApiOperation("修改模型")
    @PreAuthorize("@ss.hasPermi('xdata:model:edit')")
    @Log(title = "模型", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody XdataModel xdataModel)
    {
        return toAjax(xdataModelService.updateXdataModel(xdataModel));
    }

    /**
     * 删除模型
     */
    @ApiOperation("删除模型")
    @ApiImplicitParam(name = "id", value = "模型ID", required = true, dataType = "int", paramType = "path")
    @PreAuthorize("@ss.hasPermi('xdata:model:remove')")
    @Log(title = "模型", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(xdataModelService.deleteXdataModelByIds(ids));
    }
}
