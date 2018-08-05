package com.alessiodp.securityvillagers.configuration.data;

import com.alessiodp.securityvillagers.SecurityVillagers;
import com.alessiodp.securityvillagers.configuration.Constants;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.ArrayList;
import java.util.List;

public class Messages {
	
	public static String SECURITYVILLAGERS_UPDATEAVAILABLE;
	public static String SECURITYVILLAGERS_NOPERMISSION;
	public static String SECURITYVILLAGERS_CONFIGURATIONRELOADED;
	public static String SECURITYVILLAGERS_NOCONSOLE;
	public static String SECURITYVILLAGERS_NOTFOUND;
	public static List<String> SECURITYVILLAGERS_HELP;
	
	public static String PROTECTION_PROTECTED;
	public static String PROTECTION_UNPROTECTED;
	public static String PROTECTION_GLOBAL;
	
	public static String INTERACT_HIT;
	public static String INTERACT_SHOOT;
	public static String INTERACT_DISABLED_EGG;
	public static String INTERACT_DISABLED_TRADE;
	
	public static String SELECTION_SELECTED;
	public static String SELECTION_MISSING;
	
	public static String PROFESSION_CURRENT;
	public static String PROFESSION_DONE;
	public static String PROFESSION_WRONGCMD;
	
	public static String CONVERTER_INTO_BABY;
	public static String CONVERTER_INTO_ADULT;
	
	public static String RENAME_RENAME;
	public static String RENAME_REMOVE;
	
	public static String IRONGOLEM_HIT;
	public static String IRONGOLEM_SHOOT;
	
	private SecurityVillagers plugin;
	
	public Messages(SecurityVillagers instance) {
		plugin = instance;
		loadDefaults();
	}
	
	public void loadDefaults() {
		SECURITYVILLAGERS_UPDATEAVAILABLE = "&9New version of SecurityVillagers found: %version% (Current: %thisversion%)";
		SECURITYVILLAGERS_NOPERMISSION = "&cYou do not have access to that command";
		SECURITYVILLAGERS_CONFIGURATIONRELOADED = "&aConfiguration reloaded";
		SECURITYVILLAGERS_NOCONSOLE = "You need to be a player to perform this command";
		SECURITYVILLAGERS_NOTFOUND = "&cCommand not found";
		SECURITYVILLAGERS_HELP = new ArrayList<>();
		SECURITYVILLAGERS_HELP.add("&7===============[ &6SecurityVillagers &7]===============");
		SECURITYVILLAGERS_HELP.add("&6/sv help &7- Print this help page");
		SECURITYVILLAGERS_HELP.add("&6/sv changeage &7- Change the villager age");
		SECURITYVILLAGERS_HELP.add("&6/sv profession [profession] &7- Change the villager profession");
		SECURITYVILLAGERS_HELP.add("&6/sv protect &7- Protect/unprotect the villager");
		SECURITYVILLAGERS_HELP.add("&6/sv rename [name] &7- Rename the villager");
		SECURITYVILLAGERS_HELP.add("&6/sv reload &7- Reload the configuration");
		
		PROTECTION_PROTECTED = "&aThis villager will be protected";
		PROTECTION_UNPROTECTED = "&cThis villager isn't protected anymore!";
		PROTECTION_GLOBAL = "&cYou don't need to use this command, protection is global!";
		
		INTERACT_HIT = "&cYou can't hit villagers!";
		INTERACT_SHOOT = "&cYou can't shoot villagers!";
		INTERACT_DISABLED_EGG = "&cYou can't use eggs";
		INTERACT_DISABLED_TRADE = "&cYou can't trade with villagers!";
		
		SELECTION_SELECTED = "&aVillager selected";
		SELECTION_MISSING = "&cYou have to select a villager!";
		
		PROFESSION_CURRENT = "&aThe villager is a %profession%";
		PROFESSION_DONE =  "&aChanged villager profession to %profession%";
		PROFESSION_WRONGCMD = "&cWrong command: Use /sv profession <profession>:\n&cAvailable: Normal, Nitwit, Husk, Blacksmith, Butcher, Farmer, Librarian and Priest";
		
		CONVERTER_INTO_BABY = "&aTurned the villager into a baby!";
		CONVERTER_INTO_ADULT = "&aTurned the villager into an adult!";
		
		RENAME_RENAME = "&aVillager name set to %name%";
		RENAME_REMOVE = "&aRemoved villager name";
		
		IRONGOLEM_HIT = "&cYou can't hit iron golems!";
		IRONGOLEM_SHOOT = "&cYou can't shoot iron golems!";
	}
	
