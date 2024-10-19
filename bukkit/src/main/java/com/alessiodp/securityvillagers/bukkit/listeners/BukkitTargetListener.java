package com.alessiodp.securityvillagers.bukkit.listeners;

import com.alessiodp.securityvillagers.common.SecurityVillagersPlugin;
import com.alessiodp.securityvillagers.common.listeners.TargetListener;
import com.alessiodp.securityvillagers.common.villagers.objects.ProtectedEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityTargetLivingEntityEvent;

public class BukkitTargetListener extends TargetListener implements Listener {
	
	public BukkitTargetListener(SecurityVillagersPlugin plugin) {
		super(plugin);
	}
	
	@EventHandler(ignoreCancelled = true)
	public void onTarget(EntityTargetLivingEntityEvent event) {
		if (isTargetProtected() && !(event.getEntity() instanceof Player) && event.getTarget() != null) {
			ProtectedEntity protectedEntity = plugin.getVillagerManager().initializeProtectedEntity(event.getTarget());
			if (protectedEntity != null) {
				boolean cancelled = super.onTarget(protectedEntity, event.getEntity());
				
				if (cancelled)
					event.setCancelled(cancelled);
			}
		}
	}
}