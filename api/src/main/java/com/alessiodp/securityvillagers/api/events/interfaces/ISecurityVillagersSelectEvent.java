package com.alessiodp.securityvillagers.api.events.interfaces;

import com.alessiodp.securityvillagers.api.events.Cancellable;
import com.alessiodp.securityvillagers.api.events.SecurityVillagersEvent;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface ISecurityVillagersSelectEvent extends SecurityVillagersEvent, Cancellable {
	/**
	 * Get the player
	 *
	 * @return Returns the {@link Player}
	 */
	@NotNull
	Player getPlayer();
	
	/**
	 * Get the selected entity
	 *
	 * @return Return the {@link Entity}
	 */
	@NotNull
	Entity getSelectedEntity();
	
	/**
	 * Get the previously selected entity
	 *
	 * @return Return the {@link Entity}
	 */
	@Nullable
	Entity getOldSelection();
	
	/**
	 * Check if the player is unselecting the entity
	 *
	 * @return Returns true if unselecting
	 */
	default boolean isUnselection() {
		return getOldSelection() != null && getOldSelection().equals(getSelectedEntity());
	}
}
