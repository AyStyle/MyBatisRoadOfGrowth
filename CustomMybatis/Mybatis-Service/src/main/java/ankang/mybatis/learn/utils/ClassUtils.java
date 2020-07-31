package ankang.mybatis.learn.utils;

/**
 * @author: ankang
 * @email: dreedisgood@qq.com
 * @create: 2020-07-29
 */
public class ClassUtils {
    public static Class<?> getClass(String className) throws ClassNotFoundException {
        if (className != null) {
            return Class.forName(className);
        } else {
            return null;
        }
    }
}
