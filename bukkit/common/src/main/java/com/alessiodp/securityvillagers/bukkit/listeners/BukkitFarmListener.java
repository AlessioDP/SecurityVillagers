package com.alessiodp.securityvillagers.bukkit.listeners;

import com.alessiodp.securityvillagers.bukkit.BukkitSecurityVillagersPlugin;
import com.alessiodp.securityvillagers.common.SecurityVillagersPlugin;
import com.alessiodp.securityvillagers.common.listeners.FarmListener;
import com.alessiodp.securityvillagers.common.villagers.objects.ProtectedEntity;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityChangeBlockEvent;

public class BukkitFarmListener extends FarmListener implements Listener {
	public BukkitFarmListener(SecurityVillagersPlugin plugin) {
		super(plugin);
	}
	
	@EventHandler(ignoreCancelled = true, priority = EventPriority.HIGH)
	public void onVillagerFarm(EntityChangeBlockEvent event) {
		if (event.getEntity() instanceof Villager) {
			ProtectedEntity protectedEntity = plugin.getVillagerManager().initializeProtectedEntity(event.getEntity());
			if (protectedEntity != null) {
				boolean cancelled = false;
				if (((BukkitSecurityVillagersPlugin) plugin).getNmsManager().isHarvestable(event.getBlock().getType())) {
					// Prevent harvest
					cancelled = super.onVillagerHarvesting(protectedEntity);
				} else if (((BukkitSecurityVillagersPlugin) plugin).getNmsManager().isHarvestable(event.getTo())) {
					// Prevent plant
					cancelled = super.onVillagerPlant(protectedEntity);
				}
				
				if (cancelled)
					event.setCancelled(true);
			}
		}
	}
}
