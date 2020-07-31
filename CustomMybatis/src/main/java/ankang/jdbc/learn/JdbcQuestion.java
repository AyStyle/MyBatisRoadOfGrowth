package ankang.jdbc.learn;

/**
 * @author: ankang
 * @email: dreedisgood@qq.com
 * @create: 2020-07-28
 */
public class JdbcQuestion {

    /*
     * JDBC问题分析：
     *      1.数据库配置信息存在硬编码问题
     *      2.频繁创建释放数据库连接
     *      3.sql语句、设置参数、获取结果集参数均存在硬编码问题
     *      4.手动封装返回结果集，较为繁琐
     * 问题解决：
     *      1.硬编码问题 -> 配置文件
     *      2.频繁创建释放数据库连接 -> 连接池
     *      3.sql硬编码问题 -> 配置文件
     *      4.手动封装结果集 -> 反射、内省
     * */

}
