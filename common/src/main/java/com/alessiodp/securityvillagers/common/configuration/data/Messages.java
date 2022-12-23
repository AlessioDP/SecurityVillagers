package com.alessiodp.securityvillagers.common.configuration.data;

import com.alessiodp.core.common.ADPPlugin;
import com.alessiodp.core.common.configuration.ConfigOption;
import com.alessiodp.core.common.configuration.ConfigurationFile;
import lombok.NonNull;

public abstract class Messages extends ConfigurationFile {
	// Plugin messages
	@ConfigOption(path = "securityvillagers.update-available")
	public static String SECURITYVILLAGERS_UPDATEAVAILABLE;
	@ConfigOption(path = "securityvillagers.configuration-outdated")
	public static String SECURITYVILLAGERS_CONFIGURATION_OUTDATED;
	@ConfigOption(path = "securityvillagers.no-permission")
	public static String SECURITYVILLAGERS_NOPERMISSION;
	
	@ConfigOption(path = "securityvillagers.common-messages.invalid-command")
	public static String SECURITYVILLAGERS_COMMON_INVALIDCMD;
	@ConfigOption(path = "securityvillagers.common-messages.configuration-reloaded")
	public static String SECURITYVILLAGERS_COMMON_CONFIGRELOAD;
	
	@ConfigOption(path = "securityvillagers.syntax.wrong-message")
	public static String SECURITYVILLAGERS_SYNTAX_WRONGMESSAGE;
	@ConfigOption(path = "securityvillagers.syntax.name")
	public static String SECURITYVILLAGERS_SYNTAX_NAME;
	@ConfigOption(path = "securityvillagers.syntax.player")
	public static String SECURITYVILLAGERS_SYNTAX_PLAYER;
	@ConfigOption(path = "securityvillagers.syntax.profession")
	public static String SECURITYVILLAGERS_SYNTAX_PROFESSION;
	
	
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
	@ConfigOption(path = "general.selection.unselected")
	public static String GENERAL_SELECTION_UNSELECTED;
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
	
	@ConfigOption(path = "commands.protect.protected")
	public static String CMD_PROTECT_PROTECTED;
	@ConfigOption(path = "commands.protect.unprotected")
	public static String CMD_PROTECT_UNPROTECTED;
	
	@ConfigOption(path = "commands.rename.renamed")
	public static String CMD_RENAME_RENAMED;
	@ConfigOption(path = "commands.rename.removed")
	public static String CMD_RENAME_REMOVED;
	
	@ConfigOption(path = "commands.teleport.teleported")
	public static String CMD_TELEPORT_TELEPORTED;
	@ConfigOption(path = "commands.teleport.player-not-found")
	public static String CMD_TELEPORT_PLAYER_NOT_FOUND;
	
	@ConfigOption(path = "commands.version.updated")
	public static String CMD_VERSION_UPDATED;
	@ConfigOption(path = "commands.version.outdated")
	public static String CMD_VERSION_OUTDATED;
	
	
	// Help messages
	@ConfigOption(path = "help.header")
	public static String HELP_HEADER;
	@ConfigOption(path = "help.footer")
	public static String HELP_FOOTER;
	@ConfigOption(path = "help.perform-command")
	public static String HELP_PERFORM_COMMAND;
	@ConfigOption(path = "help.console-help.header")
	public static String HELP_CONSOLEHELP_HEADER;
	@ConfigOption(path = "help.console-help.command")
	public static String HELP_CONSOLEHELP_COMMAND;
	
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
	@ConfigOption(path = "help.commands.teleport")
	public static String HELP_CMD_TELEPORT;
	@ConfigOption(path = "help.commands.version")
	public static String HELP_CMD_VERSION;
	
	@ConfigOption(path = "help.command-descriptions.help")
	public static String HELP_CMD_DESCRIPTIONS_HELP;
	@ConfigOption(path = "help.command-descriptions.changeage")
	public static String HELP_CMD_DESCRIPTIONS_CHANGEAGE;
	@ConfigOption(path = "help.command-descriptions.profession")
	public static String HELP_CMD_DESCRIPTIONS_PROFESSION;
	@ConfigOption(path = "help.command-descriptions.protect")
	public static String HELP_CMD_DESCRIPTIONS_PROTECT;
	@ConfigOption(path = "help.command-descriptions.reload")
	public static String HELP_CMD_DESCRIPTIONS_RELOAD;
	@ConfigOption(path = "help.command-descriptions.rename")
	public static String HELP_CMD_DESCRIPTIONS_RENAME;
	@ConfigOption(path = "help.command-descriptions.teleport")
	public static String HELP_CMD_DESCRIPTIONS_TELEPORT;
	@ConfigOption(path = "help.command-descriptions.version")
	public static String HELP_CMD_DESCRIPTIONS_VERSION;
	
	protected Messages(@NonNull ADPPlugin plugin) {
		super(plugin);
	}
}
