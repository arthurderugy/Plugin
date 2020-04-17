package me.owsrii.guns.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.owsrii.guns.Main;
import me.owsrii.guns.utils.Utils;

public class FlyCommand implements CommandExecutor {
	private Main plugin;
	
	public FlyCommand(Main plugin) {
		this.plugin = plugin;
		this.plugin.getCommand("fly").setExecutor(this);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)) return true;
		if (!cmd.getName().equalsIgnoreCase("fly")) return true;
		
		Player p = (Player) sender;
		if (!p.isOp()) 
			return false;
		
		if (p.getAllowFlight()) {
			p.setFlying(false);
			p.setAllowFlight(false);
			p.sendMessage(Utils.chat("&cFly disabled."));
			return true;
		}
		else {
			p.setAllowFlight(true);
			p.setFlying(true);
			p.sendMessage(Utils.chat("&cFly enabled."));
			return true;
		}
	}

}
