## `BigInteger`
### 关于`BigInteger`类
 * `BigInteger`类是用于解决整型类型（含基本数据类型及对应的包装类）无法表示特别大的数字及运算的问题
   * 即使是占用字节数最多的整形long，能表示的范围也是有限的
     *  `-9223372036854775808 ~ 9223372036854775807`
     * 以上数字长度为`19`位
 * 理论上，你可以使用`BigInteger`表示任意整数
   * 它肯定支持 $-2^{Integer.MAX\_VALUE}$至$2^{Integer.MAX\_VALUE}$，甚至更大范围
     * `Integer.MAX_VALUE:2147483647`
### `BigInteger`的构造方法 
    本文中不展示具体构造方法，可查阅官方文档
### `BigInteger`的常用`API`
    加、减、乘、除、取余、最值等
### 将`BigInteger`转换为基本类型
    注意：在进行转换之前，应该确认是否会丢失精度，避免转换结果不符合预期
### `BigInteger`小结
* 使用`BigInteger`可以表示非常大的整数
* `BigInteger`的构造方法重载了几次，推荐使用`String`作为参数的那个
* 当需要进行算术运算时，必须使用`BigInteger`提供的`API`
* 你可以将`BigInteger`转换成任何基本数据类型，但可能丢失精度
  * 在转换之前，应先明确是否可能丢失精度
  * 你不一定需要将结果转换成数值型，使用字符串也可以用于显示或存储

### 处理大数字的建议
* 当你处理的数据对运算精度的要求不高时，可以使用更大的单位，从而使用更小的数字来处理，以简化运算操作
  * 例如统计某些大流量网站的日访问量，可以将单位由“次”换为“万次”或者“百万次“甚至更大的单位
    * 假设某网站的访问量为`100000001`（一千万零一次），处理为`1000`万次，对其评估并没有实质影响
* 注意：如果对运算精度有明确要求，不可以这样处理
  * 例如某客户在银行的存款是`100000001`元（一千万零一元），银行系统无权随意将其修改为`1000`万元

## `BigDecimal`
### 为什么需要`BigDecimal`
* 使用`float`、`double`及其对应的包装类时，运算精度可能不满足需求
  * `float`最多只有`7`位有效数，则其精度位`6~7`位
  * `double`最多只有`16`位有效数，则其精度为`15~16`位
* 使用`float`、`double`及其对应的包装类时，不便于对小数点以后的若干位进行截取、四舍五入或相关处理

### 通过静态方法创建BigDecimal对象

> 本文中不展示具体方法，可查阅官方文档
> 注：`String`参数需使用构造方法，其他参数可采用静态方法

### `BigDecimal`的常用`API`

> 加、减、乘、除（多个）、取余、最值等

### 关于除法运算的`roundingMode`
* 在调用`divide()`方法时，使用`roundingMode`可以设置运算时的舍入模式
* 使用`BigDecimal`类中以ROUND为前缀的常量表示
  * 例如：`BigDecimal.ROUND_HALF_UP`

| 常量值 | 说明 |
|-----|----|
|`ROUND_UP`     | 在丢弃非零部分之前始终增加数字(始终对非零舍弃部分前面的数字加1)   |
| `ROUND_DOWN` | 在丢弃某部分之前始终不增加数字（从不对舍弃部分前面的数字加1，即截断） |
| `ROUND_CEILING` | 正数时，与`ROUND_UP`相同，负数时，与`ROUND_DOWN`相同 |
| `ROUND_FLOOR` | 正数时，与`ROUND_DOWN`相同，负数时，与`ROUND_UP`相同 |
| `ROUND_HALF_UP` | 满足四舍五入时，与`ROUND_UP`相同，否则，与`ROUND_DOWN`相同 |
| `ROUND_HALF_DOWN` | 满足五舍六入时，与`ROUND_UP`相同，否则，与`ROUND_DOWN`相同 |
| `ROUND_HALF_EVEN` | 若舍弃部分左侧为奇数，与`ROUND_HALF_UP`相同，否则，与`ROUND_HALF_DOWN`相同 |
| `ROUND_UNNECESSARY` | 断言请求的操作具有精确的结果，因此不需要舍入 |

### 将`BigDecimal`转换为基本类型

> 注意：在进行转换之前，应该确认是否会丢失精度，避免转换结果不符合预期



### `BigDecimal`小结
* 使用`BigDecimal`主要解决基本数据类型运算精度不足的问题
* 当原数使用`String`表示时，使用构造方法创建`BigDecimal `
* 当原数使用`double`表示时，使用静态方法`valueOf()`创建`BigDecimal` 
* 当需要进行算术运算时，必须使用`BigDecimal`提供的API
* `BigDecimal`的`API`几乎包括了你所能想到的所有数值操作
* 你可以将`BigDecimal`转换成任何基本数据类型，但可能丢失精度
  * 在转换之前，应先明确是否可能丢失精度
  * 你不一定需要将结果转换为数值类型，使用字符串也可以用于显示或存储
### 处理高精度数据的建议
* 当你处理的数据对精度要求较高时，如果可以的话，将其调整为”小单位大数值“的整型数据，以避免浮点运算的精度误差
  * 例如`93.27`元改为`9327`分
    * 在运算结束之后，再使用`String`的`API`操作，转换为普通用户习惯的格式，再显示到软件的界面
  * 整型数据在算数运算中没有误差
  * 对精度要求非常高的浮点运算时，这种做法仍不可取
## `NumberFormat`类
### `NumberFormat`类
* `NumberFormat`类时`java.text`包中的一个工具类，它的主要作用是对数值进行格式化，得到期望格式的字符串
  * 例如，当你期望将数字转换为百分比格式时，如果输入`0.37`，将得到`37%`
  * 注意：只要对象的本质时数值，均可格式化
    * 例如：`BigInteger`、`BigDecimal`、`String`等
  * 格式化后得到的是`StringBuffer`对象
    * 一定程度上，你可以把`StringBuffer`直接理解为String
  * `NumberFormat`类是抽象类，不可以直接创建对象
### 预置的几种`NumberFormat`
* 你可以通过`NumberFormat`的几个静态方法得到预置的`NumberFormat`对象
### 关于`Locale`对象
* 可以通过`Local`的静态常量获取该类型的对象
### `NumberFormat`小结
* 使用`NumberFormat`可以将数值格式化为特定的格式，包括：
  * 数字
  * 整型数
  * 货币样式（可设置地区）
  * 百分比
* 只要数据的本质是数值，都可以格式化
* 先通过`NumberFormat`的静态方法获取对象，再调用`format()`方法即可得到格式化后的字符串
  * 根据期望的目标格式，调用不同的方法
  * 得到的结果是StringB`u`ffer对象