package com.util.chain;

public class ManagerHandler extends Handler {

	@Override
	public void leave(int day) {
		if(day<10){
			System.out.println("���С��10��-----��׼");
		}else{
			System.out.println("��ٴ���10��---�����ύ����");
			nextHandler.leave(day);
		}

	}

}
