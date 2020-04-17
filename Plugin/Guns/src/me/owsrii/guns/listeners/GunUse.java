package me.owsrii.guns.listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.metadata.FixedMetadataValue;

import me.owsrii.guns.Main;
import me.owsrii.guns.items.GunItem;
import me.owsrii.guns.utils.Utils;

public class GunUse implements Listener {
	private Main plugin;
	
	public GunUse(Main plugin) {
		this.plugin = plugin;
		Bukkit.getPluginManager().registerEvents(this, this.plugin);
	}
	
	@EventHandler
	public void onGunUse(PlayerInteractEvent e) {		
		if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
			if (e.getItem() != null && 
					e.getItem().getType() == GunItem.getMaterial() &&
					Utils.compare(e.getItem().getItemMeta().getDisplayName(), GunItem.getName())) {
				Player p = e.getPlayer();
				Snowball bullet = p.launchProjectile(Snowball.class);
				bullet.setGravity(false);
				bullet.setVelocity(bullet.getVelocity().multiply(1000));
				bullet.setMetadata("bullet", new FixedMetadataValue(this.plugin, "bullet"));
			}
		}
	}
	
	@EventHandler
	public void onBulletHitEntity(EntityDamageByEntityEvent e) {
		if (!(e.getDamager() instanceof Snowball)) return;
		Snowball s = (Snowball) e.getDamager();
		if (!(s.getShooter() instanceof Player)) return;
		Player shooter = (Player) s.getShooter();
		if (!s.hasMetadata("bullet")) return;
		if (e.getEntity() instanceof LivingEntity) {
			LivingEntity ent = (LivingEntity) e.getEntity();
			if (ent instanceof Player) {
				Player p = (Player) e.getEntity();
				if (p.getUniqueId() == shooter.getUniqueId()) return;
			}
			ent.damage(100);
		}
	}
}
