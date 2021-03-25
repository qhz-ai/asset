package com.ruoyi.yun.controller;

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
import com.ruoyi.yun.domain.YunFolder;
import com.ruoyi.yun.service.IYunFolderService;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 云盘目录Controller
 * 
 * @author yepanpan
 * @date 2021-03-15
 */
@Api("云盘目录管理")
@RestController
@RequestMapping("/yun/folder")
public class YunFolderController extends BaseController
{
    @Autowired
    private IYunFolderService yunFolderService;
    
    /**
     * 查询云盘目录列表
     */
    @ApiOperation("查询云盘目录列表")   
    @PreAuthorize("@ss.hasPermi('yun:folder:my')")
    @GetMapping("/my")
    public TableDataInfo my(YunFolder yunFolder)
    {
    	yunFolder.setCreateBy(SecurityUtils.getUsername());
        List<YunFolder> list = yunFolderService.selectYunFolderList(yunFolder);
        return getDataTable(list);
    }
    
    /**
     * 查询云盘目录列表
     */
    @ApiOperation("查询云盘目录列表")   
    @PreAuthorize("@ss.hasPermi('yun:folder:list')")
    @GetMapping("/list")
    public TableDataInfo list(YunFolder yunFolder)
    {
        startPage();
        List<YunFolder> list = yunFolderService.selectYunFolderList(yunFolder);
        return getDataTable(list);
    }

    /**
     * 导出云盘目录列表
     */
    @ApiOperation("导出云盘目录列表")    
    @PreAuthorize("@ss.hasPermi('yun:folder:export')")
    @Log(title = "云盘目录", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(YunFolder yunFolder)
    {
        List<YunFolder> list = yunFolderService.selectYunFolderList(yunFolder);
        ExcelUtil<YunFolder> util = new ExcelUtil<YunFolder>(YunFolder.class);
        return util.exportExcel(list, "folder");
    }

    /**
     * 获取云盘目录详细信息
     */
    @ApiOperation("获取云盘目录详细信息")
    @ApiImplicitParam(name = "id", value = "云盘目录ID", required = true, dataType = "int", paramType = "path") 
    @PreAuthorize("@ss.hasPermi('yun:folder:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(yunFolderService.selectYunFolderById(id));
    }

    /**
     * 新增云盘目录
     */
    @ApiOperation("新增云盘目录")
    @PreAuthorize("@ss.hasPermi('yun:folder:add')")
    @Log(title = "云盘目录", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody YunFolder yunFolder)
    {
        return toAjax(yunFolderService.insertYunFolder(yunFolder));
    }

    /**
     * 修改云盘目录
     */
    @ApiOperation("修改云盘目录")  
    @PreAuthorize("@ss.hasPermi('yun:folder:edit')")
    @Log(title = "云盘目录", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody YunFolder yunFolder)
    {
        return toAjax(yunFolderService.updateYunFolder(yunFolder));
    }

    /**
     * 删除云盘目录
     */
    @ApiOperation("删除云盘目录")
    @ApiImplicitParam(name = "id", value = "云盘目录ID", required = true, dataType = "int", paramType = "path") 
    @PreAuthorize("@ss.hasPermi('yun:folder:remove')")
    @Log(title = "云盘目录", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(yunFolderService.deleteYunFolderByIds(ids));
    }
}
