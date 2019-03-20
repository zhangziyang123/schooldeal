package com.util.decoratormodel;

public class Water extends Decorator {

	public Water(AbstractDrink drink) {
		super(drink);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void getDesc() {
		super.getDesc();
		System.out.println("¼ÓË®");

	}

	@Override
	public int price() {
		// TODO Auto-generated method stub
		return super.price()+2;
	}

}
