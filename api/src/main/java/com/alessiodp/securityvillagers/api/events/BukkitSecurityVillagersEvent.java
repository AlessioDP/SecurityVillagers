package com.alessiodp.securityvillagers.api.events;

import com.alessiodp.securityvillagers.api.interfaces.SecurityVillagersAPI;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

public class BukkitSecurityVillagersEvent extends Event implements SecurityVillagersEvent {
	private SecurityVillagersAPI api;
	private static final HandlerList HANDLERS = new HandlerList();
	
	/**
	 * Get the SecurityVillagers API instance
	 *
	 * @return the {@link SecurityVillagersAPI}
	 */
	@NotNull
	public SecurityVillagersAPI getApi() {
		return api;
	}
	
	/**
	 * Set the SecurityVillagers API instance. Used by SecurityVillagers instance to let you hook directly to the main API.
	 *
	 * @param instance the {@link SecurityVillagersAPI} instance to set
	 */
	public void setApi(SecurityVillagersAPI instance) {
		api = instance;
	}
	
	@Override
	@NotNull
	public HandlerList getHandlers() {
		return HANDLERS;
	}
	
	public static HandlerList getHandlerList() {
		return HANDLERS;
	}
}
