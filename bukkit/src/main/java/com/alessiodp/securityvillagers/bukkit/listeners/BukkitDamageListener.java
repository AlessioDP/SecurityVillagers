package com.alessiodp.securityvillagers.bukkit.listeners;

import com.alessiodp.securityvillagers.common.SecurityVillagersPlugin;
import com.alessiodp.securityvillagers.common.listeners.DamageListener;
import com.alessiodp.securityvillagers.common.villagers.objects.ProtectedEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class BukkitDamageListener extends DamageListener implements Listener {
	
	public BukkitDamageListener(SecurityVillagersPlugin plugin) {
		super(plugin);
	}
	
	@EventHandler(ignoreCancelled = true)
	public void onDamage(EntityDamageEvent event) {
		if (event.getCause() == EntityDamageEvent.DamageCause.ENTITY_ATTACK
				|| event.getCause() == EntityDamageEvent.DamageCause.PROJECTILE) {
			// Attacks and shoots are handled by FightListener
			return;
		}
		
		ProtectedEntity protectedEntity = plugin.getVillagerManager().initializeProtectedEntity(event.getEntity());
		if (protectedEntity != null) {
			boolean cancelled = super.onDamage(
					protectedEntity,
					event.getCause()
			);
			
			if (cancelled)
				event.setCancelled(cancelled);
		}
	}
}
