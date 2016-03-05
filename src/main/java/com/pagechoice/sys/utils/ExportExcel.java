package com.pagechoice.sys.utils;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFComment;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFPatriarch;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;


public class ExportExcel<T> {
	 public void exportExcel(Collection<T> dataset,Map<String,Object>map, OutputStream out) {
	        exportExcel("测试POI导出EXCEL文档", null, dataset,map, out, "yyyy-MM-dd");
	    }

	    public void exportExcel(String titlename,String[] headers, Collection<T> dataset,Map<String,Object>map,
	            OutputStream out) {
	        exportExcel(titlename, headers, dataset,map, out, "yyyy-MM-dd");
	    }

	    public void exportExcel(String[] headers, Collection<T> dataset,Map<String,Object>map,
	            OutputStream out, String pattern) {
	        exportExcel("测试POI导出EXCEL文档", headers, dataset, map,out, pattern);
	    }
	
	public void exportExcel(String title, String[] headers,
            Collection<T> dataset,Map<String,Object>map, OutputStream out, String pattern) {
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet(title);
        sheet.setDefaultColumnWidth((short) 15);
        HSSFCellStyle style = workbook.createCellStyle();
        HSSFCellStyle style2 = workbook.createCellStyle();
        HSSFFont font2 = workbook.createFont();
        style2.setFont(font2);
        HSSFPatriarch patriarch = sheet.createDrawingPatriarch();
        HSSFComment comment = patriarch.createComment(new HSSFClientAnchor(0,
                0, 0, 0, (short) 4, 2, (short) 6, 5));
        comment.setAuthor("leno");
        
        int index = 0;
        Set<String> set = map.keySet();
        for(String skey : set){
        	index++;
        	HSSFRow row = sheet.createRow(index);
        	HSSFCell cell = row.createCell(0);
            cell.setCellStyle(style);
            HSSFRichTextString text = new HSSFRichTextString(skey);
            cell.setCellValue(text);
            HSSFCell cell1 = row.createCell(1);
            cell1.setCellStyle(style);
            HSSFRichTextString text1 = new HSSFRichTextString(map.get(skey).toString());
            cell1.setCellValue(text1);
        }
        index = index+2;

        // 产生表格标题行
        HSSFRow row = sheet.createRow(index);
        for (short i = 0; i < headers.length; i++) {
            HSSFCell cell = row.createCell(i);
            cell.setCellStyle(style);
            HSSFRichTextString text = new HSSFRichTextString(headers[i]);
            cell.setCellValue(text);
        }
        // 遍历集合数据，产生数据行
        Iterator<T> it = dataset.iterator();
        while (it.hasNext()) {
            index++;
            row = sheet.createRow(index);
            T t = (T) it.next();
            // 利用反射，根据javabean属性的先后顺序，动态调用getXxx()方法得到属性值
            Field[] fields = t.getClass().getDeclaredFields();
            for (short i = 0; i < fields.length; i++) {
                HSSFCell cell = row.createCell(i);
                cell.setCellStyle(style2);
                Field field = fields[i];
                String fieldName = field.getName();
                String getMethodName = "get"
                        + fieldName.substring(0, 1).toUpperCase()
                        + fieldName.substring(1);
                try {
                    Class tCls = t.getClass();
                    Method getMethod = tCls.getMethod(getMethodName,
                            new Class[] {});
                    	 Object value = getMethod.invoke(t, new Object[] {});
                    	 if(null!=value){
                         // 判断值的类型后进行强制类型转换
                         String textValue = null;
                         if (value instanceof Date) {
                             Date date = (Date) value;
                             SimpleDateFormat sdf = new SimpleDateFormat(pattern);
                             textValue = sdf.format(date);
                         } else if (value instanceof byte[]) {
                             // 有图片时，设置行高为60px;
                             row.setHeightInPoints(60);
                             // 设置图片所在列宽度为80px,注意这里单位的一个换算
                             sheet.setColumnWidth(i, (short) (35.7 * 80));
                             // sheet.autoSizeColumn(i);
                             byte[] bsValue = (byte[]) value;
                             HSSFClientAnchor anchor = new HSSFClientAnchor(0, 0,
                                     1023, 255, (short) 6, index, (short) 6, index);
                             anchor.setAnchorType(2);
                             patriarch.createPicture(anchor, workbook.addPicture(
                                     bsValue, HSSFWorkbook.PICTURE_TYPE_JPEG));
                         } else {
                             // 其它数据类型都当作字符串简单处理
                             textValue = value.toString();
                         }
                         // 如果不是图片数据，就利用正则表达式判断textValue是否全部由数字组成
                         if (textValue != null) {
                             Pattern p = Pattern.compile("^//d+(//.//d+)?$");
                             Matcher matcher = p.matcher(textValue);
                             if (matcher.matches()) {
                                 // 是数字当作double处理
                                 cell.setCellValue(Double.parseDouble(textValue));
                             } else {
                                 HSSFRichTextString richString = new HSSFRichTextString(
                                         textValue);
                                 //HSSFFont font3 = workbook.createFont();
                                 //font3.setColor(HSSFColor.BLUE.index);
                                 //richString.applyFont(font3);
                                 cell.setCellValue(richString);
                             }
                         }
                    }
                } catch (SecurityException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (NoSuchMethodException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (IllegalArgumentException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } finally {
                    // 清理资源
                }
            }

        }
        try {
            workbook.write(out);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
	
	 public static void main(String[] args) {
	        // 测试学生
		 /**
	        ExportExcel<LolthTask> ex = new ExportExcel<LolthTask>();
	        String[] headers = { "colums1", "colums2", "colums3","colums4", "colums5", "colums6","colums7"};
	        List<LolthTask> dataset = new ArrayList<LolthTask>();
	        dataset.add(new LolthTask("1","2","3" ,"4" ,"5" ,"6","7"));
	        OutputStream out = null;
	        Map<String, Object> map = new HashMap<String, Object>();
		
	        try {
	            out = new FileOutputStream("E://ab.xls");
	            ex.exportExcel("test",headers, dataset, map, out);
	            
	            JOptionPane.showMessageDialog(null, "导出成功!");
	            System.out.println("excel导出成功！");
	            
	        } catch (Exception e) {
	            e.printStackTrace();
	        }finally{
	        	try {
					out.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        }
	        */
	    }
}
