package util;

import java.io.InputStream;
import java.util.Properties;

/** 读取properties文件的工具类 */
public class ConfigUtil {
	private static Properties props = new Properties();
	static {
		// 1，获取类加载器
		ClassLoader loder = ConfigUtil.class.getClassLoader();
		// 2，将配置文件以流的形式读取进来
		InputStream ips = loder
				.getResourceAsStream("util/daoconfig.properties");
		try {
			// 3，加载配置文件
			props.load(ips);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 获取配置文件中的内容
	public static String getValue(String key) {
		return props.getProperty(key);
	}

}
