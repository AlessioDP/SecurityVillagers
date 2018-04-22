package com.alessiodp.securityvillagers.listeners;

import com.alessiodp.securityvillagers.addons.external.FactionsHandler;
import com.alessiodp.securityvillagers.addons.internal.ADPUpdater;
import com.alessiodp.securityvillagers.utils.SVPermission;
import com.alessiodp.securityvillagers.utils.VillagersUtils;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.EquipmentSlot;

import com.alessiodp.securityvillagers.SecurityVillagers;
import com.alessiodp.securityvillagers.configuration.data.Messages;
import com.alessiodp.securityvillagers.configuration.data.ConfigMain;

public class PlayerListener implements Listener {
	private SecurityVillagers plugin;
	
	public PlayerListener(SecurityVillagers instance) {
		plugin = instance;
	}

	@EventHandler
	public void onPlayerInteractEntity(PlayerInteractEntityEvent event) {
		if (event.getRightClicked() instanceof Villager) {
			if (event.getHand() == EquipmentSlot.OFF_HAND) {
				// Mojang issue (double event call)
				// https://hub.spigotmc.org/jira/plugins/servlet/mobile#issue/SPIGOT-1570
				event.setCancelled(true);
				return;
			}
			
			Player player = event.getPlayer();
			Villager villager = (Villager) event.getRightClicked();
			Material handItem = player.getInventory().getItemInMainHand().getType();
			
			// Selection
			if (ConfigMain.SECURITYVILLAGERS_SELECTION_ENABLE
					&& player.hasPermission(SVPermission.ADMIN_SELECTION.toString())
					&& handItem.equals(ConfigMain.SECURITYVILLAGERS_SELECTION_ITEM)) {
				plugin.getVillagerHandler().getListSelectedVillagers().put(player.getUniqueId(), villager);
				plugin.sendMessage(player, Messages.SELECTION_SELECTED);
				event.setCancelled(true);
			} else if (ConfigMain.CHANGEAGE_ENABLE
					&& ConfigMain.CHANGEAGE_ITEM_ENABLE
					&& player.hasPermission(SVPermission.ADMIN_CHANGEAGE.toString())
					&& handItem.equals(ConfigMain.CHANGEAGE_ITEM_ITEM)) {
				// Converter
				if (ConfigMain.CHANGEAGE_ALLOWEDWORLDS.contains("*")
						|| ConfigMain.CHANGEAGE_ALLOWEDWORLDS.contains(villager.getLocation().getWorld().getName())) {
					if (villager.isAdult()) {
						villager.setBaby();
						plugin.sendMessage(player, Messages.CONVERTER_INTO_BABY);
					} else {
						villager.setAdult();
						plugin.sendMessage(player, Messages.CONVERTER_INTO_ADULT);
					}
					event.setCancelled(true);
				}
			} else if (ConfigMain.GENERAL_INTERACT_EGG
					&& !player.hasPermission(SVPermission.EGG.toString())
					&& handItem.equals(Material.MONSTER_EGG)) {
				// Egg
				if (ConfigMain.FACTIONS_ENABLE
						&& ConfigMain.FACTIONS_PREVENT_INTERACT
						&& !player.hasPermission(SVPermission.ADMIN_BYPASS_FACTIONS.toString())) {
					if (!FactionsHandler.canHit(player, villager.getLocation())) {
						plugin.sendMessage(player, Messages.INTERACT_DISABLED_EGG);
						event.setCancelled(true);
					}
				} else if (ConfigMain.GENERAL_INTERACT_WORLDS.contains("*")
						|| ConfigMain.GENERAL_INTERACT_WORLDS.contains(villager.getLocation().getWorld().getName())) {
					plugin.sendMessage(player, Messages.INTERACT_DISABLED_EGG);
					event.setCancelled(true);
				}
			} else if (ConfigMain.GENERAL_INTERACT_TRADE
					&& !player.hasPermission(SVPermission.TRADE.toString())) {
				// Trade
				if (ConfigMain.FACTIONS_ENABLE
						&& ConfigMain.FACTIONS_PREVENT_INTERACT
						&& !player.hasPermission(SVPermission.ADMIN_BYPASS_FACTIONS.toString())) {
					if (!FactionsHandler.canHit(player, villager.getLocation())) {
						plugin.sendMessage(player, Messages.INTERACT_DISABLED_TRADE);
						event.setCancelled(true);
					}
				} else if (ConfigMain.GENERAL_INTERACT_WORLDS.contains("*")
						|| ConfigMain.GENERAL_INTERACT_WORLDS.contains(villager.getLocation().getWorld().getName())) {
					plugin.sendMessage(player, Messages.INTERACT_DISABLED_TRADE);
					event.setCancelled(true);
				}
			}
		}
	}
	
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent event) {
		// Block egg spawn
		if (ConfigMain.GENERAL_INTERACT_EGG) {
			if (event.getAction() == Action.RIGHT_CLICK_BLOCK
					&& VillagersUtils.isVillagerEgg(event.getPlayer().getInventory().getItemInMainHand())) {
				Player player = event.getPlayer();
				if (!player.hasPermission(SVPermission.EGG.toString())) {
					if (ConfigMain.FACTIONS_ENABLE
							&& ConfigMain.FACTIONS_PREVENT_INTERACT
							&& !player.hasPermission(SVPermission.ADMIN_BYPASS_FACTIONS.toString())) {
						if (!FactionsHandler.canHit(player, event.getClickedBlock().getLocation())) {
							plugin.sendMessage(player, Messages.INTERACT_DISABLED_EGG);
							event.setCancelled(true);
						}
					} else if (ConfigMain.GENERAL_INTERACT_WORLDS.contains("*")
							|| ConfigMain.GENERAL_INTERACT_WORLDS.contains(event.getClickedBlock().getLocation().getWorld().getName())) {
						plugin.sendMessage(player, Messages.INTERACT_DISABLED_EGG);
						event.setCancelled(true);
					}
				}
			}
		}
	}
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
		if (ConfigMain.SECURITYVILLAGERS_UPDATES_CHECK
				&& ConfigMain.SECURITYVILLAGERS_UPDATES_WARN
				&& !ADPUpdater.getFoundVersion().isEmpty()) {
			ADPUpdater.alertPlayer(event.getPlayer());
		}
	}
	
	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent event) {
		if (plugin.getVillagerHandler().getListSelectedVillagers().containsKey(event.getPlayer().getUniqueId()))
			plugin.getVillagerHandler().getListSelectedVillagers().remove(event.getPlayer().getUniqueId());
	}
}
