package ankang.mybatis.learn.pojo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Timestamp;

/**
 * @author: ankang
 * @email: dreedisgood@qq.com
 * @create: 2020-07-28
 */
@Getter
@Setter
@ToString
public class MybatisVersion {

    private Long id;
    private String version;
    private Timestamp version_time;
    private String desc;

}
