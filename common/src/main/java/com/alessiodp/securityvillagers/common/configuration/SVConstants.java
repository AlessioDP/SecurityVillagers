package com.alessiodp.securityvillagers.common.configuration;

import com.alessiodp.core.common.logging.ConsoleColor;

public class SVConstants {
	
	// Plugin settings
	public static final String PLUGIN_NAME = "SecurityVillagers";
	public static final String PLUGIN_FALLBACK = "securityvillagers";
	public static final ConsoleColor PLUGIN_CONSOLECOLOR = ConsoleColor.RED;
	public static final String PLUGIN_SPIGOTCODE = "6064";
	
	
	// Versions
	public static final int VERSION_BUKKIT_CONFIG = 2;
	public static final int VERSION_BUKKIT_MESSAGES = 2;
	public static final int VERSION_DATABASE_YAML = 1;
	
	
	// Debug messages
	public static final String DEBUG_CMD_CHANGEAGE = "{player} performed changeage command with '{value}'";
	public static final String DEBUG_CMD_CHANGEAGE_TASK = "Started ChangeAgeCooldown for {value} by {player}";
	public static final String DEBUG_CMD_HELP = "{player} performed help command with page '{page}'";
	public static final String DEBUG_CMD_PROFESSION = "{player} performed profession command with '{value}'";
	public static final String DEBUG_CMD_PROFESSION_TASK = "Started ProfessionCooldown for {value} by {player}";
	public static final String DEBUG_CMD_PROTECT = "{player} performed protect command with '{value}'";
	public static final String DEBUG_CMD_RELOAD = "{player} performed reload command";
	public static final String DEBUG_CMD_RELOAD_CONSOLE = "Console performed reload command";
	public static final String DEBUG_CMD_RELOADED = "Configuration reloaded by {player}";
	public static final String DEBUG_CMD_RELOADED_CONSOLE = "Configuration reloaded";
	public static final String DEBUG_CMD_RENAME = "{player} performed rename command with '{value}'";
	public static final String DEBUG_CMD_VERSION = "{player} performed version command";
	public static final String DEBUG_CMD_VERSION_CONSOLE = "Performed version command";
	
	public static final String DEBUG_DB_UPDATEENTITY = "Update entity request for {mob} [{uuid}]";
	public static final String DEBUG_DB_GETALLPROTECTED = "Get all protected entities request";
	
	public static final String DEBUG_PROTECTION_IMMORTAL = "Bypass damage due to immortal protection on {entity}";
	public static final String DEBUG_PROTECTION_HIT_PLAYER = "Prevented damage by player '{player}' hit on {entity}";
	public static final String DEBUG_PROTECTION_HIT_MOB = "Prevented damage/target by {mob} hit on {entity}";
	public static final String DEBUG_PROTECTION_PROJECTILE_PLAYER = "Prevented damage by player '{player}' projectile shoot on {entity}";
	public static final String DEBUG_PROTECTION_PROJECTILE_MOB = "Prevented damage by {mob} projectile shoot on {entity}";
	public static final String DEBUG_DEATH_PROTECTEDENTITY = "The protected entity {mob} [{uuid}] is dead due to {cause}";
	
	public static final String DEBUG_TASK_CHANGEAGE_EXPIRE = "ChangeAge cooldown expired for {uuid}";
	public static final String DEBUG_TASK_PROFESSION_EXPIRE = "Profession cooldown expired for {uuid}";
}
