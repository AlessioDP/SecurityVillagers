package com.alessiodp.securityvillagers.api.events;

import com.alessiodp.securityvillagers.api.interfaces.SecurityVillagersAPI;
import org.jetbrains.annotations.NotNull;

public interface SecurityVillagersEvent  {
	/**
	 * Get the SecurityVillagers API instance
	 *
	 * @return the {@link SecurityVillagersAPI}
	 */
	@NotNull
	SecurityVillagersAPI getApi();
	
	/**
	 * Set the SecurityVillagers API instance. Used by SecurityVillagers instance to let you hook directly to the main API.
	 *
	 * @param instance the {@link SecurityVillagersAPI} instance to set
	 */
	void setApi(SecurityVillagersAPI instance);
}
