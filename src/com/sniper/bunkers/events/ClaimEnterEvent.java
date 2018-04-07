package com.sniper.bunkers.events;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import com.sniper.bunkers.objects.Team;

public class ClaimEnterEvent extends Event {

	private Player player;
	private Team claim;

	private static final HandlerList handlers = new HandlerList();

	public ClaimEnterEvent(Player player, Team team) {
		this.player = player;
		this.claim = team;
	}

	public Team getOwner() {
		return claim;
	}
	
	public Player getPlayer() {
		return player;
	}

	@Override
	public HandlerList getHandlers() {
		return handlers;
	}

	public static HandlerList getHandlerList() {
		return handlers;
	}

}
