package com.alessiodp.securityvillagers.bukkit.nms.v1_8_R3;

import com.alessiodp.securityvillagers.bukkit.utils.IDataNMS;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class DataNMS implements IDataNMS {
	
	@Override
	public void populateMobs() {
		// Nothing to do
	}
	
	@Override
	public Material getHandMaterial(Player player) {
		return player.getItemInHand().getType();
	}
	
	@Override
	public boolean existOffHand() {
		return false;
	}
	
	@Override
	public Material getVillagerEgg() {
		return Material.MONSTER_EGG;
	}
	
	@Override
	public boolean isHarvestable(Material material) {
		return material == Material.WHEAT
				|| material == Material.POTATO
				|| material == Material.CARROT;
	}
}
