package com.pagechoice.lolth.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import bootstrap.Job;
import bootstrap.JobBean;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.pagechoice.lolth.dao.LolthTaskDao;
import com.pagechoice.lolth.dao.LolthTaskDetailDao;
import com.pagechoice.lolth.dao.LolthTaskMonitorDao;
import com.pagechoice.lolth.dao.WebContrastDao;
import com.pagechoice.lolth.entity.LolthTask;
import com.pagechoice.lolth.entity.LolthTaskDetail;
import com.pagechoice.lolth.entity.LolthTaskMonitor;
import com.pagechoice.lolth.service.LolthTaskService;
import com.pagechoice.sys.utils.StringUtils;

@Service
@Transactional(rollbackFor = Exception.class)
public class LolthTaskServiceImpl implements LolthTaskService{

	@Autowired
    @Qualifier("lolthTaskDao")
    private LolthTaskDao lolthTaskDao;
	
	@Autowired
    @Qualifier("lolthTaskDetailDao")
    private LolthTaskDetailDao lolthTaskDetailDao;
	
	@Autowired
    @Qualifier("webContrastDao")
    private WebContrastDao webContrastDao;
	
	@Autowired
    @Qualifier("lolthTaskMonitorDao")
	private LolthTaskMonitorDao lolthTaskMonitorDao;
	
