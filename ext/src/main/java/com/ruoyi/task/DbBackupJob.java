package com.ruoyi.task;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.system.service.ISysConfigService;

@Component("dbBackup")
public class DbBackupJob {
    @Autowired
    private ISysConfigService sysConfigService;
    
	/**
	 * 定时任务入口
	 * @throws Exception 
	 */
	@Scheduled(cron = "0 2 0 * * ?")
	public void back() throws Exception{
		String cmd =sysConfigService.selectConfigByKey("db_backup");
		if(StringUtils.isEmpty(cmd)) return;
		
		Runtime runtime = Runtime.getRuntime();
		Process pro = runtime.exec(cmd);
		int status = pro.waitFor();
		if (status != 0)
		{
			System.out.println("Failed to call command");
		}

		BufferedReader br = new BufferedReader(new InputStreamReader(pro.getInputStream()));
		StringBuffer strbr = new StringBuffer();
		String line;
		while ((line = br.readLine())!= null)
		{
			strbr.append(line).append("\n");
		}

		String result = strbr.toString();
		System.out.println(result);

	}
}
