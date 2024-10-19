package com.alessiodp.securityvillagers.bukkit.configuration;

import com.alessiodp.securityvillagers.bukkit.configuration.data.BukkitConfigMain;
import com.alessiodp.securityvillagers.bukkit.configuration.data.BukkitMessages;
import com.alessiodp.securityvillagers.common.SecurityVillagersPlugin;
import com.alessiodp.securityvillagers.common.configuration.SVConfigurationManager;
import com.alessiodp.securityvillagers.common.configuration.data.ConfigMain;

public class BukkitSVConfigurationManager extends SVConfigurationManager {
	
	public BukkitSVConfigurationManager(SecurityVillagersPlugin plugin) {
		super(plugin);
		
		getConfigs().add(new BukkitMessages(plugin));
		getConfigs().add(new BukkitConfigMain(plugin));
	}
	
	@Override
	protected boolean isAutoUpgradeEnabled() {
		return ConfigMain.SECURITYVILLAGERS_AUTOMATIC_UPGRADE_CONFIGS;
	}
}
