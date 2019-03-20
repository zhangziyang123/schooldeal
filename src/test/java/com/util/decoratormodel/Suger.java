package com.util.decoratormodel;

public class Suger extends Decorator {

	public Suger(AbstractDrink drink) {
		super(drink);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void getDesc() {
		super.getDesc();
		System.out.println("╪слг");

	}

	@Override
	public int price() {
		// TODO Auto-generated method stub
		return super.price()+1;
	}

}
