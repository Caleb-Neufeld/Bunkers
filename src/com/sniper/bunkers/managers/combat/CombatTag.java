package com.sniper.bunkers.managers.combat;

import org.bukkit.entity.Player;

import com.sniper.bunkers.Core;
import com.sniper.bunkers.managers.Cooldowns;



public class CombatTag {
	
	public static void putInCombat(Player p) {
		Cooldowns.addCooldown("combat", p, getCombatSeconds());
	}
	
	public static int getCombatSeconds() {
		return Core.getInstance().getConfig().getInt("Combat.COMBAT_TIME");
	}
	
	public static void removeCombatTime(Player p) {
		Cooldowns.removeCooldown("combat", p);
	}
	
	public static boolean isCombatTagged(Player p) {
		if(Cooldowns.isOnCooldown("combat", p)) {
			return true;
		} else {
			return false;
		}
	}
	
	public static long getCombatMillis(Player p) {
		return Cooldowns.getCooldownForPlayerLong("combat", p);
	}

}
