package me.owsrii.guns.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.owsrii.guns.Main;
import me.owsrii.guns.ui.TestUI;

public class OpenGuiCommand implements CommandExecutor {
	private Main plugin;
	
	public OpenGuiCommand(Main plugin) {
		this.plugin = plugin;
		this.plugin.getCommand("opengui").setExecutor(this);
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)) return true;
		if (!cmd.getName().equalsIgnoreCase("opengui")) return true;
		Player p = (Player) sender;
		p.openInventory(TestUI.GUI(p));
		return true;
	}
	
}
