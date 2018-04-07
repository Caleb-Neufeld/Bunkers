package com.sniper.bunkers.objects;

public class Claim {
	
	private Team owner;
	private double x;
	private double z;
	
	public Claim(Team owner, Coordinants one, Coordinants two) {
		this.owner = owner;
		this.x = x;
		this.z = z;
	}
	
	public Team getOwner() {
		return owner;
	}
	
	public double getPos1() {
		return x;
	}
	
	public double getPos2() {
		return z;
	}

}
