package com.ruoyi.yun.mapper;

import java.util.List;
import com.ruoyi.yun.domain.YunFolder;

/**
 * 云盘目录Mapper接口
 * 
 * @author yepanpan
 * @date 2021-03-15
 */
public interface YunFolderMapper 
{
    /**
     * 查询云盘目录
     * 
     * @param id 云盘目录ID
     * @return 云盘目录
     */
    public YunFolder selectYunFolderById(Long id);

    /**
     * 查询云盘目录列表
     * 
     * @param yunFolder 云盘目录
     * @return 云盘目录集合
     */
    public List<YunFolder> selectYunFolderList(YunFolder yunFolder);

    /**
     * 新增云盘目录
     * 
     * @param yunFolder 云盘目录
     * @return 结果
     */
    public int insertYunFolder(YunFolder yunFolder);

    /**
     * 修改云盘目录
     * 
     * @param yunFolder 云盘目录
     * @return 结果
     */
    public int updateYunFolder(YunFolder yunFolder);

    /**
     * 删除云盘目录
     * 
     * @param id 云盘目录ID
     * @return 结果
     */
    public int deleteYunFolderById(Long id);

    /**
     * 批量删除云盘目录
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteYunFolderByIds(Long[] ids);
}
