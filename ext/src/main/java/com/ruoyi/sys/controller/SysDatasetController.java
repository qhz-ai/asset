package com.ruoyi.sys.controller;

import java.util.List;
import java.util.Map;

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

import com.github.pagehelper.PageInfo;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiImplicitParam;
import com.ruoyi.sys.domain.SysDataset;
import com.ruoyi.sys.service.ISysChartService;
import com.ruoyi.sys.service.ISysDatasetService;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 数据集Controller
 * 
 * @author yepanpan
 * @date 2020-12-04
 */
@Api("数据集信息管理")
@RestController
@RequestMapping("/sys/dataset")
public class SysDatasetController extends BaseController
{
    @Autowired
    private ISysDatasetService sysDatasetService;
    @Autowired
    private ISysChartService sysChartService;

    /**
     * 查询数据集列表
     */
    @ApiOperation("获取数据集列表")
    @PreAuthorize("@ss.hasPermi('sys:dataset:list')")
    @GetMapping("/list")
    public TableDataInfo list(SysDataset sysDataset)
    {
        startPage();
        List<SysDataset> list = sysDatasetService.selectSysDatasetList(sysDataset);
        return getDataTable(list);
    }
    
    /**
     * 查询数据
     */
    @ApiOperation("获取数据集列表")
    @PostMapping("/query")
    public AjaxResult query(@RequestBody SysDataset sysDataset)
    {
    	SysDataset ds = null;
    	if(sysDataset.getId() != null) ds = sysDatasetService.selectSysDatasetById(sysDataset.getId());
    	else if(sysDataset.getCode() != null) ds = sysDatasetService.selectSysDatasetByCode(sysDataset.getCode());
    	
    	if(ds == null) return AjaxResult.error("数据不存在");
    	ds.setParams(sysDataset.getParams()); 	
    	ds.setWhere(sysDataset.getWhere());
    	if("3".equals(ds.getType())) {
    		Float data = sysDatasetService.count(ds);   
    		return AjaxResult.success(data); 		
    	}else {
            startPage();
    		List<Map> list = sysDatasetService.query(ds);
    		AjaxResult rt = AjaxResult.success(list); 
    		rt.put("total", new PageInfo(list).getTotal());
    		return rt;
    	}
    }

    /**
     * 导出数据集数据
     */
    @ApiOperation("导出数据集数据")
    @PreAuthorize("@ss.hasPermi('sys:dataset:export')")
    @Log(title = "数据集", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(SysDataset sysDataset)
    {
    	SysDataset ds = null;
    	if(sysDataset.getId() != null) ds = sysDatasetService.selectSysDatasetById(sysDataset.getId());
    	else if(sysDataset.getCode() != null) ds = sysDatasetService.selectSysDatasetByCode(sysDataset.getCode());
    	
    	if(ds == null) return AjaxResult.error("数据不存在");
    	ds.setParams(sysDataset.getParams()); 	
    	ds.setWhere(sysDataset.getWhere());
    	
    	return AjaxResult.success(sysDatasetService.exports(ds));
    }

    /**
     * 获取数据集详细信息
     */
    @ApiOperation("获取数据集详细信息")
    @ApiImplicitParam(name = "id", value = "数据集ID", required = true, dataType = "int", paramType = "path")
    @PreAuthorize("@ss.hasPermi('sys:dataset:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(sysDatasetService.selectSysDatasetById(id));
    }

    /**
     * 新增数据集
     */
    @ApiOperation("新增数据集")
    @PreAuthorize("@ss.hasPermi('sys:dataset:add')")
    @Log(title = "数据集", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SysDataset sysDataset)
    {
        return toAjax(sysDatasetService.insertSysDataset(sysDataset));
    }

    /**
     * 修改数据集
     */
    @ApiOperation("修改数据集")
    @PreAuthorize("@ss.hasPermi('sys:dataset:edit')")
    @Log(title = "数据集", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysDataset sysDataset)
    {
        return toAjax(sysDatasetService.updateSysDataset(sysDataset));
    }

    /**
     * 删除数据集
     */
    @ApiOperation("删除数据集")
    @ApiImplicitParam(name = "id", value = "数据集ID", required = true, dataType = "int", paramType = "path")
    @PreAuthorize("@ss.hasPermi('sys:dataset:remove')")
    @Log(title = "数据集", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(sysDatasetService.deleteSysDatasetByIds(ids));
    }
}
