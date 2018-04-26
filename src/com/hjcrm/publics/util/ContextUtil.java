package com.hjcrm.publics.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * 
 * @author likang
 * @date 2016-10-13 上午10:42:46
 */
public class ContextUtil implements ApplicationContextAware, InitializingBean {

	private static ApplicationContext context;
	private static final String ISSEND = "issend";
	// 注入配置文件的路径,从ClassPath开始。
	private String configPath;
	private static Map<String, Object> initConfig = new HashMap<String, Object>();

	public void setConfigPath(String configPath) {
		this.configPath = configPath;
	}

	public void setApplicationContext(ApplicationContext appliactionContext)
			throws BeansException {
		context = appliactionContext;
	}

	public static ApplicationContext getApplicationContext() {
		return context;
	}

	public synchronized void afterPropertiesSet() throws Exception {
		// 读取系统初始化配置文件，因为读取了硬盘文件，所以需要做线程同步。
		String[] paths = configPath.split(",");
		for (String path : paths) {
			readConfig(path.trim());
		}
		initConfig.put(ISSEND, "0");
	}

	public static Object createBean(Class clazz) {
		String beanName = clazz.getName();
		// 将applicationContext转换为ConfigurableApplicationContext
		ConfigurableApplicationContext configurableApplicationContext = (ConfigurableApplicationContext) context;
		// 获取bean工厂并转换为DefaultListableBeanFactory
		DefaultListableBeanFactory defaultListableBeanFactory = (DefaultListableBeanFactory) configurableApplicationContext.getBeanFactory();
		if (context.containsBean(beanName))
			return context.getBean(beanName);
		BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(clazz);
		defaultListableBeanFactory.registerBeanDefinition(beanName,builder.getRawBeanDefinition());
		return context.getBean(beanName);
	}

	private void readConfig(String path) {
		InputStream in = null;
		Properties prop;
		try {
			in = this.getClass().getClassLoader().getResourceAsStream(path);
			prop = new Properties();
			prop.load(in);
		} catch (IOException e) {
			throw new RuntimeException("在读取配置文件时发生错误！请确认配置的路径正确！");
		} finally {
			try {
				if (in != null)
					in.close();
			} catch (IOException e) {
				throw new RuntimeException("在读取配置文件时发生错误！请确认文件没有被占用！");
			}
		}
		if (prop != null) {
			Set<Object> keys = prop.keySet();
			String key;
			for (Object obj : keys) {
				key = obj.toString();
				initConfig.put(key, prop.get(key));
			}
		}
	}

	public static String getInitConfig(String key) {
		Object val = initConfig.get(key);
		return val != null ? val.toString() : null;
	}

	public static void setInitConfig(String key, String value) {
		initConfig.remove(key);
		initConfig.put(key, value);
	}

	public static <T> T getBean(Class<T> clazz) {
		return (T) context.getBean(clazz.getName());
	}

	public static Object getBean(String beanName) {
		return context.getBean(beanName);
	}

	public static Object getBean(String beanName, Class<?> requireType) {
		return context.getBean(beanName, requireType);
	}

	public static boolean containsBean(String beanName) {
		return context.containsBean(beanName);
	}

	public static boolean isSingleton(String beanName) {
		return context.isSingleton(beanName);
	}

	public static Class<?> getType(String beanName) {
		return context.getType(beanName);
	}

	public static String[] getAliases(String beanName) {
		return context.getAliases(beanName);
	}

}
