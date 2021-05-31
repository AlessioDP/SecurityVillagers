package com.alessiodp.securityvillagers.bukkit.configuration.data;

import com.alessiodp.core.common.configuration.ConfigOption;
import com.alessiodp.securityvillagers.common.SecurityVillagersPlugin;
import com.alessiodp.securityvillagers.common.configuration.SVConstants;
import com.alessiodp.securityvillagers.common.configuration.data.ConfigMain;
import lombok.Getter;

public class BukkitConfigMain extends ConfigMain {
	@Getter private final String fileName = "config.yml";
	@Getter private final String resourceName = "bukkit/config.yml";
	@Getter private final int latestVersion = SVConstants.VERSION_BUKKIT_CONFIG;
	
	// Selection settings
	@ConfigOption(path = "selection.glowapi.enable")
	public static boolean	SELECTION_GLOWAPI_ENABLE;
	@ConfigOption(path = "selection.glowapi.color")
	public static String	SELECTION_GLOWAPI_COLOR;
	
	public BukkitConfigMain(SecurityVillagersPlugin plugin) {
		super(plugin);
	}
}
