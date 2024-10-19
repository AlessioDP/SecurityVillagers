package com.alessiodp.securityvillagers.bukkit.listeners;

import com.alessiodp.core.bukkit.user.BukkitUser;
import com.alessiodp.core.common.user.User;
import com.alessiodp.securityvillagers.common.SecurityVillagersPlugin;
import com.alessiodp.securityvillagers.common.listeners.FightListener;
import com.alessiodp.securityvillagers.common.villagers.objects.ProtectedEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class BukkitFightListener extends FightListener implements Listener {
	
	public BukkitFightListener(SecurityVillagersPlugin plugin) {
		super(plugin);
	}
	
	@EventHandler(ignoreCancelled = true)
	public void onDamageByEntity(EntityDamageByEntityEvent event) {
		ProtectedEntity protectedEntity = plugin.getVillagerManager().initializeProtectedEntity(event.getEntity());
		
		if (protectedEntity != null) {
			boolean cancelled = super.onDamageByEntity(
					protectedEntity,
					event.getDamager()
			);
			
			if (cancelled)
				event.setCancelled(cancelled);
		}
	}
	
	@Override
	protected User getPlayerFromEntity(Object entity) {
		User ret = null;
		if (entity instanceof Player) {
			ret = new BukkitUser(plugin, (Player) entity);
		} else if (entity instanceof Projectile && ((Projectile) entity).getShooter() instanceof Player) {
			ret = new BukkitUser(plugin, (Player) ((Projectile) entity).getShooter());
		}
		return ret;
	}
}
