package com.alessiodp.securityvillagers.common.tasks;

import com.alessiodp.securityvillagers.common.SecurityVillagersPlugin;
import com.alessiodp.securityvillagers.common.configuration.SVConstants;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@RequiredArgsConstructor
public class ProfessionCooldown implements Runnable {
	@NonNull private final SecurityVillagersPlugin plugin;
	@NonNull private final UUID uuid;
	
	@Override
	public void run() {
		plugin.getProfessionCooldown().remove(uuid);
		
		plugin.getLoggerManager().logDebug(SVConstants.DEBUG_TASK_PROFESSION_EXPIRE
				.replace("{uuid}", uuid.toString()), true);
	}
}
