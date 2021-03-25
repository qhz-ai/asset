package com.ruoyi.yun.service.impl;

import java.util.List;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.yun.mapper.YunFolderMapper;
import com.ruoyi.yun.domain.YunFolder;
import com.ruoyi.yun.service.IYunFolderService;

/**
 * 云盘目录Service业务层处理
 * 
 * @author yepanpan
 * @date 2021-03-15
 */
@Service
public class YunFolderServiceImpl implements IYunFolderService 
{
    @Autowired
    private YunFolderMapper yunFolderMapper;

    /**
     * 查询云盘目录
     * 
     * @param id 云盘目录ID
     * @return 云盘目录
     */
    @Override
    public YunFolder selectYunFolderById(Long id)
    {
        return yunFolderMapper.selectYunFolderById(id);
    }

    /**
     * 查询云盘目录列表
     * 
     * @param yunFolder 云盘目录
     * @return 云盘目录
     */
    @Override
    public List<YunFolder> selectYunFolderList(YunFolder yunFolder)
    {
        return yunFolderMapper.selectYunFolderList(yunFolder);
    }

    /**
     * 新增云盘目录
     * 
     * @param yunFolder 云盘目录
     * @return 结果
     */
    @Override
    public int insertYunFolder(YunFolder yunFolder)
    {
        yunFolder.setCreateTime(DateUtils.getNowDate());
        yunFolder.setCreateBy(SecurityUtils.getUsername());
        return yunFolderMapper.insertYunFolder(yunFolder);
    }

    /**
     * 修改云盘目录
     * 
     * @param yunFolder 云盘目录
     * @return 结果
     */
    @Override
    public int updateYunFolder(YunFolder yunFolder)
    {
        yunFolder.setUpdateTime(DateUtils.getNowDate());
        yunFolder.setUpdateBy(SecurityUtils.getUsername());
        return yunFolderMapper.updateYunFolder(yunFolder);
    }

    /**
     * 批量删除云盘目录
     * 
     * @param ids 需要删除的云盘目录ID
     * @return 结果
     */
    @Override
    public int deleteYunFolderByIds(Long[] ids)
    {
        return yunFolderMapper.deleteYunFolderByIds(ids);
    }

    /**
     * 删除云盘目录信息
     * 
     * @param id 云盘目录ID
     * @return 结果
     */
    @Override
    public int deleteYunFolderById(Long id)
    {
        return yunFolderMapper.deleteYunFolderById(id);
    }
    
}
