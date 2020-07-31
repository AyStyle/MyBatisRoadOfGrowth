package ankang.mybatis.learn.session;

import ankang.mybatis.learn.config.Configuration;

import java.lang.reflect.*;
import java.sql.SQLException;
import java.util.List;

/**
 * @author: ankang
 * @email: dreedisgood@qq.com
 * @create: 2020-07-29
 */
public class DefaultSqlSession implements SqlSession {

    private final Configuration configuration;
    private final Executor executor;

    public DefaultSqlSession(Configuration configuration) {
        this.configuration = configuration;
        this.executor = new SimpleExecutor();
    }


    /**
     * 查询所有
     *
     * @param statementId
     * @param params
     */
    @Override
    public <T> List<T> selectList(String statementId , Object... params) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException, NoSuchFieldException {
        return executor.query(configuration , statementId , params);
    }

    /**
     * 查询所有
     *
     * @param statementId
     * @param params
     * @return
     */
    @Override
    public <T> T selectOne(String statementId , Object... params) throws ClassNotFoundException, SQLException, NoSuchFieldException, InstantiationException, IllegalAccessException {
        final List<T> list = selectList(statementId , params);

        if (list.size() == 1) {
            return list.get(0);
        } else {
            throw new RuntimeException("查询结果为空或返回结果过多");
        }
    }

    /**
     * 为DAO接口生成动态代理
     *
     * @param cls
     * @return
     */
    @Override
    public <T> T getMapper(Class<?> cls) {
        return (T) Proxy.newProxyInstance(this.getClass().getClassLoader() , new Class[] {cls} , (Object proxy , Method method , Object[] args) -> {
            final Class<?> declareCls = method.getDeclaringClass();
            final String statementId = declareCls.getCanonicalName() + "." + method.getName();

            // 获取泛型返回结果
            final Type genericReturnType = method.getGenericReturnType();

            // 判断参数是否泛型化，如果有泛型，则：调用selectList，否则：调用selectOne
            if (genericReturnType instanceof ParameterizedType){
                return selectList(statementId,args);
            }else{
                return selectOne(statementId,args);
            }
        });

    }
}
