package com.pagechoice.lolth.dao;

import java.util.List;
import java.util.Map;

import com.pagechoice.lolth.entity.WebContrast;

public interface WebContrastDao {
	
	/**
	 * @param webId
	 * @return
	 * @reason 获取网站数据
	 */
	List<Map<String,Object>> selectWebContrastClsAndTable(String webId);
	
	List<WebContrast> getWebContrastList(Map<String,String> map);
	
}
