package com.ruoyi.common.utils;

import java.util.List;
import java.util.Set;

/**
 * 集合操作工具类
 * @author yepanpan
 *
 */
public class CollectionUtils {
	
	/**
	 * 将列表里的对象拼接成一个字符串，并用指定的符号隔开
	 * @param <T>
	 * @param list List<T> 列表
	 * @param split String 分隔符
	 * @return String
	 */
	public static <T> String join(List<T> list, String split) {
		if(list == null || list.size() ==0) return "";
		
		StringBuilder sb = new StringBuilder();
		for(T s:list) {
			sb.append(split).append(s);
		}
		return sb.substring(split.length());
	}
	
	/**
	 * 将列表里的对象拼接成一个字符串，并用指定的符号隔开
	 * @param <T>
	 * @param list List<T> 列表
	 * @param split String 分隔符
	 * @return String
	 */
	public static <T> String join(Set<T> list, String split) {
		if(list == null || list.size() ==0) return "";
		
		StringBuilder sb = new StringBuilder();
		for(T s:list) {
			sb.append(split).append(s);
		}
		return sb.substring(split.length());
	}
}
