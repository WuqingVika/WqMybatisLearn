《小吴同学的Mybatis学习之路》

- 1.Mybatis_01_HelloWorld 
  - 接口式编程
  

- 2.Mybatis_02_config (标签顺序很重要)
  - 2.1mybatis可以使用properties来引入外部properties配置文件的内容
     - resource：引入类路径下的资源
     - url：引入网络路径或者磁盘路径下的资源
     		如果 在某个包下 则com/wq/dbconfig.properties
  - 2.2 settings包含很多重要的设置项
     - setting:用来设置每一个设置项
     - name：设置项名
     - value：设置项取值
  - 2.3 typeAlias为某个java类型起别名
     - type:指定要起别名的类型全类名;默认别名就是类名小写；employee
     - alias:指定新的别名 
     - package:为某个包下的所有类批量起别名 name：指定包名（为当前包以及下面所有的后代包的每一个类都起一个默认别名（类名小写））
     - 批量起别名的情况下，如果有重复类，则使用@Alias注解为某个类型指定新的别名
  - 2.4environments：环境们，mybatis可以配置多种环境 ,default指定使用某种环境。可以达到快速切换环境。
  - 2.5databaseIdProvider：支持多数据库厂商；
  - 2.6sql映射文件注册到全局配置文件mybatis-config.xml
     - 注册配置文件
        - resource：引用类路径下的sql映射文件 mybatis/mapper/EmployeeMapper.xml
        - url：引用网路路径或者磁盘路径下的sql映射文件 file:///var/mappers/AuthorMapper.xml
     - 注册接口 (一般将mapper文件和dao放在相同的包名下，比如一个在src的包名com.wq.dao,另一个在conf下的包名com.wq.dao中)
       - class
            - 1.有sql映射文件，映射文件名必须和接口同名，并且放在与接口同一目录下；
            - 2.没有sql映射文件，所有的sql都是利用注解写在接口上;
       - 推荐
           	- 1.比较重要的，复杂的Dao接口我们来写sql映射文件
           	- 2.不重要，简单的Dao接口为了开发快速可以使用注解；
  
- 3.Mybatis_03_mapper
  - 3.1 增删改操作，特别是添加时获取插入的主键值
     - mysql支持自增主键，自增主键值的获取，mybatis也是利用statement.getGenreatedKeys()；
       	   useGeneratedKeys="true"；使用自增主键获取主键值策略
       	   keyProperty；指定对应的主键属性，也就是mybatis获取到主键值以后，将这个值封装给javaBean的哪个属性
     - oracle获取非自增主键的值:Oracle不支持自增；Oracle使用序列来模拟自增;每次插入的数据的主键是从序列中拿到的值；
       	 keyProperty:查出的主键值封装给javaBean的哪个属性
         - Order: "BEFORE":当前sql在插入sql之前运行
                   AFTER：当前sql在插入sql之后运行
           - BEFORE运行顺序：
             先运行selectKey查询id的sql；查出id值封装给javaBean的id属性
             在运行插入的sql；就可以取出id属性对应的值
            - AFTER运行顺序：
             先运行插入的sql（从序列中取出新值作为id）；
             再运行selectKey查询id的sql；
         - resultType:查出的数据的返回值类型
  - 3.2 mybatis参数处理(详见项目路径下mybatis参数处理.txt)
    - 1.单个参数 ====》#{id}
    - 2.多个参数 ====》#{param1},#{param2}
    - 2.pojo Employee ====》#{e.lastName}  【推荐】
    - 3.Map ====>直接#{id},#{lastName} 
    - 4.List====>#{list[0]}
    - ...其余略
    
  - 3.3 ‘#’与$的区别
    - ‘#{}’:是以预编译的形式，将参数设置到sql语句中；PreparedStatement；防止sql注入
	- ‘${}’:取出的值直接拼装在sql语句中；会有安全问题；
		大多情况下，我们去参数的值都应该去使用#{}；                    
                     
  - 3.4 jdbcType=NULL Oracle的相关配置
    - 在我们数据为null的时候，有些数据库可能不能识别mybatis对null的默认处理。比如Oracle（报错）；
    	- JdbcType OTHER：无效的类型；因为mybatis对所有的null都映射的是原生Jdbc的OTHER类型，oracle不能正确处理;
    		- 由于全局配置中：jdbcTypeForNull=OTHER；oracle不支持；两种办法
    		  - 1、#{email,jdbcType=OTHER};
    		  - 2、jdbcTypeForNull=NULL  
    		    ```xml
    		    <setting name="jdbcTypeForNull" value="NULL"/>
    		    ```
  - 3.5 resultType练习
     - 返回list时，resultType设为定义集合中返回元素的类型;
     - 返回Map(key是数据库的字段，value是对应的值)时，resultType设为java.util.Map【别名map】,map是mybatis能识别的别名;
     - 返回Map(指定key,key为JavaBean中的属性名)时，resultType设为JavaBean名称(如:employee);
     - 返回Map集合时，resultType设为定义集合中Map元素的类型;
  - 3.6 resultMap练习【详见EmployeeMapperPlus】
      - 它与resultType只能二选一;
      - 级联属性封装结果集;
      - 使用association定义关联的单个对象的封装规则；
         - property="dept"：指定哪个属性是联合的对象
         - javaType:指定这个属性对象的类型【不能省略】
      - 使用association进行分步查询：
         - select:表明当前属性是调用select指定的方法查出的结果
         - column:指定将哪一列的值传给这个方法
         - 流程：使用select指定的方法（传入column指定的这列参数的值）查出对象，并封装给property指定的属性   
      - 使用延迟加载（懒加载）(按需加载),在mybatis-config.xml中开启懒加载
           - mybatis-config中配置如下：
                ```xml
                <!--懒加载-->
                <setting name="lazyLoadingEnabled" value="true"/>
                <!--默认是true 会加载全部属性 如果设为False，只加载想要的属性-->
                <setting name="aggressiveLazyLoading" value="false"/>
              ```

  - 3.7 关联集合collection
     - 不分步：
        - property：集合名字（如：员工列表属性名称emps，必须和部门Javabean中的员工列表属性名一致）
        - ofType：集合中元素的类型（如：com.wq.bean.Employee）
     - 分步：
        - property：集合名字（如：员工列表属性名称emps，必须和部门Javabean中的员工列表属性名一致）
        - select：关联的sql查询，（如：com.wq.dao.EmployeeMapperPlus.getEmpsByDeptId） 
        - column：关联的sql里面需要的入参，比如是部门的id("id")
           - 如果多个参数时，column="{key1=column1,key2=column2}"
        - fetchType （即使全局已经配置了懒加载，也可以单独设为eager）
           - lazy：延迟
           - eager：立即
           
