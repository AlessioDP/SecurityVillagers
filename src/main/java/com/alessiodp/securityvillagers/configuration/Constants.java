package com.alessiodp.securityvillagers.configuration;

public class Constants {
	public static final int CURSE_PROJECT_ID = 45026;
	
	public static final int VERSION_CONFIG = 7;
	public static final int VERSION_MESSAGES = 5;
	
	public static final String CONFIGURATION_OUTDATED = "Configuration file outdated";
	public static final String MESSAGES_OUTDATED = "Messages file outdated";
	
	/*
	 * Updater
	 */
	public static final String UPDATER_FOUND = "SecurityVillagers v{currentVersion} found a new version: {newVersion}";
	public static final String UPDATER_FAILED_IO = "SecurityVillagers could not contact alessiodp.com for updating.";
	public static final String UPDATER_FAILED_GENERAL = "SecurityVillagers could not check for updates.";
	public static final String UPDATER_FALLBACK = "SecurityVillagers will use Gravity Updater to check for updates.";
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
