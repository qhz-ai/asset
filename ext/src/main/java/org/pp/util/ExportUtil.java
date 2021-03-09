package org.pp.util;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExportUtil {
	public ExportUtil() {}
	
	
	/**
	 * 生成Excel文件 2007以上格式
	 * @param titles List<String> 列标题
	 * @param rows List<String[]> 数据
	 * @param sheetName String 表格名称
	 * @return XSSFWorkbook
	 */
	public static XSSFWorkbook exports(List<String> titles, List<Object[]> rows, String sheetName){
		
		XSSFWorkbook book = new XSSFWorkbook();
		XSSFSheet sheet = book.createSheet(sheetName);
		fillTitle(sheet, "导出"+sheetName, titles.size());
		fillSheet(sheet, titles, rows);
		return book;
	}
	
	/**
	 * 导出到输出流
	 * @param titles List<String> 列标题
	 * @param rows List<String[]> 数据
	 * @param sheetName String 表格名称
	 * @param os OutputStream 输出流
	 */
	public static void exports(List<String> titles, List<Object[]> rows, String sheetName, OutputStream os){
		
		XSSFWorkbook book = exports(titles, rows, sheetName);
		try {
			book.write(os);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

	/**
	 * 导出到文件
	 * @param titles List<String> 列标题
	 * @param rows List<String[]> 数据
	 * @param sheetName String 表格名称
	 * @param filePath String 输出路径
	 */
	public static void exports(List<String> titles, List<Object[]> rows, String sheetName, String filePath){
		try {
			exports(titles, rows, sheetName, new FileOutputStream(filePath));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 填充表格数据
	 * @param sheet XSSFSheet 表格对象
	 * @param titles List<String> 列标题
	 * @param rows List<String[]> 数据
	 */
	protected static void fillSheet(XSSFSheet sheet, List<String> titles, List<Object[]> rows){
		XSSFRow row = sheet.createRow(1); 
		XSSFCellStyle style = sheet.getWorkbook().createCellStyle();
		style.setFillBackgroundColor(IndexedColors.GREEN.getIndex());
		style.setBorderBottom(BorderStyle.MEDIUM);
		style.setBorderTop(BorderStyle.MEDIUM);
		style.setBorderLeft(BorderStyle.MEDIUM);
		style.setBorderRight(BorderStyle.MEDIUM);
		XSSFFont font = sheet.getWorkbook().createFont();
		font.setBold(true);
		style.setFont(font);
		for(int i=0; i<titles.size(); i++) {			
			XSSFCell cell = row.createCell(i);
			cell.setCellStyle(style);
			cell.setCellValue(titles.get(i));
		}	
		style = sheet.getWorkbook().createCellStyle();
		style.setWrapText(true);
		style.setAlignment(HorizontalAlignment.CENTER);
		style.setVerticalAlignment(VerticalAlignment.CENTER);
		for(int i=0; i<rows.size(); i++) {
			row = sheet.createRow(i+2); 
			Object[] r = rows.get(i);
			for(int j=0; j<r.length; j++) {
				Object v = r[j];
				XSSFCell cell =row.createCell(j);
				if(v == null){
					cell.setCellValue("");
				}else if(v instanceof String){
					if(v != null && !"".equals(v) && v.toString().indexOf("\r\n") != -1){
						cell.setCellStyle(style);
						cell.setCellValue(new XSSFRichTextString(v.toString()));
					}else{
						cell.setCellValue(v.toString());
					}
				}else if(v instanceof Integer){
					cell.setCellValue((Integer)v);
				}else if(v instanceof Double){
					cell.setCellValue((Double)v);
				}else if(v instanceof Float){
					cell.setCellValue((Float)v);
				}else if(v instanceof Long){
					cell.setCellValue((Long)v);
				}else if(v instanceof Boolean){
					cell.setCellValue((Boolean)v);
				}else if(v instanceof Date){
					DataFormat format = sheet.getWorkbook().createDataFormat();// 日期格式化
					CellStyle cellStyle = sheet.getWorkbook().createCellStyle();
					cellStyle.setDataFormat(format.getFormat("yyyy-MM-dd"));
					cell.setCellStyle(cellStyle);

					cell.setCellValue((Date)v);
				}else if(v instanceof java.math.BigDecimal){
					cell.setCellValue(((java.math.BigDecimal)v).doubleValue());
				}else{
					cell.setCellValue(v.toString());
				}
				
			}	
		}	
	}

	protected static void fillTitle(XSSFSheet sheet, String title, int cols){
		XSSFRow row = sheet.createRow(0);
		XSSFCellStyle style = sheet.getWorkbook().createCellStyle();
		style.setFillBackgroundColor(IndexedColors.RED.getIndex());
		for(int i=0; i<cols; i++) {
			XSSFCell cell = row.createCell(i);
			cell.setCellStyle(style);
			if(i==0)cell.setCellValue(title);
		}	
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, cols-1));
	}
}
