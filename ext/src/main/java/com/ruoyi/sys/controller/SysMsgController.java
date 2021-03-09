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
import com.ruoyi.sys.domain.SysMsg;
import com.ruoyi.sys.service.ISysMsgService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 消息Controller
 * 
 * @author yepanpan
 * @date 2021-02-25
 */
@Api("站内消息管理")
@RestController
@RequestMapping("/sys/msg")
public class SysMsgController extends BaseController
{
    @Autowired
    private ISysMsgService sysMsgService;

    /**
     * 查询发件箱消息列表
     */
    @ApiOperation("获取发件箱消息列表")
    @GetMapping("/out")
    public TableDataInfo out(SysMsg oaMsg)
    {
        startPage();
        oaMsg.setAddUser(SecurityUtils.getLoginUser().getUser().getUserId());
        List<SysMsg> list = sysMsgService.selectSysMsgList(oaMsg);
        return getDataTable(list);
    }
    

    /**
     * 查询收件箱消息列表
     */
    @ApiOperation("获取收件箱消息列表")
    @GetMapping("/in")
    public TableDataInfo in(SysMsg oaMsg)
    {
        startPage();
        oaMsg.setToUser(SecurityUtils.getLoginUser().getUser().getUserId());
        List<SysMsg> list = sysMsgService.selectSysMsgList(oaMsg);
        return getDataTable(list);
    }
    
    /**
     * 查询消息列表
     */
    @PreAuthorize("@ss.hasPermi('sys:msg:list')")
    @GetMapping("/list")
    public TableDataInfo list(SysMsg sysMsg)
    {
        startPage();
        List<SysMsg> list = sysMsgService.selectSysMsgList(sysMsg);
        return getDataTable(list);
    }

    /**
     * 导出消息列表
     */
    @PreAuthorize("@ss.hasPermi('sys:msg:export')")
    @Log(title = "消息", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(SysMsg sysMsg)
    {
        List<SysMsg> list = sysMsgService.selectSysMsgList(sysMsg);
        ExcelUtil<SysMsg> util = new ExcelUtil<SysMsg>(SysMsg.class);
        return util.exportExcel(list, "msg");
    }

    /**
     * 获取消息详细信息
     */
    @PreAuthorize("@ss.hasPermi('sys:msg:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(sysMsgService.selectSysMsgById(id));
    }

    /**
     * 新增消息
     */
    @PreAuthorize("@ss.hasPermi('sys:msg:add')")
    @Log(title = "消息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SysMsg sysMsg)
    {
        return toAjax(sysMsgService.insertSysMsg(sysMsg));
    }

    /**
     * 修改消息
     */
    @PreAuthorize("@ss.hasPermi('sys:msg:edit')")
    @Log(title = "消息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysMsg sysMsg)
    {
        return toAjax(sysMsgService.updateSysMsg(sysMsg));
    }

    /**
     * 删除消息
     */
    @PreAuthorize("@ss.hasPermi('sys:msg:remove')")
    @Log(title = "消息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(sysMsgService.deleteSysMsgByIds(ids));
    }
    
    
    /**
     * 标记已读
     */
    @ApiOperation("标记已读")
    @ApiImplicitParam(name = "id", value = "消息ID", required = true, dataType = "int", paramType = "path")
    @PreAuthorize("@ss.hasPermi('oa:msg:list')")
    @Log(title = "消息", businessType = BusinessType.UPDATE)
	@GetMapping("/read/{ids}")
    public AjaxResult read(@PathVariable Long[] ids)
    {
        return toAjax(sysMsgService.readMsgByIds(ids));
    }
}
