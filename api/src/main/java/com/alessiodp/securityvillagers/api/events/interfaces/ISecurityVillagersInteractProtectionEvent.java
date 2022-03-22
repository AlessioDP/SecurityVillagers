package com.alessiodp.securityvillagers.api.events.interfaces;

import com.alessiodp.securityvillagers.api.enums.InteractType;
import com.alessiodp.securityvillagers.api.events.Cancellable;
import com.alessiodp.securityvillagers.api.events.SecurityVillagersEvent;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public interface ISecurityVillagersInteractProtectionEvent extends SecurityVillagersEvent, Cancellable {
	/**
	 * Get the interacted entity
	 *
	 * @return the {@link Entity}
	 */
	@NotNull
	Entity getEntity();
	
	/**
	 * Get who is interacting with the entity
	 *
	 * @return the {@link Player} interactor
	 */
	@NotNull
	Player getInteractor();
	
	
	/**
	 * Get the type of interaction
	 *
	 * @return the {@link InteractType}
	 */
	@NotNull
	InteractType getInteractType();
}
