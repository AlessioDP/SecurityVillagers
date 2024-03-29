package com.alessiodp.securityvillagers.api.events.interfaces;

import com.alessiodp.securityvillagers.api.enums.AttackResult;
import com.alessiodp.securityvillagers.api.events.SecurityVillagersEvent;
import org.bukkit.entity.Entity;
import org.jetbrains.annotations.NotNull;

public interface ISecurityVillagersDamageEvent extends SecurityVillagersEvent {
	/**
	 * Get the damaged entity
	 *
	 * @return the {@link Entity}
	 */
	@NotNull
	Entity getEntity();
	
	/**
	 * Get who is doing damage to the entity
	 *
	 * @return the {@link Entity} damager
	 */
	@NotNull
	Entity getDamager();
	
	/**
	 * Get the attack result of the event.
	 *
	 * @return the {@link AttackResult}
	 */
	@NotNull
	AttackResult getAttackResult();
	
	/**
	 * Set the attack result of the event
	 *
	 * @param attackResult the attack result to set, cannot be null
	 */
	void setAttackResult(@NotNull AttackResult attackResult);
}
