package com.util.decoratormodel;

public abstract class Decorator extends AbstractDrink {

	protected AbstractDrink drink;
	
	public Decorator(AbstractDrink drink) {
		super();
		this.drink = drink;
	}

	@Override
	public  void getDesc() {
		drink.getDesc();
	}

	public int price(){
		return drink.price();
		
	}
}
