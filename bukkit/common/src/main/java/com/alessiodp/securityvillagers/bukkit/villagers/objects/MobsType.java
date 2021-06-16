package com.alessiodp.securityvillagers.bukkit.villagers.objects;

import lombok.NonNull;
import lombok.Setter;

public enum MobsType {
	// Every mob handled by SecurityVillagers that are not in every version of MC
	WANDERING_TRADER, TRADER_LLAMA, // Main mobs
	EVOKER, EVOKER_FANGS, ILLUSIONER, PILLAGER, RAVAGER, STRAY, VEX, VINDICATOR, ZOGLIN; // Additional mobs
	
	
	@Setter private Class mobClass;
	
	public boolean instanceOf(@NonNull Object object) {
		return this.mobClass != null && object.getClass().isAssignableFrom(this.mobClass);
	}
}