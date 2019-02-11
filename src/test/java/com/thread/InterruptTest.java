package com.thread;

public class InterruptTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		try {
	 final Thread thread = new Thread(new Runnable() {
			public void run() {
				for(int i=0;i<5000;i++){
					System.out.println("i="+i);
					if(Thread.currentThread().isInterrupted()){
						System.out.println("线程在"+i+"时停止");
						break;
					}
				}
			}
		});
			thread.start();
			Thread.sleep(100);
			thread.interrupt();
			System.out.println("线程停止了吗"+thread.isInterrupted());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
