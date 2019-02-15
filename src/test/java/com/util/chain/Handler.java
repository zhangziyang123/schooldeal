package com.util.chain;

public abstract class Handler {

	//关键代码
	protected Handler nextHandler;//下一个处理链   
	
	public Handler getNextHandler() {
		return nextHandler;
	}

	public void setNextHandler(Handler nextHandler) {
		this.nextHandler = nextHandler;
	}

	public abstract void leave(int day);//请假方法
}
