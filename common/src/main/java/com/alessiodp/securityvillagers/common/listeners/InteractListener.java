package com.alessiodp.securityvillagers.common.listeners;

import com.alessiodp.core.common.user.User;
import com.alessiodp.securityvillagers.api.enums.InteractType;
import com.alessiodp.securityvillagers.api.events.interfaces.ISecurityVillagersInteractProtectionEvent;
import com.alessiodp.securityvillagers.common.SecurityVillagersPlugin;
import com.alessiodp.securityvillagers.common.utils.SecurityVillagersPermission;
import com.alessiodp.securityvillagers.common.configuration.data.ConfigMain;
import com.alessiodp.securityvillagers.common.configuration.data.Messages;
import com.alessiodp.securityvillagers.common.utils.WorldUtils;
import com.alessiodp.securityvillagers.common.villagers.objects.ProtectedEntity;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class InteractListener {
	protected final SecurityVillagersPlugin plugin;
	
	protected boolean onPlayerInteractEntity(User user, ProtectedEntity protectedEntity, String item) {
		boolean ret = false;
		if (plugin.getVillagerManager().isConfigPreventInteract(protectedEntity.getType())) {
			if (ConfigMain.GENERAL_INTERACT_EGG
					&& !user.hasPermission(SecurityVillagersPermission.USER_EGG.toString())
					&& isEgg(item)) {
				// Egg
				if (isInteractProtected(user, protectedEntity)) {
					ISecurityVillagersInteractProtectionEvent event = plugin.getEventManager().prepareInteractEvent(protectedEntity, user, InteractType.EGG);
					plugin.getEventManager().callEvent(event);
					if (!event.isCancelled()) {
						user.sendMessage(Messages.GENERAL_INTERACT_EGG, true);
						ret = true;
					}
				}
			} else if (ConfigMain.GENERAL_INTERACT_TRADE
					&& !user.hasPermission(SecurityVillagersPermission.USER_TRADE.toString())) {
				// Trade
				if (isInteractProtected(user, protectedEntity)) {
					ISecurityVillagersInteractProtectionEvent event = plugin.getEventManager().prepareInteractEvent(protectedEntity, user, InteractType.TRADE);
					plugin.getEventManager().callEvent(event);
					if (!event.isCancelled()) {
						user.sendMessage(Messages.GENERAL_INTERACT_TRADE, true);
						ret = true;
					}
				}
			}
		}
		return ret;
	}
	
	private boolean isInteractProtected(User user, ProtectedEntity protectedEntity) {
		return WorldUtils.containsWorld(ConfigMain.GENERAL_INTERACT_WORLDS, protectedEntity.getWorld())
				&& (ConfigMain.GENERAL_PROTECTIONTYPE != ConfigMain.ProtectionType.FACTIONS || isFactionProtected(user, protectedEntity))
				&& (!ConfigMain.GENERAL_CITIZENS_ENABLE || !ConfigMain.GENERAL_CITIZENS_BYPASS_INTERACT || !isNPCProtected(protectedEntity));
	}
	
	protected abstract boolean isEgg(String material);
	
	protected abstract boolean isFactionProtected(User user, ProtectedEntity protectedEntity);
	
	protected abstract boolean isNPCProtected(ProtectedEntity protectedEntity);
}
