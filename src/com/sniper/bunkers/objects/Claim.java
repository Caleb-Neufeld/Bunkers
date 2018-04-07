package com.sniper.bunkers.objects;

public class Claim {
	
	private Team owner;
	private Coordinants x;
	private Coordinants z;
	
	public Claim(Team owner, Coordinants one, Coordinants two) {
		this.owner = owner;
		this.x = one;
		this.z = two;
	}
	
	public Team getOwner() {
		return owner;
	}
	
	public Coordinants getPos1() {
		return x;
	}
	
	public Coordinants getPos2() {
		return z;
	}

}
