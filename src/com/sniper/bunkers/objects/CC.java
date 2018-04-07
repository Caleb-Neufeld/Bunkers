package com.sniper.bunkers.objects;

import java.util.HashMap;
import java.util.Map.Entry;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import com.sniper.bunkers.Core;
import com.sniper.bunkers.managers.combat.CombatTag;
import com.sniper.bunkers.scoreboard.Time;
import com.sniper.bunkers.teams.TeamManager;

public class CC {
	
	private String text;
	
	public CC(String text) {
		this.text = text;
	}

	public CC translate() {
		text = ChatColor.translateAlternateColorCodes('&', text);
		return this;
	}
	
	public CC variables(Player player, Player victim) {
		HashMap<String, String> variables = new HashMap<>();
		variables.put("<gt>", "");
		variables.put("<kt>", "");
		variables.put("<b>", String.valueOf(Economy.get(player)));
		variables.put("<d>", String.valueOf(TeamManager.getDTR(TeamManager.getPlayersTeam(player))));
		variables.put("<tc>", "");
		variables.put("<tn>", "");
		variables.put("<v>", (victim == null ? "NOT AVAILABLE" : victim.getName()));
		variables.put("<ct>", Time.IntegerTime.setMSFormat(Time.IntegerTime.convertMillisecondsToSeconds(CombatTag.getCombatMillis(player))));
		for(Entry<String, String> variable : variables.entrySet()) {
			text = text.replaceAll(variable.getKey(), variable.getValue());
		}
		return this;
	}
	
	public CC variables(Player victim) {
		return variables(null, victim);
	}
	
	public CC fromConfig() {
		text = Core.getInstance().getConfig().getString(text);
		return this;
	}
	
	public String string() {
		return text;
	}
	
}
