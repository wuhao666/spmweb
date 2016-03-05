package com.pagechoice.lolth.monitor;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


/**
 * 
 * @author wuhao
 * @reason 监听程序lolthTask 数据
 * step1:抽取数据任务 
 * 	select count(a.projectName) as `count` from lakenono_lolth_task a 
 * 	where 1 = 1 
 * 	and a.`status` = 'todo'
 * 	group by a.projectName
 * step2:和上一个参数做对比，如果一致存入下一个参数中
 * step3:如果数据都一致则执行生成download url 方法
 * 
 */
@Component  
public class LolthTaskMonitor {
	
	@Scheduled(cron="0/30 * * * * ? ") //间隔5秒执行  
	public void lolthTaskMoitor(){ 
		
		System.out.println("监听LolthTask 数据 ！！！！！！！！");
		
	}  
	
}
