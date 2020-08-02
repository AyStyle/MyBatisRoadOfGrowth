package ankang.mybatis.learn;

import ankang.mybatis.learn.dao.MybatisVersionDao;
import ankang.mybatis.learn.pojo.MybatisVersion;
import ankang.mybatis.learn.session.SqlSession;
import ankang.mybatis.learn.session.SqlSessionFactory;
import ankang.mybatis.learn.session.SqlSessionFactoryBuilder;
import ankang.mybatis.learn.utils.ResourcesUtils;
import org.dom4j.DocumentException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.util.List;

/**
 * @author: ankang
 * @email: dreedisgood@qq.com
 * @create: 2020-07-31
 */

public class CustomMybatisTest {

    private static SqlSession session;

    @BeforeAll
    private static void init() throws DocumentException {
        final InputStream resourceAsStream = ResourcesUtils.getResourceAsStream("mybatis.xml");
        final SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        session = factory.openSession();
    }

    @Test
    public void testSelectOne() throws Exception {
        final MybatisVersion mybatisVersion = new MybatisVersion();
        mybatisVersion.setId(3L);

        final MybatisVersion one = session.selectOne("ankang.mybatis.learn.dao.MybatisVersionDao.findOne" , mybatisVersion);
        System.out.println(one);
    }

    @Test
    public void testSelectList() throws Exception {
        final List<MybatisVersion> list = session.selectList("ankang.mybatis.learn.dao.MybatisVersionDao.findAll");
        list.forEach(System.out::println);
    }

    @Test
    public void testFindOne() throws Exception {
        final MybatisVersion mybatisVersion = new MybatisVersion();
        mybatisVersion.setId(3L);

        final MybatisVersionDao mapper = session.getMapper(MybatisVersionDao.class);
        final MybatisVersion one = mapper.findOne(mybatisVersion);

        System.out.println(one);
    }

    @Test
    public void testFindAll() throws Exception {
        final MybatisVersionDao mapper = session.getMapper(MybatisVersionDao.class);

        final List<MybatisVersion> all = mapper.findAll();
        all.forEach(System.out::println);
    }


}
