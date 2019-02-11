package com.util.statemodel;

public class Zero extends State {
	
	@Override
	public void print(Print n) {
		if(n.getI()==0){
			System.out.println("i=0");
		}else{
			n.setState(new Zero_Ten());
			n.print();
		}
		
	}

}
