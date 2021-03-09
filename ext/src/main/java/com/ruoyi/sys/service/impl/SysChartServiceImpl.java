package com.ruoyi.sys.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.sys.mapper.SysChartMapper;
import com.ruoyi.common.core.domain.entity.SysMenu;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.sys.domain.SysChart;
import com.ruoyi.sys.service.ISysChartService;
import com.ruoyi.system.mapper.SysMenuMapper;

/**
 * 报表管理Service业务层处理
 * 
 * @author yepanpan
 * @date 2020-12-04
 */
@Service
public class SysChartServiceImpl implements ISysChartService 
{
	public static long CHART_MENU = 1372l;
    @Autowired
    private SysChartMapper sysChartMapper;
    
    @Autowired
    private SysMenuMapper menuMapper;

    /**
     * 查询报表管理
     * 
     * @param id 报表管理ID
     * @return 报表管理
     */
    @Override
    public SysChart selectSysChartById(Long id)
    {
        return sysChartMapper.selectSysChartById(id);
    }
    
    @Override
    public SysChart selectSysChartByCode(String code)
    {
        return sysChartMapper.selectSysChartByCode(code);
    }

    /**
     * 查询报表管理列表
     * 
     * @param sysChart 报表管理
     * @return 报表管理
     */
    @Override
    public List<SysChart> selectSysChartList(SysChart sysChart)
    {
        return sysChartMapper.selectSysChartList(sysChart);
    }

    /**
     * 新增报表管理
     * 
     * @param sysChart 报表管理
     * @return 结果
     */
    @Override
    public int insertSysChart(SysChart sysChart)
    {
        return sysChartMapper.insertSysChart(sysChart);
    }

    /**
     * 修改报表管理
     * 
     * @param sysChart 报表管理
     * @return 结果
     */
    @Override
    public int updateSysChart(SysChart sysChart)
    {
        return sysChartMapper.updateSysChart(sysChart);
    }

    /**
     * 批量删除报表管理
     * 
     * @param ids 需要删除的报表管理ID
     * @return 结果
     */
    @Override
    public int deleteSysChartByIds(Long[] ids)
    {
        return sysChartMapper.deleteSysChartByIds(ids);
    }

    /**
     * 删除报表管理信息
     * 
     * @param id 报表管理ID
     * @return 结果
     */
    @Override
    public int deleteSysChartById(Long id)
    {
        return sysChartMapper.deleteSysChartById(id);
    }
    

    /**
     * 添加报表到菜单
     * 
     * @param id 报表ID
     * @return 结果
     */
    @Override
    public int addChartMenu(Long id) {
    	SysChart chart = sysChartMapper.selectSysChartById(id);
    	if(chart == null) return 0;
    	
    	SysMenu menu = new SysMenu();
    	menu.setPath("sys/chart/menu/"+chart.getCode());
    	List<SysMenu> list = menuMapper.selectMenuList(menu);
    	if(list != null && list.size() > 0) {
    		return list.size();
    	}
    	
    	menu.setParentId(CHART_MENU);
    	menu.setComponent("sys/chart/menu");
    	menu.setMenuName(chart.getTitle());
    	menu.setOrderNum("9");
    	menu.setIsFrame("1");
    	menu.setIsCache("0");
    	menu.setMenuType("C");
    	menu.setVisible("0");
    	menu.setStatus("0");
    	menu.setPerms("");
    	menu.setIcon("chart");
    	menu.setCreateBy(SecurityUtils.getUsername());
    	menu.setCreateTime(DateUtils.getNowDate());
    	return menuMapper.insertMenu(menu);
    }
}
