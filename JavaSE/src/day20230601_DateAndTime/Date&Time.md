# 1、关于`Date`类

## 关于`Java`元年与时间戳

- 在Java语言中，使用`1970-01-01 0:00:00 GMT`作为元年
  - `GMT : Greenwich Mean Time`，格林尼治标准时间，也称之为：世界时
  - 如果以北京时间为基准，则是`1970-01-01 08:00:00`
- 从`Java`元年至今历时的毫秒数，就是`Java`时间戳
- 在`Java`语言中，所有获取当前时间的`API`都是通过`Java`时间戳实现的
  - 有多种处理日期、时间的`API`
- 无论当前系统设置的哪个时区，只要是同一时间，获取得到的`Java`时间戳的值都死相同的

```java
long time = System.currentTimeMillis();
```

## 关于`Date`类

- `Date`类是简单的处理日期、时间相关数据的类
  - 可以创建当前时间的`Date`对象
  - 可以指定毫秒时间值创建`Date`对象
  - 可以获取`Date`对象的时间戳
  - 可以重新指定对象的毫秒时间值
  - 可以获取日期、时间中的详情值
    - 例如年、月、日、小时、分钟、秒钟、星期等
    - 这些方法被声明为已过期
  - 可以对比`2`个`Date`对象的时间先后
- 注意：在`JDK`中，有两个`Date`类，分别是`java.util.Date`和`java.sql.Date`，本次学习的是前者

# 2、关于`Calendar`类

## 关于`Date`类部分方法被声明为已过期

- 在`Date`类中，用于获取日期时间的详情值的方法均被标记为”已过期“
  - 从`Java1.1`版本起即声明为”已过期“
  - 这些方法依然可以使用
  - 但是不建议
- 全世界有许多种不同的历法，当需要其他历法表示时间时，在`Date`类中的例如`getYear()`方法获取到的详情值是不符合这些历法的
  - 例如中国还有阴历、农历
- 在`Date`类中的`getTime()`、`setTime()`、`after(Date)`、`before(Date)`这几个方法没有被声明为”已过期“，因为无论使用哪种历法，这些方法并没有问题

## 关于`Calendar`类

- `Calendar`是”历法“类
  - 也称之为”日历“类
- `Calendar`是抽象类，它只约定了作为”历法“类应该可以实现那些操作，但具体的运算却没有实现
  - 例如`protected abstract void computeTime()`等
  - Java提供了几种Calendar的实现（`Calendar`的子类），对应某些历法
  - 你也可以自定义类，继承自`Calendar`，并重写抽象方法

## 获取`Calendar`对象

- 你可以调用`public static Calendar getInstance()`方法，以获取默认的`Calendar`的实现对象

  - 它将根据你的时区来决定获取哪种`Calendar`实现对象
  - 一般来说，你获取到的会是`GregorianCalendar`类的对象

  ```java
  //获取Calendar对象
  Calendar calendar = Calendar.newInstance();
  ```

- 你也可以直接创建自定义的`Calendar`对象

  - 注意：你应该使用`Calendar`来声明对象类型，而不是自定义的类型

  ```java
  //创建自定义的Calendar对象
  Calendar calendar = new CustomCalendar();
  ```

## `Calendar`类的核心方法

- 在`Calendar`类中，最常用的是其设置值、获取值的方法

  > 详情须看`API`文档，该处不赘述

- 注意：所有`set()`方法不会同步更新其他字段

- 在设置值、获取值的方法中，`int field`参数表示你对历法对象中的哪个字段（详情值）进行操作，即你希望设置/获取的是年、月、还是那个字段，你需要使用`Calendar`类定义的常量来表示

  - 例如：`Calendar.YEAR`

