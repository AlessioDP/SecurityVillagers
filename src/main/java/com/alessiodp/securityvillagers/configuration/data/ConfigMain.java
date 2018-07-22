package com.alessiodp.securityvillagers.configuration.data;

import org.bukkit.Material;

import java.util.Collections;
import java.util.List;

public class ConfigMain {

	public static boolean SECURITYVILLAGERS_UPDATES_CHECK;
	public static boolean SECURITYVILLAGERS_UPDATES_WARN;
	public static boolean SECURITYVILLAGERS_SELECTION_ENABLE;
	public static Material SECURITYVILLAGERS_SELECTION_ITEM;
	
	public static boolean PREVENTIONS_SPAWN;
	public static boolean PREVENTIONS_TARGET;
	public static boolean PREVENTIONS_MUTE;
	
	public static boolean GENERAL_DAMAGE_HIT;
	public static boolean GENERAL_DAMAGE_ARROW;
	public static List<String> GENERAL_DAMAGE_WORLDS;
	public static boolean GENERAL_INTERACT_TRADE;
	public static boolean GENERAL_INTERACT_EGG;
	public static List<String> GENERAL_INTERACT_WORLDS;
	
	public static boolean FACTIONS_ENABLE;
	public static boolean FACTIONS_PREVENT_HIT;
	public static boolean FACTIONS_PREVENT_INTERACT;
	
	public static boolean DAMAGE_MOBS_SKELETON;
	public static boolean DAMAGE_MOBS_WITHER;
	public static boolean DAMAGE_MOBS_ZOMBIE;
	public static boolean DAMAGE_OTHER_CONTACT;
	public static boolean DAMAGE_OTHER_DRAGON;
	public static boolean DAMAGE_OTHER_DROWNING;
	public static boolean DAMAGE_OTHER_EXPLOSION;
	public static boolean DAMAGE_OTHER_FALL;
	public static boolean DAMAGE_OTHER_FALLINGBLOCK;
	public static boolean DAMAGE_OTHER_FIRE;
	public static boolean DAMAGE_OTHER_HOTFLOOR;
	public static boolean DAMAGE_OTHER_LAVA;
	public static boolean DAMAGE_OTHER_LIGHTNING;
	public static boolean DAMAGE_OTHER_MAGIC;
	public static boolean DAMAGE_OTHER_MELTING;
	public static boolean DAMAGE_OTHER_POISON;
	public static boolean DAMAGE_OTHER_SUFFOCATION;
	public static boolean DAMAGE_OTHER_THORNS;
	public static boolean DAMAGE_OTHER_VOID;
	
	public static boolean IRONGOLEM_PROTECT;
	public static boolean IRONGOLEM_PREVENTSPAWN;
	
	public static boolean PROFESSION_ENABLE;
	public static String PROFESSION_PROFESSION_BLACKSMITH;
	public static String PROFESSION_PROFESSION_BUTCHER;
	public static String PROFESSION_PROFESSION_FARMER;
	public static String PROFESSION_PROFESSION_LIBRARIAN;
	public static String PROFESSION_PROFESSION_NITWIT;
	public static String PROFESSION_PROFESSION_PRIEST;
	
	public static boolean CHANGEAGE_ENABLE;
	public static boolean CHANGEAGE_ITEM_ENABLE;
	public static Material CHANGEAGE_ITEM_ITEM;
	public static List<String> CHANGEAGE_ALLOWEDWORLDS;
	
	public static boolean RENAME_ENABLE;

	public ConfigMain() {
		loadDefaults();
	}

	public void loadDefaults() {
		SECURITYVILLAGERS_UPDATES_CHECK = true;
		SECURITYVILLAGERS_UPDATES_WARN = true;
		SECURITYVILLAGERS_SELECTION_ENABLE = true;
		SECURITYVILLAGERS_SELECTION_ITEM = Material.BLAZE_ROD;
		
		PREVENTIONS_SPAWN = false;
		PREVENTIONS_TARGET = true;
		PREVENTIONS_MUTE = false;
		
		GENERAL_DAMAGE_HIT = true;
		GENERAL_DAMAGE_ARROW = true;
		GENERAL_DAMAGE_WORLDS = Collections.singletonList("*");
		GENERAL_INTERACT_TRADE = false;
		GENERAL_INTERACT_EGG = true;
		GENERAL_INTERACT_WORLDS = Collections.singletonList("*");
		FACTIONS_ENABLE = false;
		FACTIONS_PREVENT_HIT = true;
		FACTIONS_PREVENT_INTERACT = true;
		
		DAMAGE_MOBS_SKELETON = false;
		DAMAGE_MOBS_WITHER = false;
		DAMAGE_MOBS_ZOMBIE = false;
		DAMAGE_OTHER_CONTACT = false;
		DAMAGE_OTHER_DRAGON = false;
		DAMAGE_OTHER_DROWNING = false;
		DAMAGE_OTHER_EXPLOSION = false;
		DAMAGE_OTHER_FALL = false;
		DAMAGE_OTHER_FALLINGBLOCK = false;
		DAMAGE_OTHER_FIRE = false;
		DAMAGE_OTHER_HOTFLOOR = false;
		DAMAGE_OTHER_LAVA = false;
		DAMAGE_OTHER_LIGHTNING = false;
		DAMAGE_OTHER_MAGIC = false;
		DAMAGE_OTHER_MELTING = false;
		DAMAGE_OTHER_POISON = false;
		DAMAGE_OTHER_SUFFOCATION = false;
		DAMAGE_OTHER_THORNS = false;
		DAMAGE_OTHER_VOID = false;
		
		
		IRONGOLEM_PROTECT = false;
		IRONGOLEM_PREVENTSPAWN = false;
		
		PROFESSION_ENABLE = true;
		PROFESSION_PROFESSION_BLACKSMITH = "Blacksmith";
		PROFESSION_PROFESSION_BUTCHER = "Butcher";
		PROFESSION_PROFESSION_FARMER = "Farmer";
		PROFESSION_PROFESSION_LIBRARIAN = "Librarian";
		PROFESSION_PROFESSION_NITWIT = "Nitwit";
		PROFESSION_PROFESSION_PRIEST = "Priest";
		
		CHANGEAGE_ENABLE = true;
		CHANGEAGE_ITEM_ENABLE = false;
		CHANGEAGE_ITEM_ITEM = Material.COMPASS;
		CHANGEAGE_ALLOWEDWORLDS = Collections.singletonList("*");
		
		RENAME_ENABLE = true;
	}
}
