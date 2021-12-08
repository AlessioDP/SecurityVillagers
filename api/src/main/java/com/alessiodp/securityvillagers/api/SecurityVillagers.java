package com.alessiodp.securityvillagers.api;

import com.alessiodp.securityvillagers.api.interfaces.SecurityVillagersAPI;

public final class SecurityVillagers {
	private static SecurityVillagersAPI api = null;
	private static boolean flagHook = false;
	
	private SecurityVillagers() {
	}
	
	/**
	 * Get the {@link SecurityVillagersAPI} instance
	 *
	 * @return Returns the {@link SecurityVillagersAPI} interface
	 * @throws IllegalStateException if SecurityVillagersAPI has not been initialized, in other words,
	 *                               SecurityVillagersAPI has not been loaded
	 */
	public static SecurityVillagersAPI getApi() throws IllegalStateException {
		flagHook = true;
		if (api == null)
			throw new IllegalStateException("SecurityVillagersAPI has not been initialized");
		return api;
	}
	
	/**
	 * Set the SecurityVillagers API instance. This should not be used.
	 *
	 * @param instance The SecurityVillagersAPI instance.
	 */
	public static void setApi(SecurityVillagersAPI instance) {
		api = instance;
	}
	
	/**
	 * Flag to know if SecurityVillagers has been hooked
	 *
	 * @return Returns true if the API has been hooked at least one time
	 */
	public static boolean isFlagHook() {
		return flagHook;
	}
}