- 4.Mybatis_04_DynamicSql动态Sql
  - if
  - choose(when,otherwise)
  - trim(where,set)
  - foreach
  - bind
  - include 引入一个通用的sql
     - ```<include refid="selectSql">```

- 5.Mybatis_05_cache
  - 5.1 一级缓存（SqlSession级别的一个Map）
     - sqlSession级别的缓存。一级缓存是一直开启的；
     - 与数据库同一次会话期间查询到的数据会放在本地缓存中。
     - 失效情况：
        - （1）sqlSession不同
        - （2）sqlSession相同，查询条件不同.(当前一级缓存中还没有这个数据)
        - （3）sqlSession相同，两次查询之间执行了增删改操作(这次增删改可能对当前数据有影响)
        - （4）sqlSession相同，手动清除了一级缓存（缓存清空）
  - 5.2 二级缓存（全局缓存，基于namespace级别）
     - 工作机制：
        - （1）一个会话，查询一条数据，这个数据就会被放在当前会话的一级缓存中；
        - （2）如果会话关闭；一级缓存中的数据会被保存到二级缓存中；新的会话查询信息，就可以参照二级缓存中的内容；
     - 使用：
        - （1）开启全局二级缓存配置：<setting name="cacheEnabled" value="true"/>
        - （2）去mapper.xml中配置使用二级缓存：
            *     <cache></cache>
        - （3）我们的POJO需要实现序列化接口
  - 5.3 和缓存有关的设置/属性
     - 1）、cacheEnabled=true：false：关闭缓存（二级缓存关闭）(一级缓存一直可用的)
     - 2）、这个始终在前面配置使用二级缓存三大条件不可缺一的情况下测试，每个select标签都有useCache="true";false：不使用缓存（一级缓存依然使用，二级缓存不使用）
     - 3）、【每个增删改标签的：flushCache="true"：（一级二级都会清除）】
         - 增删改执行完成后就会清除缓存；
         - 测试：flushCache="true"：一级缓存就清空了；二级也会被清除；
         - 查询标签：flushCache="false"；如果flushCache=true;每次查询之后都会清空缓存；缓存是没有被使用的；
     - 4）、sqlSession.clearCache();只是清楚当前session的一级缓存；
     - 5）、localCacheScope：本地缓存作用域：（一级缓存SESSION）；当前会话的所有数据保存在会话缓存中；STATEMENT：可以禁用一级缓存；          