package com.sniper.bunkers;

import org.bukkit.plugin.java.JavaPlugin;

import com.sniper.bunkers.managers.Cooldowns;

public class Core extends JavaPlugin {
	
	private static Core instance;
	
	public void onEnable() {
		instance = this;
		Cooldowns.createCooldown("combat");
	}
	public static Core getInstance() {
		return instance;
	}

}
