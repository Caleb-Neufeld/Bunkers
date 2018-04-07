package com.sniper.bunkers.scoreboard;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.file.FileConfigurationOptions;
import org.bukkit.craftbukkit.Main;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.PluginManager;
import org.bukkit.scheduler.BukkitRunnable;

import com.sniper.bunkers.Core;

public class Scoreboard implements Listener {

	private static Map<Player, ScoreboardHelper> scoreboardHelperMap = new HashMap<>();

	private static String scoreboardTitle;
	private static String scoreboardBars;
	private static List<String> scoreboardLines;

	@SuppressWarnings("deprecation")
	public static void registerScoreboard() {
		scoreboardLines = Chat.translateColors(Core.getInstance().getConfig().getStringList("Scoreboard.LINES"));
		scoreboardBars = Chat.translateColors(Core.getInstance().getConfig().getString("Scoreboard.BARS"));
		scoreboardTitle = Chat.translateColors(Core.getInstance().getConfig().getString("Scoreboard.TITLE"));
		for (Player player : Bukkit.getServer().getOnlinePlayers()) {
			onPlayerJoin(player);
		}

		new BukkitRunnable() {
			@Override
			public void run() {

				for (Map.Entry<Player, ScoreboardHelper> entry : scoreboardHelperMap.entrySet()) {
					final Player player = entry.getKey();
					ScoreboardHelper helper = entry.getValue();
					helper.clear();
					boolean addedSomethingButBars = false;
					{
						for (String string : scoreboardLines) {
							boolean skip = false;
							string = string.replace("%bars%", scoreboardBars);
								if (string.contains("%player%")) {
									if (Bukkit.getPlayer(player.getName()) != null) {
										string = string.replace("%player%", player.getName());
									} else {
										skip = true;
									}
								}
								if (string.contains("%online%")) {
									if (Bukkit.getOnlinePlayers().length != 0) {
										string = string.replace("%online%", Bukkit.getOnlinePlayers().length + "");
									} else {
										skip = true;
									}
								}
								if(string.contains("%%")) {
									
								}
								if (!skip) {
									addedSomethingButBars = true;
								}
							
							if (!skip) {
								helper.add(string);
							}
						}
					}
					if (!addedSomethingButBars) {
						helper.clear();
					}
					helper.update(player);
				}
			}
		}.runTaskTimer(Main.getInstance(),1L,1L);

	}

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e) {
		Player player = e.getPlayer();
		onPlayerJoin(player);
	}

	public static void onPlayerJoin(final Player p) {
		new BukkitRunnable() {
			@Override
			public void run() {
				if (p.isOnline()) {
					org.bukkit.scoreboard.Scoreboard sc = Bukkit.getScoreboardManager().getNewScoreboard();
					ScoreboardHelper sch = new ScoreboardHelper(sc, scoreboardTitle);
					scoreboardHelperMap.put(p, sch);
				}
			}
		}.runTaskLater(Main.getInstance(), 20L);
	}

	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent e) {
		Player player = e.getPlayer();
		scoreboardHelperMap.remove(player);
	}

	@EventHandler
	public void onPlayerKick(PlayerKickEvent paramPlayerKickEvent) {
		Player player = paramPlayerKickEvent.getPlayer();
		scoreboardHelperMap.remove(player);
	}

	public ConsoleCommandSender getConsoleCommandSender() {
		return Bukkit.getServer().getConsoleSender();
	}

	public FileConfigurationOptions getFileConfigurationOptions() {
		return Main.getInstance().getConfig().options();
	}

	public PluginManager getPluginManager() {
		return Bukkit.getServer().getPluginManager();
	}

	public String getString(String paramString) {
		if (Main.getInstance().getConfig().contains(paramString)) {
			return Chat.translateColors(Main.getInstance().getConfig().getString(paramString));
		}
		return Chat.translateColors("&eWe couldn't find the string located in &6" + paramString + "&e.");
	}

}