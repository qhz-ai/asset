package com.ruoyi.sys.service.impl;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ruoyi.util.ExportUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruoyi.sys.mapper.SysChartMapper;
import com.ruoyi.sys.mapper.SysDatasetMapper;
import com.ruoyi.common.exception.CustomException;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.sys.domain.SysChart;
import com.ruoyi.sys.domain.SysDataset;
import com.ruoyi.sys.service.ISysDatasetService;

/**
 * 数据集Service业务层处理
 * 
 * @author yepanpan
 * @date 2020-12-04
 */
@Service
public class SysDatasetServiceImpl implements ISysDatasetService 
{
    @Autowired
    private SysDatasetMapper sysDatasetMapper;
    @Autowired
    private SysChartMapper sysChartMapper;

    /**
     * 查询数据集
     * 
     * @param id 数据集ID
     * @return 数据集
     */
    @Override
    public SysDataset selectSysDatasetById(Long id)
    {
        return sysDatasetMapper.selectSysDatasetById(id);
    }
    
    /**
     * 查询数据集
     * 
     * @param code 数据集Code
     * @return 数据集
     */
    @Override
    public SysDataset selectSysDatasetByCode(String code)
    {
        return sysDatasetMapper.selectSysDatasetByCode(code);
    }

    /**
     * 查询数据集列表
     * 
     * @param sysDataset 数据集
     * @return 数据集
     */
    @Override
    public List<SysDataset> selectSysDatasetList(SysDataset sysDataset)
    {
        return sysDatasetMapper.selectSysDatasetList(sysDataset);
    }

    /**
     * 新增数据集
     * 
     * @param sysDataset 数据集
     * @return 结果
     */
    @Override
    public int insertSysDataset(SysDataset sysDataset)
    {
        return sysDatasetMapper.insertSysDataset(sysDataset);
    }

    /**
     * 修改数据集
     * 
     * @param sysDataset 数据集
     * @return 结果
     */
    @Override
    public int updateSysDataset(SysDataset sysDataset)
    {
        return sysDatasetMapper.updateSysDataset(sysDataset);
    }

    /**
     * 批量删除数据集
     * 
     * @param ids 需要删除的数据集ID
     * @return 结果
     */
    @Override
    public int deleteSysDatasetByIds(Long[] ids)
    {
        return sysDatasetMapper.deleteSysDatasetByIds(ids);
    }

    /**
     * 删除数据集信息
     * 
     * @param id 数据集ID
     * @return 结果
     */
    @Override
    public int deleteSysDatasetById(Long id)
    {
        return sysDatasetMapper.deleteSysDatasetById(id);
    }
    

    /**
     * 导出数据
     * @param sysDataset 数据集
     * @return 本地路径
     */
    @Override
    public String exports(SysDataset sysDataset) {    	
		List<Map> list = query(sysDataset);
		List<Object[]> rows = new ArrayList<Object[]>();
		if(list == null || list.size() == 0) {
			throw new CustomException("查询结果为空!");
		}
		List<String> titles = new ArrayList<>();
		List<String> fields = new ArrayList<>();
		if(sysDataset.getParams().containsKey("chartId")) {
			Map<String,String> map = parseChartField(Long.valueOf(sysDataset.getParams().get("chartId").toString()));
			titles.addAll(map.values());
			fields.addAll(map.keySet());
		}else {
			titles.addAll(list.get(0).keySet());
			fields.addAll(titles);
		}
		for(Map data:list) {
			Object line[] = new Object[fields.size()];
			for(int i=0;i<titles.size();i++) {
				line[i] = data.get(fields.get(i));
			}
			rows.add(line);
		}

        String filename = sysDataset.getTitle();
        ExcelUtil<SysDataset> util = new ExcelUtil<SysDataset>(SysDataset.class);
        filename = util.encodingFilename(filename);
        try {
            OutputStream out = new FileOutputStream(util.getAbsoluteFile(filename));        
    		ExportUtil.exports(titles, rows, filename,out);
    		return filename;
        }catch (Exception e) {
        	throw new CustomException("写文件失败!");
		}
    }
    
    private Map<String,String> parseChartField(Long chartId){
    	SysChart chart = sysChartMapper.selectSysChartById(chartId);
		if(chart == null) return null;

    	Map<String,String> map = new HashMap<>();
		String titles[] = chart.getComment().split("\n");
		for(String title:titles) {
			if(title == null || title.length() == 0) continue;
			
			String kv[] = title.split("=");
			map.put(kv[0], kv[1]);
		}
		return map;
    }
    

    /**
     * 查询数据
     * @param ds 数据集
     * @return
     */
    @Override
    public List<Map> query(SysDataset ds){      	
    	this.parseSql(ds);
    	
    	return sysDatasetMapper.query(ds);
    }

    /**
     * 计算数据
     * @param ds 数据集
     * @return
     */
    @Override
    public Float count(SysDataset ds) {	
    	this.parseSql(ds);
    	
    	return sysDatasetMapper.count(ds);
    }
    
    private void parseSql(SysDataset ds) {
    	String where = "";

    	//where条件
    	Object o  = ds.getParams().get("where");
    	if(StringUtils.isNotNull(o) && !"null".equalsIgnoreCase(o.toString())) {
    		where += " and " + o.toString();
    	}
    	
    	    
    	//参数条件
    	if(StringUtils.isNotEmpty(ds.getParam())) {
    		String pa[] = ds.getParam().split("\n");
    		for(String p : pa) {
    			String ps[] = p.split(":");
    			o = ds.getParams().get(ps[0]);
    			if(StringUtils.isNull(o) || "null".equalsIgnoreCase(o.toString())) {
    				continue;
    			}
    			if(ps.length == 2) {
    				where += " and " + ps[1].replaceAll("[?]", o.toString());
    			}else {
    				where += " and "+ ps[0] + " = '" + o.toString() + "'";
    			}
    		}
    	}  	
    	
    	//去除非查询操作
    	String w = where.toLowerCase();
    	if(w.indexOf("delete") >= 0 || w.indexOf("update") >=0  || w.indexOf("create") >=0  || w.indexOf("alter") >=0 || w.indexOf("truncate") >=0 || w.indexOf("drop") >=0) {
	   		 throw new CustomException("不允许的操作");
	   	}
    	
    	String sql = ds.getSqls();
    	if(where != null && where.length() > 0) {
    		if(sql.indexOf("WHERE") > 0) {
    			sql = sql.replace("WHERE", " WHERE "+where + " and ");
    		}else {
    			sql += " WHERE 1 " + where;
    		}
    	}
    	sql = sql.replaceAll("ME", ""+SecurityUtils.getLoginUser().getUser().getUserId());
    	ds.setSqls(sql);
    }
}
