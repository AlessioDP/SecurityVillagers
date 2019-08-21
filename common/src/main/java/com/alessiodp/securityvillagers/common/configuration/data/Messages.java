package com.alessiodp.securityvillagers.common.configuration.data;

import com.alessiodp.core.common.ADPPlugin;
import com.alessiodp.core.common.configuration.ConfigurationFile;
import com.alessiodp.core.common.configuration.adapter.ConfigurationAdapter;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.List;

public abstract class Messages extends ConfigurationFile {
	// Plugin messages
	public static String SECURITYVILLAGERS_UPDATEAVAILABLE;
	public static String SECURITYVILLAGERS_NOPERMISSION;
	public static String SECURITYVILLAGERS_CONFIGURATION_OUTDATED;
	
	public static String SECURITYVILLAGERS_COMMON_INVALIDCMD;
	public static String SECURITYVILLAGERS_COMMON_CONFIGRELOAD;
	
	
	// General settings
	public static String GENERAL_INTERACT_HIT;
	public static String GENERAL_INTERACT_SHOOT;
	public static String GENERAL_INTERACT_EGG;
	public static String GENERAL_INTERACT_TRADE;
	public static String GENERAL_SELECTION_SELECTED;
	public static String GENERAL_SELECTION_REQUIRED;
	
	
	// Commands settings
	public static String CMD_CHANGEAGE_BABY;
	public static String CMD_CHANGEAGE_ADULT;
	public static String CMD_CHANGEAGE_FAILED;
	public static String CMD_PROFESSION_CURRENT;
	public static String CMD_PROFESSION_CHANGED;
	public static String CMD_PROFESSION_FAILED;
	public static String CMD_PROFESSION_NOTFOUND;
	public static String CMD_PROFESSION_WRONGCMD;
	public static String CMD_PROTECT_PROTECTED;
	public static String CMD_PROTECT_UNPROTECTED;
	public static String CMD_PROTECT_WRONGCMD;
	public static String CMD_RENAME_RENAMED;
	public static String CMD_RENAME_REMOVED;
	public static String CMD_RENAME_WRONGCMD;
	public static String CMD_VERSION_UPDATED;
	public static String CMD_VERSION_OUTDATED;
	
	
	// Help messages
	public static String HELP_HEADER;
	public static String HELP_FOOTER;
	public static List<String> HELP_CONSOLEHELP;
	
	public static String HELP_CMD_HELP;
	public static String HELP_CMD_CHANGEAGE;
	public static String HELP_CMD_PROFESSION;
	public static String HELP_CMD_PROTECT;
	public static String HELP_CMD_RELOAD;
	public static String HELP_CMD_RENAME;
	public static String HELP_CMD_VERSION;
	
