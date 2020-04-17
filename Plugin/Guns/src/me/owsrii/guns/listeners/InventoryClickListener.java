package me.owsrii.guns.listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import me.owsrii.guns.Main;
import me.owsrii.guns.ui.TestUI;

public class InventoryClickListener implements Listener {
	private Main plugin;
	
	public InventoryClickListener(Main plugin) {
		this.plugin = plugin;
		Bukkit.getPluginManager().registerEvents(this, this.plugin);
	}
	
	@EventHandler
	public void onClick(InventoryClickEvent e) {
		String title = e.getView().getTitle();
		if (title.equals(TestUI.inventoryName)) {
			e.setCancelled(true);
			if (e.getCurrentItem() == null) {
				return;
			}
			Player p = (Player) e.getWhoClicked();
			TestUI.clicked(p, e.getSlot(), e.getCurrentItem(), e.getInventory());
			p.closeInventory();
		}
	}
}