	/**
	 * 开始 爬虫任务 
	 */
	public String startLolthTask(String json) {
		
		String taskBool = "0";
		String detailBool = "0";
		String monitorBool = "0";
		
		//虚拟json串 解析json 
		Map<String,Object> param = JSONObject.parseObject(json);
		
		//保存 lolthTask 主表
		LolthTask lolthTask = new LolthTask();
		//String taskId = String.valueOf(param.get("taskId"));
		String taskId = "C" + StringUtils.getUniqueString(20);
		String taskName = String.valueOf(param.get("taskName")) + "-" + taskId  ;
		String taskType = "0";
		String webClass = "";
		String data = String.valueOf(param.get("data"));
		String webId = String.valueOf(param.get("webId"));
		String startDate = String.valueOf(param.get("startDate"));
		String endDate = String.valueOf(param.get("endDate"));
		String detailType = String.valueOf(param.get("detailType"));
		String createDate = String.valueOf(param.get("startDate"));
		
		lolthTask.setTaskId(taskId);
		lolthTask.setTaskName(taskName);
		lolthTask.setTaskType(taskType);
		//lolthTask.setData(null);
		lolthTask.setWebId(webId);
		lolthTask.setStartDate(startDate);
		lolthTask.setEndDate(endDate);
		lolthTask.setDetailType(detailType);
		lolthTask.setCreateDate(createDate);
		lolthTask.setCreateUser("wuhao");
		lolthTask.setData(data);
		lolthTask.setRemark(" test ");
		
		taskBool = lolthTaskDao.saveLolthTask(lolthTask);
		
		List<Map<String,Object>> listCls = webContrastDao.selectWebContrastClsAndTable(webId);
		List<LolthTaskDetail> listltd = new ArrayList<LolthTaskDetail>();
		
		List<Map<String,String>> classBeanList = new ArrayList<Map<String,String>>();
		List<Map<String,String>> forumList = (List<Map<String, String>>) JSON.parse(data);
		System.out.println(" >>>>>>>>>>>>>>>>>> " + forumList);
		System.out.println(" >>>>>>>>>>>>>>>>>> " + forumList.toString());
		
		if(listCls != null && listCls.size() > 0){
			for(int i = 0 ;  i < listCls.size() ; i++){
				Map<String,Object> mapcls = new HashMap<String,Object>();
				mapcls = listCls.get(i);
				if( i == 0){
					webClass = String.valueOf(mapcls.get("webClass"));
				}
				String main = String.valueOf(mapcls.get("main"));
				String order = String.valueOf(mapcls.get("order"));
				String classBean = String.valueOf(mapcls.get("classBean"));
				Map<String,String> classBeanMap = new HashMap<String,String>();
				classBeanMap.put("classBean", classBean);
				classBeanList.add(classBeanMap);
				String tableName = String.valueOf(mapcls.get("tableName")) + "_" + taskId;
				String tableNameCn = String.valueOf(mapcls.get("tableNameCn"));
				
				LolthTaskDetail lolthTaskDetail = new LolthTaskDetail();
				
				lolthTaskDetail.setMain(main);
				lolthTaskDetail.setOrder(order);
				lolthTaskDetail.setTaskId(taskId);
				lolthTaskDetail.setClassBean(classBean);
				lolthTaskDetail.setTableName(tableName);
				lolthTaskDetail.setTableNameCn(tableNameCn);
				
				listltd.add(lolthTaskDetail);
			}
			detailBool = lolthTaskDetailDao.saveLolthTaskDetailByList(listltd);
		}
		
		LolthTaskMonitor lolthTaskMonitor = new LolthTaskMonitor();
		lolthTaskMonitor.setTaskId(taskId);
		lolthTaskMonitor.setTaskName(taskName);
		lolthTaskMonitor.setAttribute1("0");
		lolthTaskMonitor.setIsComplete("0");
		
		monitorBool = lolthTaskMonitorDao.infoLolthTaskMonitor(lolthTaskMonitor);
		
		/**
		System.out.println( " webId : " + param.get("webId").toString());
		System.out.println( " projectName : " + param.get("projectName").toString());
		System.out.println( " taskId : " + param.get("taskId").toString());
		
		List<Map<String,String>> list = (List<Map<String,String>>) param.get("data");
		for(int i = 0 ; i < list.size() ; i++){
			System.out.println(" >>>>>> id  : " + list.get(i).get("id") + " >>>  keyword : " + list.get(i).get("keyword") ); 
		}
		*/
		
		System.out.println( " >>> taskBool : " + taskBool + " >>> detailBool : " + detailBool + " >>> monitorBool " + monitorBool  );
		
		String result = "0";
		if(!taskBool.equals("0") && !detailBool.equals("0") && !monitorBool.equals("0")){
			result = "1";
			
			for(int i =0;i<forumList.size();i++){
				Map<String,String> forumMap = forumList.get(i);
				System.out.println(" >>>>>> : > id : " + forumMap.get("id") + ">>>>> keyword  :" + forumMap.get("keyword"));
				
			}
			
			JobBean jobBean = new JobBean();
			jobBean.setTaskId(taskId);
			jobBean.setTaskName(taskName);
			jobBean.setWebClass(webClass);
			
			jobBean.setClassBeanList(classBeanList);
			jobBean.setForumList(forumList);
			
			jobBean.setStartDate(startDate);
			jobBean.setEndDate(endDate);
			
			Job job = new Job();
			result = job.startLolthTask(jobBean);

			
		}else{
			result = "0";
			System.out.println("数据没有插入成功！");
		}
		
		if(detailType.equals("频道")){
			System.out.println("根据频道解析！");
		}
		
		if(detailType.equals("关键字")){
			System.out.println("根据关键字解析！");
		}
		
		return String.valueOf(result);
	}
	
	/**
	 * @author wuhao
	 * 获取LolthTask list 数据
	 */
	public String getLolthTaskList(String json) {
		
		Map<String,Object> jsonMap = (Map<String, Object>) JSON.parse(json);
		Map<String,Object> searchMap =  (Map<String, Object>)jsonMap.get("search");
		Map<String,Object> pageMap =  (Map<String, Object>)jsonMap.get("page");
		
		Map<String,Object> reList = new HashMap<String,Object>();
		Map<String,Object> param = lolthTaskDao.selectLolTaskPageDetail(searchMap,pageMap);
		List<LolthTask> lolList = lolthTaskDao.selectLolthTask(searchMap,pageMap);
		
		reList.put("page",param);
		reList.put("data",lolList);
		
		String str = JSON.toJSONString(reList);

		return str;
	}

	public String getLolthTask(Map<String, String> map) {
		LolthTask task = lolthTaskDao.selectLolthTaskSingle(map);
		String str = JSON.toJSONString(task);	
		return str;
	}

	
	
}
