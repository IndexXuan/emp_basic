package util;

/** dao工厂，为调用者提供dao实现类的实例 */
public class DAOFactory {
	// 定义获取实例的方法
	public static Object getInstance(String type) {
		Object obj = null;
		// 根据接口名找到对应的类名
		String className = ConfigUtil.getValue(type);
		// 使用反射创建实例
		try {
			obj = Class.forName(className).newInstance();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return obj;
	}
}