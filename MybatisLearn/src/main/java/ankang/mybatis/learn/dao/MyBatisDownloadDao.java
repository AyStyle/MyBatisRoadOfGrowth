package ankang.mybatis.learn.dao;

import ankang.mybatis.learn.pojo.MyBatisDownload;
import ankang.mybatis.learn.pojo.MyBatisVersion;
import org.apache.ibatis.annotations.*;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * Mybatis 注解开发
 *
 * @author: ankang
 * @email: dreedisgood@qq.com
 * @create: 2020-08-01
 */
//@CacheNamespace
//@CacheNamespaceRef
public interface MyBatisDownloadDao extends Mapper<MyBatisDownload> {

    /*
     * MyBatis注解开发：
     *      1.@Insert - 增加
     *      2.@Update - 更新
     *      3.@Delete - 删除
     *      4.@Select - 查询
     *      5.@Result - 封装结果集
     *      6.@Results - 配合@Result封装多个结果集
     *      7.@One - 一对一查询结果集
     *      8.@Many - 实现一对多查询结果集
     *      9.@Param - 给方法参数设置名字
     *      10.@CacheNamespace - 开启二级缓存
     *      11.@CacheNamespaceRef - 引用其他Mapper二级缓存
     * */

    // 因为通用Mapper已经实现了下面的方法，所以下面的方法被注了
//    /**
//     * 添加
//     *
//     * @param mbd
//     */
//    @Insert("INSERT INTO db_mybatis.tb_mybatis_download VALUES(#{versionId},#{downloadCount})")
//    void insert(MyBatisDownload mbd);

    /**
     * 更新
     *
     * @param mbd
     */
    @Update("UPDATE db_mybatis.tb_mybatis_download SET download_count = #{mbd.downloadCount} WHERE version_id = #{mbd.versionId}")
    void update(@Param("mbd") MyBatisDownload mbd);

    /**
     * 查询
     *
     * @param id
     * @return
     */
    @Select("SELECT * FROM db_mybatis.tb_mybatis_download WHERE version_id = #{id}")
    MyBatisDownload findBy(@Param("id") Integer id);

//    /**
//     * 删除
//     *
//     * @param id
//     */
//    @Delete("DELETE FROM db_mybatis.tb_mybatis_download WHERE version_id = #{id}")
//    void delete(@Param("id") Integer id);

    /**
     * 一对一查询
     *
     * @param id
     * @return
     */
    @Results({
            @Result(property = "id", column = "id") ,
            @Result(property = "version", column = "version") ,
            @Result(property = "versionTime", column = "version_time") ,
            @Result(property = "desc", column = "desc") ,
            @Result(property = "download", column = "id", javaType = MyBatisDownload.class, one = @One(select = "ankang.mybatis.learn.dao.MyBatisDownloadDao.findBy"))
    })
    @Select("SELECT * FROM db_mybatis.tb_mybatis_version WHERE id = #{id}")
    MyBatisVersion findByVersionWithDownload(@Param("id") Integer id);

    /**
     * 一对多查询
     *
     * @param id
     * @return
     */
    @Results({
            @Result(property = "id", column = "id") ,
            @Result(property = "version", column = "version") ,
            @Result(property = "versionTime", column = "version_time") ,
            @Result(property = "desc", column = "desc") ,
            @Result(property = "downloadList", column = "id", javaType = List.class, many = @Many(select = "ankang.mybatis.learn.dao.MyBatisDownloadDao.findBy"))
    })
    @Select("SELECT * FROM db_mybatis.tb_mybatis_version WHERE id = #{id}")
    MyBatisVersion findByVersionWithDownloads(@Param("id") Integer id);
}