- 可以调用`public void add(int field, int amount)`增加某个字段的值，实现一些时间相关的运算

  - 在调用该方法时，你可以向第`2`个参数`int amount`传递一个负数值，以表示”减少“的意思，以将历法对象的时间设置得更早期一些
  - 该方法会同步更新其他字段
    - 该方法是抽象方法，当你自定义历法类时，需要注意同步更新其他字段

- `Calendar`类也有与`Date`相似的`before()`和`after()`方法，用于判断`2`个对象在时间上的先后

  - `public boolean before(Object when)`
  - `public boolean after(Object when)`
  - 虽然参数是`Object`类型，但仅当传入的是`Calendar`类型才可以得到期望结果
    - 详情可直接查看源代码

## `Calendar`与时间戳、`Date`

- `Calendar`中还有与时间戳、`Date`进行转换的方法

## 关于`Calendar`的小结

- `Calendar`是用于解决`Date`类无法作用于不同历法的问题
  - `Calendar`只是一个抽象类，针对不同历法，可以有不同的子类，但各子类的`API`操作可以是相同的
- `Calendar`对象中包含丰富的字段，可以通过`get()`方法获取
- 通过`set()`方法可以设置某个字段的值
- 通过`add()`方法可以对某个字段增加或减去一定的值
- 通过`before()`和`after()`方法可以与另一个`Calendar`对象对比时间的先后

# 3、关于`SimpleDateFormat`类

## 关于`Date`对象的格式化

- 如果直接输出`Date`对象，得到的结果可能不符合需求
- 例如：`Thu Jun 01 11:13:11 CST 2023`
- 各详情值的先后、语种、格式不符合需求
- 你希望的格式可能是这样的：
  - `1970/1/1 8:00:00`
  - `1970/01/01 08:00:00`
  - `1970年01月01日 08时00分00秒`
- 将`Date`对象转换为指定格式，即为：格式化
  - `Calendar`可以转换为`Date`

## 关于`SimpleDateFormat`类

- `SimpleDateFormat`类是专门处理`Date`与字符串转换的工具类
  - 可以将`Date`转换为特定格式的字符串
  - 也可以将字符串表示的日期时间转换为`Date`

## 模式字符串

- 字符串模式的写法

  - 模式字符串：用于指定日期时间格式的字符串，可以在其中使用特定的字母表示特定的意义
    - 例如y表示年份，你使用`yyyy`年，可以格式化为：`2023`年（当`Date`表示的年份是`2023`年时）
  - 在模式字符串中，严格区分特定的字母的大小写
    - 例如`M`表示月份，`m`表示分钟
  - 同一个字母，可能需要写多个
    - 使用`M`月时，当`Date`对象的时间时`1`月份时，格式为：`1`月
      - 如果月份为`10`、`11`、`12`，会自动使用`2`位数字表示
    - 使用`MM`月时，当`Date`对象的时间是`1`月份时，格式为：`01`月

- 一般，定义模式字符串

  ```java
  //设计模式字符串
  String pattern1 = "yyyy-MM-dd HH:mm:ss";
  String pattern2 = "yyyy/MM/dd HH:mm:ss";
  String pattern3 = "yyyy年MM月dd日 HH:mm:ss";
  ```

- 可以只关心某个部分的值

  - 例如在许多音乐、视频的应用中，获取到的音乐、视频的时长都是以毫秒为单位的值，可以先通过这些值创建`Date`对象，再使用以上模式字符串进行格式化，就可以显示出普通用户所习惯的格式了

    ```java
    //设计模式字符串
    String pattern = "HH:mm:ss";
    ```

## 格式化`Date`对象

- 可以先写好模式字符串，作为创建`SimpleDateFormat`对象时构造方法的参数，然后调用该对象的`String format(Date)`方法即可得到格式化后的字符串结果

  ```java
  //创建当前时间对象
  Date now = new Date();
  //设计模式字符串
  String pattern = "yyyy-MM-dd HH:mm:ss";
  //创建格式化工具，并应用模式字符串
  SimpleDateFormat sdf = new SimpleDateFormat(pattern);
  //执行格式化，并获取结果
  String result = sdf.format(now);
  //输出结果
  System.out.println(result);
  ```

