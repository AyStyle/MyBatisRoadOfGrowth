package ankang.mybatis.learn.config;

import ankang.mybatis.learn.utils.ResourcesUtils;
import com.alibaba.druid.pool.DruidDataSource;
import lombok.Getter;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.util.List;
import java.util.Properties;

/**
 * @author: ankang
 * @email: dreedisgood@qq.com
 * @create: 2020-07-28
 */
public class XmlConfigBuilder {

    @Getter
    private Configuration configuration = new Configuration();

    /**
     * 解析mybatis.xml文件，并返回Configuration对象
     * 使用dom4j解析配置文件，将解析出来的内容封装到Configuration中
     *
     * @param inputStream mybatis.xml资源文件流
     * @return Configuration对象
     */
    public Configuration parseConfig(InputStream inputStream) throws DocumentException {
        final Document document = new SAXReader().read(inputStream);

        // 解析dataSource标签
        parseDataSource(document);

        // 解析StatementMap
        parseStatementMap(document);

        return configuration;
    }

    /**
     * 解析DataSource
     */
    private void parseDataSource(Document document) {
        final List<Node> propertyList = document.selectNodes("//dataSource/property");
        final Properties properties = new Properties();

        for (Node node : propertyList) {
            final Element element = (Element) node;
            final String name = element.attributeValue("name");
            final String value = element.attributeValue("value");
            properties.put(name , value);
        }

        final DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl(properties.getProperty("jdbcUrl"));
        dataSource.setDriverClassName(properties.getProperty("driverClass"));
        dataSource.setUsername(properties.getProperty("username"));
        dataSource.setPassword(properties.getProperty("password"));

        configuration.setDataSource(dataSource);
    }

    /**
     * 解析StatementMap
     *
     * @param document
     */
    private void parseStatementMap(Document document) throws DocumentException {
        final MapperXmlConfigBuilder mapperXmlConfigBuilder = new MapperXmlConfigBuilder(configuration);

        // 1.获取mapper文件路
        final List<Node> mapperList = document.selectNodes("//mappers/mapper");
        for (Node node : mapperList) {
            final Element element = (Element) node;
            final String resource = element.attributeValue("resource");
            // 2.获取mapper文件流
            final InputStream resourceAsStream = ResourcesUtils.getResourceAsStream(resource);
            // 3.使用dom4j解析
            mapperXmlConfigBuilder.parseMapper(resourceAsStream);
        }

    }


}
