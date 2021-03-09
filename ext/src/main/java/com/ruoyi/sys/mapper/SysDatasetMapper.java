package com.ruoyi.sys.mapper;

import java.util.List;
import java.util.Map;

import com.ruoyi.sys.domain.SysDataset;

/**
 * 数据集Mapper接口
 * 
 * @author yepanpan
 * @date 2020-12-04
 */
public interface SysDatasetMapper 
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
     * 删除数据集
     * 
     * @param id 数据集ID
     * @return 结果
     */
    public int deleteSysDatasetById(Long id);

    /**
     * 批量删除数据集
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteSysDatasetByIds(Long[] ids);
    

    /**
     * 查询数据
     * @param sysDataset 数据集
     * @return
     */
    public List<Map> query(SysDataset sysDataset);
    

    /**
     * 计算数据
     * @param ds 数据集
     * @return
     */
    public Float count(SysDataset ds);
}