## 应用模式字符串到`SimpleDateFormat`对象

- 可以使用`void applyPattern(String)`方法，将模式字符串应用到已存在的`SimpleDateFormat`对象

  - 创建`SimpleDateFormat`对象时，没有在构造方法里添加模式字符串作为参数

  - 需要使用新的模式字符串

    ```java
    String pattern = "yyyy-MM-dd HH:mm:ss";
    sdf.applyPattern(pattern);
    ```

## 将String格式的日期转换为Date对象

- 调用Date parse(String source) throws ParseException方法将字符串格式的日期转换为Date对象
  - 该方法是继承自其弗雷DateFormat的方法
  - 如果字符串表示的日期格式不符合模式字符串的格式，当尝试转换时，将抛出ParseException异常

## `SimpleDateFormat`是线程不安全的

- `SimpleDateFormat`在执行`format()`方法内部使用Calender设置时间值，可能有多个线程同时执行这条语句，导致当前线程的时间值被其他线程修改
- 除了`format()`方法，其实`parse()`方法也是多线程不安全的
- 解决方案
  - 不要将`SimpleDateFormat`声明为全局变量
    - 局部变量不存在线程安全问题
  - 使用`synchronized`互斥锁
  - 使用`ThreadLocal`类，则个线程使用独立的`SimpleDateFormat`对象
  - 使用替代类，例如`DateTimeFormatter`

# 4、`Java8`的日期与时间

## 原来的问题

- 在`Java8`之前，与日期时间相关的类存在的问题
  - `Date`类的默认输出格式可能不直观
    - 例如：`Thu Jan 01 08:00:01 CST 1970`
  - 使用`SimpleDateFormat`需要考虑线程安全问题
    - 可声明为局部变量，可避免出现线程安全问题，每次调用方法都会创建新的对象，并在方法运行结束后销毁
    - 但是，它本事只是一个工具，工具理应可以复用，（创建一个工具，多处使用），即声明为全局变量

## `Java8`中的日期时间相关类

- `Java8`添加了一些新的类，用于处理日期、时间数据：
  - `LocalDate`
    - 只关注日期，即：年、月、日、星期等
  - `LocalTime`
    - 只关注时间，即：小时、分钟、秒钟等
  - `LocalDateTime`
    - 只关心日期与时间
    - 本质上它是基于`LocalDate`和`LocalTime`这2个对象所创建出来的新对象

## 创建`LocalDate`对象

- 可以通过静态的`now()`方法创建当前时间对应的`LocalDate`对象，例如

  ```java
  LocalDate LocalDate = LocalDate.now();
  ```

- 可以通过年、月、日的数值来创建`LocalDate`对象，例如：

  ```java
  LocalDate LocalDate = LocalDate.of(2023, 6, 1);
  ```

- `LocalDate`还提供了`parse()`方法，可以按照yyyy-MM-dd模式的字符串来创建`LocalDate`对象

  - 必须严格按照该模式使用，例如8月，只能使用08而不能使用8来表示
  - `LocalDate`类重载了`parse()`方法，可以指定格式化参数，从而使用其他格式的日期字符串
    - 需要学习`DateTimeFormatter`类

  ```java
  LocalDate LocalDate = LocalDate.parse("2021-08-18");
  ```

## `LocalDate`常用API

- 可以轻松获取各详细值，并且，这些返回值都是符合普通用户的日常习惯

  > 详情须看`API`文档，该处不赘述

- 可以直接修改某个详情值

  - 注意：`LocalDate`对象不可变，修改后返回新的`LocalDate`对象

- 可以很方便的实现年、月、日的增加或减去

- `API`会保证增加或减去的日期是准确的

  - 例如在`1`月`31`日增加`1`天，将得到`2`月`1`日的`LocalDate`对象
  - 注意：`LocalDate`对象不可变，修改后返回新的`LocalDate`对象

