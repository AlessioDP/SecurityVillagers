package com.alessiodp.securityvillagers.bukkit.addons.external;

import com.alessiodp.core.common.configuration.Constants;
import com.alessiodp.securityvillagers.bukkit.configuration.data.BukkitConfigMain;
import com.alessiodp.securityvillagers.common.SecurityVillagersPlugin;
import com.alessiodp.securityvillagers.common.villagers.objects.ProtectedEntity;
import lombok.NonNull;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.inventivetalent.glow.GlowAPI;

import java.util.UUID;

public class GlowHandler {
	private static SecurityVillagersPlugin plugin;
	private static final String ADDON_NAME = "GlowAPI";
	private static boolean active;
	
	public GlowHandler(@NonNull SecurityVillagersPlugin sv) {
		plugin = sv;
	}
	
	public void init() {
		active = false;
		if (BukkitConfigMain.SELECTION_GLOWAPI_ENABLE) {
			try {
				Class.forName("org.inventivetalent.glow.GlowAPI");
				active = true;
				
				plugin.getLoggerManager().log(String.format(Constants.DEBUG_ADDON_HOOKED, ADDON_NAME), true);
			} catch (Throwable ignored) {
				plugin.getLoggerManager().log(String.format(Constants.DEBUG_ADDON_FAILED, ADDON_NAME), true);
			}
		}
	}
	
	public static void glowEntity(ProtectedEntity entity, UUID glowFor) {
		if (active) {
			Player player = Bukkit.getPlayer(glowFor);
			try {
				GlowAPI.Color color = GlowAPI.Color.valueOf(BukkitConfigMain.SELECTION_GLOWAPI_COLOR);
				
				Object bukkitEntity = entity.getEntity();
				if (bukkitEntity != null)
					GlowAPI.setGlowing((Entity) bukkitEntity, color, player);
			} catch (IllegalArgumentException ex) {
				ex.printStackTrace();
			}
		}
	}
	
	public static void unglowEntity(ProtectedEntity entity, UUID glowFor) {
		if (active && glowFor != null && entity != null) {
			Player player = Bukkit.getPlayer(glowFor);
			try {
				Object bukkitEntity = entity.getEntity();
				if (bukkitEntity != null)
					GlowAPI.setGlowing((Entity) bukkitEntity, false, player);
			} catch (IllegalArgumentException ex) {
				ex.printStackTrace();
			}
		}
	}
}
