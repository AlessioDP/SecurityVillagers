package com.alessiodp.securityvillagers.common.listeners;

import com.alessiodp.securityvillagers.common.SecurityVillagersPlugin;
import com.alessiodp.securityvillagers.common.configuration.data.ConfigMain;
import com.alessiodp.securityvillagers.common.utils.WorldUtils;
import com.alessiodp.securityvillagers.common.villagers.objects.ProtectedEntity;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class FarmListener {
	protected final SecurityVillagersPlugin plugin;
	
	protected boolean onVillagerHarvesting(ProtectedEntity protectedEntity) {
		return ConfigMain.GENERAL_FARMING_HARVESTING && WorldUtils.containsWorld(ConfigMain.GENERAL_FARMING_WORLDS, protectedEntity.getWorld());
	}
	
	protected boolean onVillagerPlant(ProtectedEntity protectedEntity) {
		return ConfigMain.GENERAL_FARMING_PLANTING && WorldUtils.containsWorld(ConfigMain.GENERAL_FARMING_WORLDS, protectedEntity.getWorld());
	}
}
