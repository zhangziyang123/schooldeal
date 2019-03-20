package com.util;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class MapT {
    public static void main(String[] args) {
        Map<Integer,String> map=new HashMap<Integer, String>();
        map.put(1,"��һ��");
        map.put(2,"�ڶ���");
        map.put(3,"������");
        map.put(4,"���ĸ�");
 
        //��һ�֣��ȵõ�key��ֵ Ȼ��ͨ��keyֵ�õ�valueֵ
        Set<Integer> set=map.keySet();//����ķ�����keyֵ�÷���
        for(Integer integer:set){
            System.out.println(integer+"->"+map.get(integer));//key->value
        }
 
        //�ڶ���:ͨ��Map.values()�������е�value���ǲ��ܱ���key
        for(String s:map.values()){
            System.out.println(s);
        }
 
        //������:ͨ��Map.entrySetʹ�õ�����iterator����key��value
        Iterator<Map.Entry<Integer,String>> iterable=map.entrySet().iterator();
        while(iterable.hasNext()){
            Map.Entry<Integer,String>entry=iterable.next();
            System.out.println(entry.getKey()+"->"+entry.getValue());
        }
 
        //������:ֱ��ͨ��Map.entrySet����key��value
        //���
        for(Map.Entry<Integer,String> entry:map.entrySet()){
            System.out.println(entry.getKey()+"->"+entry.getValue());
        }
 
 
        //ע��Map.Entry��������
        //Map.Entry��Map������һ���ڲ��ӿڣ��˽ӿ�Ϊ���ͣ�����ΪEntry<K,V>������ʾMap�е�һ��ʵ�壨һ��key-value�ԣ�
    }

}