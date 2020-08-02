package ankang.mybatis.learn.pojo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

@Getter
@Setter
@ToString
public class MyBatisVersion implements Serializable {
    private Integer id;
    private String version;
    private Timestamp versionTime;
    private String desc;

    // 一对一查询
    private MyBatisDownload download;

    // 一对多查询
    private List<MyBatisDownload> downloadList;
}

