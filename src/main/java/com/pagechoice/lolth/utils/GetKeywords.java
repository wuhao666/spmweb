package com.pagechoice.lolth.utils;

import java.util.ArrayList;
import java.util.Map;

import org.fnlp.app.keyword.AbstractExtractor;
import org.fnlp.app.keyword.WordExtract;

import edu.fudan.nlp.cn.tag.CWSTagger;
import edu.fudan.nlp.corpus.StopWords;

/**
 * @author wuhao
 * NLP 词频计算基础类
 */
public class GetKeywords {
	
	public ArrayList<String> GetKeyword(String News,int keywordsNumber) throws Exception{
		ArrayList<String> keywords=new ArrayList<String>();
		StopWords sw= new StopWords("./models/stopwords");
		CWSTagger seg = new CWSTagger("./models/seg.m");
		AbstractExtractor key = new WordExtract(seg,sw);
		
		Map<String,Integer> ans = key.extract(News, keywordsNumber);
		
		for (Map.Entry<String, Integer> entry : ans.entrySet()) {
		   String keymap = entry.getKey().toString();
		   String value = entry.getValue().toString();
		   keywords.add(keymap);
		   System.out.println("key=" + keymap + " value=" + value);
		}
		return keywords;
	}
	
	public static void main(String[] args) throws Exception {
		
		StopWords sw= new StopWords("./models/stopwords");
		CWSTagger seg = new CWSTagger("./models/seg.m");
		AbstractExtractor key = new WordExtract(seg,sw);
		
		System.out.println(key.extract("甬温线特别重大铁路交通事故车辆经过近24小时的清理工作，26日深夜已经全部移出事故现场，之前埋下的D301次动车车头被挖出运走", 20, true));
		
		//处理已经分好词的句子
		sw=null;
		key = new WordExtract(seg,sw);
		System.out.println(key.extract("甬温线 特别 重大 铁路交通事故车辆经过近24小时的清理工作，26日深夜已经全部移出事故现场，之前埋下的D301次动车车头被挖出运走", 20));
		System.out.println(key.extract("赵嘉亿 是 好人 还是 坏人", 5));
		
		key = new WordExtract();
		System.out.println(key.extract("", 5));
		
	}
}
