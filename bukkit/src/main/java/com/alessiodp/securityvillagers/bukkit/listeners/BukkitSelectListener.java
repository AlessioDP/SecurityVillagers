package com.alessiodp.securityvillagers.bukkit.listeners;

import com.alessiodp.core.bukkit.user.BukkitUser;
import com.alessiodp.securityvillagers.bukkit.BukkitSecurityVillagersPlugin;
import com.alessiodp.securityvillagers.bukkit.villagers.objects.MobsType;
import com.alessiodp.securityvillagers.common.SecurityVillagersPlugin;
import com.alessiodp.securityvillagers.common.listeners.SelectListener;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.EquipmentSlot;

public class BukkitSelectListener extends SelectListener implements Listener {
	public BukkitSelectListener(SecurityVillagersPlugin plugin) {
		super(plugin);
	}
	
	@EventHandler(ignoreCancelled = true, priority = EventPriority.LOW)
	public void  onSelect(PlayerInteractEntityEvent event) {
		if (event.getRightClicked() instanceof Villager
				|| ((BukkitSecurityVillagersPlugin) plugin).getNmsManager().isMobInstanceOf(MobsType.WANDERING_TRADER, event.getRightClicked())
				|| ((BukkitSecurityVillagersPlugin) plugin).getNmsManager().isMobInstanceOf(MobsType.TRADER_LLAMA, event.getRightClicked())) {
			if (((BukkitSecurityVillagersPlugin) plugin).getNmsManager().existOffHand()
					&& event.getHand() == EquipmentSlot.OFF_HAND) {
				// Mojang issue (double event call)
				// https://hub.spigotmc.org/jira/plugins/servlet/mobile#issue/SPIGOT-1570
				event.setCancelled(true);
				return;
			}
			
			boolean cancelled = super.onSelect(
					new BukkitUser(plugin, event.getPlayer()),
					plugin.getVillagerManager().initializeProtectedEntity(event.getRightClicked()),
					((BukkitSecurityVillagersPlugin) plugin).getNmsManager().getMaterialInMainHand(event.getPlayer()).name()
			);
			if (cancelled)
				event.setCancelled(cancelled);
		}
	}
}
