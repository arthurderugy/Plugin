package me.owsrii.guns.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.owsrii.guns.Main;
import me.owsrii.guns.items.GunItem;
import me.owsrii.guns.utils.Utils;

public class GunCommand implements CommandExecutor {
	private Main plugin;
	
	public GunCommand(Main plugin) {
		this.plugin = plugin;
		this.plugin.getCommand("gun").setExecutor(this);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)) return true;
		if (!cmd.getName().equalsIgnoreCase("gun")) return true;
		Player p = (Player) sender;
		Utils.createItem(p.getInventory(), GunItem.getMaterial(), 1, 9*4, GunItem.getName(), GunItem.getLore());
		return false;
	}

}
