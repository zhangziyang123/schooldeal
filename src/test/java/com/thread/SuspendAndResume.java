package com.thread;
/**
 * suspend方法造成数据不同步
 * @author admin
 *
 */
public class SuspendAndResume {

	static class People{
		private String userName="1";
		private String passWord="2";
		public void setValue(String username,String password){
			userName=username;
			Thread.currentThread().suspend();
			passWord=password;
		}
		@Override
		public String toString() {
			return "People [userName=" + userName + ", passWord=" + passWord + "]";
		}
		
	}
	public static void main(String[] args) throws InterruptedException {
		final People people=new People();
		Thread thread=new Thread(new Runnable() {
			
			public void run() {
				people.setValue("A", "B");
			}
		});
		thread.start();
		Thread.sleep(1000);
		System.out.println(people.toString());
		
	}

}
