package me.owsrii.guns.commands;

import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.owsrii.guns.Main;
import me.owsrii.guns.utils.Utils;

public class NickCommand implements CommandExecutor {
	private Main plugin;
	
	public NickCommand(Main plugin) {
		this.plugin = plugin;
		this.plugin.getCommand("nick").setExecutor(this);
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!cmd.getName().equalsIgnoreCase("nick")) return true;
		if (!(sender instanceof Player)) return true;
		
		Player p = (Player) sender;
		if (args.length != 1) 
			p.sendMessage(Utils.chat(this.plugin.getConfig().getString("nick_command_invalid_use")));
		else {
			for (OfflinePlayer offlinePlayer : this.plugin.getServer().getOfflinePlayers()) {
				if (offlinePlayer.getName().equalsIgnoreCase(args[0])) {
					p.sendMessage(this.plugin.getConfig().getString("nick_command_nick_is_player_name"));
					return true;
				}
			}
			for (Player player : this.plugin.getServer().getOnlinePlayers()) {
				if (player.getName().equalsIgnoreCase(args[0]) && !player.getName().equals(p.getName())) {
					p.sendMessage(this.plugin.getConfig().getString("nick_command_nick_is_player_name"));
					return true;
				}
			}
			p.setDisplayName(Utils.chat(args[0]));
			p.sendMessage(Utils.chat(plugin.getConfig().getString("nick_command_successful").replace("<player_nick>", p.getDisplayName())));
		}
		return false;
	}

}
