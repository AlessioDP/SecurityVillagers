package com.alessiodp.securityvillagers.common.listeners;

import com.alessiodp.core.common.user.User;
import com.alessiodp.securityvillagers.api.events.interfaces.ISecurityVillagersSelectEvent;
import com.alessiodp.securityvillagers.common.SecurityVillagersPlugin;
import com.alessiodp.securityvillagers.common.utils.SecurityVillagersPermission;
import com.alessiodp.securityvillagers.common.configuration.data.ConfigMain;
import com.alessiodp.securityvillagers.common.configuration.data.Messages;
import com.alessiodp.securityvillagers.common.villagers.objects.ProtectedEntity;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class SelectListener {
	@NonNull protected final SecurityVillagersPlugin plugin;
	
	protected boolean onSelect(User user, ProtectedEntity protectedEntity, String item) {
		if (ConfigMain.SELECTION_ITEM.equals(item)
				&& user.hasPermission(SecurityVillagersPermission.ADMIN_SELECT.toString())) {
			// Select villager
			ProtectedEntity oldSelection = plugin.getVillagerManager().getSelectedEntityBy(user.getUUID());
			
			ISecurityVillagersSelectEvent event = plugin.getEventManager().prepareSelectEvent(user, protectedEntity, oldSelection);
			plugin.getEventManager().callEvent(event);
			if (!event.isCancelled()) {
				plugin.getVillagerManager().unselectEntityBySelector(user.getUUID());
				
				if (oldSelection != null && oldSelection.getUuid().equals(protectedEntity.getUuid())) {
					// Unselection
					user.sendMessage(Messages.GENERAL_SELECTION_UNSELECTED, true);
				} else {
					// Selection
					plugin.getVillagerManager().selectEntity(user.getUUID(), protectedEntity);
					user.sendMessage(Messages.GENERAL_SELECTION_SELECTED, true);
				}
			}
		}
		return false;
	}
}
