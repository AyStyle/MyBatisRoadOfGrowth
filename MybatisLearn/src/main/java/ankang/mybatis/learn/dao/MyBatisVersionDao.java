package ankang.mybatis.learn.dao;

import ankang.mybatis.learn.pojo.MyBatisVersion;

import java.util.List;

/**
 * MyBatis xml配置开发
 *
 * @author: ankang
 * @email: dreedisgood@qq.com
 * @create: 2020-07-31
 */
public interface MyBatisVersionDao {

    /*
     * DAO层的开发方式
     * 1.传统开发方式（不推荐）：
     *      1.写一个DAO接口
     *      2.写一个DAO接口的实现类
     *
     * 2.代理开发方式（推荐）：
     *   使用session.getMapper(Mapper.class)方法获取代理对象，代理对象需要满足下面规范
     *      1.Mapper.xml文件中的namespace属性和Mapper接口的全限定名相同
     *      2.Mapper接口方法名和Mapper.xml中定义的每个statement的id相同
     *      3.Mapper接口的方法参数和Mapper.xml中定义的每个sql的parameterType类型相同
     *      4.Mapper接口的返回结果和Mapper.xml中定义的每个sql的resultType类型相同
     */

    /**
     * 查询全部
     *
     * @return
     */
    List<MyBatisVersion> findAll();

    /**
     * 根据条件查询
     *
     * @param mbv
     * @return
     */
    MyBatisVersion findBy(MyBatisVersion mbv);

    /**
     * 插入一个
     *
     * @param mbv
     */
    void save(MyBatisVersion mbv);

    /**
     * 插入全部
     *
     * @param all
     */
    void saveAll(List<MyBatisVersion> all);

    /**
     * 一对一查询
     *
     * @param mbv
     * @return
     */
    MyBatisVersion findByVersionWithDownload(MyBatisVersion mbv);

    /**
     * 一对多查询
     *
     * @param mbv
     * @return
     */
    MyBatisVersion findByVersionWithDownloads(MyBatisVersion mbv);
}
