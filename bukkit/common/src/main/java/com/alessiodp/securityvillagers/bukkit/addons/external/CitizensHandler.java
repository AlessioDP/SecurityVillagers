package com.alessiodp.securityvillagers.bukkit.addons.external;

import com.alessiodp.core.common.configuration.Constants;
import com.alessiodp.securityvillagers.bukkit.configuration.data.BukkitConfigMain;
import com.alessiodp.securityvillagers.common.SecurityVillagersPlugin;
import lombok.NonNull;
import net.citizensnpcs.api.CitizensAPI;
import org.bukkit.entity.Entity;

public class CitizensHandler {
	private static SecurityVillagersPlugin plugin;
	private static final String ADDON_NAME = "Citizens";
	private static boolean active;
	
	public CitizensHandler(@NonNull SecurityVillagersPlugin sv) {
		plugin = sv;
	}
	
	public void init() {
		active = false;
		if (BukkitConfigMain.GENERAL_CITIZENS_ENABLE) {
			try {
				Class.forName("net.citizensnpcs.api.CitizensAPI");
				active = true;
				
				plugin.getLoggerManager().log(String.format(Constants.DEBUG_ADDON_HOOKED, ADDON_NAME), true);
			} catch (Throwable ignored) {
				plugin.getLoggerManager().log(String.format(Constants.DEBUG_ADDON_FAILED, ADDON_NAME), true);
				BukkitConfigMain.GENERAL_CITIZENS_ENABLE = false;
			}
		}
	}
	
	public static boolean isNPC(Entity entity) {
		if (active) {
			return CitizensAPI.getNPCRegistry().isNPC(entity);
		}
		return false;
	}
}
