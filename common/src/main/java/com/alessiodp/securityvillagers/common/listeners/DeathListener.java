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
		if (ConfigMain.GENERAL_PROTECTIONTYPE == ConfigMain.ProtectionType.CUSTOM) {
			// Remove protected entity
			protectedEntity.setProtectionEnabled(false);
			protectedEntity.updateProtectedEntity();
			
			plugin.getLoggerManager().logDebug(SVConstants.DEBUG_DEATH_PROTECTEDENTITY
					.replace("{mob}", protectedEntity.getType().name())
					.replace("{uuid}", protectedEntity.getUuid().toString())
					.replace("{cause}", damageCause), true);
		}
	}
}
