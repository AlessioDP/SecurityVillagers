package com.alessiodp.securityvillagers.bukkit.listeners;

import com.alessiodp.core.bukkit.user.BukkitUser;
import com.alessiodp.core.common.user.User;
import com.alessiodp.securityvillagers.bukkit.BukkitSecurityVillagersPlugin;
import com.alessiodp.securityvillagers.bukkit.addons.external.FactionsHandler;
import com.alessiodp.securityvillagers.bukkit.villagers.objects.MobsType;
import com.alessiodp.securityvillagers.common.SecurityVillagersPlugin;
import com.alessiodp.securityvillagers.common.configuration.data.ConfigMain;
import com.alessiodp.securityvillagers.common.listeners.InteractListener;
import com.alessiodp.securityvillagers.common.villagers.objects.ProtectedEntity;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.EquipmentSlot;

public class BukkitInteractListener extends InteractListener implements Listener {
	public BukkitInteractListener(SecurityVillagersPlugin plugin) {
		super(plugin);
	}
	
	@EventHandler(ignoreCancelled = true, priority = EventPriority.HIGH)
	public void  onPlayerInteractEntity(PlayerInteractEntityEvent event) {
		if (event.getRightClicked() instanceof Villager
				|| MobsType.WANDERING_TRADER.instanceOf(event.getRightClicked())
				|| MobsType.TRADER_LLAMA.instanceOf(event.getRightClicked())) {
			if (((BukkitSecurityVillagersPlugin) plugin).getNmsManager().existOffHand()
					&& event.getHand() == EquipmentSlot.OFF_HAND) {
				// Mojang issue (double event call)
				// https://hub.spigotmc.org/jira/plugins/servlet/mobile#issue/SPIGOT-1570
				event.setCancelled(true);
				return;
			}
			
			boolean cancelled = super.onPlayerInteractEntity(
					new BukkitUser(plugin, event.getPlayer()),
					plugin.getVillagerManager().initializeProtectedEntity(event.getRightClicked()),
					((BukkitSecurityVillagersPlugin) plugin).getNmsManager().getMaterialInMainHand(event.getPlayer()).name()
			);
			if (cancelled)
				event.setCancelled(cancelled);
		}
	}
	
	@Override
	protected boolean isEgg(String material) {
		return Material.getMaterial(material) == ((BukkitSecurityVillagersPlugin) plugin).getNmsManager().getVillagerEgg();
	}
	
	@Override
	protected boolean isFactionProtected(User user, ProtectedEntity protectedEntity) {
		return ConfigMain.GENERAL_PROTECTIONTYPE == ConfigMain.ProtectionType.FACTIONS
				&& FactionsHandler.isClaimProtectedByInteract(Bukkit.getPlayer(user.getUUID()), ((Entity) protectedEntity.getEntity()).getLocation());
	}
}
