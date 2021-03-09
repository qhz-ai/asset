package com.ruoyi.sys.mapper;

import java.util.List;
import com.ruoyi.sys.domain.SysChart;

/**
 * 报表管理Mapper接口
 * 
 * @author yepanpan
 * @date 2020-12-04
 */
public interface SysChartMapper 
{
    /**
     * 查询报表管理
     * 
     * @param id 报表管理ID
     * @return 报表管理
     */
    public SysChart selectSysChartById(Long id);
    

    /**
     * 查询报表管理
     * 
     * @param code 报表code
     * @return 报表管理
     */
    public SysChart selectSysChartByCode(String code);

    /**
     * 查询报表管理列表
     * 
     * @param sysChart 报表管理
     * @return 报表管理集合
     */
    public List<SysChart> selectSysChartList(SysChart sysChart);

    /**
     * 新增报表管理
     * 
     * @param sysChart 报表管理
     * @return 结果
     */
    public int insertSysChart(SysChart sysChart);

    /**
     * 修改报表管理
     * 
     * @param sysChart 报表管理
     * @return 结果
     */
    public int updateSysChart(SysChart sysChart);

    /**
     * 删除报表管理
     * 
     * @param id 报表管理ID
     * @return 结果
     */
    public int deleteSysChartById(Long id);

    /**
     * 批量删除报表管理
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteSysChartByIds(Long[] ids);
}
