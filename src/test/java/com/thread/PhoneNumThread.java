package com.thread;

import java.util.Random;

public class PhoneNumThread implements Runnable{

	private PhoneNumPool pool;
	
	
	
	public PhoneNumThread(PhoneNumPool pool) {
		super();
		this.pool = pool;
	}



	public PhoneNumPool getPool() {
		return pool;
	}



	public void setPool(PhoneNumPool pool) {
		this.pool = pool;
	}



	public void run() {
		pool.selectPhone();
	}

	

	public static void main(String[] args) {
		PhoneNumPool pool=new PhoneNumPool();
		pool.getPool();
		for(int i=0;i<1000;i++){
			PhoneNumThread thread=new PhoneNumThread(pool);
			Thread t=new Thread(thread);
			t.start();;
		}
		 
	}

}
