package com.util.chain;

public abstract class Handler {

	//�ؼ�����
	protected Handler nextHandler;//��һ��������   
	
	public Handler getNextHandler() {
		return nextHandler;
	}

	public void setNextHandler(Handler nextHandler) {
		this.nextHandler = nextHandler;
	}

	public abstract void leave(int day);//��ٷ���
}
