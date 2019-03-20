package com.util.chain;

public class ManagerHandler extends Handler {

	@Override
	public void leave(int day) {
		if(day<10){
			System.out.println("请假小与10天-----批准");
		}else{
			System.out.println("请假大与10天---往上提交申请");
			nextHandler.leave(day);
		}

	}

}
