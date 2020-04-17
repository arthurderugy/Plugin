package me.owsrii.guns.commands;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

import me.owsrii.guns.Main;
import me.owsrii.guns.utils.Utils;

public class NearCommand implements CommandExecutor{
	private Main plugin;
	private List<Player> nearbyPlayers = new ArrayList<>();
	
	public NearCommand(Main plugin) {
		this.plugin = plugin;
		this.plugin.getCommand("near").setExecutor(this);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)) return true;
		if (!cmd.getName().equalsIgnoreCase("near")) return true;
		Player p = (Player) sender;
		
		for (Entity e : p.getWorld().getEntities()) 
			if (getDistance(p, e) < 100 && e.getType() == EntityType.PLAYER && !((Player) e).getUniqueId().equals(p.getUniqueId()))
				this.nearbyPlayers.add((Player) e);
				
		StringBuilder output = new StringBuilder();
		output.append("&cNearby players: ");
		
		if (!this.nearbyPlayers.isEmpty()) {
			for (Player nearbyPlayer : this.nearbyPlayers)
				output.append(nearbyPlayer.getDisplayName()).append(" &7(").append(this.getDistance(nearbyPlayer, p)).append("), ");
			
			output.deleteCharAt(output.length() - 1).deleteCharAt(output.length() - 1);
		}
		p.sendMessage(Utils.chat(output.toString()));
		this.nearbyPlayers.clear();
		return false;
	}
	
	public int getDistance(Entity e, Entity p) {
		return (int) Math.sqrt((
				(e.getLocation().getX() - p.getLocation().getX()) * (e.getLocation().getX() - p.getLocation().getX()) + 
				(e.getLocation().getZ() - p.getLocation().getZ()) * (e.getLocation().getZ() - p.getLocation().getZ())
				));
	}
}
