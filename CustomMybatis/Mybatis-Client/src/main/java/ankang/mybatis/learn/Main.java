package ankang.mybatis.learn;

import ankang.mybatis.learn.dao.MybatisVersionDao;
import ankang.mybatis.learn.pojo.MybatisVersion;
import ankang.mybatis.learn.session.SqlSession;
import ankang.mybatis.learn.session.SqlSessionFactory;
import ankang.mybatis.learn.session.SqlSessionFactoryBuilder;
import ankang.mybatis.learn.utils.ResourcesUtils;

import java.io.InputStream;
import java.util.List;

/**
 * @author: ankang
 * @email: dreedisgood@qq.com
 * @create: 2020-07-28
 */
public class Main {

    public static void main(String[] args) throws Exception {
        final InputStream resourceAsStream = ResourcesUtils.getResourceAsStream("mybatis.xml");
        final SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        final SqlSession sqlSession = factory.openSession();

        final MybatisVersion mybatisVersion = new MybatisVersion();
        mybatisVersion.setId(3L);

        final MybatisVersionDao mapper = sqlSession.getMapper(MybatisVersionDao.class);
        final MybatisVersion one = mapper.findOne(mybatisVersion);
        System.out.println(one);

        final List<MybatisVersion> all = mapper.findAll();
        all.forEach(System.out::println);

    }

}
