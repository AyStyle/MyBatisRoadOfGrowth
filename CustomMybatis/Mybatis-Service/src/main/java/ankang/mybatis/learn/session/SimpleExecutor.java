package ankang.mybatis.learn.session;

import ankang.mybatis.learn.config.Configuration;
import ankang.mybatis.learn.config.MappedStatement;
import ankang.mybatis.learn.utils.ClassUtils;
import ankang.mybatis.learn.utils.StringUtils;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: ankang
 * @email: dreedisgood@qq.com
 * @create: 2020-07-29
 */
public class SimpleExecutor implements Executor {
    @Override
    public <T> List<T> query(Configuration configuration , String statementId , Object... params) throws SQLException, ClassNotFoundException, NoSuchFieldException, IllegalAccessException, InstantiationException {
        // 1.获取数据库连接
        final Connection connection = configuration.getDataSource().getConnection();

        /*
         * 2.获取sql语句，转换sql语句
         * mapper中的：SELECT * FROM table WHERE id = #{id}
         * statement中的：SELECT * FROM table WHERE id = ?
         * */
        final MappedStatement mappedStatement = configuration.getStatementMap().get(statementId);
        final String sql = mappedStatement.getSql();
        final BoundSql boundSql = getBoundSql(sql);

        // 3.获取预处理对象
        final PreparedStatement preparedStatement = connection.prepareStatement(boundSql.getPrepareSql());

        // 4.设置参数
        final String paramterType = mappedStatement.getParamterType();
        Class<?> paramterCls = ClassUtils.getClass(paramterType);

        final List<BoundSql.Token> tokenList = boundSql.getTokenList();
        for (int i = 0 ; i < tokenList.size() ; i++) {
            final BoundSql.Token token = tokenList.get(i);
            final String paramter = token.getParamter();
            final String tokenStr = token.getTokenStr();

            final Field field = paramterCls.getDeclaredField(paramter);
            field.setAccessible(true);

            // 获取参数value
            final Object o = field.get(params[0]);

            preparedStatement.setObject(i + 1 , o);
        }


        // 5.执行sql
        final ResultSet resultSet = preparedStatement.executeQuery();

        // 6.封装结果集
        final List<T> result = new ArrayList<>();

        // 结果集类对象
        final String resultType = mappedStatement.getResultType();
        final Class<?> resultClass = ClassUtils.getClass(resultType);

        // 元数据
        final ResultSetMetaData metaData = resultSet.getMetaData();
        while (resultSet.next()) {
            final Object instance = resultClass.newInstance();
            for (int i = 1 ; i <= metaData.getColumnCount() ; i++) {
                // 字段名
                final String columnName = metaData.getColumnName(i);
                // 字段值
                final Object value = resultSet.getObject(columnName);

                // 使用反射实现resultSet和结果集对象的映射关系
                final Field field = resultClass.getDeclaredField(columnName);
                field.setAccessible(true);
                field.set(instance , value);
            }

            result.add((T) instance);
        }

        return result;
    }

    /**
     * 完成对#{}的解析工作：
     * 1.将#{}使用?代替
     * 2.解析出#{}中的值，并进行存储
     *
     * @param sql
     * @return
     */
    private BoundSql getBoundSql(String sql) {
        final String openToken = "#{";
        final String closeToken = "}";
        final List<String> tokenStrList = StringUtils.getTokenList(sql , openToken , closeToken);

        final ArrayList<BoundSql.Token> tokenList = new ArrayList<>();
        for (String tokenStr : tokenStrList) {
            final BoundSql.Token token = new BoundSql.Token(tokenStr.replace(openToken , "").replace(closeToken , "") , tokenStr);
            tokenList.add(token);
        }

        return new BoundSql(sql , tokenList);
    }

}
