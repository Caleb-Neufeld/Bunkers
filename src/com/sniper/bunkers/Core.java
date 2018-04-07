package com.sniper.bunkers;

import org.bukkit.plugin.java.JavaPlugin;

public class Core extends JavaPlugin {
	
	private static Core instance;
	
	public void onEnable() {
		instance = this;
	}
	public static Core getInstance() {
		return instance;
	}

}
