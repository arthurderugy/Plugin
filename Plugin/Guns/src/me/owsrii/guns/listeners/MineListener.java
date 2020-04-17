package me.owsrii.guns.listeners;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Minecart;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerVelocityEvent;
import org.bukkit.event.vehicle.VehicleDamageEvent;
import org.bukkit.event.vehicle.VehicleDestroyEvent;
import org.bukkit.event.vehicle.VehicleEnterEvent;
import org.bukkit.metadata.FixedMetadataValue;

import me.owsrii.guns.Main;

public class MineListener implements Listener {
	private Main plugin;
	
	public MineListener(Main plugin) {
		this.plugin = plugin;
		Bukkit.getPluginManager().registerEvents(this, this.plugin);
	}
	
	@EventHandler
	public void onInteract(PlayerInteractEvent e) {
		if (!(e.getAction() == Action.RIGHT_CLICK_AIR) && !(e.getAction() == Action.RIGHT_CLICK_BLOCK)) return;
		if (e.getItem() == null) return;

		if (e.getItem().getType() != Material.STICK) return;
			
		if (e.getPlayer().getGameMode() == GameMode.SURVIVAL)
			e.getItem().setAmount(e.getItem().getAmount() - 1);
			
		Player p = e.getPlayer();
		Minecart m = (Minecart) p.getWorld().spawnEntity(p.getLocation(), EntityType.MINECART);
		m.setMetadata("owner:" +  p.getName(), new FixedMetadataValue(this.plugin, "owner"));
		m.setMetadata("mine", new FixedMetadataValue(plugin, "mine"));
	}
	
	@EventHandler
	public void onPlayerAcceleration(PlayerVelocityEvent e) {
		e.setCancelled(true);
	}
	
	@EventHandler
	public void onMineHit(VehicleDestroyEvent e) {
		Minecart m = (Minecart) e.getVehicle();
		this.explode(m);
	}
	
	private void explode(Minecart m) {
		Location loc = m.getLocation();
		m.remove();
		m.getWorld().playSound(loc, Sound.BLOCK_STONE_BUTTON_CLICK_ON, 20, 20);
		
		m.getWorld().createExplosion(loc, 4, false, false);
	}
	
	@EventHandler
	public void onEnterMine(VehicleEnterEvent e) {
		e.setCancelled(true);
	}
	
	@EventHandler
	public void onDamaged(VehicleDamageEvent e) {
		e.setCancelled(true);
		Minecart m = (Minecart) e.getVehicle();
		this.explode(m);
	}
}
