package com.ruoyi.xdata.service.impl;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruoyi.xdata.mapper.XdataModelMapper;
import com.ruoyi.xdata.mapper.XdataPipeMapper;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.generator.domain.GenTableColumn;
import com.ruoyi.generator.mapper.GenTableColumnMapper;
import com.ruoyi.task.XDataJob;
import com.ruoyi.xdata.domain.XdataModel;
import com.ruoyi.xdata.domain.XdataPipe;
import com.ruoyi.xdata.service.IXdataPipeService;

/**
 * 通道Service业务层处理
 * 
 * @author yepanpan
 * @date 2020-12-29
 */
@Service
public class XdataPipeServiceImpl implements IXdataPipeService 
{
    @Autowired
    private XdataPipeMapper xdataPipeMapper;
    @Autowired
    private XdataModelMapper xdataModelMapper;
    @Autowired
    private GenTableColumnMapper genTableColumnMapper;
    @Autowired
    private XDataJob job;

    /**
     * 查询通道
     * 
     * @param id 通道ID
     * @return 通道
     */
    @Override
    public XdataPipe selectXdataPipeById(Long id)
    {
        return xdataPipeMapper.selectXdataPipeById(id);
    }

    /**
     * 查询通道列表
     * 
     * @param xdataPipe 通道
     * @return 通道
     */
    @Override
    public List<XdataPipe> selectXdataPipeList(XdataPipe xdataPipe)
    {
        return xdataPipeMapper.selectXdataPipeList(xdataPipe);
    }

    /**
     * 新增通道
     * 
     * @param xdataPipe 通道
     * @return 结果
     */
    @Override
    public int insertXdataPipe(XdataPipe xdataPipe)
    {    	
    	defaultSql(xdataPipe);
        return xdataPipeMapper.insertXdataPipe(xdataPipe);
    }

    /**
     * 修改通道
     * 
     * @param xdataPipe 通道
     * @return 结果
     */
    @Override
    public int updateXdataPipe(XdataPipe xdataPipe)
    {
    	defaultSql(xdataPipe);
        return xdataPipeMapper.updateXdataPipe(xdataPipe);
    }
    
    private void defaultSql(XdataPipe pipe) {
    	XdataModel model = xdataModelMapper.selectXdataModelById(pipe.getModelId());
    	List<GenTableColumn> list =genTableColumnMapper.selectDbTableColumnsByName(model.getTables());
		if(StringUtils.isBlank(pipe.getReadSql())) {
			pipe.setReadSql(readSql(model.getTables(), list));
		}
		if(StringUtils.isBlank(pipe.getInsertSql())) {
			pipe.setInsertSql(insertSql(model.getTables(), list));
		}
		if(StringUtils.isBlank(pipe.getUpdateSql())) {
			pipe.setUpdateSql(updateSql(model.getTables(), list));
		}
		if(StringUtils.isBlank(pipe.getExistSql())) {
			pipe.setExistSql(existSql(model.getTables(), list));
		}
    }
    
    private String readSql(String table, List<GenTableColumn> list) {
    	String sql = "SELECT ";
    	for(GenTableColumn c : list) {
    		sql += c.getColumnName() + ", ";
    	}
    	sql = sql.substring(0, sql.length() - 2);
    	return sql + " FROM " + table + " limit #{_page}, #{_rows};";
    }

    private String insertSql(String table, List<GenTableColumn> list) {
    	String sql = "INSERT INTO "+table + "( ";
    	for(GenTableColumn c : list) {
    		sql += c.getColumnName() + ", ";
    	}
    	sql = sql.substring(0, sql.length() - 2) + " ) VALUES ( ";
    	for(GenTableColumn c : list) {
    		if(c.isPk()) {
    			sql += "NULL, ";
    		}else {
    			sql += "#{"+c.getColumnName() + "}, ";
    		}
    		
    	}
    	sql = sql.substring(0, sql.length() - 2);
    	return sql + " );";
    }

    private String updateSql(String table, List<GenTableColumn> list) {
    	String sql = "UPDATE "+table + " SET ";
    	for(GenTableColumn c : list) {
    		if(!c.isPk()) {
    			sql += c.getColumnName() + " =  #{"+c.getColumnName() + "}, ";
    		}    		
    	}
    	sql = sql.substring(0, sql.length() - 2) + " WHERE ";
    	for(GenTableColumn c : list) {
    		if(c.isPk()) {
    			sql += c.getColumnName() + " = #{"+c.getColumnName() + "};";
    		}
    		
    	}
    	return sql;
    }
    private String existSql(String table, List<GenTableColumn> list) {
    	String sql = "SELECT * FROM "+table + " WHERE ";
    	for(GenTableColumn c : list) {
    		if(c.isPk()) {
    			sql += c.getColumnName() + " = #{"+c.getColumnName() + "} ";
    		}    		
    	}
    	return sql + " LIMIT 1";
    }
    


    /**
     * 批量删除通道
     * 
     * @param ids 需要删除的通道ID
     * @return 结果
     */
    @Override
    public int deleteXdataPipeByIds(Long[] ids)
    {
        return xdataPipeMapper.deleteXdataPipeByIds(ids);
    }

    /**
     * 删除通道信息
     * 
     * @param id 通道ID
     * @return 结果
     */
    @Override
    public int deleteXdataPipeById(Long id)
    {
        return xdataPipeMapper.deleteXdataPipeById(id);
    }
    
    /**
     * 执行通道
     * 
     * @param id 通道ID
     * @return 通道
     */
    public int run(Long id) {
    	try {
        	XdataPipe pipe = xdataPipeMapper.selectXdataPipeById(id);
        	int count = job.trans(pipe);
        	if(count > 0) {
	        	pipe.setLastResult(0l + count);
	        	pipe.setLastTime(new Date());
				xdataPipeMapper.updateXdataPipe(pipe);
        	}
			return count;
    	}catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
    }
}
