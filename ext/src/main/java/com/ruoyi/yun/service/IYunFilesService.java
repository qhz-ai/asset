package com.ruoyi.yun.service;

import java.util.List;
import com.ruoyi.yun.domain.YunFiles;

/**
 * 云盘文件Service接口
 * 
 * @author yepanpan
 * @date 2021-03-15
 */
public interface IYunFilesService 
{
    /**
     * 查询云盘文件
     * 
     * @param id 云盘文件ID
     * @return 云盘文件
     */
    public YunFiles selectYunFilesById(Long id);

    /**
     * 查询云盘文件列表
     * 
     * @param yunFiles 云盘文件
     * @return 云盘文件集合
     */
    public List<YunFiles> selectYunFilesList(YunFiles yunFiles);

    /**
     * 查询共享云盘文件列表
     * 
     * @param yunFiles 云盘文件
     * @return 云盘文件集合
     */
    public List<YunFiles> selectShareFilesList(YunFiles yunFiles);

    /**
     * 新增云盘文件
     * 
     * @param yunFiles 云盘文件
     * @return 结果
     */
    public int insertYunFiles(YunFiles yunFiles);

    /**
     * 修改云盘文件
     * 
     * @param yunFiles 云盘文件
     * @return 结果
     */
    public int updateYunFiles(YunFiles yunFiles);

    /**
     * 批量删除云盘文件
     * 
     * @param ids 需要删除的云盘文件ID
     * @return 结果
     */
    public int deleteYunFilesByIds(String[] ids);

    /**
     * 删除云盘文件信息
     * 
     * @param id 云盘文件ID
     * @return 结果
     */
    public int deleteYunFilesById(Long id);
    

    /**
     * 批量共享云盘文件
     * 
     * @param ids 需要共享的云盘文件或者目录
     * @return 结果
     */
    public int share(String[] ids);
}
