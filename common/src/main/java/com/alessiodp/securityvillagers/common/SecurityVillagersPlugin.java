package com.alessiodp.securityvillagers.common;

import com.alessiodp.core.common.ADPPlugin;
import com.alessiodp.core.common.bootstrap.ADPBootstrap;
import com.alessiodp.core.common.configuration.Constants;
import com.alessiodp.core.common.libraries.LibraryUsage;
import com.alessiodp.core.common.logging.ConsoleColor;
import com.alessiodp.securityvillagers.api.SecurityVillagers;
import com.alessiodp.securityvillagers.api.interfaces.SecurityVillagersAPI;
import com.alessiodp.securityvillagers.common.events.EventManager;
import com.alessiodp.securityvillagers.common.utils.SecurityVillagersPermission;
import com.alessiodp.securityvillagers.common.configuration.SVConstants;
import com.alessiodp.securityvillagers.common.configuration.data.ConfigMain;
import com.alessiodp.securityvillagers.common.configuration.data.Messages;
import com.alessiodp.securityvillagers.common.storage.SVDatabaseManager;
import com.alessiodp.securityvillagers.common.utils.SVPlayerUtils;
import com.alessiodp.securityvillagers.common.villagers.VillagerManager;
import lombok.Getter;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public abstract class SecurityVillagersPlugin extends ADPPlugin {
	// Plugin fields
	@Getter private final String pluginName = SVConstants.PLUGIN_NAME;
	@Getter private final String pluginFallbackName = SVConstants.PLUGIN_FALLBACK;
	@Getter private final ConsoleColor consoleColor = SVConstants.PLUGIN_CONSOLECOLOR;
	@Getter private final String packageName = SVConstants.PLUGIN_PACKAGENAME;
	
	// SecurityVillagers fields
	@Getter protected SecurityVillagersAPI api;
	@Getter protected EventManager eventManager;
	@Getter protected VillagerManager villagerManager;
	@Getter protected HashMap<UUID, Long> changeAgeCooldown;
	@Getter protected HashMap<UUID, Long> professionCooldown;
	
	public SecurityVillagersPlugin(ADPBootstrap bootstrap) {
		super(bootstrap);
	}
	
	@Override
	public void onDisabling() {
		// Nothing to disable
	}
	
	@Override
	protected void initializeCore() {
		databaseManager = new SVDatabaseManager(this);
		professionCooldown = new HashMap<>();
		changeAgeCooldown = new HashMap<>();
	}
	
	@Override
	protected void loadCore() {
		getConfigurationManager().reload();
		reloadLoggerManager();
		getDatabaseManager().reload();
	}
	
	@Override
	protected void postHandle() {
		playerUtils = new SVPlayerUtils();
		
		getVillagerManager().reload();
		getCommandManager().setup();
		registerListeners();
		
		reloadAdpUpdater();
		getAddonManager().loadAddons();
		SecurityVillagers.setApi(api);
	}
	
	protected abstract void registerListeners();
	
	@Override
	public void reloadConfiguration() {
		getLoggerManager().logDebug(Constants.DEBUG_PLUGIN_RELOADING, true);
		getConfigurationManager().reload();
		reloadLoggerManager();
		getDatabaseManager().reload();
		
		getVillagerManager().reload();
		getAddonManager().loadAddons();
		getCommandManager().setup();
		
		reloadAdpUpdater();
	}
	
	@Override
	public SVDatabaseManager getDatabaseManager() {
		return (SVDatabaseManager) databaseManager;
	}
	
	private void reloadLoggerManager() {
		getLoggerManager().reload(
				ConfigMain.SECURITYVILLAGERS_LOGGING_DEBUG,
				ConfigMain.SECURITYVILLAGERS_LOGGING_SAVE_ENABLE,
				ConfigMain.SECURITYVILLAGERS_LOGGING_SAVE_FILE,
				ConfigMain.SECURITYVILLAGERS_LOGGING_SAVE_FORMAT
		);
	}
	
	private void reloadAdpUpdater() {
		getAdpUpdater().reload(
				getPluginFallbackName(),
				SVConstants.PLUGIN_SPIGOTCODE,
				ConfigMain.SECURITYVILLAGERS_UPDATES_CHECK,
				ConfigMain.SECURITYVILLAGERS_UPDATES_WARN,
				SecurityVillagersPermission.ADMIN_ALERTS,
				Messages.SECURITYVILLAGERS_UPDATEAVAILABLE
		);
		getAdpUpdater().asyncTaskCheckUpdates();
	}
	
	@Override
	public List<LibraryUsage> getLibrariesUsages() {
		return Collections.emptyList();
	}
}
