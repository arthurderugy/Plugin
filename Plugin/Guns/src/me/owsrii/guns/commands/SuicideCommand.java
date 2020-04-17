package me.owsrii.guns.commands;

import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.owsrii.guns.Main;

public class SuicideCommand implements CommandExecutor {
	private Main plugin;
	
	public SuicideCommand(Main plugin) {
		this.plugin = plugin;
		this.plugin.getCommand("suicide").setExecutor(this);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("suicide")) {
			if (sender instanceof Player) {
				Player p = (Player) sender;
				if (!p.getGameMode().equals(GameMode.SURVIVAL)) 
					p.setGameMode(GameMode.SURVIVAL);
				p.damage(2000);
				plugin.getServer().broadcastMessage(p.getName() + " ended his days.");
			}
			else
				sender.sendMessage("This command can only be executed by a player.");
		}
		return false;
	}
}
