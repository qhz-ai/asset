package com.ruoyi.sys.service;

import java.util.List;
import java.util.Map;

import com.ruoyi.sys.domain.SysDataset;

/**
 * 数据集Service接口
 * 
 * @author yepanpan
 * @date 2020-12-04
 */
public interface ISysDatasetService 
{
    /**
     * 查询数据集
     * 
     * @param id 数据集ID
     * @return 数据集
     */
    public SysDataset selectSysDatasetById(Long id);
    
    /**
     * 查询数据集
     * 
     * @param code String 数据集代码
     * @return 数据集
     */
    public SysDataset selectSysDatasetByCode(String code);

    /**
     * 查询数据集列表
     * 
     * @param sysDataset 数据集
     * @return 数据集集合
     */
    public List<SysDataset> selectSysDatasetList(SysDataset sysDataset);

    /**
     * 新增数据集
     * 
     * @param sysDataset 数据集
     * @return 结果
     */
    public int insertSysDataset(SysDataset sysDataset);

    /**
     * 修改数据集
     * 
     * @param sysDataset 数据集
     * @return 结果
     */
    public int updateSysDataset(SysDataset sysDataset);

    /**
     * 批量删除数据集
     * 
     * @param ids 需要删除的数据集ID
     * @return 结果
     */
    public int deleteSysDatasetByIds(Long[] ids);

    /**
     * 删除数据集信息
     * 
     * @param id 数据集ID
     * @return 结果
     */
    public int deleteSysDatasetById(Long id);
    
    /**
     * 查询数据
     * @param sysDataset 数据集
     * @return
     */
    public List<Map> query(SysDataset sysDataset);
    
    /**
     * 计算数据
     * @param sysDataset 数据集
     * @return
     */
    public Float count(SysDataset sysDataset);

    /**
     * 导出数据
     * @param sysDataset 数据集
     * @return 本地路径
     */
    public String exports(SysDataset sysDataset);
    
}
