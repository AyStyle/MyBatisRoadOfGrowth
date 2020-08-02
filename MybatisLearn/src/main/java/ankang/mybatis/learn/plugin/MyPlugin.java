package ankang.mybatis.learn.plugin;

import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.plugin.*;

import java.sql.Connection;
import java.util.Properties;

/**
 * @author: ankang
 * @email: dreedisgood@qq.com
 * @create: 2020-08-02
 */
// Intercepts配置拦截目标Signature
@Intercepts({
        // 配置具体的拦截目标
        @Signature(
                // 配置拦截目标对象类型
                type = StatementHandler.class,
                // 配置拦截目标对象方法
                method = "prepare",
                // 配置拦截目标对象方法的参数，避免重载
                args = {Connection.class , Integer.class}
        )
})
public class MyPlugin implements Interceptor {

    private Object a;

    /**
     * 拦截方法：只要被拦截对象的目标方法执行时，每次都会执行intercept方法
     *
     * @param invocation 被拦截目标像的容器，容器中有：target、method、args
     * @return 返回代理对象执行完毕的结果
     * @throws Throwable
     */
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        System.out.println("MyPlugin Intercept method ...");
        return invocation.proceed();
    }

    /**
     * 主要为当前拦截对象生成代理，并存到拦截器链中
     *
     * @param target 拦截的目标对象
     * @return
     */
    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target , this);
    }

    /**
     * 获取配置文件的参数
     *
     * @param properties
     */
    @Override
    public void setProperties(Properties properties) {
        properties.put("age", 18);
        properties.forEach((k , v) -> System.out.println("key: " + k + ", v: " + v));
    }
}
