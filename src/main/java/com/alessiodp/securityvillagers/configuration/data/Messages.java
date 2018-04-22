package com.alessiodp.securityvillagers.configuration.data;

import java.util.ArrayList;
import java.util.List;

public class Messages {
	
	public static String SECURITYVILLAGERS_UPDATEAVAILABLE;
	public static String SECURITYVILLAGERS_NOPERMISSION;
	public static String SECURITYVILLAGERS_CONFIGURATIONRELOADED;
	public static String SECURITYVILLAGERS_NOCONSOLE;
	public static String SECURITYVILLAGERS_NOTFOUND;
	public static List<String> SECURITYVILLAGERS_HELP;
	
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
	
	public Messages() {
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
		SECURITYVILLAGERS_HELP.add("&6/sv rename [name] &7- Rename the villager");
		SECURITYVILLAGERS_HELP.add("&6/sv reload &7- Reload the configuration");
		
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
}
