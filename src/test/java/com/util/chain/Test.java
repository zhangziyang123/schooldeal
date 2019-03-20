package com.util.chain;

public class Test {

	public static void main(String[] args) {
		Handler headman=new HeadmanHandler();
		Handler manager=new ManagerHandler();
		Handler boss=new BossHandler();
		headman.setNextHandler(manager);
		manager.setNextHandler(boss);
		headman.leave(11);

	}

}