	protected Messages(@NonNull ADPPlugin plugin) {
		super(plugin);
	}
	
	
	@Override
	public void loadDefaults() {
		// Plugin messages
		SECURITYVILLAGERS_UPDATEAVAILABLE = "&6New version of SecurityVillagers found: %version% (Current: %thisversion%)";
		SECURITYVILLAGERS_NOPERMISSION = "&cYou do not have access to that command";
		SECURITYVILLAGERS_CONFIGURATION_OUTDATED = "&cThe configuration file '%config%' of SecurityVillagers is outdated!";
		
		SECURITYVILLAGERS_COMMON_INVALIDCMD = "&cInvalid command";
		SECURITYVILLAGERS_COMMON_CONFIGRELOAD = "&aConfiguration reloaded";
		
		
		// General settings
		GENERAL_INTERACT_HIT = "&cYou can't hit this mob!";
		GENERAL_INTERACT_SHOOT = "&cYou can't shoot this mob!";
		GENERAL_INTERACT_EGG = "&cYou can't use eggs on this mob";
		GENERAL_INTERACT_TRADE = "&cYou can't trade with this mob!";
		
		GENERAL_SELECTION_SELECTED = "&aMob selected";
		GENERAL_SELECTION_REQUIRED = "&cYou have to select a mob first!";
		
		
		// Commands settings
		CMD_CHANGEAGE_BABY = "&aTurned the mob into a baby!";
		CMD_CHANGEAGE_ADULT = "&aTurned the mob into an adult!";
		CMD_CHANGEAGE_FAILED = "&cYou cannot change the age of this mob!";
		
		CMD_PROFESSION_CURRENT = "&aThe villager is a %profession%";
		CMD_PROFESSION_CHANGED = "&aChanged villager profession to %profession%";
		CMD_PROFESSION_NOTFOUND = "&cProfession '%profession%' not found!";
		CMD_PROFESSION_FAILED = "&cYou cannot change the profession of this mob!";
		CMD_PROFESSION_WRONGCMD = "&cWrong variables: Type &7/sv profession <profession>";
		
		CMD_PROTECT_PROTECTED = "&aThe selected mob is now protected";
		CMD_PROTECT_UNPROTECTED = "&cThe selected mob is not protected anymore";
		CMD_PROTECT_WRONGCMD = "&cWrong variables: Type &7/sv protect [on/off]";
		
		CMD_RENAME_RENAMED = "&aThe mob name has been set to %name%";
		CMD_RENAME_REMOVED = "&aRemoved mob name";
		CMD_RENAME_WRONGCMD = "&Wrong variables: Type &7/sv rename <name/remove>";
		
		CMD_VERSION_UPDATED = "&6&lSecurityVillagers &7%version% - Developed by &6AlessioDP";
		CMD_VERSION_OUTDATED = "&6&lSecurityVillagers &7%version% - Developed by &6AlessioDP\n&aNew version found: &2%newversion%";
		
		
		// Help messages
		HELP_HEADER = "&6============ &lSecurityVillagers Help Page &r&6============";
		HELP_FOOTER = "";
		HELP_CONSOLEHELP = new ArrayList<>();
		HELP_CONSOLEHELP.add("You can only make these commands:");
		HELP_CONSOLEHELP.add(" > oa reload - Reload the configuration");
		HELP_CONSOLEHELP.add(" > oa version - Show SecurityVillagers information");
		
		HELP_CMD_HELP = "{\"text\":\"\",\"extra\":[{\"text\":\"/sv help\",\"color\":\"gold\"},{\"text\":\" - Show help pages\",\"color\":\"gray\"}],\"clickEvent\":{\"action\":\"suggest_command\",\"value\":\"/sv help \"},\"hoverEvent\":{\"action\":\"show_text\",\"value\":{\"text\":\"Perform the command\",\"color\":\"gold\"}}}";
		HELP_CMD_CHANGEAGE = "{\"text\":\"\",\"extra\":[{\"text\":\"/sv changeage\",\"color\":\"gold\"},{\"text\":\" - Change mob age\",\"color\":\"gray\"}],\"clickEvent\":{\"action\":\"suggest_command\",\"value\":\"/sv changeage\"},\"hoverEvent\":{\"action\":\"show_text\",\"value\":{\"text\":\"Perform the command\",\"color\":\"gold\"}}}";
		HELP_CMD_PROFESSION = "{\"text\":\"\",\"extra\":[{\"text\":\"/sv profession <profession>\",\"color\":\"gold\"},{\"text\":\" - Change villager profession\",\"color\":\"gray\"}],\"clickEvent\":{\"action\":\"suggest_command\",\"value\":\"/sv profession \"},\"hoverEvent\":{\"action\":\"show_text\",\"value\":{\"text\":\"Perform the command\",\"color\":\"gold\"}}}";
		HELP_CMD_PROTECT = "{\"text\":\"\",\"extra\":[{\"text\":\"/sv protect [on/off]\",\"color\":\"gold\"},{\"text\":\" - Protect/unprotect mob\",\"color\":\"gray\"}],\"clickEvent\":{\"action\":\"suggest_command\",\"value\":\"/sv protect\"},\"hoverEvent\":{\"action\":\"show_text\",\"value\":{\"text\":\"Perform the command\",\"color\":\"gold\"}}}";
		HELP_CMD_RELOAD = "{\"text\":\"\",\"extra\":[{\"text\":\"/sv reload\",\"color\":\"gold\"},{\"text\":\" - Reload SecurityVillagers configuration files\",\"color\":\"gray\"}],\"clickEvent\":{\"action\":\"suggest_command\",\"value\":\"/sv reload\"},\"hoverEvent\":{\"action\":\"show_text\",\"value\":{\"text\":\"Perform the command\",\"color\":\"gold\"}}}";
		HELP_CMD_RENAME = "{\"text\":\"\",\"extra\":[{\"text\":\"/sv rename <name/remove>\",\"color\":\"gold\"},{\"text\":\" - Change mob name\",\"color\":\"gray\"}],\"clickEvent\":{\"action\":\"suggest_command\",\"value\":\"/sv rename \"},\"hoverEvent\":{\"action\":\"show_text\",\"value\":{\"text\":\"Perform the command\",\"color\":\"gold\"}}}";
		HELP_CMD_VERSION = "{\"text\":\"\",\"extra\":[{\"text\":\"/sv version\",\"color\":\"gold\"},{\"text\":\" - Show SecurityVillagers information\",\"color\":\"gray\"}],\"clickEvent\":{\"action\":\"suggest_command\",\"value\":\"/sv version\"},\"hoverEvent\":{\"action\":\"show_text\",\"value\":{\"text\":\"Perform the command\",\"color\":\"gold\"}}}";
	}
	