- 可以通过`with()`方法结合`TemporalAdjuster`的方法得到相关日期

  - `public LocalDate with(TemporalAdjuster adjuster)`

- `TemporalAdjuster`是一个接口，`LocalDate`实现了该接口，但一般使用它的实现类`TemporalAdjusters`

- `TemporalAdjuster`类中可能使用到的方法

  > 详情须看`API`文档，该处不赘述

- 可以根据`LocalDate`对象来创建`LocalDateTime`对象

  > 详情须看`API`文档，该处不赘述

## 关于`LocalDate`小结

- 可以直接获取当前时间对应的`LocalDate`对象，也可以通过年、月、日的数值，或通过符合`yyyy-MM-dd`模式的字符串来创建`LocalDate`对象
- 通过`get`前缀的方法可以获取各详情值，还可以判断是否是闰年、月份的天数、年份的天数
- 通过`with`前缀的方法可以修改详情值中的某个值，将得到新的`LocalDate`对象
- 通过`with()`方法可以获取某些关于`LocalDate`对象
- 通过`plus/minus`前缀的方法可以对某个详情值进行增加/减去的操作，将得到新的`LocalDate`对象
- 通过`at`前缀的方法可以在当前`LocalDate`对象的基础上设置时间相关数据，将得到`LocalDateTime`对象

## 创建`LocalTime`对象

- 创建`LocalTime`对象的方式与创建`LocalDate`非常相似，例如：

  ```java
  LocalTime LocalTime1 = LocalTime.now();
  LocalTime LocalTime2 = LocalTime.of(10, 15, 30);
  LocalTime LocalTime3 = LocalTime.parse("10:15:30");
  ```

## `LocalTime`的常用API

- 获取各详情值
- 修改各种值
  - 注意：`LocalTime`对象不可变，修改后均返回新的对象
- 对各详情值进行增加、减去
  - 注意：`LocalTime`对象不可变，修改后均返回新的对象
- 结合`LocalDate`创建`LocalDateTime`对象
  - `public LocalDateTime atDate(LocalDate date)`

## 关于`LocalTime`的小结

- 可以直接获取当前时间对应的`LocalTime`对象，也可以通过时、分、秒的数值，或通过符合`HH:mm:ss`模式的字符串来创建`LocalTime`对象
- 通过`get`前缀的方法可以获取各详情值，还可以判断是否是闰年、月份的天数、年份的天数
- 通过`with`前缀的方法可以修改详情值中的某个值，将得到新的`LocalTime`对象
- 通过`with()`方法可以获取某些关于`LocalTime`对象
- 通过`plus/minus`前缀的方法可以对某个详情值进行增加/减去的操作，将得到新的`LocalTime`对象
- 通过`atDate()`方法可以结合`LocalDate`对象以创建`LocalDateTime`对象

## 关于`LocalDateTime`类

- `LocalDateTime`本质上是基于`LocalDate`和`LocalTime`这两个对象所创建出来的新对象

## 创建`LocalDateTime`对象

- 创建`LocalTime`对象的方式与`LocalDate`/`LocalTime`非常相似，例如：

- ```java
  LocalDateTime LocalDateTime1 = LocalDateTime.now();
  LocalDateTime LocalDateTime2 = LocalDateTime.of(2023, 6, 1, 14, 56, 0);
  LocalDateTime LocalDateTime3 = LocalDateTime.parse("2023-06-01T14:56:00");
  ```

## `LocalDateTime`的常用`API`

- `LocalDate`、`LocalTime`可实现的功能，`LocalDateTime`均可实现

  - 在`LocalDateTime`内部，有`LocalDate`和`LocalTime`这2个对象
  - 相关API是直接调用`LocalDate`/`LocalTime`的API实现的

