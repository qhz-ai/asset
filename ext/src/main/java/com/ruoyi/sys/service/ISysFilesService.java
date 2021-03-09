package com.ruoyi.sys.service;

import java.util.List;
import com.ruoyi.sys.domain.SysFiles;

/**
 * 文件Service接口
 * 
 * @author yepanpan
 * @date 2020-11-20
 */
public interface ISysFilesService 
{
    /**
     * 查询文件
     * 
     * @param id 文件ID
     * @return 文件
     */
    public SysFiles selectSysFilesById(Long id);

    /**
     * 查询文件列表
     * 
     * @param sysFiles 文件
     * @return 文件集合
     */
    public List<SysFiles> selectSysFilesList(SysFiles sysFiles);
    
    /**
     * 查询文件
     * 
     * @param sysFiles 文件
     * @return 文件
     */
    public SysFiles selectSysFiles(SysFiles sysFiles);

    /**
     * 新增文件
     * 
     * @param sysFiles 文件
     * @return 结果
     */
    public int insertSysFiles(SysFiles sysFiles);

    /**
     * 修改文件
     * 
     * @param sysFiles 文件
     * @return 结果
     */
    public int updateSysFiles(SysFiles sysFiles);

    /**
     * 批量删除文件
     * 
     * @param ids 需要删除的文件ID
     * @return 结果
     */
    public int deleteSysFilesByIds(Long[] ids);

    /**
     * 删除文件信息
     * 
     * @param id 文件ID
     * @return 结果
     */
    public int deleteSysFilesById(Long id);
}
