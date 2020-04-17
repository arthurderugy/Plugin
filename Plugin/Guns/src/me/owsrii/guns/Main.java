package me.owsrii.guns;

import org.bukkit.Bukkit;
import org.bukkit.permissions.Permission;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import me.owsrii.guns.commands.AfkCommand;
import me.owsrii.guns.commands.FeedCommand;
import me.owsrii.guns.commands.FlyCommand;
import me.owsrii.guns.commands.GodCommand;
import me.owsrii.guns.commands.GunCommand;
import me.owsrii.guns.commands.NearCommand;
import me.owsrii.guns.commands.NickCommand;
import me.owsrii.guns.commands.OpenGuiCommand;
import me.owsrii.guns.commands.SpawnHorseCommand;
import me.owsrii.guns.commands.SuicideCommand;
import me.owsrii.guns.listeners.GunUse;
import me.owsrii.guns.listeners.InventoryClickListener;
import me.owsrii.guns.listeners.JoinListener;
import me.owsrii.guns.listeners.MineListener;
import me.owsrii.guns.listeners.PlayerEventListener;
import me.owsrii.guns.listeners.QuitListener;
import me.owsrii.guns.ui.TestUI;

public class Main extends JavaPlugin {
	String[] permissions = new String[] {"guns.gun.use", "guns.fly", "guns.feed", "guns.near", "guns.*"};
	
	@Override
	public void onEnable() {
		saveDefaultConfig();
		TestUI.initialize();
		this.registerCommands();
		this.registerListeners();
	}
	
	public void registerListeners() {
		new InventoryClickListener(this);
		new JoinListener(this);
		new QuitListener(this);
		new PlayerEventListener(this);
		new GunUse(this);
		new MineListener(this);
	}
	
	public void registerCommands() {
		new SuicideCommand(this);
		new SpawnHorseCommand(this);
		new AfkCommand(this);
		new NickCommand(this);
		new FlyCommand(this);
		new GodCommand(this);
		new FeedCommand(this);
		new OpenGuiCommand(this);
		new NearCommand(this);
		new GunCommand(this);
	}
	
	public void registerPermissions() {
		PluginManager pm = Bukkit.getPluginManager();
		for (String s : permissions) {
			pm.addPermission(new Permission(s));
		}
	}
}
