package me.owsrii.guns.commands;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.metadata.FixedMetadataValue;

import me.owsrii.guns.Main;
import me.owsrii.guns.utils.Utils;

public class AfkCommand implements CommandExecutor {
	private Main plugin;
	private List<Player> afkPlayers = new ArrayList<>();
	
	public AfkCommand(Main plugin) {
		this.plugin = plugin;
		this.plugin.getCommand("afk").setExecutor(this);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!cmd.getName().equals("afk")) return true;
		if (!(sender instanceof Player)) return true;
		
		Player p = (Player) sender;
		
		if (this.afkPlayers.contains(p)) {
			this.remove(p);
			p.sendMessage(Utils.chat(this.plugin.getConfig().getString("quiting_afk_mode")));
		}
		else {
			this.add(p);	
			p.sendMessage(Utils.chat(this.plugin.getConfig().getString("entering_afk_mode")));
		}
				
		return false;
	}
	
	public synchronized void add(Player p) {
		this.afkPlayers.add(p);
		freeze(p);
	}
	
	public synchronized void remove(Player p) {
		this.afkPlayers.remove(p);
		unfreeze(p);
	}
	
	public void freeze(Player p) {
		p.setMetadata("isFrozen", new FixedMetadataValue(this.plugin, "frozen"));
		p.setInvulnerable(true);
		p.setCanPickupItems(false);
	}
	
	public void unfreeze(Player p) {
		p.removeMetadata("isFrozen", this.plugin);
		p.setInvulnerable(false);
		p.setCanPickupItems(true);
	}
}
