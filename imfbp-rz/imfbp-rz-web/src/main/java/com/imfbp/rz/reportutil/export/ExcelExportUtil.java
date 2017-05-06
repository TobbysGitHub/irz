package com.imfbp.rz.reportutil.export;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.imfbp.rz.domain.pub.BeanHelper;
import com.imfbp.rz.domain.pub.SuperBean;
import com.platform.common.utils.StringUtil;

public class ExcelExportUtil {

	public static XSSFWorkbook generateExcelDoc(List<? extends SuperBean> loanItemReport,
			ArrayList<String[]> titleList, String reportname) {
	    // 第一步，创建一个webbook，对应一个Excel文件  
        XSSFWorkbook wb = new XSSFWorkbook();
        // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet  
        XSSFSheet sheet = wb.createSheet();
		//设置工作表名
		wb.setSheetName(0, reportname);
        // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short  
        XSSFRow row = sheet.createRow(0);  
        // 第四步，创建单元格，并设置值表头 设置表头居中  
        XSSFCellStyle style = wb.createCellStyle();  
        style.setAlignment(XSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式(标题行)  
        
        CellRangeAddress range = new CellRangeAddress(0, 0, 0, titleList.size()-1);
        sheet.addMergedRegion(range);
        
        
        XSSFCell cell = row.createCell(0);  
        cell.setCellValue(reportname);  
        XSSFCellStyle style1 = wb.createCellStyle();  
        style1.setAlignment(XSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式 (表头)
        XSSFFont font = wb.createFont();
        font.setFontHeightInPoints((short) 20);
        style1.setFont(font);
        cell.setCellStyle(style1);
        
        HashMap<Integer, Integer> colWidthMap = new HashMap<Integer, Integer>();
        XSSFRow titlerow = sheet.createRow(1);  
        
        
        for (int i = 0; i < titleList.size(); i++) {
			String[] titleary = titleList.get(i);
			
			String title = titleary[0];
			
			XSSFCell titlecell = titlerow.createCell(i); 
			 
			titlecell.setCellValue(title); 
	        colWidthMap.put(i, title.getBytes().length);
	        titlecell.setCellStyle(style);
		}
        
        for (int i = 0; i < loanItemReport.size(); i++) {
        	SuperBean reportBean = loanItemReport.get(i);
        	
        	XSSFRow contentrow = sheet.createRow(i+2); 
        	
        	for (int j = 0; j < titleList.size(); j++) {
				String[] titleary = titleList.get(j);
				String fieldname = titleary[1];
				
				XSSFCell contentcell = contentrow.createCell(j);  
				if(BeanHelper.getFieldType(reportBean, fieldname).equals(String.class.getName())){
					String value = (String)reportBean.getAttribute(fieldname);
					contentcell.setCellValue(value);
					if(!StringUtil.isEmpty(value)){
						if(colWidthMap.get(j) < value.getBytes().length){
							colWidthMap.put(j, value.getBytes().length);
						}
					}
					
				} else if(BeanHelper.getFieldType(reportBean, fieldname).equals(BigDecimal.class.getName())){
					BigDecimal decvalue = (BigDecimal) reportBean.getAttribute(fieldname);
					contentcell.setCellValue(decvalue == null ? 0.00 : decvalue.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue());
				} else {
					Object decvalue = reportBean.getAttribute(fieldname);
					contentcell.setCellValue(decvalue == null ? "" : decvalue.toString());
					if(decvalue != null){
						if(colWidthMap.get(j) < decvalue.toString().getBytes().length){
							colWidthMap.put(j, decvalue.toString().getBytes().length);
						}
					}
				}
        	}
		}
        
       for (Iterator<Integer> iterator = colWidthMap.keySet().iterator(); iterator.hasNext();) {
    	    Integer index = (Integer) iterator.next();
    	    Integer value = colWidthMap.get(index);
    	    
    	    sheet.setColumnWidth(index, value*2*150);
       }
        
       return wb;
	}
	
	
	public static XSSFWorkbook generateHeadTailExcelDoc(SuperBean headBean,List<SuperBean> bodyBeanList,
			ArrayList<String[]> headnameList,ArrayList<String[]> bodynameList, String reportname) {
		// 第一步，创建一个webbook，对应一个Excel文件  
        XSSFWorkbook wb = new XSSFWorkbook();  
        // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet  
        XSSFSheet sheet = wb.createSheet();  
        // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short  
        XSSFRow row = sheet.createRow(0);  
        // 第四步，创建单元格，并设置值表头 设置表头居中  
        XSSFCellStyle titlestyle = wb.createCellStyle();  
        titlestyle.setAlignment(XSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式 
        Font ztFont = wb.createFont();   
        ztFont.setBoldweight(Font.BOLDWEIGHT_BOLD);//加粗
        titlestyle.setFont(ztFont);     //字体应用样式
        XSSFCellStyle style = wb.createCellStyle();  
        style.setAlignment(XSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式 
		
        CellRangeAddress range = new CellRangeAddress(0, 0, 0, bodynameList.size()-1);
        sheet.addMergedRegion(range);
        
        
        XSSFCell cell = row.createCell(0);  
        cell.setCellValue(reportname);  
        XSSFCellStyle style1 = wb.createCellStyle();  
        style1.setAlignment(XSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式 
        XSSFFont font = wb.createFont();
        font.setFontHeightInPoints((short) 20);
        style1.setFont(font);
        cell.setCellStyle(style1);
        
        int headcol = bodynameList.size() / 2;
        int rownum = 0;
        XSSFRow headrow = null;
        for (int i = 0; i < headnameList.size(); i++) {
        	int col = i % headcol;
        	if( col == 0){
        		headrow = sheet.createRow(rownum+1);
        		rownum ++;
        	} 
        	String[] titleary = headnameList.get(i);
        	
        	XSSFCell titlecell = headrow.createCell(col*2);
        	titlecell.setCellValue(titleary[0]); 
        	titlecell.setCellStyle(titlestyle);
        	
        	String fieldname = titleary[1];
        	XSSFCell contentcell = headrow.createCell(col*2+1);
        	if(headBean.getAttribute(fieldname) != null){
        		contentcell.setCellValue(headBean.getAttribute(fieldname).toString()); 
        	} else {
        		contentcell.setCellValue(""); 
        	}
        	contentcell.setCellStyle(style);
		}
        //XSSFRow emptyrow = sheet.createRow(rownum+1);  
        
        HashMap<Integer, Integer> colWidthMap = new HashMap<Integer, Integer>();
        XSSFRow titlerow = sheet.createRow(rownum+2);  
        
        
        for (int i = 0; i < bodynameList.size(); i++) {
			String[] titleary = bodynameList.get(i);
			
			String title = titleary[0];
			
			XSSFCell titlecell = titlerow.createCell(i); 
			 
			titlecell.setCellValue(title); 
	        colWidthMap.put(i, title.getBytes().length);
	        titlecell.setCellStyle(titlestyle);
		}
        rownum += 3;
        
        for (int i = 0; i < bodyBeanList.size(); i++) {
        	SuperBean reportBean = bodyBeanList.get(i);
        	
        	XSSFRow contentrow = sheet.createRow(i+rownum); 
        	
        	for (int j = 0; j < bodynameList.size(); j++) {
				String[] titleary = bodynameList.get(j);
				String fieldname = titleary[1];
				
				XSSFCell contentcell = contentrow.createCell(j);  
				if(BeanHelper.getFieldType(reportBean, fieldname).equals(String.class.getName())){
					String value = (String)reportBean.getAttribute(fieldname);
					contentcell.setCellValue(value);
					if(!StringUtil.isEmpty(value)){
						if(colWidthMap.get(j) < value.getBytes().length){
							colWidthMap.put(j, value.getBytes().length);
						}
					}
					
				} else if(BeanHelper.getFieldType(reportBean, fieldname).equals(BigDecimal.class.getName())){
					BigDecimal decvalue = (BigDecimal) reportBean.getAttribute(fieldname);
					contentcell.setCellValue(decvalue == null ? 0.0 : decvalue.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue());
				} else if(BeanHelper.getFieldType(reportBean, fieldname).equals(Double.class.getName())){
					if(reportBean.getAttribute(fieldname) != null){
						Double value = (Double) reportBean.getAttribute(fieldname);
						BigDecimal  dec = new  BigDecimal(value);  
						double scalevalue = dec.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();  
						reportBean.setAttribute(fieldname, scalevalue);
						contentcell.setCellValue(scalevalue+"");
					}
					
				} else {
					Object decvalue = reportBean.getAttribute(fieldname);
					contentcell.setCellValue(decvalue == null ? "" : decvalue.toString());
					if(decvalue != null){
						if(colWidthMap.get(j) < decvalue.toString().getBytes().length){
							colWidthMap.put(j, decvalue.toString().getBytes().length);
						}
					}
				}
        	}
		}
        
       for (Iterator<Integer> iterator = colWidthMap.keySet().iterator(); iterator.hasNext();) {
    	    Integer index = (Integer) iterator.next();
    	    Integer value = colWidthMap.get(index);
    	    
    	    sheet.setColumnWidth(index, value*2*150);
       }
        
        return wb;
	}
}
