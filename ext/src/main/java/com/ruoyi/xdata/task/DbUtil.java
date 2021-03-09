package com.ruoyi.xdata.task;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.xdata.domain.XdataClient;

public class DbUtil {
    private static final Logger log = LoggerFactory.getLogger(DbUtil.class);
	private static Map<Long,Connection> conMap = new HashMap<>();
	private Map<String,PreparedStatement> psMap = new HashMap<>();
	private Map<String,List<String>> paramMap = new HashMap<>();
	private static boolean running = false;
	private Connection conn;

	public DbUtil(Connection conn) {
		this.conn = conn;
	}
	
	synchronized public static  boolean run() {
		if(running) return false;
		running = true;
		return running;
	}
	
	public void stop() throws SQLException {
		for(PreparedStatement ps: psMap.values()) {
			ps.close();
		}
		for(Connection c: conMap.values()) {
			c.close();
		}
		conMap.clear();
		psMap.clear();
		paramMap.clear();
		DbUtil.running = false;
	}
	
	public static Connection getConnection(XdataClient client) {
		Connection conn = conMap.get(client.getId());
		if(conn == null) {
			conn = createConn(client.getDbType(), client.getJdbc(), client.getDbUser(), client.getDbPass());
			conMap.put(client.getId(), conn);
		}
		return conn;
	}
	
	public static Connection createConn(String dbType, String jdbc,String dbUser,String dbPass) {
		try {
			if("1".equals(dbType)) {
				Class.forName("com.mysql.cj.jdbc.Driver");
			}else if("2".equals(dbType)) {
				Class.forName("oracle.jdbc.driver.OracleDriver");
			}else if("3".equals(dbType)) {
				Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			}else if("4".equals(dbType)) {
				Class.forName("org.postgresql.Driver");
			}else {
				return null;
			}
			return DriverManager.getConnection(jdbc, dbUser, dbPass);
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}		
	}
	
	
	public List<Map<String,Object>> query(String sql, Map<String,Object> param)  throws SQLException{
		log.debug(sql);
		log.debug("param:"+param.toString());
		PreparedStatement ps = psMap.get(sql);
		if(ps == null) {
			prepare(sql);
		}
		ps = psMap.get(sql);
		if(ps == null) {
			return null;
		}
		List<String> keys =paramMap.get(sql);
		int i=1;
		for(String k:keys) {
			ps.setObject(i++, param.get(k));
		}
		List<Map<String,Object>> list = new ArrayList<>();
		ResultSet rs = ps.executeQuery();
		ResultSetMetaData rsmd = rs.getMetaData();
		int rsd = rsmd.getColumnCount();
		while(rs.next()) {
			Map<String,Object> data = new HashMap<String, Object>();
			for(int j=0;j<rsd;j++) {
				String col = rsmd.getColumnLabel(j+1);
				data.put(col, rs.getObject(col));
			}
			list.add(data);
		}
		rs.close();
		return list;
	}
	
	public Map<String,Object> find(String sql, Map<String,Object> param)  throws SQLException{
		log.debug(sql);
		log.debug("param:"+param.toString());
		PreparedStatement ps = psMap.get(sql);
		if(ps == null) {
			prepare(sql);
		}
		ps = psMap.get(sql);
		if(ps == null) {
			return null;
		}
		List<String> keys =paramMap.get(sql);
		int i=1;
		for(String k:keys) {
			ps.setObject(i++, param.get(k));
		}
		Map<String,Object> data = new HashMap<>();
		ResultSet rs = ps.executeQuery();
		ResultSetMetaData rsmd = rs.getMetaData();
		int rsd = rsmd.getColumnCount();
		if(rs.next()) {
			for(int j=0;j<rsd;j++) {
				String col = rsmd.getColumnLabel(j+1);
				data.put(col, rs.getObject(col));
			}
		}else {
			return null;
		}
		rs.close();
		return data;
	}
	
	public int exec(String sql, Map<String,Object> data) throws SQLException {
		log.debug(sql);
		log.debug("param:"+data.toString());
		PreparedStatement ps = psMap.get(sql);
		if(ps == null) {
			prepare(sql);
		}
		ps = psMap.get(sql);
		if(ps == null) {
			return -1;
		}
		List<String> keys =paramMap.get(sql);
		int i=1;
		for(String k:keys) {
			ps.setObject(i++, data.get(k));
		}
		return ps.executeUpdate();
	}
	
	public void prepare(String sql) throws SQLException{
		if(StringUtils.isBlank(sql)) return ;
		
		List<String> list = new ArrayList<>();
		Pattern p = Pattern.compile("#\\{.*?\\}");

		Matcher m = p.matcher(sql);

		while(m.find()){
			String str = m.group().substring(2);
			str = str.substring(0,str.length()-1);
			list.add(str);
		}
		psMap.put(sql, conn.prepareStatement(sql.replaceAll("#\\{.*?\\}", "?")));
		paramMap.put(sql, list);
	}
	 
	public static void main(String[] args) {
		String sql = "insert into sys_user (username,password) values (#{username}, #{password})";
		Pattern p = Pattern.compile("#\\{.*?\\}");

		Matcher m = p.matcher(sql);

		while(m.find()){
			String str = m.group().substring(2);
			str = str.substring(0,str.length()-1);
					System.out.println(str);
		}
		sql = sql.replaceAll("#\\{.*?\\}", "?");
		System.out.println(sql);
	}
}
