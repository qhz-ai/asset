package com.ruoyi.task;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.ruoyi.util.DbUtil;
import com.ruoyi.xdata.domain.XdataPipe;
import com.ruoyi.xdata.mapper.XdataClientMapper;
import com.ruoyi.xdata.mapper.XdataPipeMapper;

/**
 * 数据同步的定时任务
 * @author Administrator
 *
 */
@Component("xdata")
public class XDataJob {
	@Autowired	 
    private DataSource dataSource; 
	@Autowired	 
	private XdataPipeMapper xdataPipeMapper;
	@Autowired	 
	private XdataClientMapper xdataClientMapper;
	
	/**
	 * 定时任务入口
	 */
	//@Scheduled(cron = "0 0/5 * * * ?")
	@Scheduled(cron = "0 * * * * ?")
	public void sync() {
		if(!DbUtil.run()) {
			System.out.println("正在执行中，本次任务将忽略...");
			return;
		}
		System.out.println("开始执行数据同步任务...");
		List<XdataPipe> list = xdataPipeMapper.selectJobs();
		for(XdataPipe p : list) {
			try { 
				int count = trans(p);
				
				p.setLastResult(0l + count);
				p.setLastTime(new Date());
				xdataPipeMapper.updateXdataPipe(p);
				
			}catch (Exception e) {	
				p.setLastResult(-1l);
				p.setLastTime(new Date());
				xdataPipeMapper.updateXdataPipe(p);
				
				e.printStackTrace();
			}
		}
		System.out.println("数据同步任务结束...");
		DbUtil.stop();
	}
	
	/**
	 * 自定义任务入口
	 */
	public void run(int id) throws SQLException{
		XdataPipe pipe = xdataPipeMapper.selectXdataPipeById(0l + id);
		if(pipe != null) {
			trans(pipe);
		}else {
			System.err.println("管道不存在!");
		}
	}
	
	public int trans(XdataPipe pipe) throws SQLException{
		Connection con1 = DbUtil.getConnection(xdataClientMapper.selectXdataClientById(pipe.getClientId()));
		if(con1 == null) {
			System.err.println("连接目标数据库失败!");
			return -1;
		}
		
		Connection con2 = dataSource.getConnection();
		DbUtil db1 = new DbUtil(con1);
		DbUtil db2 = new DbUtil(con2);
		con1.setAutoCommit(false);
		con2.setAutoCommit(false);
		int count = 0;
		if("r".equals(pipe.getMode())) {
			count = trans(pipe, db2, db1);
		}else {
			count =trans(pipe, db1, db2);
		}
		con1.commit();
		con2.commit();
		db1.release();
		db2.release();
		con1.close();
		con2.close();
		return count;
	}
	
	public int trans(XdataPipe pipe, DbUtil read, DbUtil write) throws SQLException{
		int page = 0;
		int rows = 1000;
		int count = 0;
		Map<String,Object> param = new HashMap<>();
		param.put("_page", page);
		param.put("_rows", 1000);
		param.put("_last", pipe.getLastTime());
		while(true) {
			List<Map<String,Object>> list = read.query(pipe.getReadSql(), param);
			if(list == null || list.size() == 0) {
				break;
			}
			for(Map<String,Object> data : list) {
				if(write.find(pipe.getExistSql(), data) == null) {
					write.exec(pipe.getInsertSql(), data);
				}else {
					write.exec(pipe.getUpdateSql(), data);
				}
				count ++;
			}		
			page ++;
			param.put("_page", page * rows);	
		}
		return count;
	}
}
