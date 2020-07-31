package ankang.mybatis.learn.session;

import ankang.mybatis.learn.config.Configuration;
import ankang.mybatis.learn.config.XmlConfigBuilder;
import org.dom4j.DocumentException;

import java.io.InputStream;

/**
 * @author: ankang
 * @email: dreedisgood@qq.com
 * @create: 2020-07-28
 */
public class SqlSessionFactoryBuilder {

    /**
     * 解析mybatis配置文件，并返回SqlSessionFactory对象
     *
     * @param inputStream
     * @return
     */
    public SqlSessionFactory build(InputStream inputStream) throws DocumentException {
        // 1.使用dom4j解析配置文件，将解析出来的内容封装到Configuration中
        final Configuration configuration = new XmlConfigBuilder().parseConfig(inputStream);

        // 2.创建SqlSessionFactory对象
        final SqlSessionFactory factory = new DefaultSqlSessionFactory(configuration);

        return factory;
    }

}
