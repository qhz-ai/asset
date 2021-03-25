package com.ruoyi.util;

import java.util.List;

import org.apache.poi.ss.formula.functions.T;

public class CollectUtil {
	
	/**
	 * 用指定的分隔符将集合中的数据组合成一个字符串 2,3,4
	 * @param list 集合
	 * @param sp 分隔符
	 * @return
	 */
	public static<T> String join(List<T> list, String sp) {
		if(list == null || list.size() == 0) return null;
		String str = "";
		for(T t:list) {
			str += sp + t.toString();
		}
		return str.substring(1);
	}
	
	/**
	 * 用指定的分隔符将集合中的数据组合成一个字符串（含首尾） ,2,3,4,
	 * @param list 集合
	 * @param sp 分隔符
	 * @return
	 */
	public static<T> String joins(List<T> list, String sp) {
		if(list == null || list.size() == 0) return null;
		String str = "";
		for(T t:list) {
			str += sp + t.toString();
		}
		return str+",";
	}
	
	/**
	 * 用指定的分隔符将数组中的数据组合成一个字符串 2,3,4
	 * @param list 集合
	 * @param sp 分隔符
	 * @return
	 */
	public static String join(T arr[], String sp) {
		if(arr == null || arr.length == 0) return null;
		String str = "";
		for(T a:arr) {
			str += sp + a.toString();
		}
		return str.substring(1);
	}
	
	/**
	 * 用指定的分隔符将数组中的数据组合成一个字符串（含首尾） ,2,3,4,
	 * @param list 集合
	 * @param sp 分隔符
	 * @return
	 */
	public static<T> String joins(T arr[], String sp) {
		if(arr == null || arr.length == 0) return null;
		String str = "";
		for(T a:arr) {
			str += sp + a.toString();
		}
		return str+",";
	}
}
