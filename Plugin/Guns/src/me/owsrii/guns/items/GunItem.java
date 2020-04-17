package me.owsrii.guns.items;

import org.bukkit.Material;

import me.owsrii.guns.utils.Utils;

public class GunItem {
	private final static String name = Utils.chat("&4Deagle");
	private final static Material material = Material.matchMaterial("wood_sword");
	private final static String[] lore = new String[] {"&5This is a deagle", "&7&uEven if it doesn't look like a deagle."};
	
	public static String getName() {
		return name;
	}
	
	public static Material getMaterial() {
		return material;
	}
	
	public static String[] getLore() {
		return lore;
	}
}
