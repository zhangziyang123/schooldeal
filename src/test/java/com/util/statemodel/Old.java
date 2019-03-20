package com.util.statemodel;

public class Old {

	public void print(int i){
		if(i==0){
			System.out.println("i=0");
		}else if(0<i&&i<10){
			System.out.println("0<i<10");
		}else if(i>=10 && i<20){
			System.out.println("10<=i<20");
		}else if(i>=20){
			System.out.println("20<=i");
		}
	}
	public static void main(String[] args) {
		Old o=new Old();
		o.print(20);

	}

}
