/**
 * 
 */
package com.jspmyadmin.framework.tag.support;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.servlet.jsp.tagext.SimpleTagSupport;

import com.jspmyadmin.framework.util.FrameworkConstants;

/**
 * @author Yugandhar Gangu
 * @created_at 2016/01/28
 *
 */
public class AbstractSimpleTagSupport extends SimpleTagSupport {

	/**
	 * 
	 * @param bean
	 * @param name
	 * @return
	 */
	protected Object getReflectValue(Object bean, String name) {
		Object value = null;
		try {
			Method method = bean.getClass()
					.getMethod(FrameworkConstants.GET + name.substring(0, 1).toUpperCase() + name.substring(1));
			if (method == null) {
				method = bean.getClass().getMethod(name);
			}
			value = method.invoke(bean);
			method = null;
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return value;
	}

	/**
	 * 
	 * @param bean
	 * @param name
	 * @return
	 */
	protected void setReflectValue(Object bean, String name, Serializable value) {
		try {
			Method method = bean.getClass()
					.getMethod(FrameworkConstants.SET + name.substring(0, 1).toUpperCase() + name.substring(1));
			if (method == null) {
				method = bean.getClass().getMethod(FrameworkConstants.SET + name.substring(2));
			}
			method.invoke(bean, value);
			method = null;
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @param bean
	 * @param name
	 * @param value
	 */
	protected Object getReflectValue(Object bean, String name, int index) {
		Object value = null;
		try {
			Method method = bean.getClass().getMethod(name, Integer.TYPE);
			value = method.invoke(bean, index);
			method = null;
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return value;
	}

	/**
	 * 
	 * @param val
	 * @return
	 */
	protected boolean isEmpty(String val) {
		if (val == null || FrameworkConstants.BLANK.equals(val.trim())) {
			return true;
		}
		return false;
	}

}