package ankang.mybatis.learn.pojo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author: ankang
 * @email: dreedisgood@qq.com
 * @create: 2020-08-01
 */
@Getter
@Setter
@ToString
@Table(name = "db_mybatis.tb_mybatis_download")
public class MyBatisDownload implements Serializable {

    @Id // 指定主键
    @Column(name = "version_id")
    @GeneratedValue(strategy = GenerationType.AUTO,generator = "JDBC") // 设置主键生成策略
    private Integer versionId;

    @Column(name = "download_count")
    private Integer downloadCount;

}
