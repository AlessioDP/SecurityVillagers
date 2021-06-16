package com.alessiodp.securityvillagers.bukkit.nms.v1_12_R1;

import com.alessiodp.securityvillagers.bukkit.utils.IDataNMS;
import com.alessiodp.securityvillagers.bukkit.villagers.objects.MobsType;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftEvoker;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftEvokerFangs;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftStray;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftVex;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftVindicator;

public class DataNMS implements IDataNMS {
	
	@Override
	public void populateMobs() {
		MobsType.EVOKER.setMobClass(CraftEvoker.class);
		MobsType.EVOKER_FANGS.setMobClass(CraftEvokerFangs.class);
		MobsType.STRAY.setMobClass(CraftStray.class);
		MobsType.VEX.setMobClass(CraftVex.class);
		MobsType.VINDICATOR.setMobClass(CraftVindicator.class);
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
