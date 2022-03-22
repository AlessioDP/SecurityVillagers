package com.alessiodp.securityvillagers.api.events.interfaces;

import com.alessiodp.securityvillagers.api.events.Cancellable;
import com.alessiodp.securityvillagers.api.events.SecurityVillagersEvent;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public interface ISecurityVillagersProtectionChangeEvent extends SecurityVillagersEvent, Cancellable {
	/**
	 * Get the player who is changing the protection
	 *
	 * @return the {@link Player}
	 */
	@NotNull
	Player getPlayer();
	
	/**
	 * Get the selected entity
	 *
	 * @return the {@link Entity}
	 */
	@NotNull
	Entity getEntity();
	
	/**
	 * Get the new protection value for the entity
	 *
	 * @return true if protected
	 */
	boolean getProtection();
	
	/**
	 * Set a new protection value for the entity
	 *
	 * @param protection the protection value
	 */
	void setProtection(boolean protection);
}
