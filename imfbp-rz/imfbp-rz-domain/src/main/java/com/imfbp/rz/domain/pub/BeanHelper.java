package com.imfbp.rz.domain.pub;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.imfbp.rz.pub.IRZBeanItemsConsts;
import com.imfbp.rz.pub.IRZConsts;
import com.imfbp.rz.util.DateUtil;
import com.platform.common.utils.StringUtil;

public class BeanHelper {

	protected static final Object[] NULL_ARGUMENTS = new Object[0];

	private static BeanHelper bhelp = new BeanHelper();

	public static BeanHelper getInstance() {
		return bhelp;
	}

	public static Object getProperty(Object bean, String propertyName) {
		try {
			Method method = getInstance().getGetMethod(bean.getClass(),
					propertyName);
			if ((propertyName != null) && (method == null))
				return null;
			if (method == null) {
				return null;
			}
			return method.invoke(bean, NULL_ARGUMENTS);
		} catch (Exception e) {
			String errStr = new StringBuilder()
					.append("Failed to get property: ").append(propertyName)
					.toString();

			throw new RuntimeException(errStr, e);
		}
	}

	public static void setProperty(Object bean, String propertyName,
			Object value) {
		try {
			Method method = getInstance().getSetMethod(bean.getClass(),
					propertyName);
			if ((propertyName != null) && (method == null)) {
				return;
			}
			if (method == null) {
				return;
			}
			method.invoke(bean, new Object[] { value });
		} catch (IllegalArgumentException e) {
			String errStr = new StringBuilder()
					.append("Failed to set property: ")
					.append(propertyName)
					.append(" at bean: ")
					.append(bean.getClass().getName())
					.append(" with value:")
					.append(value)
					.append(" type:")
					.append(value == null ? "null" : value.getClass().getName())
					.toString();

			throw new IllegalArgumentException(errStr, e);
		} catch (Exception e) {
			String errStr = new StringBuilder()
					.append("Failed to set property: ").append(propertyName)
					.append(" at bean: ").append(bean.getClass().getName())
					.append(" with value:").append(value).toString();

			throw new RuntimeException(errStr, e);
		}
	}

