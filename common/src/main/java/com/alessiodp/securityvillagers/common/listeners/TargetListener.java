package com.alessiodp.securityvillagers.common.listeners;

import com.alessiodp.securityvillagers.common.SecurityVillagersPlugin;
import com.alessiodp.securityvillagers.common.configuration.data.ConfigMain;
import com.alessiodp.securityvillagers.common.utils.WorldUtils;
import com.alessiodp.securityvillagers.common.villagers.objects.ProtectedEntity;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class TargetListener {
	@NonNull protected final SecurityVillagersPlugin plugin;
	
	protected boolean onTarget(ProtectedEntity protectedEntity, Object targeter) {
		boolean ret = false;
		if (WorldUtils.containsWorld(ConfigMain.GENERAL_TARGET_WORLDS, protectedEntity.getWorld())) {
			ret = !plugin.getVillagerManager().canBeAttacked(protectedEntity, targeter).isSuccess();
		}
		return ret;
	}
	
	protected boolean isTargetProtected() {
		// Used to do not load mobs if disabled
		return ConfigMain.GENERAL_TARGET_TARGET;
	}
}
