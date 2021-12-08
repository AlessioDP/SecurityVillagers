package com.alessiodp.securityvillagers.bukkit.nms.v1_18_R1;

import com.alessiodp.securityvillagers.bukkit.utils.IDataNMS;
import com.alessiodp.securityvillagers.bukkit.villagers.objects.MobsType;
import org.bukkit.craftbukkit.v1_18_R1.entity.CraftEvoker;
import org.bukkit.craftbukkit.v1_18_R1.entity.CraftEvokerFangs;
import org.bukkit.craftbukkit.v1_18_R1.entity.CraftIllusioner;
import org.bukkit.craftbukkit.v1_18_R1.entity.CraftPillager;
import org.bukkit.craftbukkit.v1_18_R1.entity.CraftRavager;
import org.bukkit.craftbukkit.v1_18_R1.entity.CraftTraderLlama;
import org.bukkit.craftbukkit.v1_18_R1.entity.CraftVex;
import org.bukkit.craftbukkit.v1_18_R1.entity.CraftVindicator;
import org.bukkit.craftbukkit.v1_18_R1.entity.CraftWanderingTrader;
import org.bukkit.craftbukkit.v1_18_R1.entity.CraftZoglin;

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
		MobsType.ZOGLIN.setMobClass(CraftZoglin.class);
	}
}
