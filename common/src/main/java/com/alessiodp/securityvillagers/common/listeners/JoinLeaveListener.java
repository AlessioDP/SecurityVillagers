package com.alessiodp.securityvillagers.common.listeners;

import com.alessiodp.core.common.user.User;
import com.alessiodp.securityvillagers.common.SecurityVillagersPlugin;
import com.alessiodp.securityvillagers.common.configuration.data.ConfigMain;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class JoinLeaveListener{
	protected final SecurityVillagersPlugin plugin;
	
	protected void onPlayerJoin(User user) {
		// Make it async
		plugin.getScheduler().runAsync(() -> plugin.getLoginAlertsManager().sendAlerts(user));
	}
	
	protected void onPlayerQuit(User user) {
		// Make it async
		plugin.getScheduler().runAsync(() -> {
			if (ConfigMain.SELECTION_CLEAR_SELECTION_ON_QUIT) {
				plugin.getVillagerManager().getSelectedEntities().remove(user.getUUID());
			}
		});
	}
}

