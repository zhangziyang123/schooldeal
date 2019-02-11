package com.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PhoneNumPool {

	private static String phone1="15225192461";
	private static String phone2="15225192462";
	private static String phone3="15225192463";
	
	private static List<String> list=new ArrayList<String>();
	
	public List getPool(){
		list.add(phone1);
		list.add(phone2);
		list.add(phone3);
		return list;
	}
	
	public   void selectPhone(){
		if(list.size()>0){
		Random rand=new Random();
		int a=list.size();
		int index=rand.nextInt(a);
		String phoneNum=list.get(index);
		synchronized(phoneNum){
		System.out.println("号码锁为:"+phoneNum);
		System.out.println("我是线程:"+Thread.currentThread().getName()+",我要选择第"+index+"个号码");
		list.remove(index);
		System.out.println("已选择剩余号码"+list.toString());
		}
		}
	}
}
