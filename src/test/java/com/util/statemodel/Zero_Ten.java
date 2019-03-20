package com.util.statemodel;

public class Zero_Ten extends State {

	@Override
	public void print(Print n) {

		if(n.getI()>0&&n.getI()<10){
			System.out.println("0<i<10");
		}else{
			System.out.println("i>10");
		}
	}

	
}
