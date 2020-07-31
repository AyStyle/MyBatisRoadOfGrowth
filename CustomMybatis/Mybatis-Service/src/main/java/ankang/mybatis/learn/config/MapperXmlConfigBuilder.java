package ankang.mybatis.learn.config;

import lombok.Getter;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

/**
 * @author: ankang
 * @email: dreedisgood@qq.com
 * @create: 2020-07-29
 */
public class MapperXmlConfigBuilder {

    @Getter
    private final Configuration configuration;

    public MapperXmlConfigBuilder(Configuration configuration) {
        this.configuration = configuration;
    }

    /**
     * 解析mapper.xml文件
     *
     * @param inputStream mapper.xml文件流
     */
     public void parseMapper(InputStream inputStream) throws DocumentException {
        final Document document = new SAXReader().read(inputStream);

        // 解析select标签
        parseSelect(document);
    }

    /**
     * 解析select标签
     *
     * @param document
     */
    private void parseSelect(Document document) {
        final String namespace = document.getRootElement().attributeValue("namespace");
        final Map<String, MappedStatement> statementMap = configuration.getStatementMap();
        final List<Node> selectList = document.selectNodes("//select");

        for (Node node : selectList) {
            final Element element = (Element) node;
            final String id = element.attributeValue("id");
            final String resultType = element.attributeValue("resultType");
            final String paramterType = element.attributeValue("paramterType");
            final String sql = element.getTextTrim();

            final String statementId = namespace + "." + id;
            final MappedStatement mappedStatement = new MappedStatement(id , resultType , paramterType , sql);

            statementMap.put(statementId , mappedStatement);
        }
    }
}
