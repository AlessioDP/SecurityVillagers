package com.alessiodp.securityvillagers.bukkit.nms.v1_13_R2;

import com.alessiodp.securityvillagers.bukkit.utils.IDataNMS;
import com.alessiodp.securityvillagers.bukkit.villagers.objects.MobsType;
import org.bukkit.craftbukkit.v1_13_R2.entity.CraftEvoker;
import org.bukkit.craftbukkit.v1_13_R2.entity.CraftEvokerFangs;
import org.bukkit.craftbukkit.v1_13_R2.entity.CraftIllusioner;
import org.bukkit.craftbukkit.v1_13_R2.entity.CraftVex;
import org.bukkit.craftbukkit.v1_13_R2.entity.CraftVindicator;

public class DataNMS implements IDataNMS {
	
	@Override
	public void populateMobs() {
		MobsType.WANDERING_TRADER.setMobClass(null);
		MobsType.TRADER_LLAMA.setMobClass(null);
		
		MobsType.EVOKER.setMobClass(CraftEvoker.class);
		MobsType.EVOKER_FANGS.setMobClass(CraftEvokerFangs.class);
		MobsType.ILLUSIONER.setMobClass(CraftIllusioner.class);
		MobsType.PILLAGER.setMobClass(null);
		MobsType.RAVAGER.setMobClass(null);
		MobsType.VEX.setMobClass(CraftVex.class);
		MobsType.VINDICATOR.setMobClass(CraftVindicator.class);
	}
}
