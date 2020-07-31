package ankang.mybatis.learn.dao;

import ankang.mybatis.learn.pojo.MybatisVersion;

import java.util.List;

/**
 * @author: ankang
 * @email: dreedisgood@qq.com
 * @create: 2020-07-30
 */
public interface MybatisVersionDao {

    /**
     * 查询所有
     * @return
     */
    List<MybatisVersion> findAll() throws Exception;

    /**
     * 根据条件查询
     * @param condition
     * @return
     */
    MybatisVersion findOne(MybatisVersion condition) throws Exception;

}
