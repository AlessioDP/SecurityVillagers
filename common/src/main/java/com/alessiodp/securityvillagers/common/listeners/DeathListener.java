package com.alessiodp.securityvillagers.common.listeners;

import com.alessiodp.securityvillagers.common.SecurityVillagersPlugin;
import com.alessiodp.securityvillagers.common.configuration.SVConstants;
import com.alessiodp.securityvillagers.common.configuration.data.ConfigMain;
import com.alessiodp.securityvillagers.common.villagers.objects.ProtectedEntity;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class DeathListener {
	@NonNull
	protected final SecurityVillagersPlugin plugin;
	
	protected void onDeath(ProtectedEntity protectedEntity, String damageCause) {
		// Unselect the entity
		plugin.getVillagerManager().unselectEntity(protectedEntity);
		
		if (ConfigMain.GENERAL_PROTECTIONTYPE == ConfigMain.ProtectionType.CUSTOM) {
			// Remove protected entity
			protectedEntity.setProtectionEnabled(false);
			protectedEntity.updateProtectedEntity();
			
			plugin.getLoggerManager().logDebug(String.format(SVConstants.DEBUG_DEATH_PROTECTEDENTITY,
					protectedEntity.getType().name(),
					protectedEntity.getUuid().toString(),
					damageCause), true);
		}
	}
}
