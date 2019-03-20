package com.thread;

public class SuspendAndResume2 {

	
	static class Print{
		synchronized public void print() throws InterruptedException{
				System.out.println("��ǰ�߳�:"+Thread.currentThread().getName()+"����print");
				//Thread.currentThread().suspend();
		}
	}
	public static void main(String[] args) throws InterruptedException {
		final Print p=new Print();
		Thread thread=new Thread(new Runnable() {
				public void run() {
					try {
						System.out.println("�߳�0����print��������?");
						p.print();
						System.out.println("�߳�0���벢�˳��ˣ�");
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
					System.out.println("�߳�1����print��������?");
					p.print();
					System.out.println("�߳�1���벢�˳��ˣ�");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
		}
	});
		thread2.start();
		
		
	}

}
