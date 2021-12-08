package com.alessiodp.securityvillagers.common.listeners;

import com.alessiodp.securityvillagers.common.SecurityVillagersPlugin;
import com.alessiodp.securityvillagers.common.villagers.objects.ProtectedEntity;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class SpawnListener {
	@NonNull protected final SecurityVillagersPlugin plugin;
	
	protected boolean onSpawn(ProtectedEntity protectedEntity) {
		return plugin.getVillagerManager().isConfigPreventSpawn(
				protectedEntity.getType(),
				protectedEntity.getWorld()
		);
	}
}
