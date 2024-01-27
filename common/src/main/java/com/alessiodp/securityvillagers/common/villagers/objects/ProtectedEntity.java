package com.alessiodp.securityvillagers.common.villagers.objects;

import com.alessiodp.core.common.user.User;
import com.alessiodp.securityvillagers.api.enums.ProtectedEntityType;
import com.alessiodp.securityvillagers.common.SecurityVillagersPlugin;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@RequiredArgsConstructor
public abstract class ProtectedEntity {
	@NonNull protected final SecurityVillagersPlugin plugin;
	@NonNull @Getter private ProtectedEntityType type;
	@Getter @Setter private boolean protectionEnabled = false;
	
	public void updateProtectedEntity() {
		plugin.getDatabaseManager().updateProtectedEntity(this);
		
		// Update protected entities list
		if (isProtectionEnabled()) {
			plugin.getVillagerManager().getProtectedEntities().add(getUuid());
		} else {
			plugin.getVillagerManager().getProtectedEntities().remove(getUuid());
		}
	}
	
	public abstract UUID getUuid();
	public abstract void setCustomName(String name);
	
	public abstract String getWorld();
	public abstract Object getEntity();
	
	public abstract void teleportTo(User user);
	
	// Only Villager/Wanderer trader
	public abstract boolean isAgeable();
	public abstract boolean isAdult();
	public abstract void setToAdult();
	public abstract void setToBaby();
	
	// Only Villager
	public abstract boolean haveProfession();
	public abstract VillagerProfession getProfession();
	public abstract boolean setProfession(VillagerProfession profession);
}
