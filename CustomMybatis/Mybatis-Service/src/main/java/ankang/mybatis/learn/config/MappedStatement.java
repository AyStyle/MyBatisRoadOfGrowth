package ankang.mybatis.learn.config;

import lombok.Getter;
import lombok.Setter;

/**
 * @author: ankang
 * @email: dreedisgood@qq.com
 * @create: 2020-07-28
 */
@Getter
@Setter
public class MappedStatement {

    /**
     * id标识
     */
    private String id;

    /**
     * 返回值类型
     */
    private String resultType;

    /**
     * 参数值类型
     */
    private String paramterType;

    /**
     * sql语句
     */
    private String sql;

    public MappedStatement(String id , String resultType , String paramterType , String sql) {
        this.id = id;
        this.resultType = resultType;
        this.paramterType = paramterType;
        this.sql = sql;
    }
}
