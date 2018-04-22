package com.alessiodp.securityvillagers.addons.external;

import com.alessiodp.securityvillagers.SecurityVillagers;
import com.alessiodp.securityvillagers.configuration.Constants;
import com.alessiodp.securityvillagers.configuration.data.ConfigMain;
import com.alessiodp.securityvillagers.utils.SVPermission;
import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.events.PacketEvent;
import org.bukkit.Bukkit;
import org.bukkit.Sound;

public class ProtocolLibHandler {
	private SecurityVillagers plugin;
	private static final String ADDON_NAME = "ProtocolLib";
	
	public ProtocolLibHandler(SecurityVillagers instance) {
		plugin = instance;
		init();
	}
	
	private void init() {
		if (Bukkit.getPluginManager().isPluginEnabled(ADDON_NAME)) {
			if (ConfigMain.PREVENTIONS_MUTE) {
				addListener();
				plugin.log(Constants.DEBUG_ADDON_HOOK
						.replace("{addon}", ADDON_NAME));
			}
		} else {
			if (ConfigMain.PREVENTIONS_MUTE) {
				ConfigMain.PREVENTIONS_MUTE = false;
				removeListener();
				plugin.log(Constants.DEBUG_ADDON_MISSING
						.replace("{addon}", ADDON_NAME));
			}
		}
	}
	
	private void addListener() {
		ProtocolLibrary.getProtocolManager()
				.addPacketListener(
						new PacketAdapter(plugin,
								new PacketType[]{PacketType.Play.Server.NAMED_SOUND_EFFECT}) {
			public void onPacketSending(PacketEvent event) {
				if (event.getPacketType() == PacketType.Play.Server.NAMED_SOUND_EFFECT) {
					PacketContainer packet = event.getPacket();
					if (!event.getPlayer().hasPermission(SVPermission.ADMIN_BYPASS_MUTE.toString())) {
						if (packet.getSoundEffects().getValues().get(0).equals(Sound.ENTITY_VILLAGER_AMBIENT)) {
							event.setCancelled(true);
						}
					}
				}
			}
		});
	}
	
	private void removeListener() {
		ProtocolLibrary.getProtocolManager().removePacketListeners(plugin);
	}
}
