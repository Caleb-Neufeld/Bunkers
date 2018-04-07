package com.sniper.bunkers.teams.claims.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class PlayerMoveListener implements Listener {
	
	@EventHandler
	public void onMove(PlayerMoveEvent event) {
		double xFrom = event.getFrom().getX();
		double zFrom = event.getFrom().getZ();
		double xTo = event.getTo().getX();
		double zTo = event.getTo().getZ();
		if(xTo != xFrom || zTo != zFrom) {
			Player player = event.getPlayer();
		}
	}

}
