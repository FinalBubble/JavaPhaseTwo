package day20230511;

import java.util.HashMap;
import java.util.Map;

/**
 * java.util.Map接口 查找表
 * Map体现的结构是一个多行两列的表格,其中左列称为key,右列称为value
 * Map总是一个key-value的形式保存一组数据,并且可以根据key获取对应的value,
 * Map有一个要求,key不允许重复
 * 常用的类:
 * java.util.HashMap 我们称为"散列表",当今查询速度最快的数据结构
 */
public class MapDemo01 {
    public static void main(String[] args) {
        //创建一个Map实例
        Map<String,Integer> map = new HashMap<>();
        //向map中存储一组键值对 V put(K k,V v)
        map.put("语文", 100);
        map.put("数学", 90);
        map.put("物理", 80);
        Integer v = map.put("化学", 88);
        System.out.println(v);//null
        //{物理=80, 数学=90, 化学=88, 语文=100}
        System.out.println(map);
        //此时map中已经存在key=化学的键值对,此时再存储重复的key的键值对时,会将原先的value进行覆盖
        //并且put方法会将覆盖的value值作为返回值进行返回
        v = map.put("化学", 98);
        System.out.println(v);//88
        System.out.println(map);
        //根据指定的key值,获取对应的value  V get(K k)
        v = map.get("化学");
        System.out.println(v);
        v = map.get("语文");
        System.out.println(v);
        //获取当前map中键值对的个数
        int size = map.size();
        System.out.println("map中的元素数:" + size);
        //删除给定的key对应的这组键值对,返回值为这个key对应的value
        v = map.remove("物理");
        System.out.println(v);
        System.out.println(map);
        //可以判定Map中是否包含给定的key或者value
        boolean ck = map.containsKey("英文");
        System.out.println(ck);
        boolean cv = map.containsValue(98);
        System.out.println(cv);
    }
}