	@SuppressWarnings("rawtypes")
	public static void setPropertyValue(Object bean, String propertyName,
			Object value) {
		try {
			Method method = getInstance().getSetMethod(bean.getClass(),
					propertyName);
			if ((propertyName != null) && (method == null)) {
				return;
			}
			if (method == null) {
				return;
			}
			String fieldName = getFieldType(bean, propertyName);
			if (StringUtil.isEmpty(fieldName)) {
				return;
			}
			Class clz = Class.forName(fieldName);
			if (clz.isInstance(value)) {
				// 如果类型兼容
				method.invoke(bean, new Object[] { value });
			} else {
				// 不兼容
				Object filedObj = clz.newInstance();
				if (filedObj instanceof Integer) {
					// Integer.valueOf(value.toString());
					method.invoke(bean,
							new Object[] { Integer.valueOf(value.toString()) });
				} else if (filedObj instanceof Double) {
					// Double.valueOf(value.toString());
					method.invoke(bean,
							new Object[] { Double.valueOf(value.toString()) });
				} else if (filedObj instanceof BigDecimal) {
					// new BigDecimal(value.toString());
					method.invoke(bean,
							new Object[] { new BigDecimal(value.toString()) });
				} else if (filedObj instanceof Byte) {
					// Byte.parseByte(value.toString());
					method.invoke(bean,
							new Object[] { Byte.parseByte(value.toString()) });
				} else {
					method.invoke(bean, new Object[] { value });
				}
			}
			// method.invoke(bean, new Object[] { value });
		} catch (IllegalArgumentException e) {
			String errStr = new StringBuilder()
					.append("Failed to set property: ")
					.append(propertyName)
					.append(" at bean: ")
					.append(bean.getClass().getName())
					.append(" with value:")
					.append(value)
					.append(" type:")
					.append(value == null ? "null" : value.getClass().getName())
					.toString();

			throw new IllegalArgumentException(errStr, e);
		} catch (Exception e) {
			String errStr = new StringBuilder()
					.append("Failed to set property: ").append(propertyName)
					.append(" at bean: ").append(bean.getClass().getName())
					.append(" with value:").append(value).toString();

			throw new RuntimeException(errStr, e);
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Method getGetMethod(Class objectClass, String fieldName)
			throws NoSuchMethodException, SecurityException {

		StringBuffer sb = new StringBuffer();

		sb.append("get");

		sb.append(fieldName.substring(0, 1).toUpperCase());

		sb.append(fieldName.substring(1));

		return objectClass.getMethod(sb.toString());
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Method getSetMethod(Class objectClass, String fieldName)
			throws Exception {
		Class[] parameterTypes = new Class[1];
		Field field = objectClass.getDeclaredField(fieldName);
		parameterTypes[0] = field.getType();
		StringBuffer sb = new StringBuffer();
		sb.append("set");

		sb.append(fieldName.substring(0, 1).toUpperCase());

		sb.append(fieldName.substring(1));

		Method method = objectClass.getMethod(sb.toString(), parameterTypes);

		return method;
	}

	public static String getFieldType(Object bean, String fieldname) {
		Field field = null;
		try {
			field = bean.getClass().getDeclaredField(fieldname);
		} catch (Exception e) {
			String errStr = new StringBuilder()
					.append("Failed to get property: ").append(fieldname)
					.append(" at bean: ").append(bean.getClass().getName())
					.toString();

			throw new RuntimeException(errStr);
		}
		return field.getType().getName();
	}

	public static String[] getPropertiesAry(Object bean) {
		Field[] fields = bean.getClass().getDeclaredFields();

		List<String> fieldnameList = new ArrayList<String>();
		for (int i = 0; i < fields.length; i++) {
			Field f = fields[i];
			if (f.getName().equals("serialVersionUID")) {
				continue;
			}
			fieldnameList.add(f.getName());
		}

		return fieldnameList.toArray(new String[0]);
	}


	public static String getBillmaker(SuperHeadBean headbean) {
		return (String) getProperty(headbean, headbean.billMakerFieldName());
	}

	public static void setBillmaker(SuperHeadBean headbean, String billmaker) {
		setProperty(headbean, headbean.billMakerFieldName(), billmaker);
	}

	public static String getInstanceId(SuperHeadBean headbean) {
		return (String) getProperty(headbean, headbean.instanceIdFieldName());
	}

	public static void setInstanceId(SuperHeadBean headbean,
			String flowinstanceid) {
		setProperty(headbean, headbean.instanceIdFieldName(), flowinstanceid);
	}

	public static String getBillno(SuperHeadBean headbean) {
		return (String) getProperty(headbean, headbean.billNoFieldName());
	}

	public static void setBillno(SuperHeadBean headbean, String billno) {
		setProperty(headbean, headbean.billNoFieldName(), billno);
	}

	public static String getBilldate(SuperHeadBean headbean) {
		return (String) getProperty(headbean, headbean.billDateName());
	}

	public static void setBilldate(SuperHeadBean headbean, String billdate) {
		setProperty(headbean, headbean.billDateName(), billdate);
	}

	public static String getApproveid(SuperHeadBean headbean) {
		return (String) getProperty(headbean, headbean.approverFieldName());
	}

	public static void setApproveid(SuperHeadBean headbean, String approveid) {
		setProperty(headbean, headbean.approverFieldName(), approveid);
	}

	public static String getApprovedate(SuperHeadBean headbean) {
		return (String) getProperty(headbean, headbean.approvedateFieldName());
	}

	public static void setApprovedate(SuperHeadBean headbean,
			String approvedate) {
		setProperty(headbean, headbean.approvedateFieldName(), approvedate);
	}

	public static String getApprovenote(SuperHeadBean headbean) {
		return (String) getProperty(headbean, headbean.approveNoteFieldName());
	}

	public static void setApprovenote(SuperHeadBean headbean, String approvenote) {
		setProperty(headbean, headbean.approveNoteFieldName(), approvenote);
	}

	public static Integer getApprovestatus(SuperHeadBean headbean) {
		return (Integer) getProperty(headbean,
				headbean.approveStatusFieldName());
	}

	public static void setApprovestatus(SuperHeadBean headbean,
			Integer approvestatus) {
		setProperty(headbean, headbean.approveStatusFieldName(), approvestatus);
	}

	public static void setModifier(SuperHeadBean headbean, String modifier) {
		setProperty(headbean, headbean.modifierFieldName(), modifier);
	}

	public static String getModifier(SuperHeadBean headbean) {
		return (String) getProperty(headbean, headbean.modifierFieldName());
	}

	public static void setModifitime(SuperHeadBean headbean, String modifitime) {
		setProperty(headbean, headbean.modifitimeFieldName(),
				modifitime);
	}
	
	/**
	 * 设置SuperBean的关键属性值
	 * 
	 * @param bean
	 * @param id
	 */
	public static void setObjectValue(SuperBean bean, String id) {
		bean.setAttribute(IRZBeanItemsConsts.ID, id);
		bean.setAttribute(IRZBeanItemsConsts.DR, IRZConsts.DRSTATE_INIT);
		bean.setAttribute(IRZBeanItemsConsts.TS, DateUtil.getTs());
	}

	public static String getModifitime(SuperHeadBean headbean) {
		return (String) getProperty(headbean,
				headbean.modifitimeFieldName());
	}
}
