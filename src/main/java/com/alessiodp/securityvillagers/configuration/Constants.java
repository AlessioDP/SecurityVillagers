package com.alessiodp.securityvillagers.configuration;

public class Constants {
	public static final int VERSION_CONFIG = 8;
	public static final int VERSION_MESSAGES = 5;
	
	public static final String CONFIGURATION_OUTDATED = "Configuration file outdated";
	public static final String MESSAGES_OUTDATED = "Messages file outdated";
	
	/*
	 * Updater
	 */
	public static final String UPDATER_FOUND = "SecurityVillagers v{currentVersion} found a new version: {newVersion}";
	public static final String UPDATER_FAILED_IO = "SecurityVillagers could not contact alessiodp.com for updating.";
	public static final String UPDATER_FAILED_GENERAL = "SecurityVillagers could not check for updates.";
	public static final String UPDATER_FALLBACK_URL = "https://www.spigotmc.org/api/general.php";
	public static final String UPDATER_FALLBACK_KEY = "98BE0FE67F88AB82B4C197FAF1DC3B69206EFDCC4D3B80FC83A00037510B99B4";
	public static final String UPDATER_FALLBACK_RESOURCE = "6064";
	public static final String UPDATER_FALLBACK_WARN = "SecurityVillagers will check updates manually via Spigot API.";
	public static final String UPDATER_URL = "https://api.alessiodp.com/version.php?plugin=securityvillagers&version={version}";
	public static final String UPDATER_FIELD_VERSION = "version";
	public static final String UPDATER_DELIMITER_TYPE = "\\-";
	public static final String UPDATER_DELIMITER_VERSION = "\\.";
	
	/*
	 * Log
	 */
	public static final String DEBUG_SV_ENABLING = "Initializing SecurityVillagers {version}";
	public static final String DEBUG_SV_ENABLED = "SecurityVillagers v{version} enabled";
	public static final String DEBUG_SV_DISABLED = "SecurityVillagers disabled";
	
	public static final String DEBUG_ADDON_HOOK = "Hooked into {addon}";
	public static final String DEBUG_ADDON_MISSING = "Failed to hook into {addon}, disabled its features";
}
