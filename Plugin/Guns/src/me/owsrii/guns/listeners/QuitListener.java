package me.owsrii.guns.listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import me.owsrii.guns.Main;
import me.owsrii.guns.utils.Utils;

public class QuitListener implements Listener {
	private Main plugin;
	
	public QuitListener(Main plugin) {
		this.plugin = plugin;
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}
	
	@EventHandler
	public void onQuit(PlayerQuitEvent e) {
		Player p = e.getPlayer();
		e.setQuitMessage(Utils.chat(plugin.getConfig().getString("quit_message").replace("<player>", p.getName())));
	}
}
