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
                    
                     

           	