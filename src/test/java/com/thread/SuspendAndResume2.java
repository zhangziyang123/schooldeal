package com.thread;

public class SuspendAndResume2 {

	
	static class Print{
		synchronized public void print() throws InterruptedException{
				System.out.println("当前线程:"+Thread.currentThread().getName()+"进入print");
				//Thread.currentThread().suspend();
		}
	}
	public static void main(String[] args) throws InterruptedException {
		final Print p=new Print();
		Thread thread=new Thread(new Runnable() {
				public void run() {
					try {
						System.out.println("线程0进入print方法了吗?");
						p.print();
						System.out.println("线程0进入并退出了！");
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
			}
		});
		thread.start();
		Thread.sleep(2000);
		Thread thread2=new Thread(new Runnable() {
			public void run() {
				try {
					System.out.println("线程1进入print方法了吗?");
					p.print();
					System.out.println("线程1进入并退出了！");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
		}
	});
		thread2.start();
		
		
	}

}
