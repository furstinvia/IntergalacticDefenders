package com.game.src.main;

public class GameObject {

	public double x,y;
	
	public GameObject(double x, double y) {
		this.x=x;
		this.y=y;
	}
	
	public double getX() {
		return x;
	}
	public double getY() {
		return y;
	}
	
	public void setY(int y) {
		this.y = y;
	}

	public void setX(int x) {
		this.x=x;
	}
}
