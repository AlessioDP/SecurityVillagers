package com.alessiodp.securityvillagers.common.villagers;

import com.alessiodp.securityvillagers.api.enums.AttackResult;
import com.alessiodp.securityvillagers.api.enums.ProtectedEntityType;
import com.alessiodp.securityvillagers.common.SecurityVillagersPlugin;
import com.alessiodp.securityvillagers.common.configuration.data.ConfigMain;
import com.alessiodp.securityvillagers.common.utils.WorldUtils;
import com.alessiodp.securityvillagers.common.villagers.objects.ProtectedEntity;
import lombok.Getter;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
	
	public abstract ProtectedEntityType getEntityType(UUID uuid);
	
	public abstract ProtectedEntityType getEntityType(Object entity);
	
	public abstract AttackResult canBeAttacked(ProtectedEntity protectedEntity, Object attacker);
	
	public abstract boolean canBeDamaged(ProtectedEntity protectedEntity, Object damageCause);
	
	public boolean isConfigProtected(ProtectedEntityType protectedEntityType) {
		boolean ret = false;
		switch (protectedEntityType) {
			case VILLAGER:
				ret = ConfigMain.MOBS_VILLAGER_PROTECT;
				break;
			case WANDERING_TRADER:
				ret = ConfigMain.MOBS_WANDERINGTRADER_PROTECT;
				break;
			case TRADER_LLAMA:
				ret = ConfigMain.MOBS_TRADERLLAMA_PROTECT;
				break;
			case IRON_GOLEM:
				ret = ConfigMain.MOBS_IRONGOLEM_PROTECT;
				break;
			case EVOKER:
				ret = ConfigMain.MOBS_ILLAGER_EVOKER_PROTECT;
				break;
			case ILLUSIONER:
				ret = ConfigMain.MOBS_ILLAGER_ILLUSIONER_PROTECT;
				break;
			case PILLAGER:
				ret = ConfigMain.MOBS_ILLAGER_PILLAGER_PROTECT;
				break;
			case RAVAGER:
				ret = ConfigMain.MOBS_ILLAGER_RAVAGER_PROTECT;
				break;
			case VEX:
				ret = ConfigMain.MOBS_ILLAGER_VEX_PROTECT;
				break;
			case VINDICATOR:
				ret = ConfigMain.MOBS_ILLAGER_VINDICATOR_PROTECT;
				break;
			case WITCH:
				ret = ConfigMain.MOBS_ILLAGER_WITCH_PROTECT;
				break;
			default:
				// Not supported
				break;
		}
		return ret;
	}
	
	public boolean isConfigPreventInteract(ProtectedEntityType protectedEntityType) {
		boolean ret = false;
		switch (protectedEntityType) {
			case VILLAGER:
				ret = ConfigMain.MOBS_VILLAGER_PREVENT_INTERACT;
				break;
			case WANDERING_TRADER:
				ret = ConfigMain.MOBS_WANDERINGTRADER_PREVENT_INTERACT;
				break;
			case TRADER_LLAMA:
				ret = ConfigMain.MOBS_TRADERLLAMA_PREVENT_INTERACT;
				break;
			default:
				// Nothing to do
				break;
		}
		return ret;
	}
	
	public final boolean isConfigPreventSpawn(ProtectedEntityType protectedEntityType, String world) {
		boolean ret = false;
		List<String> worlds = new ArrayList<>();
		switch (protectedEntityType) {
			case VILLAGER:
				ret = ConfigMain.MOBS_VILLAGER_SPAWN_PREVENT;
				worlds = ConfigMain.MOBS_VILLAGER_SPAWN_WORLDS;
				break;
			case WANDERING_TRADER:
				ret = ConfigMain.MOBS_WANDERINGTRADER_SPAWN_PREVENT;
				worlds = ConfigMain.MOBS_WANDERINGTRADER_SPAWN_WORLDS;
				break;
			case TRADER_LLAMA:
				ret = ConfigMain.MOBS_TRADERLLAMA_SPAWN_PREVENT;
				worlds = ConfigMain.MOBS_TRADERLLAMA_SPAWN_WORLDS;
				break;
			case IRON_GOLEM:
				ret = ConfigMain.MOBS_IRONGOLEM_SPAWN_PREVENT;
				worlds = ConfigMain.MOBS_IRONGOLEM_SPAWN_WORLDS;
				break;
			case EVOKER:
				ret = ConfigMain.MOBS_ILLAGER_EVOKER_SPAWN_PREVENT;
				worlds = ConfigMain.MOBS_ILLAGER_EVOKER_SPAWN_WORLDS;
				break;
			case ILLUSIONER:
				ret = ConfigMain.MOBS_ILLAGER_ILLUSIONER_SPAWN_PREVENT;
				worlds = ConfigMain.MOBS_ILLAGER_ILLUSIONER_SPAWN_WORLDS;
				break;
			case PILLAGER:
				ret = ConfigMain.MOBS_ILLAGER_PILLAGER_SPAWN_PREVENT;
				worlds = ConfigMain.MOBS_ILLAGER_PILLAGER_SPAWN_WORLDS;
				break;
			case RAVAGER:
				ret = ConfigMain.MOBS_ILLAGER_RAVAGER_SPAWN_PREVENT;
				worlds = ConfigMain.MOBS_ILLAGER_RAVAGER_SPAWN_WORLDS;
				break;
			case VEX:
				ret = ConfigMain.MOBS_ILLAGER_VEX_SPAWN_PREVENT;
				worlds = ConfigMain.MOBS_ILLAGER_VEX_SPAWN_WORLDS;
				break;
			case VINDICATOR:
				ret = ConfigMain.MOBS_ILLAGER_VINDICATOR_SPAWN_PREVENT;
				worlds = ConfigMain.MOBS_ILLAGER_VINDICATOR_SPAWN_WORLDS;
				break;
			case WITCH:
				ret = ConfigMain.MOBS_ILLAGER_WITCH_SPAWN_PREVENT;
				worlds = ConfigMain.MOBS_ILLAGER_WITCH_SPAWN_WORLDS;
				break;
			default:
				// Not supported
				break;
		}
		return ret && WorldUtils.containsWorld(worlds, world);
	}
}
