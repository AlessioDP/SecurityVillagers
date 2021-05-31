package com.alessiodp.securityvillagers.common.tasks;

import com.alessiodp.securityvillagers.common.SecurityVillagersPlugin;
import com.alessiodp.securityvillagers.common.configuration.SVConstants;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@RequiredArgsConstructor
public class ChangeAgeCooldown implements Runnable {
	@NonNull private final SecurityVillagersPlugin plugin;
	@NonNull private final UUID uuid;
	
	@Override
	public void run() {
		plugin.getChangeAgeCooldown().remove(uuid);
		
		plugin.getLoggerManager().logDebug(String.format(SVConstants.DEBUG_TASK_CHANGEAGE_EXPIRE, uuid.toString()), true);
	}
}
