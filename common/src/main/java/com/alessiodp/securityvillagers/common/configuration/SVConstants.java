package com.alessiodp.securityvillagers.common.configuration;

import com.alessiodp.core.common.logging.ConsoleColor;

public class SVConstants {
	
	// Plugin settings
	public static final String PLUGIN_NAME = "SecurityVillagers";
	public static final String PLUGIN_FALLBACK = "securityvillagers";
	public static final ConsoleColor PLUGIN_CONSOLECOLOR = ConsoleColor.RED;
	public static final String PLUGIN_PACKAGENAME = "com.alessiodp.securityvillagers";
	public static final String PLUGIN_SPIGOTCODE = "6064";
	public static final int PLUGIN_BSTATS_BUKKIT_ID = 525;
	
	
	// Versions
	public static final int VERSION_BUKKIT_CONFIG = 6;
	public static final int VERSION_BUKKIT_MESSAGES = 5;
	public static final int VERSION_DATABASE_YAML = 1;
	
	
	// Debug messages
	public static final String DEBUG_CMD_RELOADED = "%s reloaded the configuration";
	
	public static final String DEBUG_DB_UPDATEENTITY = "Update entity request for %s [%s]";
	public static final String DEBUG_DB_GETALLPROTECTED = "Get all protected entities request";
	
	public static final String DEBUG_PROTECTION_IMMORTAL = "Bypass damage due to immortal protection on %s";
	public static final String DEBUG_PROTECTION_HIT_PLAYER = "Prevented damage by player '%s' hit on %s";
	public static final String DEBUG_PROTECTION_HIT_MOB = "Prevented damage/target by mob '%s' hit on %s";
	public static final String DEBUG_PROTECTION_PROJECTILE_PLAYER = "Prevented damage by player '%s' projectile shoot on %s";
	public static final String DEBUG_PROTECTION_PROJECTILE_MOB = "Prevented damage by mob '%s' projectile shoot on %s";
	public static final String DEBUG_PROTECTION_PROJECTILE_DISPENSER = "Prevented damage by dispenser projectile shoot on %s";
	public static final String DEBUG_DEATH_PROTECTEDENTITY = "The protected entity %s [%s] is dead due to %s";
	
	public static final String DEBUG_TASK_CHANGEAGE_START = "ChangeAge cooldown of %d started for %s";
	public static final String DEBUG_TASK_CHANGEAGE_EXPIRE = "ChangeAge cooldown expired for %s";
	public static final String DEBUG_TASK_PROFESSION_START = "Profession cooldown of %d started for %s";
	public static final String DEBUG_TASK_PROFESSION_EXPIRE = "Profession cooldown expired for %s";
}
