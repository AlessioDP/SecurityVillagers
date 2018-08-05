package com.alessiodp.securityvillagers.configuration.data;

import com.alessiodp.securityvillagers.SecurityVillagers;
import com.alessiodp.securityvillagers.configuration.Constants;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;

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
	public static String PREVENTIONS_PROTECTIONTYPE;
	
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
	
	private SecurityVillagers plugin;
	
	public ConfigMain(SecurityVillagers instance) {
		plugin = instance;
	}

	public void loadDefaults() {
		SECURITYVILLAGERS_UPDATES_CHECK = true;
		SECURITYVILLAGERS_UPDATES_WARN = true;
		SECURITYVILLAGERS_SELECTION_ENABLE = true;
		SECURITYVILLAGERS_SELECTION_ITEM = Material.BLAZE_ROD;
		
		PREVENTIONS_SPAWN = false;
		PREVENTIONS_TARGET = true;
		PREVENTIONS_MUTE = false;
		PREVENTIONS_PROTECTIONTYPE = "global";
		
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
	
	public void loadConfiguration(FileConfiguration fileConfiguration) {
		SECURITYVILLAGERS_UPDATES_CHECK = fileConfiguration.getBoolean("securityvillagers.updates.check", SECURITYVILLAGERS_UPDATES_CHECK);
		SECURITYVILLAGERS_UPDATES_WARN = fileConfiguration.getBoolean("securityvillagers.updates.warn", SECURITYVILLAGERS_UPDATES_WARN);
		SECURITYVILLAGERS_SELECTION_ENABLE = fileConfiguration.getBoolean("securityvillagers.selection.enable", SECURITYVILLAGERS_SELECTION_ENABLE);
		Material mat = Material.getMaterial(fileConfiguration.getString("securityvillagers.selection.item"));
		if (mat != null)
			SECURITYVILLAGERS_SELECTION_ITEM = mat;
		
		PREVENTIONS_SPAWN = fileConfiguration.getBoolean("preventions.spawn", PREVENTIONS_SPAWN);
		PREVENTIONS_TARGET = fileConfiguration.getBoolean("preventions.target", PREVENTIONS_TARGET);
		PREVENTIONS_MUTE = fileConfiguration.getBoolean("preventions.mute-villager-sounds", PREVENTIONS_MUTE);
		PREVENTIONS_PROTECTIONTYPE = fileConfiguration.getString("preventions.protection-type", PREVENTIONS_PROTECTIONTYPE);
		
		GENERAL_DAMAGE_HIT = fileConfiguration.getBoolean("general.damage.disable-hit-damage", GENERAL_DAMAGE_HIT);
		GENERAL_DAMAGE_ARROW = fileConfiguration.getBoolean("general.damage.disable-arrow-damage", GENERAL_DAMAGE_ARROW);
		GENERAL_DAMAGE_WORLDS = fileConfiguration.getStringList("general.damage.prevent-worlds");
		GENERAL_INTERACT_TRADE = fileConfiguration.getBoolean("general.interact.disable-trade", GENERAL_INTERACT_TRADE);
		GENERAL_INTERACT_EGG = fileConfiguration.getBoolean("general.interact.disable-egg", GENERAL_INTERACT_EGG);
		GENERAL_INTERACT_WORLDS = fileConfiguration.getStringList("general.interact.prevent-worlds");
		
		FACTIONS_ENABLE = fileConfiguration.getBoolean("factions.enable", FACTIONS_ENABLE);
		FACTIONS_PREVENT_HIT = fileConfiguration.getBoolean("factions.prevent-hit", FACTIONS_PREVENT_HIT);
		FACTIONS_PREVENT_INTERACT = fileConfiguration.getBoolean("factions.prevent-interact", FACTIONS_PREVENT_INTERACT);
		
		DAMAGE_MOBS_SKELETON = fileConfiguration.getBoolean("damage.mobs.skeleton", DAMAGE_MOBS_SKELETON);
		DAMAGE_MOBS_WITHER = fileConfiguration.getBoolean("damage.mobs.wither", DAMAGE_MOBS_WITHER);
		DAMAGE_MOBS_ZOMBIE = fileConfiguration.getBoolean("damage.mobs.zombie", DAMAGE_MOBS_ZOMBIE);
		DAMAGE_OTHER_CONTACT = fileConfiguration.getBoolean("damage.other.contact", DAMAGE_OTHER_CONTACT);
		DAMAGE_OTHER_DRAGON = fileConfiguration.getBoolean("damage.other.dragon", DAMAGE_OTHER_DRAGON);
		DAMAGE_OTHER_DROWNING = fileConfiguration.getBoolean("damage.other.drowning", DAMAGE_OTHER_DROWNING);
		DAMAGE_OTHER_EXPLOSION = fileConfiguration.getBoolean("damage.other.explosion", DAMAGE_OTHER_EXPLOSION);
		DAMAGE_OTHER_FALL = fileConfiguration.getBoolean("damage.other.fall", DAMAGE_OTHER_FALL);
		DAMAGE_OTHER_FALLINGBLOCK = fileConfiguration.getBoolean("damage.other.falling-block", DAMAGE_OTHER_FALLINGBLOCK);
		DAMAGE_OTHER_FIRE = fileConfiguration.getBoolean("damage.other.fire", DAMAGE_OTHER_FIRE);
		DAMAGE_OTHER_HOTFLOOR = fileConfiguration.getBoolean("damage.other.hot-floor", DAMAGE_OTHER_HOTFLOOR);
		DAMAGE_OTHER_LAVA = fileConfiguration.getBoolean("damage.other.lava", DAMAGE_OTHER_LAVA);
		DAMAGE_OTHER_LIGHTNING = fileConfiguration.getBoolean("damage.other.lightning", DAMAGE_OTHER_LIGHTNING);
		DAMAGE_OTHER_MAGIC = fileConfiguration.getBoolean("damage.other.magic", DAMAGE_OTHER_MAGIC);
		DAMAGE_OTHER_MELTING = fileConfiguration.getBoolean("damage.other.melting", DAMAGE_OTHER_MELTING);
		DAMAGE_OTHER_POISON = fileConfiguration.getBoolean("damage.other.poison", DAMAGE_OTHER_POISON);
		DAMAGE_OTHER_SUFFOCATION = fileConfiguration.getBoolean("damage.other.suffocation", DAMAGE_OTHER_SUFFOCATION);
		DAMAGE_OTHER_THORNS = fileConfiguration.getBoolean("damage.other.thorns", DAMAGE_OTHER_THORNS);
		DAMAGE_OTHER_VOID = fileConfiguration.getBoolean("damage.other.void", DAMAGE_OTHER_VOID);
		
		IRONGOLEM_PROTECT = fileConfiguration.getBoolean("iron-golem.protect", IRONGOLEM_PROTECT);
		IRONGOLEM_PREVENTSPAWN = fileConfiguration.getBoolean("iron-golem.prevent-spawn", IRONGOLEM_PREVENTSPAWN);
		
		PROFESSION_ENABLE = fileConfiguration.getBoolean("profession.enable", PROFESSION_ENABLE);
		PROFESSION_PROFESSION_BLACKSMITH = fileConfiguration.getString("profession.professions.blacksmith", PROFESSION_PROFESSION_BLACKSMITH);
		PROFESSION_PROFESSION_BUTCHER = fileConfiguration.getString("profession.professions.butcher", PROFESSION_PROFESSION_BUTCHER);
		PROFESSION_PROFESSION_FARMER = fileConfiguration.getString("profession.professions.farmer", PROFESSION_PROFESSION_FARMER);
		PROFESSION_PROFESSION_LIBRARIAN = fileConfiguration.getString("profession.professions.librarian", PROFESSION_PROFESSION_LIBRARIAN);
		PROFESSION_PROFESSION_NITWIT = fileConfiguration.getString("profession.professions.nitwit", PROFESSION_PROFESSION_NITWIT);
		PROFESSION_PROFESSION_PRIEST = fileConfiguration.getString("profession.professions.priest", PROFESSION_PROFESSION_PRIEST);
		
		CHANGEAGE_ENABLE = fileConfiguration.getBoolean("changeage.enable", CHANGEAGE_ENABLE);
		CHANGEAGE_ITEM_ENABLE = fileConfiguration.getBoolean("changeage.item.enable", CHANGEAGE_ITEM_ENABLE);
		mat = Material.getMaterial(fileConfiguration.getString("changeage.item.item"));
		if (mat != null)
			CHANGEAGE_ITEM_ITEM = mat;
		CHANGEAGE_ALLOWEDWORLDS = fileConfiguration.getStringList("changeage.allowed-worlds");
		
		RENAME_ENABLE = fileConfiguration.getBoolean("rename.enable", RENAME_ENABLE);
	}
	
	public void checkVersion(FileConfiguration fileConfiguration) {
		if (fileConfiguration.getInt("dont-edit-this.config-version", -1) < Constants.VERSION_CONFIG) {
			plugin.log(Constants.CONFIGURATION_OUTDATED);
		}
	}
}
