package com.ruoyi.sys.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.prepost.PreAuthorize;
import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.config.RuoYiConfig;
import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiImplicitParam;
import com.ruoyi.sys.domain.SysFiles;
import com.ruoyi.sys.service.ISysFilesService;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.file.FileUploadUtils;
import com.ruoyi.common.utils.file.FileUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.config.ServerConfig;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 文件Controller
 * 
 * @author yepanpan
 * @date 2020-11-20
 */
@Api("文件信息管理")
@RestController
@RequestMapping("/sys/files")
public class SysFilesController extends BaseController
{
    private static final Logger log = LoggerFactory.getLogger(SysFilesController.class);
    
    @Autowired
    private ISysFilesService sysFilesService;
    @Autowired
    private ServerConfig serverConfig;

    /**
     * 查询文件列表
     */
    @ApiOperation("获取文件列表")
    @PreAuthorize("@ss.hasPermi('sys:files:list')")
    @GetMapping("/list")
    public TableDataInfo list(SysFiles sysFiles)
    {
        startPage();
        List<SysFiles> list = sysFilesService.selectSysFilesList(sysFiles);
        return getDataTable(list);
    }

    /**
     * 导出文件列表
     */
    @ApiOperation("导出文件列表")
    @PreAuthorize("@ss.hasPermi('sys:files:export')")
    @Log(title = "文件", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(SysFiles sysFiles)
    {
        List<SysFiles> list = sysFilesService.selectSysFilesList(sysFiles);
        ExcelUtil<SysFiles> util = new ExcelUtil<SysFiles>(SysFiles.class);
        return util.exportExcel(list, "files");
    }

    /**
     * 获取文件详细信息
     */
    @ApiOperation("获取文件详细信息")
    @ApiImplicitParam(name = "id", value = "文件ID", required = true, dataType = "int", paramType = "path")
    @PreAuthorize("@ss.hasPermi('sys:files:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(sysFilesService.selectSysFilesById(id));
    }

    /**
     * 新增文件
     */
    @ApiOperation("新增文件")
    @PreAuthorize("@ss.hasPermi('sys:files:add')")
    @Log(title = "文件", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SysFiles sysFiles)
    {
        return toAjax(sysFilesService.insertSysFiles(sysFiles));
    }
    
    /**
     * 上传文件
     */
    @ApiOperation("上传文件")
    @Log(title = "上传文件", businessType = BusinessType.INSERT)
    @PostMapping("/upload")
    public AjaxResult upload(MultipartFile file)
    {
		SysFiles files = new SysFiles();
    	try
        {
    		String md5 = DigestUtils.md5Hex(file.getInputStream());
    		
    		files.setMd5(md5);
    		SysFiles old = sysFilesService.selectSysFiles(files);
    		if(old == null) {
    			// 上传文件路径
                String filePath = RuoYiConfig.getUploadPath();
    			files.setTitle(file.getOriginalFilename());
                // 上传并返回新文件名称
    			files.setPath(FileUploadUtils.upload(filePath, file));
                files.setCreateTime(DateUtils.getNowDate());
                files.setCreateBy(SecurityUtils.getUsername());
                
                sysFilesService.insertSysFiles(files);
    		}else {
    			files = old;
    		}
        }
        catch (Exception e)
        {
            return AjaxResult.error(e.getMessage());
        }
    	
        return AjaxResult.success(files);
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
    		SysFiles files = sysFilesService.selectSysFilesById(id);
    		
            if (files == null)
            {
                throw new Exception(StringUtils.format("文件({})不存在或者不允许下载。 ", id));
            }
            
            String realFileName = files.getTitle();
            String filePath = RuoYiConfig.getUploadPath() + files.getPath().substring(Constants.RESOURCE_PREFIX.length()+7);

            response.setCharacterEncoding("utf-8");
            response.setContentType("multipart/form-data");
            response.setHeader("Content-Disposition",
                    "attachment;fileName=" + FileUtils.setFileDownloadHeader(request, realFileName));
            FileUtils.writeBytes(filePath, response.getOutputStream());
        }
        catch (Exception e)
        {
            log.error("下载文件失败", e);
        }    	  
    }

    /**
     * 修改文件
     */
    @ApiOperation("修改文件")
    @PreAuthorize("@ss.hasPermi('sys:files:edit')")
    @Log(title = "文件", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysFiles sysFiles)
    {
        return toAjax(sysFilesService.updateSysFiles(sysFiles));
    }

    /**
     * 删除文件
     */
    @ApiOperation("删除文件")
    @ApiImplicitParam(name = "id", value = "文件ID", required = true, dataType = "int", paramType = "path")
    @PreAuthorize("@ss.hasPermi('sys:files:remove')")
    @Log(title = "文件", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(sysFilesService.deleteSysFilesByIds(ids));
    }
}
