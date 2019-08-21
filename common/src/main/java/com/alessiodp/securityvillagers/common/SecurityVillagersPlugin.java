package com.alessiodp.securityvillagers.common;

import com.alessiodp.core.common.ADPPlugin;
import com.alessiodp.core.common.bootstrap.ADPBootstrap;
import com.alessiodp.core.common.configuration.Constants;
import com.alessiodp.core.common.logging.ConsoleColor;
import com.alessiodp.securityvillagers.common.commands.utils.SecurityVillagersPermission;
import com.alessiodp.securityvillagers.common.configuration.SVConstants;
import com.alessiodp.securityvillagers.common.configuration.data.ConfigMain;
import com.alessiodp.securityvillagers.common.configuration.data.Messages;
import com.alessiodp.securityvillagers.common.storage.SVDatabaseManager;
import com.alessiodp.securityvillagers.common.utils.SVPlayerUtils;
import com.alessiodp.securityvillagers.common.villagers.VillagerManager;
import lombok.Getter;

public abstract class SecurityVillagersPlugin extends ADPPlugin {
	// Plugin fields
	@Getter private final String pluginName = SVConstants.PLUGIN_NAME;
	@Getter private final String pluginFallbackName = SVConstants.PLUGIN_FALLBACK;
	@Getter private final ConsoleColor consoleColor = SVConstants.PLUGIN_CONSOLECOLOR;
	
	// SecurityVillagers fields
	@Getter protected VillagerManager villagerManager;
	
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
				SecurityVillagersPermission.ADMIN_ALERTS.toString(),
				Messages.SECURITYVILLAGERS_UPDATEAVAILABLE
		);
		getAdpUpdater().asyncTaskCheckUpdates();
	}
}