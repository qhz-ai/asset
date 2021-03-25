package com.ruoyi.yun.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import com.ruoyi.common.config.RuoYiConfig;
import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiImplicitParam;
import com.ruoyi.yun.domain.YunFiles;
import com.ruoyi.yun.service.IYunFilesService;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.file.FileUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.sys.domain.SysFiles;
import com.ruoyi.sys.service.ISysFilesService;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 云盘文件Controller
 * 
 * @author yepanpan
 * @date 2021-03-15
 */
@Api("云盘文件管理")
@RestController
@RequestMapping("/yun/files")
public class YunFilesController extends BaseController
{
    @Autowired
    private IYunFilesService yunFilesService;
    @Autowired
    private ISysFilesService sysFilesService;

    /**
     * 查询云盘文件列表
     */
    @ApiOperation("查询云盘文件列表")   
    @PreAuthorize("@ss.hasPermi('yun:files:my')")
    @GetMapping("/my")
    public TableDataInfo my(YunFiles yunFiles)
    {
    	yunFiles.setCreateBy(SecurityUtils.getUsername());
        List<YunFiles> list = yunFilesService.selectYunFilesList(yunFiles);
        return getDataTable(list);
    }
    
    /**
     * 查询云盘文件列表
     */
    @ApiOperation("查询云盘文件列表")   
    @PreAuthorize("@ss.hasPermi('yun:files:share')")
    @GetMapping("/shares")
    public TableDataInfo shares(YunFiles yunFiles)
    {
        startPage();
        List<YunFiles> list = yunFilesService.selectShareFilesList(yunFiles);
        return getDataTable(list);
    }


    /**
     * 查询云盘文件列表
     */
    @ApiOperation("查询云盘文件列表")   
    @PreAuthorize("@ss.hasPermi('yun:files:list')")
    @GetMapping("/list")
    public TableDataInfo list(YunFiles yunFiles)
    {
        startPage();
        List<YunFiles> list = yunFilesService.selectYunFilesList(yunFiles);
        return getDataTable(list);
    }

    /**
     * 导出云盘文件列表
     */
    @ApiOperation("导出云盘文件列表")    
    @PreAuthorize("@ss.hasPermi('yun:files:export')")
    @Log(title = "云盘文件", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(YunFiles yunFiles)
    {
        List<YunFiles> list = yunFilesService.selectYunFilesList(yunFiles);
        ExcelUtil<YunFiles> util = new ExcelUtil<YunFiles>(YunFiles.class);
        return util.exportExcel(list, "files");
    }

    /**
     * 获取云盘文件详细信息
     */
    @ApiOperation("获取云盘文件详细信息")
    @ApiImplicitParam(name = "id", value = "云盘文件ID", required = true, dataType = "int", paramType = "path") 
    @PreAuthorize("@ss.hasPermi('yun:files:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(yunFilesService.selectYunFilesById(id));
    }

    /**
     * 新增云盘文件
     */
    @ApiOperation("新增云盘文件")
    @PreAuthorize("@ss.hasPermi('yun:files:add')")
    @Log(title = "云盘文件", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody YunFiles yunFiles)
    {
        return toAjax(yunFilesService.insertYunFiles(yunFiles));
    }

    /**
     * 修改云盘文件
     */
    @ApiOperation("修改云盘文件")  
    @PreAuthorize("@ss.hasPermi('yun:files:edit')")
    @Log(title = "云盘文件", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody YunFiles yunFiles)
    {
        return toAjax(yunFilesService.updateYunFiles(yunFiles));
    }

    /**
     * 删除云盘文件
     */
    @ApiOperation("删除云盘文件")
    @ApiImplicitParam(name = "id", value = "云盘文件ID", required = true, dataType = "String", paramType = "path") 
    @PreAuthorize("@ss.hasPermi('yun:files:remove')")
    @Log(title = "云盘文件", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(yunFilesService.deleteYunFilesByIds(ids));
    }
    
    /**
     * 共享云盘文件
     */
    @ApiOperation("共享云盘文件")
    @ApiImplicitParam(name = "id", value = "云盘文件ID", required = true, dataType = "int", paramType = "path") 
    @Log(title = "云盘文件", businessType = BusinessType.UPDATE)
	@GetMapping("/share/{ids}")
    public AjaxResult share(@PathVariable String[] ids)
    {
        return toAjax(yunFilesService.share(ids));
    }
    
    /**
     * 下载文件
     */
    @ApiOperation("下载文件")
    @Log(title = "下载文件", businessType = BusinessType.OTHER)
    @GetMapping("/download/{id}")
    public void download(@PathVariable("id") Long id, HttpServletResponse response, HttpServletRequest request)
    {
    	try
        {
    		YunFiles yunFiles = yunFilesService.selectYunFilesById(id);
            if (yunFiles == null)
            {
                throw new Exception(StringUtils.format("文件({})不存在或者不允许下载。 ", id));
            }
    		SysFiles files = sysFilesService.selectSysFilesById(yunFiles.getFileId());
    		
            if (files == null)
            {
                throw new Exception(StringUtils.format("文件({})不存在或者不允许下载。 ", id));
            }
            
            String realFileName = yunFiles.getTitle();
            String filePath = RuoYiConfig.getUploadPath() + files.getPath().substring(Constants.RESOURCE_PREFIX.length()+7);

            response.setCharacterEncoding("utf-8");
            response.setContentType("multipart/form-data");
            response.setHeader("Content-Disposition",
                    "attachment;fileName=" + FileUtils.setFileDownloadHeader(request, realFileName));
            FileUtils.writeBytes(filePath, response.getOutputStream());
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }    	  
    }
}
