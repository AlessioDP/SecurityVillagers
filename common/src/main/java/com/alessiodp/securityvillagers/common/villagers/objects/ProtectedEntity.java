package com.alessiodp.securityvillagers.common.villagers.objects;

import com.alessiodp.securityvillagers.common.SecurityVillagersPlugin;
import com.alessiodp.securityvillagers.common.configuration.data.ConfigMain;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
public abstract class ProtectedEntity {
	@NonNull private final SecurityVillagersPlugin plugin;
	@NonNull @Getter private ProtectedEntityType type;
	@Getter @Setter private boolean protectionEnabled = false;
	
	public void updateProtectedEntity() {
		plugin.getDatabaseManager().updateProtectedEntity(this);
	}
	
	public final boolean isConfigProtected() {
		boolean ret = false;
		switch (type) {
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
		}
		return ret;
	}
	
	public final boolean isConfigPreventInteract() {
		boolean ret = false;
		switch (type) {
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
	
	public final boolean isConfigPreventSpawn(String world) {
		boolean ret = false;
		List<String> worlds = new ArrayList<>();
		switch (type) {
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
		return ret && (worlds.contains("*") || worlds.contains(world));
	}
	
	public abstract UUID getUuid();
	public abstract void setCustomName(String name);
	
	public abstract String getWorld();
	public abstract Object getEntity();
	
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
