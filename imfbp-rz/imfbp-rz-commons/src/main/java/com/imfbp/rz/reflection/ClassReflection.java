package com.imfbp.rz.reflection;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ClassReflection {

	/**
	 * 
	 * description：反射实体类赋值
	 * @param class1 用于赋值的实体类
	 * @param class2 需要待赋值的实体类 
	 * @throws Exception 
	 * 
	 */
	public static void reflectionAttr(Object class1, Object class2) throws Exception {
		Class clazz1 = Class.forName(class1.getClass().getName());
		Class clazz2 = Class.forName(class2.getClass().getName());
		// 获取两个实体类的所有属性
		Field[] fields1 = clazz1.getDeclaredFields();
		Field[] fields2 = clazz2.getDeclaredFields();
		ClassReflection cr = new ClassReflection();
		// 遍历class1Bean，获取逐个属性值，然后遍历class2Bean查找是否有相同的属性，如有相同则赋值
		for (Field f1 : fields1) {
			if (f1.getName().equals("serialVersionUID"))
				continue;
			Object value = cr.invokeGetMethod(class1, f1.getName(), null);
			for (Field f2 : fields2) {
				if (f1.getName().equals(f2.getName())) {
					Object[] obj = new Object[1];
					obj[0] = value;
					cr.invokeSetMethod(class2, f2.getName(), obj);
				}
			}
		}

	}

	/**
	 * 
	 * 执行某个Field的getField方法
	 * 
	 * @param clazz 类
	 * @param fieldName 类的属性名称
	 * @param args 参数，默认为null
	 * @return
	 */
	private Object invokeGetMethod(Object clazz, String fieldName, Object[] args) {
		String methodName = fieldName.substring(0, 1).toUpperCase()
				+ fieldName.substring(1);
		Method method = null;
		try {
			method = Class.forName(clazz.getClass().getName())
					.getDeclaredMethod("get" + methodName);
			return method.invoke(clazz);
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

	/**
	 * 
	 * 执行某个Field的setField方法
	 * 
	 * @param clazz 类
	 * @param fieldName 类的属性名称
	 * @param args 参数，默认为null
	 * @return
	 */
	private Object invokeSetMethod(Object clazz, String fieldName, Object[] args) {
		String methodName = fieldName.substring(0, 1).toUpperCase()
				+ fieldName.substring(1);
		Method method = null;
		try {
			Class[] parameterTypes = new Class[1];
			Class c = Class.forName(clazz.getClass().getName());
			Field field = c.getDeclaredField(fieldName);
			parameterTypes[0] = field.getType();
			method = c.getDeclaredMethod("set" + methodName, parameterTypes);
			return method.invoke(clazz, args);
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

}
