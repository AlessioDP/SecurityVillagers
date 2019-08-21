package com.alessiodp.securityvillagers.bukkit.listeners;

import com.alessiodp.securityvillagers.common.SecurityVillagersPlugin;
import com.alessiodp.securityvillagers.common.listeners.SpawnListener;
import com.alessiodp.securityvillagers.common.villagers.objects.ProtectedEntity;
import lombok.NonNull;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;

public class BukkitSpawnListener extends SpawnListener implements Listener {
	
	public BukkitSpawnListener(@NonNull SecurityVillagersPlugin plugin) {
		super(plugin);
	}
	
	@EventHandler(ignoreCancelled = true)
	public void onSpawn(EntitySpawnEvent event) {
		ProtectedEntity protectedEntity = plugin.getVillagerManager().initializeProtectedEntity(event.getEntity());
		if (protectedEntity != null) {
			boolean cancelled = super.onSpawn(protectedEntity);
			
			if (cancelled)
				event.setCancelled(cancelled);
		}
	}
}