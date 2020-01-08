package com.alessiodp.securityvillagers.common.configuration.data;

import com.alessiodp.core.common.ADPPlugin;
import com.alessiodp.core.common.configuration.ConfigOption;
import com.alessiodp.core.common.configuration.ConfigurationFile;
import lombok.NonNull;

import java.util.List;

public abstract class Messages extends ConfigurationFile {
	// Plugin messages
	@ConfigOption(path = "securityvillagers.update-available")
	public static String SECURITYVILLAGERS_UPDATEAVAILABLE;
	@ConfigOption(path = "securityvillagers.no-permission")
	public static String SECURITYVILLAGERS_NOPERMISSION;
	@ConfigOption(path = "securityvillagers.configuration-outdated")
	public static String SECURITYVILLAGERS_CONFIGURATION_OUTDATED;
	
	@ConfigOption(path = "securityvillagers.common-messages.invalid-command")
	public static String SECURITYVILLAGERS_COMMON_INVALIDCMD;
	@ConfigOption(path = "securityvillagers.common-messages.configuration-reloaded")
	public static String SECURITYVILLAGERS_COMMON_CONFIGRELOAD;
	
	
	// General settings
	@ConfigOption(path = "general.interact.hit")
	public static String GENERAL_INTERACT_HIT;
	@ConfigOption(path = "general.interact.shoot")
	public static String GENERAL_INTERACT_SHOOT;
	@ConfigOption(path = "general.interact.egg")
	public static String GENERAL_INTERACT_EGG;
	@ConfigOption(path = "general.interact.trade")
	public static String GENERAL_INTERACT_TRADE;
	@ConfigOption(path = "general.selection.selected")
	public static String GENERAL_SELECTION_SELECTED;
	@ConfigOption(path = "general.selection.required")
	public static String GENERAL_SELECTION_REQUIRED;
	
	
	// Commands settings
	@ConfigOption(path = "commands.changeage.baby")
	public static String CMD_CHANGEAGE_BABY;
	@ConfigOption(path = "commands.changeage.adult")
	public static String CMD_CHANGEAGE_ADULT;
	@ConfigOption(path = "commands.changeage.cooldown")
	public static String CMD_CHANGEAGE_COOLDOWN;
	@ConfigOption(path = "commands.changeage.failed")
	public static String CMD_CHANGEAGE_FAILED;
	@ConfigOption(path = "commands.profession.current")
	public static String CMD_PROFESSION_CURRENT;
	@ConfigOption(path = "commands.profession.changed")
	public static String CMD_PROFESSION_CHANGED;
	@ConfigOption(path = "commands.profession.cooldown")
	public static String CMD_PROFESSION_COOLDOWN;
	@ConfigOption(path = "commands.profession.not-found")
	public static String CMD_PROFESSION_NOTFOUND;
	@ConfigOption(path = "commands.profession.failed")
	public static String CMD_PROFESSION_FAILED;
	@ConfigOption(path = "commands.profession.wrong-command")
	public static String CMD_PROFESSION_WRONGCMD;
	@ConfigOption(path = "commands.protect.protected")
	public static String CMD_PROTECT_PROTECTED;
	@ConfigOption(path = "commands.protect.unprotected")
	public static String CMD_PROTECT_UNPROTECTED;
	@ConfigOption(path = "commands.protect.wrong-command")
	public static String CMD_PROTECT_WRONGCMD;
	@ConfigOption(path = "commands.rename.renamed")
	public static String CMD_RENAME_RENAMED;
	@ConfigOption(path = "commands.rename.removed")
	public static String CMD_RENAME_REMOVED;
	@ConfigOption(path = "commands.rename.wrong-command")
	public static String CMD_RENAME_WRONGCMD;
	@ConfigOption(path = "commands.version.updated")
	public static String CMD_VERSION_UPDATED;
	@ConfigOption(path = "commands.version.outdated")
	public static String CMD_VERSION_OUTDATED;
	
	
	// Help messages
	@ConfigOption(path = "help.header")
	public static String HELP_HEADER;
	@ConfigOption(path = "help.footer")
	public static String HELP_FOOTER;
	@ConfigOption(path = "help.console-help")
	public static List<String> HELP_CONSOLEHELP;
	
	@ConfigOption(path = "help.commands.help")
	public static String HELP_CMD_HELP;
	@ConfigOption(path = "help.commands.changeage")
	public static String HELP_CMD_CHANGEAGE;
	@ConfigOption(path = "help.commands.profession")
	public static String HELP_CMD_PROFESSION;
	@ConfigOption(path = "help.commands.protect")
	public static String HELP_CMD_PROTECT;
	@ConfigOption(path = "help.commands.reload")
	public static String HELP_CMD_RELOAD;
	@ConfigOption(path = "help.commands.rename")
	public static String HELP_CMD_RENAME;
	@ConfigOption(path = "help.commands.version")
	public static String HELP_CMD_VERSION;
	
	protected Messages(@NonNull ADPPlugin plugin) {
		super(plugin);
	}
	
	@Override
	public void loadDefaults() {
		loadDefaultConfigOptions();
	}
	
	@Override
	public void loadConfiguration() {
		loadConfigOptions();
	}
}
