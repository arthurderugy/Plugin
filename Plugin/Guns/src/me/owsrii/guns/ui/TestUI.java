package me.owsrii.guns.ui;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import me.owsrii.guns.utils.Utils;

public class TestUI {
	public static Inventory inv;
	public static String itemName = "&cAcacia boat";
	public static int slot = 2 * 9 + 5;
	public static int amount = 1;
	public static String inventoryName;
	public static int invRows = 5 * 9;
	
	public static void initialize() {
		inventoryName = Utils.chat("&6&lTest GUI");
		inv = Bukkit.createInventory(null, invRows);
	}
	
	public static Inventory GUI(Player p) {
		Inventory toReturn = Bukkit.createInventory(null, invRows, inventoryName);
		//Utils.createItem(inv, "wood_sword", amount, slot, itemName, Utils.chat("&6This boat has an history."), Utils.chat("&6Lemme tell you"));
		toReturn.setContents(inv.getContents());
		return toReturn;
	}
	
	public static void clicked(Player p, int slot, ItemStack clicked, Inventory inv) {
		if (clicked.getItemMeta().getDisplayName().equalsIgnoreCase(Utils.chat(itemName))) {
			p.sendMessage(Utils.chat("&6&lMon premier GUI !"));
		}
	}
}
