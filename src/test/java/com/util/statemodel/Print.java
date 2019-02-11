package com.util.statemodel;

public class Print {

	private State state;
	private int i;
	
	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}
	
	
	public Print() {
		state=new Zero();
	}

	public int getI() {
		return i;
	}

	public void setI(int i) {
		this.i = i;
	}

	public void print(){
		state.print(this);
	}
}
