# CustomMybatis 自定mybatis

1. Mybatis-Service - 自定义的mybatis框架，对JDBC代码进行封装

   1. 加载配置文件：根据配置文件的路径，加载配置文件，然后存储在内存中
   
        `Resources：加载资源文件`
   
   2. 创建JavaBean（容器对象）：存放的就是配置文件的解析内容
   
        `Configuration：核心配置类，存放mybatis.xml解析出来的内容`
        
        `MappedStatement：映射配置类，存放mapper.xml解析出来的内容`
   
   3. 解析配置文件：dom4j
   
      1. 使用dom4j解析配置文件，将解析出来的内容封装到容器中
      
      2. 创建SqlSessionFactory对象，用来生产SqlSession会话对象

   4. 创建SqlSessionFactory接口及实现类DefaultSqlSessionFactory，生产SqlSession

   5. 创建SqlSession接口及实现类DefaultSession，定义对数据库的crud操作

   6. 创建Executor接口及实现类SimpleExecutor实现类，实现执行sql的功能

2. Mybatis-Client - 引入自定义mybatis，并提供配置文件

   1.mybatis.xml - 提供数据库配置信息，存放mapper.xml的全路径
   
   2.mapper.xml - 提供sql配置信息        