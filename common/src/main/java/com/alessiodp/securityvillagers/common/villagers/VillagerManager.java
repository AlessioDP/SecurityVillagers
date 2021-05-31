package com.alessiodp.securityvillagers.common.villagers;

import com.alessiodp.securityvillagers.common.SecurityVillagersPlugin;
import com.alessiodp.securityvillagers.common.configuration.data.ConfigMain;
import com.alessiodp.securityvillagers.common.villagers.objects.ProtectedEntity;
import com.alessiodp.securityvillagers.common.villagers.objects.ProtectedEntityType;
import lombok.Getter;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public abstract class VillagerManager {
	protected final SecurityVillagersPlugin plugin;
	
	@Getter private ArrayList<UUID> protectedEntities;
	@Getter private final HashMap<UUID, ProtectedEntity> selectedEntities;
	
	public VillagerManager(@NonNull SecurityVillagersPlugin plugin) {
		this.plugin = plugin;
		protectedEntities = new ArrayList<>();
		selectedEntities = new HashMap<>();
	}
	
	public void reload() {
		protectedEntities.clear();
		if (ConfigMain.GENERAL_PROTECTIONTYPE == ConfigMain.ProtectionType.CUSTOM)
			protectedEntities = plugin.getDatabaseManager().getAllProtectedEntities();
	}
	
	public void selectEntity(UUID selector, ProtectedEntity entity) {
		selectedEntities.put(selector, entity);
	}
	
	public void unselectEntity(ProtectedEntity entity) {
		selectedEntities.values().removeIf(pe -> pe.getUuid().equals(entity.getUuid()));
	}
	
	public void unselectEntityBySelector(UUID selector) {
		selectedEntities.remove(selector);
	}
	
	public ProtectedEntity getSelectedEntityBy(UUID selector) {
		return selectedEntities.get(selector);
	}
	
	public abstract ProtectedEntity initializeProtectedEntity(Object protectedEntity);
	
	public abstract ProtectedEntityType getEntityType(Object entity);
	
	public abstract AttackResult canBeAttacked(ProtectedEntity protectedEntity, Object attacker);
	
	public abstract boolean canBeDamaged(ProtectedEntity protectedEntity, Object damageCause);
	
	public enum AttackResult {
		SUCCESS, HIT, SHOOT;
		
		public boolean isSuccess() {
			return this.equals(SUCCESS);
		}
		public boolean isHit() {
			return this.equals(HIT);
		}
		public boolean isShoot() {
			return this.equals(SHOOT);
		}
	}
}
