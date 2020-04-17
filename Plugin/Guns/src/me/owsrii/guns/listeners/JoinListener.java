package me.owsrii.guns.listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import me.owsrii.guns.Main;
import me.owsrii.guns.utils.Utils;

public class JoinListener implements Listener {
	private Main plugin;
	
	public JoinListener(Main plugin) {
		this.plugin = plugin;
		
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		e.setJoinMessage(!p.hasPlayedBefore() ? 
				Utils.chat(plugin.getConfig().getString("firstJoin_message").replace("<player>", p.getName())) : 
				Utils.chat(plugin.getConfig().getString("join_message"
						+ "").replace("<player>", p.getName())));
	}
}
