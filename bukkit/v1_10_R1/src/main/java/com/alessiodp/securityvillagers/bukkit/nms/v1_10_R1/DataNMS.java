package com.alessiodp.securityvillagers.bukkit.nms.v1_10_R1;

import com.alessiodp.securityvillagers.bukkit.utils.IDataNMS;
import org.bukkit.Material;

public class DataNMS implements IDataNMS {
	
	@Override
	public void populateMobs() {
		// Nothing to do
	}
	
	@Override
	public Material getVillagerEgg() {
		return Material.MONSTER_EGG;
	}
	
	@Override
	public boolean isHarvestable(Material material) {
		return material == Material.WHEAT
				|| material == Material.POTATO
				|| material == Material.CARROT
				|| material == Material.BEETROOT;
	}
}