	public void loadConfiguration(FileConfiguration fileConfiguration) {
		SECURITYVILLAGERS_UPDATEAVAILABLE = fileConfiguration.getString("securityvillagers.update-available", SECURITYVILLAGERS_UPDATEAVAILABLE);
		SECURITYVILLAGERS_NOPERMISSION = fileConfiguration.getString("securityvillagers.no-permission", SECURITYVILLAGERS_NOPERMISSION);
		SECURITYVILLAGERS_CONFIGURATIONRELOADED = fileConfiguration.getString("securityvillagers.configuration-reloaded", SECURITYVILLAGERS_CONFIGURATIONRELOADED);
		SECURITYVILLAGERS_NOCONSOLE = fileConfiguration.getString("securityvillagers.no-console", SECURITYVILLAGERS_NOCONSOLE);
		SECURITYVILLAGERS_NOTFOUND = fileConfiguration.getString("securityvillagers.not-found", SECURITYVILLAGERS_NOTFOUND);
		SECURITYVILLAGERS_HELP = fileConfiguration.getStringList("securityvillagers.help");
		
		PROTECTION_PROTECTED = fileConfiguration.getString("protection.protected", PROTECTION_PROTECTED);
		PROTECTION_UNPROTECTED = fileConfiguration.getString("protection.unprotected", PROTECTION_UNPROTECTED);
		PROTECTION_GLOBAL = fileConfiguration.getString("protection.is-global", PROTECTION_GLOBAL);
		
		INTERACT_HIT = fileConfiguration.getString("interact.hit", INTERACT_HIT);
		INTERACT_SHOOT = fileConfiguration.getString("interact.shoot", INTERACT_SHOOT);
		INTERACT_DISABLED_EGG = fileConfiguration.getString("interact.disabled-egg", INTERACT_DISABLED_EGG);
		INTERACT_DISABLED_TRADE = fileConfiguration.getString("interact.disabled-trade", INTERACT_DISABLED_TRADE);
		
		SELECTION_SELECTED = fileConfiguration.getString("selection.selected", SELECTION_SELECTED);
		SELECTION_MISSING = fileConfiguration.getString("selection.missing", SELECTION_MISSING);
		
		PROFESSION_CURRENT = fileConfiguration.getString("profession.current", PROFESSION_CURRENT);
		PROFESSION_DONE = fileConfiguration.getString("profession.done", PROFESSION_DONE);
		PROFESSION_WRONGCMD = fileConfiguration.getString("profession.wrong-command", PROFESSION_WRONGCMD);
		
		CONVERTER_INTO_BABY = fileConfiguration.getString("converter.into-baby", CONVERTER_INTO_BABY);
		CONVERTER_INTO_ADULT = fileConfiguration.getString("converter.into-adult", CONVERTER_INTO_ADULT);
		
		RENAME_RENAME = fileConfiguration.getString("rename.done", RENAME_RENAME);
		RENAME_REMOVE = fileConfiguration.getString("rename.remove", RENAME_REMOVE);
		
		IRONGOLEM_HIT = fileConfiguration.getString("iron-golem.hit", IRONGOLEM_HIT);
		IRONGOLEM_SHOOT = fileConfiguration.getString("iron-golem.shoot", IRONGOLEM_SHOOT);
	}
	
	public void checkVersion(FileConfiguration fileConfiguration) {
		if (fileConfiguration.getInt("dont-edit-this.messages-version", -1) < Constants.VERSION_MESSAGES) {
			plugin.log(Constants.MESSAGES_OUTDATED);
		}
	}
}
