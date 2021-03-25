package com.ruoyi.yun.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ruoyi.yun.domain.YunFiles;

/**
 * 云盘文件Mapper接口
 * 
 * @author yepanpan
 * @date 2021-03-15
 */
public interface YunFilesMapper 
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
     * 删除云盘文件
     * 
     * @param id 云盘文件ID
     * @return 结果
     */
    public int deleteYunFilesById(Long id);

    /**
     * 批量删除云盘文件
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteYunFilesByIds(Long[] ids);

    /**
     * 删除云盘文件
     * 
     * @param fromFolder 原目录
     * @param toFolder 新目录
     * @return 结果
     */
    public int moveYunFiles(@Param("fromFolder")Long fromFolder, @Param("toFolder")Long toFolder);
    
}
