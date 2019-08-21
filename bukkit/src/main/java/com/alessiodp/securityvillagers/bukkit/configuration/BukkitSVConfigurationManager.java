package com.alessiodp.securityvillagers.bukkit.configuration;

import com.alessiodp.core.bukkit.configuration.adapter.BukkitConfigurationAdapter;
import com.alessiodp.core.common.configuration.adapter.ConfigurationAdapter;
import com.alessiodp.securityvillagers.bukkit.configuration.data.BukkitConfigMain;
import com.alessiodp.securityvillagers.bukkit.configuration.data.BukkitMessages;
import com.alessiodp.securityvillagers.common.SecurityVillagersPlugin;
import com.alessiodp.securityvillagers.common.configuration.SVConfigurationManager;

import java.nio.file.Path;

public class BukkitSVConfigurationManager extends SVConfigurationManager {
	
	public BukkitSVConfigurationManager(SecurityVillagersPlugin plugin) {
		super(plugin);
		
		getConfigs().add(new BukkitMessages(plugin));
		getConfigs().add(new BukkitConfigMain(plugin));
	}
	
	@Override
	protected ConfigurationAdapter initializeConfigurationAdapter(Path configurationFile) {
		return new BukkitConfigurationAdapter(configurationFile);
	}
}
