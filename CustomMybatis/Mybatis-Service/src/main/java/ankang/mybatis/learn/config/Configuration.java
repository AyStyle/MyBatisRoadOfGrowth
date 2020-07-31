package ankang.mybatis.learn.config;

import lombok.Getter;
import lombok.Setter;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: ankang
 * @email: dreedisgood@qq.com
 * @create: 2020-07-28
 */
@Getter
@Setter
public class Configuration {

    /**
     * 数据源配置
     */
    private DataSource dataSource;

    /**
     * mapper配置
     * key：statementId
     * value：封装好的mappedStatement对象
     */
    private Map<String, MappedStatement> statementMap = new HashMap<>();

}
