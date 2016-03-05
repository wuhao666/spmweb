package com.pagechoice.lolth.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.pagechoice.lolth.dao.DictionaryWordDao;
import com.pagechoice.lolth.dao.WebSpiderDao;
import com.pagechoice.lolth.entity.DictionaryWord;

public class WordDisposeServiceImplTest  {
	
	@Autowired
    @Qualifier("dictionaryWordDao")
    private DictionaryWordDao dictionaryWordDao;
	
	
	@Autowired
    @Qualifier("webSpiderDao")
    private WebSpiderDao webSpiderDao;
	
	
	/**
	 * @reason 数据校验
	 * 1.获取字典数据源
	 * 2.获取数据源
	 * 3.根据 数据 - 字典 校验 
	 * 4.结果存储校验结果表 word_frequency 
	 * 
	 * Map<String,String> map 
	 * taskId classBean map(key,value)参数
	 * 
	 */
	public String wordDisposeService(Map<String,String> map) {
		DictionaryWord dw = null;
				//dictionaryWordDao.selectDictionaryWordToMap(map);
		
		DictionaryWord dictWord = new DictionaryWord();
		
		
		/**
		 * @reason
		 * 1. posits ： 正面词汇
		 * 2. negats ： 负面词汇
		 * 3. neuts ： 中性词汇
		 * 4. nons ： 不可切分词汇
		 * 5. outs ： 排除词汇
		 * 6. oths ： 其他词汇
		
		String posit = dictWord.getPositive();
		String negat = dictWord.getNegative();
		String neut = dictWord.getNeutral();
		String non = dictWord.getNonSeparability();
		String out = dictWord.getOutOfWords();
		String oth = dictWord.getOther();
		
		String[] posits = posit.split(",");
		int[] positInt = new int[posits.length];
		for(int i = 0 ; i < posits.length ; i++){
			positInt[i] = 0;
		}
		
		String[] negats = negat.split(",");
		int[] negatInt = new int[negats.length];
		for(int i = 0 ; i < negats.length ; i++){
			negatInt[i] = 0;
		}
		
		String[] neuts = neut.split(",");
		int[] neutInt = new int[neuts.length];
		for(int i = 0 ; i < neuts.length ; i++){
			neutInt[i] = 0;
		}
		
		String[] nons = non.split(",");
		int[] nonInt = new int[nons.length];
		for(int i = 0 ; i < nons.length ; i++){
			nonInt[i] = 0;
		}
		
		String[] outs = out.split(",");
		int[] outInt = new int[outs.length];
		for(int i = 0 ; i < outs.length ; i++){
			outInt[i] = 0;
		}
		
		String[] oths = oth.split(",");
		int[] othInt = new int[oths.length];
		for(int i = 0 ; i < oths.length ; i++){
			othInt[i] = 0;
		}
		
		 */
		/**
		 * 数据分析
		 
		int count = webSpiderDao.selectCountByTask(map);
		for(int i = 0 ; i <= count ; i = i + 1000){
			map.put("limit1", String.valueOf(i));
			List<Map<String,Object>> list = webSpiderDao.selectWebSpiderData(map);
			
			for(int n = 0 ; n < list.size() ; n++){
				String title =  list.get(n).get("title").toString();
				String text = list.get(n).get("text").toString();
				StringBuilder strBuilder = new StringBuilder();
				
				for(int m = 0 ; m < posits.length ; m++  ){
					if( strBuilder.indexOf(posits[m]) > 0 ){
						positInt[m] = positInt[m] + 1;
					}
				}
				
				for(int m = 0 ; m < negats.length ; m++  ){
					if( strBuilder.indexOf(negats[m]) > 0 ){
						negatInt[m] = negatInt[m] + 1;
					}
				}
				
				for(int m = 0 ; m < neuts.length ; m++  ){
					if( strBuilder.indexOf(neuts[m]) > 0 ){
						neutInt[m] = neutInt[m] + 1;
					}
				}
				
				for(int m = 0 ; m < nons.length ; m++  ){
					if( strBuilder.indexOf(nons[m]) > 0 ){
						nonInt[m] = nonInt[m] + 1;
					}
				}
				
				for(int m = 0 ; m < outs.length ; m++  ){
					if( strBuilder.indexOf(outs[m]) > 0 ){
						outInt[m] = outInt[m] + 1;
					}
				}
				
				for(int m = 0 ; m < oths.length ; m++  ){
					if( strBuilder.indexOf(oths[m]) > 0 ){
						othInt[m] = othInt[m] + 1;
					}
				}
			}
			
			
			
			
			
		}
		//DBBean.getSelectColumnDatas(dbBean, taskId, map)
		
		
		
		*/
		
		
		
		return null;
	}
	
	
}
