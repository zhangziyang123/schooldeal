package com.thread;

public abstract class Common {

	public Common(){
		System.out.println("22222222");

	}
	
	public static void main(String[] args) {
		new Abc();
	}
}
class Abc extends Common{
	public Abc(){
		System.out.println("111111111");
	}
}
