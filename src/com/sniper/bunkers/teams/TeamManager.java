package com.sniper.bunkers.teams;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import com.sniper.bunkers.objects.Team;

public class TeamManager {
	
	private static HashMap<Team, UUID> teams = new HashMap<>();
	private static HashMap<Team, Double> dtr = new HashMap<>();
	
	public static Team getPlayersTeam(Player player) {
		for(Entry<Team, UUID> team : teams.entrySet()) {
			if(team.getValue().equals(player.getUniqueId())) {
				return team.getKey();
			}
		}
		return Team.SPECTATOR;
	}
	
	public static List<Player> getTeamPlayers(Team team) {
		ArrayList<Player> player = new ArrayList<>();
		for(Entry<Team, UUID> teams : teams.entrySet()) {
			if(teams.getKey()==team) {
				player.add(Bukkit.getPlayer(teams.getValue()));
			}
		}
		return player;
	}
	
	public static double getDTR(Team team) {
		return dtr.get(team);
	}
	
	public static void setDTR(Team team, double amount) {
		dtr.put(team, amount);
	}

	public static void broadcastTeam(Team team, String broadcast) {
		for(Player player : getTeamPlayers(team)) {
			player.sendMessage(broadcast);
		}
	}
	
	
}
