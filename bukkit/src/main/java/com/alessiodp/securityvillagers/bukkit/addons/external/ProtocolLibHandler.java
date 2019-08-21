package com.alessiodp.securityvillagers.bukkit.addons.external;

import com.alessiodp.core.common.configuration.Constants;
import com.alessiodp.securityvillagers.bukkit.addons.external.hooks.ProtocolLibHook;
import com.alessiodp.securityvillagers.common.SecurityVillagersPlugin;
import com.alessiodp.securityvillagers.common.configuration.data.ConfigMain;
import lombok.NonNull;
import org.bukkit.Bukkit;

public class ProtocolLibHandler {
	private static SecurityVillagersPlugin plugin;
	private static final String ADDON_NAME = "ProtocolLib";
	
	private static ProtocolLibHook hook;
	
	public ProtocolLibHandler(@NonNull SecurityVillagersPlugin sv) {
		plugin = sv;
	}
	
	public void init() {
		boolean active = false;
		if (ConfigMain.GENERAL_MUTE_SOUND) {
			if (Bukkit.getPluginManager().isPluginEnabled(ADDON_NAME)) {
				if (hook == null)
					hook = new ProtocolLibHook(plugin);
				
				active = hook.init();
				
				if (active) {
					plugin.getLoggerManager().log(Constants.DEBUG_ADDON_HOOKED
							.replace("{addon}", ADDON_NAME), true);
				}
			}
			
			if (!active) {
				ConfigMain.GENERAL_MUTE_SOUND = false;
				
				plugin.getLoggerManager().log(Constants.DEBUG_ADDON_FAILED
						.replace("{addon}", ADDON_NAME), true);
			}
		}
		
		if (!active && hook != null) {
			hook.unregister();
		}
	}
}
