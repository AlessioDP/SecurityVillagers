package com.alessiodp.securityvillagers.bukkit.utils;

import org.bukkit.Material;
import org.bukkit.entity.Player;

public interface IDataNMS {
	
	void populateMobs();
	
	default Material getHandMaterial(Player player) {
		return player.getInventory().getItemInMainHand().getType();
	}
	
	default boolean existOffHand() {
		return true;
	}
	
	default Material getVillagerEgg() {
		return Material.VILLAGER_SPAWN_EGG;
	}
	
	default boolean isHarvestable(Material material) {
		return material == Material.WHEAT
				|| material == Material.POTATOES
				|| material == Material.CARROTS
				|| material == Material.BEETROOTS;
	}
}
