package com.thread;

public class Synchonized {

	static class Count{
		private int count=0;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		final Count count =new Count();
		Thread t1=new Thread(new Runnable() {
			
			public void run() {
				for(int i=0;i<10000;i++)
				count.count++;
				
			}
		});
		
		Thread t2=new Thread(new Runnable() {
			
			public void run() {
				System.out.println(count.count);
				
			}
		});
		
	
		t1.start();
		t2.start();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(count.count);
	}

}
