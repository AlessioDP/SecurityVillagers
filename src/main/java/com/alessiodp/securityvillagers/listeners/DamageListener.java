package com.alessiodp.securityvillagers.listeners;

import com.alessiodp.securityvillagers.SecurityVillagers;
import com.alessiodp.securityvillagers.configuration.data.ConfigMain;
import com.alessiodp.securityvillagers.configuration.data.Messages;
import com.alessiodp.securityvillagers.utils.AttackBlockResult;
import com.alessiodp.securityvillagers.utils.VillagersUtils;
import org.bukkit.entity.IronGolem;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;

public class DamageListener implements Listener {
	private SecurityVillagers plugin;
	
	public DamageListener(SecurityVillagers instance) {
		plugin = instance;
	}
	
	@EventHandler
	public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
		// IronGolem protection
		if (event.getEntity() instanceof IronGolem && ConfigMain.IRONGOLEM_PROTECT) {
			IronGolem entity = (IronGolem) event.getEntity();
			
			AttackBlockResult res = VillagersUtils.canBeAttacked(entity, event.getDamager());
			
			if (!res.getResult().isSuccess()) {
				if (res.getDamager() instanceof Player) {
					if (res.getResult().isHit()) {
						// Hit
						plugin.sendMessage(res.getDamager(), Messages.IRONGOLEM_HIT);
					} else {
						// Shoot
						plugin.sendMessage(res.getDamager(), Messages.IRONGOLEM_SHOOT);
					}
				}
				event.setCancelled(true);
			}
		}
		
		// Villager protection
		if (event.getEntity() instanceof Villager) {
			Villager entity = (Villager) event.getEntity();
			
			AttackBlockResult res = VillagersUtils.canBeAttacked(entity, event.getDamager());
			
			if (!res.getResult().isSuccess()) {
				if (res.getDamager() instanceof Player) {
					if (res.getResult().isHit()) {
						// Hit
						plugin.sendMessage(res.getDamager(), Messages.INTERACT_HIT);
					} else {
						// Shoot
						plugin.sendMessage(res.getDamager(), Messages.INTERACT_SHOOT);
					}
				}
				event.setCancelled(true);
			}
		}
	}

	@EventHandler
	public void onEntityDamage(EntityDamageEvent event) {
		if ((event.getEntity() instanceof IronGolem && ConfigMain.IRONGOLEM_PROTECT)
				|| event.getEntity() instanceof Villager) {
			if (!VillagersUtils.canBeAttackedFromOther(event.getEntity(), event.getCause())) {
				event.setCancelled(true);
			}
		}
	}
}
