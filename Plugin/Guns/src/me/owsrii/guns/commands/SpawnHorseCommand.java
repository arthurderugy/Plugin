package me.owsrii.guns.commands;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Horse;
import org.bukkit.entity.Horse.Color;
import org.bukkit.entity.Horse.Style;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import me.owsrii.guns.Main;
import me.owsrii.guns.utils.Utils;

public class SpawnHorseCommand implements CommandExecutor {
	private Main plugin;
	
	public SpawnHorseCommand(Main plugin) {
		this.plugin = plugin;
		this.plugin.getCommand("spawnhorse").setExecutor(this);
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!cmd.getName().equalsIgnoreCase("spawnhorse")) return false;
		if (!(sender instanceof Player)) return false;
		
		String name = null;
		boolean isGlowing = false;
		Color color = Color.WHITE;
		float speed = 80;
		int health = 20;
		
		if (args.length != 0) {
			for (String tag : args) {
				String s = tag.toLowerCase();
				
				if (s.startsWith("name:")) 
					name = tag.substring(s.indexOf(":") + 1);
				
				else if (s.startsWith("glowing:"))
					isGlowing = s.endsWith("true");
				
				else if (s.startsWith("color:")) {
					switch (s.substring(s.indexOf(":") + 1).toUpperCase()) {
					case "WHITE":
						color = Color.WHITE;
						break;
					case "BROWN":
						color = Color.BROWN;
						break;
					case "GRAY":
						color = Color.GRAY;
						break;
					case "CHESTNUT":
						color = Color.CHESTNUT;
						break;
					case "CREAMY":
 						color = Color.CREAMY;
 						break;
					case "DARK_BROWN":
						color = Color.DARK_BROWN;
						break;
 					default: 
 						sender.sendMessage(Utils.chat(this.plugin.getConfig().getString("spawn_horse_illegal_color")));
 						return true;
					}
				}
				
				else if (s.startsWith("speed")) {
					try {
						speed = Float.parseFloat(s.substring(s.indexOf(":") + 1));
						if (health > 100) {
							throw new IllegalArgumentException();
						}
					}
					catch (Exception e) {
						sender.sendMessage(Utils.chat(this.plugin.getConfig().getString("spawn_horse_illegal_speed_argument")));
						return true;
					}
				}
				
				else if (s.startsWith("health:")) {
					try {
						health = Integer.parseInt(s.substring(s.indexOf(":") + 1));
						if (health == 0 || health > 27) {
							throw new IllegalArgumentException();
						}
					}
					catch (Exception e) {
						sender.sendMessage(Utils.chat(this.plugin.getConfig().getString("spawn_horse_illegal_health_argument")));
						return true;
					}
				}
				
				
				else if (s.equalsIgnoreCase("help")) {
					String shh = "spawn_horse_help"; 
					for (int i = 1; i <= 12; i++) {
						sender.sendMessage(Utils.chat(this.plugin.getConfig().getString(shh + i)));
					}
					return true;
				}
				
				else  {
					sender.sendMessage(Utils.chat(this.plugin.getConfig().getString("spawn_horse_illegal_tag").replace("<tag>", s.substring(0, s.indexOf(":") + 1))));
					return true;
				}
			}
		}
		else {
			sender.sendMessage(Utils.chat(this.plugin.getConfig().getString("spawn_horse_invalid_use_of_command")));
			return true;
		}
		name = Utils.chat(name);
		
		Player p = (Player) sender;
		if (name != null)
			this.spawnHorse(p, name, health, speed, isGlowing, color);
		else
			sender.sendMessage(Utils.chat(this.plugin.getConfig().getString("spawn_horse_no_name_specified")));
		return true;
	}
	
	private void spawnHorse(Player p, String name, int health, float speed, boolean isGlowing, Color color) {
		Entity horse = p.getWorld().spawnEntity(p.getLocation(), EntityType.HORSE);
		Bukkit.broadcastMessage(Utils.chat(plugin.getConfig().getString("spawnHorse_message")
				.replaceAll("<player>", p.getName())
				.replaceAll("<horsename>", name)));
		
		horse.setCustomName(Utils.chat(name));
		horse.setCustomNameVisible(true);
		
		Horse h = (Horse) horse;
		h.setStyle(Style.WHITE);
		h.setColor(color);
		h.setAdult();
		h.setGlowing(isGlowing);
		h.setTamed(true);
		h.getInventory().setSaddle(new ItemStack(Material.SADDLE));
		//h.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(health);
		h.setHealth(health);
		h.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(speed / 100);;
		h.getAttribute(Attribute.HORSE_JUMP_STRENGTH).setBaseValue(1.2f);
		h.getAttribute(Attribute.GENERIC_ARMOR).setBaseValue(10);
		h.setAgeLock(true);
	}
}
