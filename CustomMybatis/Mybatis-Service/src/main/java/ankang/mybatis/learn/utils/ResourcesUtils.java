package ankang.mybatis.learn.utils;

import java.io.InputStream;

/**
 * @author: ankang
 * @email: dreedisgood@qq.com
 * @create: 2020-07-28
 */
public class ResourcesUtils {

    /**
     * 读取资源文件，并返回资源文件流
     * @param path 资源文件路径
     * @return 资源文件流
     */
    public static InputStream getResourceAsStream(String path){
        return ResourcesUtils.class.getClassLoader().getResourceAsStream(path);
    }

}
