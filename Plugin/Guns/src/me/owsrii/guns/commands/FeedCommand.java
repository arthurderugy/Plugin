package me.owsrii.guns.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.owsrii.guns.Main;
import me.owsrii.guns.utils.Utils;

public class FeedCommand implements CommandExecutor {
	private Main plugin;
	
	public FeedCommand(Main plugin)  {
		this.plugin = plugin;
		this.plugin.getCommand("feed").setExecutor(this);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)) return true;
		if (!cmd.getName().equalsIgnoreCase("feed")) return true;
		Player p = (Player) sender;
		p.setSaturation(100);
		p.setFoodLevel(20);
		p.sendMessage(Utils.chat(this.plugin.getConfig().getString("feed_command_successfull")));
		return true;
	}
}