	@Override
	public void loadConfiguration(ConfigurationAdapter confAdapter) {
		// Plugin messages
		SECURITYVILLAGERS_UPDATEAVAILABLE = confAdapter.getString("securityvillagers.update-available", SECURITYVILLAGERS_UPDATEAVAILABLE);
		SECURITYVILLAGERS_NOPERMISSION = confAdapter.getString("securityvillagers.no-permission", SECURITYVILLAGERS_NOPERMISSION);
		SECURITYVILLAGERS_CONFIGURATION_OUTDATED = confAdapter.getString("securityvillagers.configuration-outdated", SECURITYVILLAGERS_CONFIGURATION_OUTDATED);
		
		SECURITYVILLAGERS_COMMON_INVALIDCMD = confAdapter.getString("securityvillagers.common-messages.invalid-command", SECURITYVILLAGERS_COMMON_INVALIDCMD);
		SECURITYVILLAGERS_COMMON_CONFIGRELOAD = confAdapter.getString("securityvillagers.common-messages.configuration-reloaded", SECURITYVILLAGERS_COMMON_CONFIGRELOAD);
		
		
		// General settings
		GENERAL_INTERACT_HIT = confAdapter.getString("general.interact.hit", GENERAL_INTERACT_HIT);
		GENERAL_INTERACT_SHOOT = confAdapter.getString("general.interact.shoot", GENERAL_INTERACT_SHOOT);
		GENERAL_INTERACT_EGG = confAdapter.getString("general.interact.egg", GENERAL_INTERACT_EGG);
		GENERAL_INTERACT_TRADE = confAdapter.getString("general.interact.trade", GENERAL_INTERACT_TRADE);
		GENERAL_SELECTION_SELECTED = confAdapter.getString("general.selection.selected", GENERAL_SELECTION_SELECTED);
		GENERAL_SELECTION_REQUIRED = confAdapter.getString("general.selection.required", GENERAL_SELECTION_REQUIRED);
		
		
		// Commands settings
		CMD_CHANGEAGE_BABY = confAdapter.getString("commands.changeage.baby", CMD_CHANGEAGE_BABY);
		CMD_CHANGEAGE_ADULT = confAdapter.getString("commands.changeage.adult", CMD_CHANGEAGE_ADULT);
		CMD_CHANGEAGE_FAILED = confAdapter.getString("commands.changeage.failed", CMD_CHANGEAGE_FAILED);
		
		CMD_PROFESSION_CURRENT = confAdapter.getString("commands.profession.current", CMD_PROFESSION_CURRENT);
		CMD_PROFESSION_CHANGED = confAdapter.getString("commands.profession.changed", CMD_PROFESSION_CHANGED);
		CMD_PROFESSION_NOTFOUND = confAdapter.getString("commands.profession.not-found", CMD_PROFESSION_NOTFOUND);
		CMD_PROFESSION_FAILED = confAdapter.getString("commands.profession.failed", CMD_PROFESSION_FAILED);
		CMD_PROFESSION_WRONGCMD = confAdapter.getString("commands.profession.wrong-command", CMD_PROFESSION_WRONGCMD);
		
		CMD_PROTECT_PROTECTED = confAdapter.getString("commands.protect.protected", CMD_PROTECT_PROTECTED);
		CMD_PROTECT_UNPROTECTED = confAdapter.getString("commands.protect.unprotected", CMD_PROTECT_UNPROTECTED);
		CMD_PROTECT_WRONGCMD = confAdapter.getString("commands.protect.wrong-command", CMD_PROTECT_WRONGCMD);
		
		CMD_RENAME_RENAMED = confAdapter.getString("commands.rename.renamed", CMD_RENAME_RENAMED);
		CMD_RENAME_REMOVED = confAdapter.getString("commands.rename.removed", CMD_RENAME_REMOVED);
		CMD_RENAME_WRONGCMD = confAdapter.getString("commands.rename.wrong-command", CMD_RENAME_WRONGCMD);
		
		CMD_VERSION_UPDATED = confAdapter.getString("commands.version.updated", CMD_VERSION_UPDATED);
		CMD_VERSION_OUTDATED = confAdapter.getString("commands.version.outdated", CMD_VERSION_OUTDATED);
		
		
		// Help messages
		HELP_HEADER = confAdapter.getString("help.header", HELP_HEADER);
		HELP_FOOTER = confAdapter.getString("help.footer", HELP_FOOTER);
		HELP_CONSOLEHELP = confAdapter.getStringList("help.console-help", HELP_CONSOLEHELP);
		
		HELP_CMD_HELP = confAdapter.getString("help.commands.help", HELP_CMD_HELP);
		HELP_CMD_CHANGEAGE = confAdapter.getString("help.commands.changeage", HELP_CMD_CHANGEAGE);
		HELP_CMD_PROFESSION = confAdapter.getString("help.commands.profession", HELP_CMD_PROFESSION);
		HELP_CMD_PROTECT = confAdapter.getString("help.commands.protect", HELP_CMD_PROTECT);
		HELP_CMD_RELOAD = confAdapter.getString("help.commands.reload", HELP_CMD_RELOAD);
		HELP_CMD_RENAME = confAdapter.getString("help.commands.rename", HELP_CMD_RENAME);
		HELP_CMD_VERSION = confAdapter.getString("help.commands.version", HELP_CMD_VERSION);
	}
}
