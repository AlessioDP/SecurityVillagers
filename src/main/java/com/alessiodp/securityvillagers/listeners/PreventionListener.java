package com.alessiodp.securityvillagers.listeners;

import com.alessiodp.securityvillagers.SecurityVillagers;
import com.alessiodp.securityvillagers.configuration.data.ConfigMain;
import com.alessiodp.securityvillagers.utils.VillagersUtils;
import org.bukkit.entity.Entity;
import org.bukkit.entity.IronGolem;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.event.entity.EntityTargetLivingEntityEvent;

public class PreventionListener implements Listener {
	private SecurityVillagers plugin;
	
	public PreventionListener(SecurityVillagers instance) {
		plugin = instance;
	}
	
	// Target prevention
	@EventHandler
	public void onEntityTarget(EntityTargetLivingEntityEvent event) {
		if (event.getTarget() instanceof Villager) {
			Villager target = (Villager) event.getTarget();
			if (!(event.getEntity() instanceof Player)) {
				if (ConfigMain.PREVENTIONS_TARGET) {
					Entity damager = event.getEntity();
					
					// Check if the entity that have tag the villager is prevented
					if (!VillagersUtils.canBeAttacked(target, damager).getResult().isSuccess())
						event.setCancelled(true);
				}
			}
		}
	}
	
	// Spawn prevention
	@EventHandler
	public void onEntitySpawn(EntitySpawnEvent event) {
		if (event.getEntity() instanceof Villager) {
			if (ConfigMain.PREVENTIONS_SPAWN)
				event.setCancelled(true);
		} else if (event.getEntity() instanceof IronGolem) {
			if (ConfigMain.IRONGOLEM_PREVENTSPAWN)
				event.setCancelled(true);
		}
	}
}
