package com.thread;

public class ThreadStartAndEnd {

	public static void main(String[] args) {
		new Thread(new Runnable() {
			
			public void run() {
				// TODO Auto-generated method stub
				while(true){
					System.out.println("111111111");
				}
			}
		}).start();
		
	}
}
