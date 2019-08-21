package com.alessiodp.securityvillagers.bukkit.listeners;

import com.alessiodp.securityvillagers.common.SecurityVillagersPlugin;
import com.alessiodp.securityvillagers.common.listeners.DeathListener;
import com.alessiodp.securityvillagers.common.villagers.objects.ProtectedEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

public class BukkitDeathListener extends DeathListener implements Listener {
	
	public BukkitDeathListener(SecurityVillagersPlugin plugin) {
		super(plugin);
	}
	
	@EventHandler(ignoreCancelled = true, priority = EventPriority.MONITOR)
	public void onDeath(EntityDeathEvent event) {
		ProtectedEntity protectedEntity = plugin.getVillagerManager().initializeProtectedEntity(event.getEntity());
		if (protectedEntity != null) {
			super.onDeath(protectedEntity, event.getEntity().getLastDamageCause().getCause().name());
		}
	}
}
