package com.util.decoratormodel;

public class MilkDrink extends AbstractDrink {

	@Override
	public void getDesc() {
		System.out.println("еёдл");

	}

	@Override
	public int price() {
		
		return 5;
	}

}
