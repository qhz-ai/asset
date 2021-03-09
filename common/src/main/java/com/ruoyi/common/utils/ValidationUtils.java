package com.ruoyi.common.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

/**
 * Bean验证工具
 * @author yepanpan
 *
 */
public class ValidationUtils {
	private static ValidatorFactory factory = Validation.buildDefaultValidatorFactory();

	/**
	 * 验证指定的对象
	 * @param <T> 泛型
	 * @param t 要验证的对象
	 * @return List<String> 如果验证通过返回null,否则返回全部的错误信息
	 */
	public static <T> List<String> validate(T t) {
		Validator validator = factory.getValidator();
		Set<ConstraintViolation<T>> constraintViolations = validator.validate(t);
		if(constraintViolations.isEmpty()) {
			return null;
		}
		
		List<String> messageList = new ArrayList<>();
		for (ConstraintViolation<T> constraintViolation : constraintViolations) {
			messageList.add(constraintViolation.getMessage());
		}
		return messageList;
	}
	
	/**
	 * 
	 * 验证指定的对象
	 * @param <T> 泛型
	 * @param t 要验证的对象
	 * @param groups
	 * @return List<String> 如果验证通过返回null,否则返回全部的错误信息
	 */
	public static <T> List<String> validate(T t, Class... groups) {
		Validator validator = factory.getValidator();
		Set<ConstraintViolation<T>> constraintViolations = validator.validate(t, groups);
		if(constraintViolations.isEmpty()) {
			return null;
		}
		
		List<String> messageList = new ArrayList<>();
		for (ConstraintViolation<T> constraintViolation : constraintViolations) {
			messageList.add(constraintViolation.getMessage());
		}
		return messageList;
	}
}
