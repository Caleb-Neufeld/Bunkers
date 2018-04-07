package com.sniper.bunkers.managers.combat.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import com.sniper.bunkers.managers.combat.CombatTag;
import com.sniper.bunkers.objects.CC;
import com.sniper.bunkers.teams.TeamManager;

public class CombatListener implements Listener {
	
	@EventHandler
	public void onAttack(EntityDamageByEntityEvent e) {
		if(!(e.getDamager() instanceof Player)) return;
		if(!(e.getEntity() instanceof Player)) return;
		Player damager = (Player) e.getDamager();
		Player victim = (Player) e.getEntity();
		if(TeamManager.getPlayersTeam(damager)==TeamManager.getPlayersTeam(victim)) {
			e.setCancelled(true);
			damager.sendMessage(new CC("Messages.PLAYER_SAME_TEAM").fromConfig().variables(victim).translate().string());
			return;
		}
		CombatTag.putInCombat(victim);
		CombatTag.putInCombat(damager);
		if(!CombatTag.isCombatTagged(damager)) damager.sendMessage(new CC("Messages.COMBAT_TAG").fromConfig().variables(damager, victim).translate().string());
		if(!CombatTag.isCombatTagged(victim)) victim.sendMessage(new CC("Messages.COMBAT_TAGGED").fromConfig().variables(damager, victim).translate().string());
	}
}
