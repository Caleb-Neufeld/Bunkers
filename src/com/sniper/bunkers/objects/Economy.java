package com.sniper.bunkers.objects;

import java.util.HashMap;
import java.util.UUID;

import org.bukkit.entity.Player;

public class Economy {

	private static HashMap<UUID, Double> balance = new HashMap<>();

	public static double get(Player player) {
		try {
			return balance.get(player.getUniqueId());
		} catch (NullPointerException e) {
			return 0;
		}
	}
	
	public static void add(Player player, double amount) {
		try {
			balance.put(player.getUniqueId(), get(player) + amount);
		} catch (NullPointerException e) {
			balance.put(player.getUniqueId(), amount);
		}
	}
	
	public static void remove(Player player, double amount) {
		try {
			balance.put(player.getUniqueId(), (get(player)-amount < 0 ? 0 : get(player) - amount));
		} catch (NullPointerException e) {
			player.sendMessage(new CC("&cECONOMY ERROR: remove").translate().string());
		}
	}

}
