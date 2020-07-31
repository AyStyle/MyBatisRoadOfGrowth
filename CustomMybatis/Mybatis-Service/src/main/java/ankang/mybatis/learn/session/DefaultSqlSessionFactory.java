package ankang.mybatis.learn.session;

import ankang.mybatis.learn.config.Configuration;

/**
 * @author: ankang
 * @email: dreedisgood@qq.com
 * @create: 2020-07-29
 */
public class DefaultSqlSessionFactory implements SqlSessionFactory {

    private final Configuration configuration;

    public DefaultSqlSessionFactory(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public SqlSession openSession() {
        return new DefaultSqlSession(configuration);
    }
}
