package com.alessiodp.securityvillagers.bukkit.nms.v1_16_R3;

import com.alessiodp.securityvillagers.bukkit.utils.IDataNMS;
import com.alessiodp.securityvillagers.bukkit.villagers.objects.MobsType;
import org.bukkit.craftbukkit.v1_16_R3.entity.CraftEvoker;
import org.bukkit.craftbukkit.v1_16_R3.entity.CraftEvokerFangs;
import org.bukkit.craftbukkit.v1_16_R3.entity.CraftIllusioner;
import org.bukkit.craftbukkit.v1_16_R3.entity.CraftPillager;
import org.bukkit.craftbukkit.v1_16_R3.entity.CraftRavager;
import org.bukkit.craftbukkit.v1_16_R3.entity.CraftTraderLlama;
import org.bukkit.craftbukkit.v1_16_R3.entity.CraftVex;
import org.bukkit.craftbukkit.v1_16_R3.entity.CraftVindicator;
import org.bukkit.craftbukkit.v1_16_R3.entity.CraftWanderingTrader;

public class DataNMS implements IDataNMS {
	
	@Override
	public void populateMobs() {
		MobsType.WANDERING_TRADER.setMobClass(CraftWanderingTrader.class);
		MobsType.TRADER_LLAMA.setMobClass(CraftTraderLlama.class);
		
		MobsType.EVOKER.setMobClass(CraftEvoker.class);
		MobsType.EVOKER_FANGS.setMobClass(CraftEvokerFangs.class);
		MobsType.ILLUSIONER.setMobClass(CraftIllusioner.class);
		MobsType.PILLAGER.setMobClass(CraftPillager.class);
		MobsType.RAVAGER.setMobClass(CraftRavager.class);
		MobsType.VEX.setMobClass(CraftVex.class);
		MobsType.VINDICATOR.setMobClass(CraftVindicator.class);
	}
}
