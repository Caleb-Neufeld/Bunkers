package com.sniper.bunkers.teams.claims.listener;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import com.sniper.bunkers.events.ClaimEnterEvent;
import com.sniper.bunkers.objects.CC;
import com.sniper.bunkers.teams.claims.ClaimsManager;

public class PlayerMoveListener implements Listener {
	
	@EventHandler
	public void onMove(PlayerMoveEvent event) {
		double xFrom = event.getFrom().getX();
		double zFrom = event.getFrom().getZ();
		double xTo = event.getTo().getX();
		double zTo = event.getTo().getZ();
		if(xTo != xFrom || zTo != zFrom) {
			Player player = event.getPlayer();
			if(ClaimsManager.getOwnerAt(event.getTo()) != ClaimsManager.getOwnerAt(event.getFrom())) {
				Bukkit.getPluginManager().callEvent(new ClaimEnterEvent(player, ClaimsManager.getOwnerAt(event.getTo())));
				player.sendMessage(new CC("Messages.ENTER_CLAIM").fromConfig().variables(player).translate().string());
			}
		}
	}
}