- 可以从`LocalDateTime`中获取`LocalDate`、`LocalTime`的对象

  - `public LocalDate toLocalDate()`
  - `public LocalTime toLocalTime()`

- 将`LocalDateTime`转换为时间戳的操作略复杂一些，你必须先设置时区信息，才可以获取`long`类型的时间戳，例如：

  ```java
  long time = LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli();
  ```

  

## 关于`LocalDateTime`的小结

- `LocalDateTime`是通过`LocalDate`和`LocalTime`实现的，它可以实现中`LocalDate`和`LocalTime`已实现的所有操作

# 5、关于`DateTimeFormatter`类

## 关于`DateTimeFormatter`类

- `DateTimeFormatter`是`Java8`新增的用于对日期、时间进行格式化的类
- `DateTimeFormatter`是线程安全的
  - 不建议再使用`SimpleDateFormat`处理日期时间与字符串的转换

## 获取`DateTimeFormatter`对象

- `DateTimeFormatter`仅有一个默认权限的构造方法，即无法使用构造方法创建对象（不在同一个包下）

- 调用`DateTimeFormatter`的`ofPattern()`静态方法可以应用模式字符串，并创建`DateTimeFormatter`对象

  ```java
  `DateTimeFormatter` formatter =  `DateTimeFormatter`.ofPattern("yyyy-MM-dd HH:mm:ss");
  ```

## 格式化日期时间

- 调用`DateTimeFormatter`的`format()`方法即可对日期、时间进行格式化
  - `public String format(TemporalAdccessor temporal)`
  - 该方法的参数类型是`TemporalAdccessor `，`LocalDateTime`、`LocalDate`、`LocalTime`均实现了`TemporalAdccessor `的子级接口`Temporal`
- 其实，在`LocalDateTime`、`LocalDate`、`LocalTime`类中也有`format()`方法，本质上还是通过`DateTimeFormatter`的`format()`方法实现的
- 在`DateTimeFormatter`、`LocalDateTime`、`LocalDate`、`LocalTime`这4个类中都有`format()`方法
  - 在`DateTimeFormatter`的`format()`方法中，参数可以是`LocalDateTime`、`LocalDate`、`LocalTime`对象
  - 在`LocalDateTime`、`LocalDate`、`LocalTime`的`format()`方法中，参数是`DateTimeFormatter`对象

## 将字符串格式的日期时间转换为对象

- 与`SimpleDateFormat`可实现的功能一样，`DateTimeFormatter`也可以将字符串格式的日期、时间转换为对象
  - `public TemporalAdccessor parse(CharSequence text)`
  - 不建议使用，处理结果比较复杂
- 使用`LocalDateTime`、`LocalDate`、`LocalTime`时，通过静态的`parse()`方法创建对象也是基于`DateTimeFormatter`的`parse()`方法实现的

## 获取`DateTimeFormatter`对象-补充

- `DateTimeFormatter`提供了一些静态属性，本身就是`DateTimeFormatter`对象，已应用特定的模式字符串，但不一定符合需求

## 关于`DateTimeFormatter`类的小结

- `DateTimeFormatter`是`Java8`新增的类，用于实现日期时间与字符串的转换，它是线程安全的
  - 可以把它声明为全局变量，甚至使用`static`修饰
- 可以使用`DateTimeFormatter`的静态属性得到对象，但这些对象的模式字符串不一定符合需求，也可以通过`ofPattern()`方法自定义模式字符串并得到`DateTimeFormatter`对象
- 调用`format()`方法可以将日期时间转换为字符串
  - 也可以调用`LocalDateTime`、`LocalDate`、`LocalTime`的`format()`方法
- 调用`parse()`方法可以将字符串转换为日期时间的对象，不推荐
  - 推荐使用`LocalDateTime`、`LocalDate`、`LocalTime`对象时使用的`parse()`方法，更加简便，也是基于`DateTimeFormatter`的`parse()`方法实现的