package com.util.decoratormodel;

public class Test {

	public static void main(String[] args) {

		AbstractDrink drink=new MilkDrink();
		drink=new Suger(drink);
		drink=new Water(drink);
		//drink.getDesc();
		int p=drink.price();
		System.out.println("×Ü¼Û"+p);
	}

}
