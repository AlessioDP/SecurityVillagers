package com.alessiodp.securityvillagers.bukkit.addons.external.hooks;

import com.alessiodp.securityvillagers.common.SecurityVillagersPlugin;
import com.alessiodp.securityvillagers.common.commands.utils.SecurityVillagersPermission;
import com.alessiodp.securityvillagers.common.configuration.data.ConfigMain;
import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketEvent;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.bukkit.Sound;
import org.bukkit.plugin.Plugin;

@RequiredArgsConstructor
public class ProtocolLibHook {
	@NonNull private SecurityVillagersPlugin plugin;
	
	private static ProtocolManager protocol;
	private static SVPacketAdapter packetAdapter;
	
	public boolean init() {
		boolean ret = false;
		protocol = ProtocolLibrary.getProtocolManager();
		if (protocol != null) {
			register();
			
			ret = true;
		}
		return ret;
	}
	
	private void register() {
		if (protocol != null) {
			if (packetAdapter == null) {
				packetAdapter = new SVPacketAdapter((Plugin) plugin.getBootstrap());
			}
			protocol.addPacketListener(packetAdapter);
		}
	}
	
	public void unregister() {
		if (protocol != null && packetAdapter != null) {
			protocol.removePacketListener(packetAdapter);
		}
	}
	
	private static class SVPacketAdapter extends PacketAdapter {
		SVPacketAdapter(Plugin plugin) {
			super(
					plugin,
					PacketType.Play.Server.NAMED_SOUND_EFFECT
			);
		}
		
		@Override
		public void onPacketSending(PacketEvent event) {
			if (event.getPacketType() == PacketType.Play.Server.NAMED_SOUND_EFFECT
					&& event.getPacket().getSoundEffects().getValues().get(0).equals(Sound.ENTITY_VILLAGER_AMBIENT)
					&& !event.getPlayer().hasPermission(SecurityVillagersPermission.ADMIN_BYPASS_MUTE.toString())
					&& ConfigMain.GENERAL_MUTE_WORLDS.contains(event.getPlayer().getWorld().getName())) {
				event.setCancelled(true);
			}
		}
	}
}