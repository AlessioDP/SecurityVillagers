package com.alessiodp.securityvillagers.common.listeners;

import com.alessiodp.securityvillagers.common.SecurityVillagersPlugin;
import com.alessiodp.securityvillagers.common.villagers.objects.ProtectedEntity;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class DamageListener {
	@NonNull protected final SecurityVillagersPlugin plugin;
	
	protected boolean onDamage(ProtectedEntity protectedEntity, Object damageCause) {
		return !plugin.getVillagerManager().canBeDamaged(protectedEntity, damageCause);
	}
}
