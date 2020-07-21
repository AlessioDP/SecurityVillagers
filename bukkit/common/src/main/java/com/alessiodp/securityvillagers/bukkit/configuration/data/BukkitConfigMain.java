package com.alessiodp.securityvillagers.bukkit.configuration.data;

import com.alessiodp.securityvillagers.common.SecurityVillagersPlugin;
import com.alessiodp.securityvillagers.common.configuration.SVConstants;
import com.alessiodp.securityvillagers.common.configuration.data.ConfigMain;
import lombok.Getter;

public class BukkitConfigMain extends ConfigMain {
	@Getter private final String fileName = "config.yml";
	@Getter private final String resourceName = "bukkit/config.yml";
	@Getter private final int latestVersion = SVConstants.VERSION_BUKKIT_CONFIG;
	
	public BukkitConfigMain(SecurityVillagersPlugin plugin) {
		super(plugin);
	}
}
