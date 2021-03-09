package com.ruoyi.sys.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.sys.mapper.SysFilesMapper;
import com.ruoyi.sys.domain.SysFiles;
import com.ruoyi.sys.service.ISysFilesService;

/**
 * 文件Service业务层处理
 * 
 * @author yepanpan
 * @date 2020-11-20
 */
@Service
public class SysFilesServiceImpl implements ISysFilesService 
{
    @Autowired
    private SysFilesMapper sysFilesMapper;

    /**
     * 查询文件
     * 
     * @param id 文件ID
     * @return 文件
     */
    @Override
    public SysFiles selectSysFilesById(Long id)
    {
        return sysFilesMapper.selectSysFilesById(id);
    }

    /**
     * 查询文件列表
     * 
     * @param sysFiles 文件
     * @return 文件
     */
    @Override
    public List<SysFiles> selectSysFilesList(SysFiles sysFiles)
    {
        return sysFilesMapper.selectSysFilesList(sysFiles);
    }
    
    /**
     * 查询文件
     * 
     * @param sysFiles 文件
     * @return 文件
     */
    @Override
    public SysFiles selectSysFiles(SysFiles sysFiles) {
    	return sysFilesMapper.selectSysFiles(sysFiles);
    }

    /**
     * 新增文件
     * 
     * @param sysFiles 文件
     * @return 结果
     */
    @Override
    public int insertSysFiles(SysFiles sysFiles)
    {
        sysFiles.setCreateTime(DateUtils.getNowDate());
        return sysFilesMapper.insertSysFiles(sysFiles);
    }

    /**
     * 修改文件
     * 
     * @param sysFiles 文件
     * @return 结果
     */
    @Override
    public int updateSysFiles(SysFiles sysFiles)
    {
        return sysFilesMapper.updateSysFiles(sysFiles);
    }

    /**
     * 批量删除文件
     * 
     * @param ids 需要删除的文件ID
     * @return 结果
     */
    @Override
    public int deleteSysFilesByIds(Long[] ids)
    {
        return sysFilesMapper.deleteSysFilesByIds(ids);
    }

    /**
     * 删除文件信息
     * 
     * @param id 文件ID
     * @return 结果
     */
    @Override
    public int deleteSysFilesById(Long id)
    {
        return sysFilesMapper.deleteSysFilesById(id);
    }
}
