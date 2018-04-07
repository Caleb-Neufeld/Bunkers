package com.sniper.bunkers.objects;

import java.util.HashMap;
import java.util.Map.Entry;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import com.sniper.bunkers.Core;

public class CC {
	
	private String text;
	
	public CC(String text) {
		this.text = text;
	}

	public CC translate() {
		text = ChatColor.translateAlternateColorCodes('&', text);
		return this;
	}
	
	public CC variables(Player player) {
		HashMap<String, String> variables = new HashMap<>();
		variables.put("<gt>", "");
		variables.put("<kt>", "");
		variables.put("<b>", "");
		variables.put("<d>", "");
		variables.put("<tc>", "");
		variables.put("<tn>", "");
		for(Entry<String, String> variable : variables.entrySet()) {
			text = text.replaceAll(variable.getKey(), variable.getValue());
		}
		return this;
	}
	
	public CC fromConfig() {
		text = Core.getInstance().getConfig().getString(text);
		return this;
	}
	
	public String string() {
		return text;
	}
	
}
