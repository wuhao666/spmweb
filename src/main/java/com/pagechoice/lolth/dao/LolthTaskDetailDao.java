package com.pagechoice.lolth.dao;

import java.util.List;

import com.pagechoice.lolth.entity.LolthTaskDetail;

public interface LolthTaskDetailDao {
	
	String saveLolthTaskDetail(LolthTaskDetail task);
	
	String saveLolthTaskDetailByList(List<LolthTaskDetail> list);
	
	String updateLolthTaskDetail();
	
	List<LolthTaskDetail> selectLolthTaskDetailByTaskId(String taskId);
	
	String updateLolthTaskDetailDownloadUrl(String id,String downloadUrl);
	
}
