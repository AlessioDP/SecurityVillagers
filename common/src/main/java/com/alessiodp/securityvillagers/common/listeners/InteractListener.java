package com.alessiodp.securityvillagers.common.listeners;

import com.alessiodp.core.common.user.User;
import com.alessiodp.securityvillagers.common.SecurityVillagersPlugin;
import com.alessiodp.securityvillagers.common.commands.utils.SecurityVillagersPermission;
import com.alessiodp.securityvillagers.common.configuration.data.ConfigMain;
import com.alessiodp.securityvillagers.common.configuration.data.Messages;
import com.alessiodp.securityvillagers.common.villagers.objects.ProtectedEntity;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class InteractListener {
	protected final SecurityVillagersPlugin plugin;
	
	protected boolean onPlayerInteractEntity(User user, ProtectedEntity protectedEntity, String item) {
		boolean ret = false;
		if (protectedEntity.isConfigPreventInteract()) {
			if (ConfigMain.GENERAL_INTERACT_EGG
					&& !user.hasPermission(SecurityVillagersPermission.USER_EGG.toString())
					&& isEgg(item)) {
				// Egg
				if (isInteractProtected(user, protectedEntity)) {
					user.sendMessage(Messages.GENERAL_INTERACT_EGG, true);
					ret = true;
				}
			} else if (ConfigMain.GENERAL_INTERACT_TRADE
					&& !user.hasPermission(SecurityVillagersPermission.USER_TRADE.toString())) {
				// Trade
				if (isInteractProtected(user, protectedEntity)) {
					user.sendMessage(Messages.GENERAL_INTERACT_TRADE, true);
					ret = true;
				}
			}
		}
		return ret;
	}
	
	private boolean isInteractProtected(User user, ProtectedEntity protectedEntity) {
		return (ConfigMain.GENERAL_INTERACT_WORLDS.contains("*")
				|| ConfigMain.GENERAL_INTERACT_WORLDS.contains(protectedEntity.getWorld()))
				&& isFactionProtected(user, protectedEntity);
	}
	
	protected abstract boolean isEgg(String material);
	
	protected abstract boolean isFactionProtected(User user, ProtectedEntity protectedEntity);
}
