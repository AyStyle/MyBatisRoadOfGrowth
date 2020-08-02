import ankang.mybatis.learn.dao.MyBatisDownloadDao;
import ankang.mybatis.learn.pojo.MyBatisDownload;
import ankang.mybatis.learn.pojo.MyBatisVersion;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import tk.mybatis.mapper.entity.Example;

import java.io.InputStream;
import java.util.List;

/**
 * @author: ankang
 * @email: dreedisgood@qq.com
 * @create: 2020-08-01
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class MyBatisDownloadTest {

    private SqlSession session;

    @BeforeAll
    private void init() {
        final InputStream resourceAsStream = MyBatisDownloadTest.class.getClassLoader().getResourceAsStream("mybatis.xml");
        final SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        session = factory.openSession(true);
    }

    @AfterAll
    private void del() {
        session.close();
    }

    @Test
    public void insert() {
        final MyBatisDownloadDao mapper = session.getMapper(MyBatisDownloadDao.class);
        final MyBatisDownload mbd = new MyBatisDownload();
        mbd.setVersionId(21);
        mbd.setDownloadCount(436723);

        mapper.insert(mbd);
    }

    @Test
    public void update() {
        final MyBatisDownloadDao mapper = session.getMapper(MyBatisDownloadDao.class);
        final MyBatisDownload mbd = new MyBatisDownload();
        mbd.setVersionId(21);
        mbd.setDownloadCount(436723896);

        mapper.update(mbd);
    }

    @Test
    public void select() {
        final MyBatisDownloadDao mapper = session.getMapper(MyBatisDownloadDao.class);

        final MyBatisDownload by = mapper.findBy(21);
        System.out.println(by);
    }

    @Test
    public void delete() {
        final MyBatisDownloadDao mapper = session.getMapper(MyBatisDownloadDao.class);
//        mapper.delete(21);
    }

    @Test
    public void findByVersionWithDownload(){
        final MyBatisDownloadDao mapper = session.getMapper(MyBatisDownloadDao.class);

        final MyBatisVersion mbv = mapper.findByVersionWithDownload(3);
        System.out.println(mbv);
    }

    @Test
    public void findByVersionWithDownloads(){
        final MyBatisDownloadDao mapper = session.getMapper(MyBatisDownloadDao.class);

        final MyBatisVersion mbv = mapper.findByVersionWithDownloads(1);
        System.out.println(mbv);

        mbv.getDownloadList().forEach(System.out::println);
    }

    /**
     * 通用Mapper插件demo，Mapper版本
     */
    @Test
    public void mapperHelperTest(){
        final MyBatisDownloadDao mapper = session.getMapper(MyBatisDownloadDao.class);

        final MyBatisDownload mbv = mapper.selectByPrimaryKey(1);
        System.out.println(mbv);
    }

    /**
     * 通用Mapper插件demo，Example版本
     */
    @Test
    public void mapperHelperExampleTest(){
        final MyBatisDownloadDao mapper = session.getMapper(MyBatisDownloadDao.class);

        final Example example = new Example(MyBatisDownload.class);
        example.createCriteria().andEqualTo("versionId",1);

        final List<MyBatisDownload> all = mapper.selectByExample(example);
        all.forEach(System.out::println);
    }

}
