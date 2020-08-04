import ankang.mybatis.learn.dao.MyBatisDownloadDao;
import ankang.mybatis.learn.dao.MyBatisVersionDao;
import ankang.mybatis.learn.pojo.MyBatisVersion;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.io.InputStream;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: ankang
 * @email: dreedisgood@qq.com
 * @create: 2020-07-31
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class MyBatisVersionTest {

    private SqlSession session;

    @BeforeAll
    private void init() {
        // 1.加载配置文件
        final InputStream resourceAsStream = MyBatisVersionTest.class.getClassLoader().getResourceAsStream("mybatis.xml");
        // 2.解析了配置文件，并创建SqlSessionFactory工厂
        final SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        // 3.SqlSessionFactory创建SqlSession会话。openSession接受一个参数autoCommit，用于控制是否自动提交事务
        session = factory.openSession(true);
    }

    @AfterAll
    private void del() {
        // 运行完成后销毁
        session.close();
    }

    @Test
    public void testSelectList() {
        /*
         * 使用SqlSession与数据库交互：
         *   1.selectList查询多个
         *   2.selectOne查询一个
         *   3.insert插入
         *   4.update更新
         *   5.delete删除
         * */
        final List<MyBatisVersion> list = session.selectList("ankang.mybatis.learn.dao.MyBatisVersionDao.findAll");
        list.forEach(System.out::println);
    }

    @Test
    public void testFindBy() {
        final MyBatisVersionDao mapper = session.getMapper(MyBatisVersionDao.class);
        final MyBatisVersion mbv = new MyBatisVersion();
        mbv.setVersionTime(new Timestamp(1596251719000L));

        final MyBatisVersion by = mapper.findBy(mbv);
        System.out.println(by);
    }

    @Test
    public void testInsert() {
        final MyBatisVersion mbv = new MyBatisVersion();
        mbv.setVersion("3.9.9");
        System.out.println(mbv);

        session.insert("ankang.mybatis.learn.dao.MyBatisVersionDao.save" , mbv);
        System.out.println(mbv);
    }

    @Test
    public void testSaveAll() {
        final MyBatisVersionDao mapper = session.getMapper(MyBatisVersionDao.class);
        final List<MyBatisVersion> all = new ArrayList<>();

        final MyBatisVersion mbv1 = new MyBatisVersion();
        final MyBatisVersion mbv2 = new MyBatisVersion();
        final MyBatisVersion mbv3 = new MyBatisVersion();

        mbv1.setVersion("3.9.4");
        mbv2.setVersion("3.9.5");
        mbv3.setVersion("3.9.6");

        all.add(mbv1);
        all.add(mbv2);
        all.add(mbv3);

        mapper.saveAll(all);

        all.forEach(System.out::println);
    }

    @Test
    public void testUpdate() {
        final MyBatisVersion mbv = new MyBatisVersion();
        mbv.setId(7);
        mbv.setVersion("3.8.8");
        mbv.setDesc("修改版本号：3.8. -> 3.8.8");
        System.out.println(mbv);

        session.update("ankang.mybatis.learn.dao.MyBatisVersionDao.update" , mbv);
        System.out.println(mbv);
    }

    @Test
    public void testDelete() {
        final MyBatisVersion mbv = new MyBatisVersion();
        mbv.setId(2);
        System.out.println(mbv);

        session.delete("ankang.mybatis.learn.dao.MyBatisVersionDao.delete" , mbv);
    }

    @Test
    public void testFindByVersionWithDownload() {
        final MyBatisVersionDao mapper = session.getMapper(MyBatisVersionDao.class);
        final MyBatisVersion mbv = new MyBatisVersion();
        mbv.setId(3);

        final MyBatisVersion result = mapper.findByVersionWithDownload(mbv);
        System.out.println(result);
    }

    @Test
    public void testFindByVersionWithDownloads() {
        final MyBatisVersionDao mapper = session.getMapper(MyBatisVersionDao.class);
        final MyBatisVersion mbv = new MyBatisVersion();
        mbv.setId(3);

        final MyBatisVersion result = mapper.findByVersionWithDownloads(mbv);
        System.out.println(result);
        result.getDownloadList().forEach(System.out::println);
    }

    /**
     * 分页插件测试demo
     */
    @Test
    public void pageHelperTest() {
        final MyBatisVersionDao mapper = session.getMapper(MyBatisVersionDao.class);

        int pageNum = 1;
        PageInfo<MyBatisVersion> pageInfo ;
        do {
            PageHelper.startPage(pageNum , 1);
            final List<MyBatisVersion> all = mapper.findAll();
            pageInfo = new PageInfo<>(all);

            System.out.println("总条数：" + pageInfo.getTotal());
            System.out.println("总页数：" + pageInfo.getPages());
            System.out.println("当前页：" + pageInfo.getPageNum());
            System.out.println("每页显示条数：" + pageInfo.getPageSize());
            all.forEach(System.out::println);

            pageNum = pageInfo.getNextPage();
        } while (pageInfo.isHasNextPage());
    }
}
