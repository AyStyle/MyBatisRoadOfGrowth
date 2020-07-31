package ankang.mybatis.learn.session;

import ankang.mybatis.learn.config.Configuration;

import java.sql.SQLException;
import java.util.List;

/**
 * @author: ankang
 * @email: dreedisgood@qq.com
 * @create: 2020-07-29
 */
public interface Executor {

    <T> List<T> query(Configuration configuration , String statementId , Object... params) throws SQLException, ClassNotFoundException, NoSuchFieldException, IllegalAccessException, InstantiationException;

}
