package com.util.chain;

public class HeadmanHandler extends Handler {

	@Override
	public void leave(int day) {
		if(day<2){
			System.out.println("���С������---��׼");
		}else{
			System.out.println("��ٴ�������---�����ύ����");
			nextHandler.leave(day);
		}

	}

}
