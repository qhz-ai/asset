package com.ruoyi.util;

public class FileUtil {
	private static  String docExt = ",doc,docx,ppt,pptx,xls,xlsx,pdf,txt,";
	private static String videoExt = ",mp4,mp3,wmv,avi,3gp,flv,";
	private static String imageExt = ",jpg,jpeg,png,gif,";
	private static String zipExt = ",zip,rar,gz,";
	
	/**
	 * 根据扩展名获取文件类型
	 * @param ext
	 * @return
	 */
	public static String getType(String ext) {
		ext = ","+ext.toLowerCase()+",";
		if(docExt.indexOf(ext) > -1) return "1";
		if(imageExt.indexOf(ext) > -1) return "2";
		if(videoExt.indexOf(ext) > -1) return "3";
		if(zipExt.indexOf(ext) > -1) return "4";
		return "5";
	}
	
	/**
	 * 根据扩展名获取文件类型
	 * @param ext
	 * @return
	 */
	public static String getExt(String name) {
		return name.substring(name.lastIndexOf('.')+1);
	}
}
