package ankang.mybatis.learn.session;

import java.sql.SQLException;
import java.util.List;

/**
 * @author: ankang
 * @email: dreedisgood@qq.com
 * @create: 2020-07-29
 */
public interface SqlSession {

    /**
     * 查询所有
     */
    <T> List<T> selectList(String statementId , Object... params) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException, NoSuchFieldException;

    /**
     * 查询所有
     *
     * @param <T>
     * @return
     */
    <T> T selectOne(String statementId , Object... params) throws ClassNotFoundException, SQLException, NoSuchFieldException, InstantiationException, IllegalAccessException;

    /**
     * 为DAO接口生成动态代理
     *
     * @param cls
     * @param <T>
     * @return
     */
    <T> T getMapper(Class<?> cls);
}
