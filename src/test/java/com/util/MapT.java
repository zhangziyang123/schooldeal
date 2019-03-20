package com.util;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class MapT {
    public static void main(String[] args) {
        Map<Integer,String> map=new HashMap<Integer, String>();
        map.put(1,"第一个");
        map.put(2,"第二个");
        map.put(3,"第三个");
        map.put(4,"第四个");
 
        //第一种：先得到key的值 然后通过key值得到value值
        Set<Integer> set=map.keySet();//这里的泛型是key值得泛型
        for(Integer integer:set){
            System.out.println(integer+"->"+map.get(integer));//key->value
        }
 
        //第二种:通过Map.values()遍历所有的value但是不能遍历key
        for(String s:map.values()){
            System.out.println(s);
        }
 
        //第三种:通过Map.entrySet使用迭代器iterator遍历key和value
        Iterator<Map.Entry<Integer,String>> iterable=map.entrySet().iterator();
        while(iterable.hasNext()){
            Map.Entry<Integer,String>entry=iterable.next();
            System.out.println(entry.getKey()+"->"+entry.getValue());
        }
 
        //第四种:直接通过Map.entrySet遍历key和value
        //最常见
        for(Map.Entry<Integer,String> entry:map.entrySet()){
            System.out.println(entry.getKey()+"->"+entry.getValue());
        }
 
 
        //注：Map.Entry方法解释
        //Map.Entry是Map声明的一个内部接口，此接口为泛型，定义为Entry<K,V>。它表示Map中的一个实体（一个key-value对）
    }

}