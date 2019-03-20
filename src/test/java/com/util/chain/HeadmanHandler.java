package com.util.chain;

public class HeadmanHandler extends Handler {

	@Override
	public void leave(int day) {
		if(day<2){
			System.out.println("请假小与两天---批准");
		}else{
			System.out.println("请假大与两天---往上提交申请");
			nextHandler.leave(day);
		}

	}

}
