package com.imfbp.rz.util;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
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
import org.apache.poi.hssf.util.HSSFColor;

import com.platform.common.utils.StringUtil;
import com.platform.common.utils.excel.ExcelWriterUtil;

/**
 * 生成导出Excel文件对象
 * 
 * @author samlee
 * 
 */
public class ExcelWriterTool<T> extends ExcelWriterUtil{
	
    public void exportExcel(String title,String[] headers, Collection<T> dataset,  
            OutputStream out)  
    {  
        exportExcel(title, headers, dataset, out, "yyyy-MM-dd");  
    }  
    public void exportExcel(String title,Map<String,String> map, Collection<T> dataset,  
    		OutputStream out)  
    {  
    	exportExcel(title,map, dataset, out, "yyyy-MM-dd");  
    }  
  
    public void exportExcel(String[] headers, Collection<T> dataset,  
            OutputStream out, String pattern)  
    {  
        exportExcel("测试POI导出EXCEL文档", headers, dataset, out, pattern);  
    }  
  
    /** 
     * 这是一个通用的方法，利用了JAVA的反射机制，可以将放置在JAVA集合中并且符号一定条件的数据以EXCEL 的形式输出到指定IO设备上 
     *  
     * @param title 
     *            表格标题名 
     * @param headers 
     *            表格属性列名数组 
     * @param dataset 
     *            需要显示的数据集合,集合中一定要放置符合javabean风格的类的对象。此方法支持的 
     *            javabean属性的数据类型有基本数据类型及String,Date,byte[](图片数据) 
     * @param out 
     *            与输出设备关联的流对象，可以将EXCEL文档导出到本地文件或者网络中 
     * @param pattern 
     *            如果有时间数据，设定输出格式。默认为"yyy-MM-dd" 
     */  
    @SuppressWarnings("unchecked")  
    public void exportExcel(String title, String[] headers,  
            Collection<T> dataset, OutputStream out, String pattern)  
    {  
    	//申明宽度
    	Map<Integer,Integer> colWidthMap= new HashMap<Integer, Integer>();
    	
        // 声明一个工作薄  
        HSSFWorkbook workbook = new HSSFWorkbook();  
        // 生成一个表格  
        HSSFSheet sheet = workbook.createSheet(title);  
        // 设置表格默认列宽度为15个字节  
        sheet.setDefaultColumnWidth((short) 15);  
        // 生成一个样式  
        HSSFCellStyle style = workbook.createCellStyle();  
        // 设置这些样式  
        style.setFillForegroundColor(HSSFColor.SKY_BLUE.index);  
        style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);  
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);  
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);  
        style.setBorderRight(HSSFCellStyle.BORDER_THIN);  
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);  
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);  
        // 生成一个字体  
        HSSFFont font = workbook.createFont();  
        font.setColor(HSSFColor.VIOLET.index);  
        font.setFontHeightInPoints((short) 12);  
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);  
        // 把字体应用到当前的样式  
        style.setFont(font);  
        // 生成并设置另一个样式  
        HSSFCellStyle style2 = workbook.createCellStyle();  
        style2.setFillForegroundColor(HSSFColor.LIGHT_YELLOW.index);  
        style2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);  
        style2.setBorderBottom(HSSFCellStyle.BORDER_THIN);  
        style2.setBorderLeft(HSSFCellStyle.BORDER_THIN);  
        style2.setBorderRight(HSSFCellStyle.BORDER_THIN);  
        style2.setBorderTop(HSSFCellStyle.BORDER_THIN);  
        style2.setAlignment(HSSFCellStyle.ALIGN_CENTER);  
        style2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);  
        // 生成另一个字体  
        HSSFFont font2 = workbook.createFont();  
        font2.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);  
        // 把字体应用到当前的样式  
        style2.setFont(font2);  
  
        // 声明一个画图的顶级管理器  
        //HSSFPatriarch patriarch = sheet.createDrawingPatriarch();  
        // 定义注释的大小和位置,详见文档  
       // HSSFComment comment = patriarch.createComment(new HSSFClientAnchor(0,  
        //        0, 0, 0, (short) 4, 2, (short) 6, 5));  
        // 设置注释内容  
        //comment.setString(new HSSFRichTextString("可以在POI中添加注释！"));  
        // 设置注释作者，当鼠标移动到单元格上是可以在状态栏中看到该内容.  
        //comment.setAuthor("leno");  
  
        // 产生表格标题行  
        HSSFRow row = sheet.createRow(0);  
        for (short i = 0; i < headers.length; i++)  
        {  
            HSSFCell cell = row.createCell(i);  
            cell.setCellStyle(style);  
            HSSFRichTextString text = new HSSFRichTextString(headers[i]);  
            cell.setCellValue(text);  
        }  
  
        // 遍历集合数据，产生数据行  
        Iterator<T> it = dataset.iterator();  
        int index = 0;  
        while (it.hasNext())  
        {  
            index++;  
            row = sheet.createRow(index);  
            T t = (T) it.next();  
            // 利用反射，根据javabean属性的先后顺序，动态调用getXxx()方法得到属性值  
            Field[] fields = t.getClass().getDeclaredFields();  
            for (Integer i = 0; i < fields.length; i++)  
            {  
                HSSFCell cell = row.createCell(i);  
                cell.setCellStyle(style2);  
                Field field = fields[i];  
                String fieldName = field.getName();  
                String getMethodName = "get"  
                        + fieldName.substring(0, 1).toUpperCase()  
                        + fieldName.substring(1);  
                try  
                {  
                    Class tCls = t.getClass();  
                    Method getMethod = tCls.getMethod(getMethodName,  
                            new Class[]  
                            {});  
                    Object value = getMethod.invoke(t, new Object[]  
                    {});  
                    // 判断值的类型后进行强制类型转换  
                    String textValue = null;  
                   
                    if (fieldName.equals("balancedirect"))  
                    {  
                    	
                        if (StringUtil.objectToString(value).equals("D"))  
                        {  
                            textValue = "借";  
                        }else if(StringUtil.objectToString(value).equals("C") ){
                        	textValue = "贷";  
                        }else{
                        	textValue = "平";  
                        }
                    }  
                    else if (value instanceof Date)  
                    {  
                        Date date = (Date) value;  
                        SimpleDateFormat sdf = new SimpleDateFormat(pattern);  
                        textValue = sdf.format(date);  
                    }  
                   
                    else  
                    {  
                        // 其它数据类型都当作字符串简单处理  
                        textValue = StringUtil.objectToString(value);
                    }  
                    // 如果不是图片数据，就利用正则表达式判断textValue是否全部由数字组成  
                    if (textValue != null)  
                    {  
                        Pattern p = Pattern.compile("^//d+(//.//d+)?$");  
                        Matcher matcher = p.matcher(textValue);  
                        if (matcher.matches())  
                        {  
                            // 是数字当作double处理  
                            cell.setCellValue(Double.parseDouble(textValue));  
                        }  
                        else  
                        {  
                            HSSFRichTextString richString = new HSSFRichTextString(  
                                    textValue);  
                            HSSFFont font3 = workbook.createFont();  
                            font3.setColor(HSSFColor.BLUE.index);  
                            richString.applyFont(font3);  
                            cell.setCellValue(richString);  
                            if(!colWidthMap.containsKey(i) ||colWidthMap.get(i) < textValue.toString().getBytes().length){
    							colWidthMap.put(i, textValue.toString().getBytes().length);
    						}
                        }
                    }
                }  
                catch (SecurityException e)  
                {  
                    e.printStackTrace();  
                }  
                catch (NoSuchMethodException e)  
                {  
                    e.printStackTrace();  
                }  
                catch (IllegalArgumentException e)  
                {  
                    e.printStackTrace();  
                }  
                catch (IllegalAccessException e)  
                {  
                    e.printStackTrace();  
                }  
                catch (InvocationTargetException e)  
                {  
                    e.printStackTrace();  
                }  
                finally  
                {  
                    // 清理资源  
                }  
            }  
        }  
        for (Iterator<Integer> iterator = colWidthMap.keySet().iterator(); iterator.hasNext();) {
    	    Integer indexCol = (Integer) iterator.next();
    	    Integer valueCol = colWidthMap.get(index);
    	    //默认长度
    	    if(valueCol<20){
    	    	sheet.setColumnWidth(indexCol-1, 20*2*100);
    	    	continue;
    	    }
    	    sheet.setColumnWidth(indexCol-1, valueCol*2*100);
    	 }
        try  
        {  
            workbook.write(out);  
        }  
        catch (IOException e)  
        {  
            e.printStackTrace();  
        }  
    }  
    
    @SuppressWarnings("unchecked")  
    public void exportExcel(String title,Map<String,String> map,
            Collection<T> dataset, OutputStream out, String pattern)  
    {  
    	//申明宽度
    	Map<Integer,Integer> colWidthMap= new HashMap<Integer, Integer>();
        // 声明一个工作薄  
        HSSFWorkbook workbook = new HSSFWorkbook();  
        // 生成一个表格  
        HSSFSheet sheet = workbook.createSheet(title);  
        // 设置表格默认列宽度为15个字节  
        sheet.setDefaultColumnWidth((short) 16);  
        // 生成一个样式  
        HSSFCellStyle style = workbook.createCellStyle();  
        // 设置这些样式  
        style.setFillForegroundColor(HSSFColor.SKY_BLUE.index);  
        style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);  
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);  
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);  
        style.setBorderRight(HSSFCellStyle.BORDER_THIN);  
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);  
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);  
        // 生成一个字体  
        HSSFFont font = workbook.createFont();  
        font.setColor(HSSFColor.VIOLET.index);  
        font.setFontHeightInPoints((short) 12);  
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);  
        // 把字体应用到当前的样式  
        style.setFont(font);  
        // 生成并设置另一个样式  
        HSSFCellStyle style2 = workbook.createCellStyle();  
        style2.setFillForegroundColor(HSSFColor.LIGHT_YELLOW.index);  
        style2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);  
        style2.setBorderBottom(HSSFCellStyle.BORDER_THIN);  
        style2.setBorderLeft(HSSFCellStyle.BORDER_THIN);  
        style2.setBorderRight(HSSFCellStyle.BORDER_THIN);  
        style2.setBorderTop(HSSFCellStyle.BORDER_THIN);  
        style2.setAlignment(HSSFCellStyle.ALIGN_CENTER);  
        style2.setVerticalAlignment(HSSFCellStyle.ALIGN_LEFT);  
        // 生成另一个字体  
        HSSFFont font2 = workbook.createFont();  
        font2.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);  
        // 把字体应用到当前的样式  
        style2.setFont(font2);  
  
        // 声明一个画图的顶级管理器  
        //HSSFPatriarch patriarch = sheet.createDrawingPatriarch();  
        // 定义注释的大小和位置,详见文档  
        //HSSFComment comment = patriarch.createComment(new HSSFClientAnchor(0,  
         //       0, 0, 0, (short) 4, 2, (short) 6, 5));  
        // 设置注释内容  
        //comment.setString(new HSSFRichTextString("编码"));  
        // 设置注释作者，当鼠标移动到单元格上是可以在状态栏中看到该内容.  
        //comment.setAuthor("在线财务系统");  
  
        // 产生表格标题行  
        HSSFRow row = sheet.createRow(0);  
        int titleIndex = 0;
        for (Iterator<String> titleIt = map.keySet().iterator();titleIt.hasNext();)  
        {  
            HSSFCell cell = row.createCell(titleIndex++);  
            cell.setCellStyle(style);  
            HSSFRichTextString text = new HSSFRichTextString(titleIt.next());  
            cell.setCellValue(text);  
        }  
  
        // 遍历集合数据，产生数据行  
        Iterator<T> it = dataset.iterator();  
        int index = 0;  
        while (it.hasNext())  
        {  
            index++;  
            row = sheet.createRow(index);  
            T t = (T) it.next();  
            int valueIndex = 0;
            Logger log=Logger.getLogger(getClass());
            // 利用反射，根据javabean属性的先后顺序，动态调用getXxx()方法得到属性值  
            for (Iterator<String> valueIt = map.values().iterator();valueIt.hasNext();)  
            {  
                HSSFCell cell = row.createCell(valueIndex++);  
                cell.setCellStyle(style2);  
                String fieldName = valueIt.next();  
                log.error("excel 列名"+fieldName);
                String getMethodName = "get"  
                        + fieldName.substring(0, 1).toUpperCase()  
                        + fieldName.substring(1);  
                log.error("excel 反射方法名"+getMethodName);
                try  
                {  
                    Class tCls = t.getClass();  
                    log.error("excel 反射类名"+tCls);
                    Method getMethod = tCls.getMethod(getMethodName,  
                            new Class[]  
                            {});  
                    Object value = getMethod.invoke(t, new Object[]  
                    {});  
                    log.error("excel 反射get获取的值"+value);
                    // 判断值的类型后进行强制类型转换  
                    String textValue = null;  
                    if (fieldName.equals("balancedirect"))  
                    {  
                        if (StringUtil.objectToString(value).equals("D"))  
                        {  
                            textValue = "借";  
                        }else if(StringUtil.objectToString(value).equals("C") ){
                        	textValue = "贷";  
                        }else{
                        	textValue = "平";  
                        }
                    }
                    else if(fieldName.equals("balanceDire")){
                             if (StringUtil.objectToString(value).equals("D"))  
                             {  
                                 textValue = "借";  
                             }else if(StringUtil.objectToString(value).equals("C") ){
                             	textValue = "贷";  
                             }else{
                             	textValue = "平";  
                             }
                    }
                    else if (value instanceof Date)  
                    {  
                        Date date = (Date) value;  
                        SimpleDateFormat sdf = new SimpleDateFormat(pattern);  
                        textValue = sdf.format(date);  
                    } 
                    else  
                    {    
                        // 其它数据类型都当作字符串简单处理  
                        textValue = StringUtil.objectToString(value);
                    }  
                    // 如果不是图片数据，就利用正则表达式判断textValue是否全部由数字组成  
                    if (textValue != null)  
                    {  
                        Pattern p = Pattern.compile("^//d+(//.//d+)?$");  
                        Matcher matcher = p.matcher(textValue);  
                        if (matcher.matches())  
                        {  
                            // 是数字当作double处理  
                            cell.setCellValue(Double.parseDouble(textValue));  
                        }  
                        else  
                        {  
                            HSSFRichTextString richString = new HSSFRichTextString(  
                                    textValue);  
                            HSSFFont font3 = workbook.createFont();  
                            font3.setColor(HSSFColor.BLUE.index);  
                            richString.applyFont(font3);  
                            cell.setCellValue(richString);  
                            if(!colWidthMap.containsKey(valueIndex) || colWidthMap.get(valueIndex) < textValue.toString().getBytes().length){
    							colWidthMap.put(valueIndex, textValue.toString().getBytes().length);
    						}
                        }    
                    }
                    }  
                catch (SecurityException e)  
                {  
                    e.printStackTrace();  
                }  
                catch (NoSuchMethodException e)  
                {  
                    e.printStackTrace();  
                }  
                catch (IllegalArgumentException e)  
                {  
                    e.printStackTrace();  
                }  
                catch (IllegalAccessException e)  
                {  
                    e.printStackTrace();  
                }  
                catch (InvocationTargetException e)  
                {  
                    e.printStackTrace();  
                }  
                finally  
                {  
                    // 清理资源  
                }  
            }
            
        }  
        for (Iterator<Integer> iterator = colWidthMap.keySet().iterator(); iterator.hasNext();) {
    	    Integer indexCol = (Integer) iterator.next();
    	    Integer valueCol = colWidthMap.get(indexCol);
    	    //默认长度
    	    if(valueCol<20){
    	    	sheet.setColumnWidth(indexCol-1, 20*2*100);
    	    	continue;
    	    }
    	    sheet.setColumnWidth(indexCol-1, valueCol*2*100);
    	 }  
        try  
        {  
            workbook.write(out);  
        }  
        catch (IOException e)  
        {  
            e.printStackTrace();  
        }  
    }  

}