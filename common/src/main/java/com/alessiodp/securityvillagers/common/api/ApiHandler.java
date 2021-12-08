package com.alessiodp.securityvillagers.common.api;

import com.alessiodp.securityvillagers.api.interfaces.SecurityVillagersAPI;
import com.alessiodp.securityvillagers.common.SecurityVillagersPlugin;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
public abstract class ApiHandler implements SecurityVillagersAPI {
	@NonNull protected final SecurityVillagersPlugin plugin;
	
	@Override
	public void reloadPlugin() {
		plugin.reloadConfiguration();
	}
	
	@Override
	public List<UUID> getProtectedEntities() {
		return new ArrayList<>(plugin.getVillagerManager().getProtectedEntities());
	}
}
