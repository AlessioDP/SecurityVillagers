package com.alessiodp.securityvillagers.common.configuration.data;

import com.alessiodp.core.common.ADPPlugin;
import com.alessiodp.core.common.configuration.ConfigurationFile;
import com.alessiodp.core.common.configuration.adapter.ConfigurationAdapter;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.List;

public abstract class ConfigMain extends ConfigurationFile {
	// Plugin settings
	public static boolean		SECURITYVILLAGERS_UPDATES_CHECK;
	public static boolean		SECURITYVILLAGERS_UPDATES_WARN;
	public static boolean		SECURITYVILLAGERS_LOGGING_DEBUG;
	public static boolean		SECURITYVILLAGERS_LOGGING_SAVE_ENABLE;
	public static String		SECURITYVILLAGERS_LOGGING_SAVE_FORMAT;
	public static String		SECURITYVILLAGERS_LOGGING_SAVE_FILE;
	
	
	// Storage settings
	public static String		STORAGE_DATABASE_FILE;
	
	
	// General settings
	public static ProtectionType GENERAL_PROTECTIONTYPE;
	public static boolean		GENERAL_IMMORTAL;
	
	public static boolean		GENERAL_DAMAGE_HIT;
	public static boolean		GENERAL_DAMAGE_ARROW;
	public static List<String>	GENERAL_DAMAGE_WORLDS;
	
	public static boolean		GENERAL_INTERACT_TRADE;
	public static boolean		GENERAL_INTERACT_EGG;
	public static List<String>	GENERAL_INTERACT_WORLDS;
	
	public static boolean		GENERAL_MUTE_SOUND;
	public static List<String>	GENERAL_MUTE_WORLDS;
	
	public static boolean		GENERAL_TARGET_TARGET;
	public static List<String>	GENERAL_TARGET_WORLDS;
	
	public static boolean		GENERAL_FACTIONS_MEMBERBYPASS_PROTECTION;
	public static boolean		GENERAL_FACTIONS_MEMBERBYPASS_INTERACT;
	
	
	// Mobs settings
	public static boolean		MOBS_VILLAGER_PROTECT;
	public static boolean		MOBS_VILLAGER_PREVENT_INTERACT;
	public static boolean		MOBS_VILLAGER_SPAWN_PREVENT;
	public static List<String>	MOBS_VILLAGER_SPAWN_WORLDS;
	
	public static boolean		MOBS_WANDERINGTRADER_PROTECT;
	public static boolean		MOBS_WANDERINGTRADER_PREVENT_INTERACT;
	public static boolean		MOBS_WANDERINGTRADER_SPAWN_PREVENT;
	public static List<String>	MOBS_WANDERINGTRADER_SPAWN_WORLDS;
	
	public static boolean		MOBS_TRADERLLAMA_PROTECT;
	public static boolean		MOBS_TRADERLLAMA_PREVENT_INTERACT;
	public static boolean		MOBS_TRADERLLAMA_SPAWN_PREVENT;
	public static List<String>	MOBS_TRADERLLAMA_SPAWN_WORLDS;
	
	public static boolean		MOBS_IRONGOLEM_PROTECT;
	public static boolean		MOBS_IRONGOLEM_SPAWN_PREVENT;
	public static List<String>	MOBS_IRONGOLEM_SPAWN_WORLDS;
	
	public static boolean		MOBS_ILLAGER_EVOKER_PROTECT;
	public static boolean		MOBS_ILLAGER_EVOKER_SPAWN_PREVENT;
	public static List<String>	MOBS_ILLAGER_EVOKER_SPAWN_WORLDS;
	public static boolean		MOBS_ILLAGER_ILLUSIONER_PROTECT;
	public static boolean		MOBS_ILLAGER_ILLUSIONER_SPAWN_PREVENT;
	public static List<String>	MOBS_ILLAGER_ILLUSIONER_SPAWN_WORLDS;
	public static boolean		MOBS_ILLAGER_PILLAGER_PROTECT;
	public static boolean		MOBS_ILLAGER_PILLAGER_SPAWN_PREVENT;
	public static List<String>	MOBS_ILLAGER_PILLAGER_SPAWN_WORLDS;
	public static boolean		MOBS_ILLAGER_RAVAGER_PROTECT;
	public static boolean		MOBS_ILLAGER_RAVAGER_SPAWN_PREVENT;
	public static List<String>	MOBS_ILLAGER_RAVAGER_SPAWN_WORLDS;
	public static boolean		MOBS_ILLAGER_VEX_PROTECT;
	public static boolean		MOBS_ILLAGER_VEX_SPAWN_PREVENT;
	public static List<String>	MOBS_ILLAGER_VEX_SPAWN_WORLDS;
	public static boolean		MOBS_ILLAGER_VINDICATOR_PROTECT;
	public static boolean		MOBS_ILLAGER_VINDICATOR_SPAWN_PREVENT;
	public static List<String>	MOBS_ILLAGER_VINDICATOR_SPAWN_WORLDS;
	public static boolean		MOBS_ILLAGER_WITCH_PROTECT;
	public static boolean		MOBS_ILLAGER_WITCH_SPAWN_PREVENT;
	public static List<String>	MOBS_ILLAGER_WITCH_SPAWN_WORLDS;
	
	
	// Damage settings
	public static boolean		DAMAGE_MOBS_EVOKER;
	public static boolean		DAMAGE_MOBS_ILLUSIONER;
	public static boolean		DAMAGE_MOBS_PILLAGER;
	public static boolean		DAMAGE_MOBS_RAVAGER;
	public static boolean		DAMAGE_MOBS_SKELETON;
	public static boolean		DAMAGE_MOBS_VEX;
	public static boolean		DAMAGE_MOBS_VINDICATOR;
	public static boolean		DAMAGE_MOBS_WITCH;
	public static boolean		DAMAGE_MOBS_WITHER;
	public static boolean		DAMAGE_MOBS_ZOMBIE;
	public static boolean		DAMAGE_OTHER_CONTACT;
	public static boolean		DAMAGE_OTHER_CRAMMING;
	public static boolean		DAMAGE_OTHER_DRAGON;
	public static boolean		DAMAGE_OTHER_DROWNING;
	public static boolean		DAMAGE_OTHER_EXPLOSION;
	public static boolean		DAMAGE_OTHER_FALL;
	public static boolean		DAMAGE_OTHER_FALLINGBLOCK;
	public static boolean		DAMAGE_OTHER_FIRE;
	public static boolean		DAMAGE_OTHER_HOTFLOOR;
	public static boolean		DAMAGE_OTHER_LAVA;
	public static boolean		DAMAGE_OTHER_LIGHTNING;
	public static boolean		DAMAGE_OTHER_MAGIC;
	public static boolean		DAMAGE_OTHER_POISON;
	public static boolean		DAMAGE_OTHER_SUFFOCATION;
	public static boolean		DAMAGE_OTHER_THORNS;
	public static boolean		DAMAGE_OTHER_VOID;
	
	
	// Selection settings
	public static String		SELECTION_ITEM;
	public static boolean		SELECTION_CLEAR_ON_QUIT;
	
	
	// Profession settings
	public static boolean		PROFESSION_ENABLE;
	public static String		PROFESSION_TYPE_ARMORER;
	public static String		PROFESSION_TYPE_BUTCHER;
	public static String		PROFESSION_TYPE_CARTOGRAPHER;
	public static String		PROFESSION_TYPE_CLERIC;
	public static String		PROFESSION_TYPE_FARMER;
	public static String		PROFESSION_TYPE_FISHERMAN;
	public static String		PROFESSION_TYPE_FLETCHER;
	public static String		PROFESSION_TYPE_LEATHERWORKER;
	public static String		PROFESSION_TYPE_LIBRARIAN;
	public static String		PROFESSION_TYPE_MASON;
	public static String		PROFESSION_TYPE_NITWIT;
	public static String		PROFESSION_TYPE_NONE;
	public static String		PROFESSION_TYPE_SHEPHERD;
	public static String		PROFESSION_TYPE_TOOLSMITH;
	public static String		PROFESSION_TYPE_WEAPONSMITH;
	
	
	// Change age settings
	public static boolean		CHANGEAGE_ENABLE;
	
	
	// Rename settings
	public static boolean		RENAME_ENABLE;
	
	
	// Commands settings
	public static boolean		COMMANDS_TABSUPPORT;
	public static String		COMMANDS_DESCRIPTION_SV;
	
	public static String		COMMANDS_MAIN_HELP;
	public static String		COMMANDS_MAIN_SV;
	public static String		COMMANDS_MAIN_CHANGEAGE;
	public static String		COMMANDS_MAIN_PROFESSION;
	public static String		COMMANDS_MAIN_PROTECT;
	public static String		COMMANDS_MAIN_RELOAD;
	public static String		COMMANDS_MAIN_RENAME;
	public static String		COMMANDS_MAIN_VERSION;
	public static String		COMMANDS_SUB_ON;
	public static String		COMMANDS_SUB_OFF;
	public static String		COMMANDS_SUB_REMOVE;
	
	protected ConfigMain(@NonNull ADPPlugin plugin) {
		super(plugin);
	}
	
	
	@Override
	public void loadDefaults() {
		// Plugin settings
		SECURITYVILLAGERS_UPDATES_CHECK = true;
		SECURITYVILLAGERS_UPDATES_WARN = true;
		SECURITYVILLAGERS_LOGGING_DEBUG = false;
		SECURITYVILLAGERS_LOGGING_SAVE_ENABLE = false;
		SECURITYVILLAGERS_LOGGING_SAVE_FORMAT = "%date% [%time%] %message%\n";
		SECURITYVILLAGERS_LOGGING_SAVE_FILE = "log.txt";
		
		
		// Storage settings
		STORAGE_DATABASE_FILE = "database.yml";
		
		
		// General settings
		GENERAL_PROTECTIONTYPE = ProtectionType.GLOBAL;
		GENERAL_IMMORTAL = false;
		
		GENERAL_DAMAGE_HIT = true;
		GENERAL_DAMAGE_ARROW = true;
		GENERAL_DAMAGE_WORLDS = new ArrayList<>();
		GENERAL_DAMAGE_WORLDS.add("*");
		
		GENERAL_INTERACT_TRADE = false;
		GENERAL_INTERACT_EGG = true;
		GENERAL_INTERACT_WORLDS = new ArrayList<>();
		GENERAL_INTERACT_WORLDS.add("*");
		
		GENERAL_MUTE_SOUND = false;
		GENERAL_MUTE_WORLDS = new ArrayList<>();
		GENERAL_MUTE_WORLDS.add("*");
		
		GENERAL_TARGET_TARGET = true;
		GENERAL_TARGET_WORLDS = new ArrayList<>();
		GENERAL_TARGET_WORLDS.add("*");
		
		GENERAL_FACTIONS_MEMBERBYPASS_PROTECTION = true;
		GENERAL_FACTIONS_MEMBERBYPASS_INTERACT = true;
		
		
		// Mobs settings
		MOBS_VILLAGER_PROTECT = true;
		MOBS_VILLAGER_PREVENT_INTERACT = false;
		MOBS_VILLAGER_SPAWN_PREVENT = false;
		MOBS_VILLAGER_SPAWN_WORLDS = new ArrayList<>();
		MOBS_VILLAGER_SPAWN_WORLDS.add("*");
		
		MOBS_WANDERINGTRADER_PROTECT = true;
		MOBS_WANDERINGTRADER_PREVENT_INTERACT = false;
		MOBS_WANDERINGTRADER_SPAWN_PREVENT = false;
		MOBS_WANDERINGTRADER_SPAWN_WORLDS = new ArrayList<>();
		MOBS_WANDERINGTRADER_SPAWN_WORLDS.add("*");
		
		MOBS_TRADERLLAMA_PROTECT = true;
		MOBS_TRADERLLAMA_PREVENT_INTERACT = false;
		MOBS_TRADERLLAMA_SPAWN_PREVENT = false;
		MOBS_TRADERLLAMA_SPAWN_WORLDS = new ArrayList<>();
		MOBS_TRADERLLAMA_SPAWN_WORLDS.add("*");
		
		MOBS_IRONGOLEM_PROTECT = false;
		MOBS_IRONGOLEM_SPAWN_PREVENT = false;
		MOBS_IRONGOLEM_SPAWN_WORLDS = new ArrayList<>();
		MOBS_IRONGOLEM_SPAWN_WORLDS.add("*");
		
		MOBS_ILLAGER_EVOKER_PROTECT = false;
		MOBS_ILLAGER_EVOKER_SPAWN_PREVENT = false;
		MOBS_ILLAGER_EVOKER_SPAWN_WORLDS = new ArrayList<>();
		MOBS_ILLAGER_EVOKER_SPAWN_WORLDS.add("*");
		MOBS_ILLAGER_ILLUSIONER_PROTECT = false;
		MOBS_ILLAGER_ILLUSIONER_SPAWN_PREVENT = false;
		MOBS_ILLAGER_ILLUSIONER_SPAWN_WORLDS = new ArrayList<>();
		MOBS_ILLAGER_ILLUSIONER_SPAWN_WORLDS.add("*");
		MOBS_ILLAGER_PILLAGER_PROTECT = false;
		MOBS_ILLAGER_PILLAGER_SPAWN_PREVENT = false;
		MOBS_ILLAGER_PILLAGER_SPAWN_WORLDS = new ArrayList<>();
		MOBS_ILLAGER_PILLAGER_SPAWN_WORLDS.add("*");
		MOBS_ILLAGER_RAVAGER_PROTECT = false;
		MOBS_ILLAGER_RAVAGER_SPAWN_PREVENT = false;
		MOBS_ILLAGER_RAVAGER_SPAWN_WORLDS = new ArrayList<>();
		MOBS_ILLAGER_RAVAGER_SPAWN_WORLDS.add("*");
		MOBS_ILLAGER_VEX_PROTECT = false;
		MOBS_ILLAGER_VEX_SPAWN_PREVENT = false;
		MOBS_ILLAGER_VEX_SPAWN_WORLDS = new ArrayList<>();
		MOBS_ILLAGER_VEX_SPAWN_WORLDS.add("*");
		MOBS_ILLAGER_VINDICATOR_PROTECT = false;
		MOBS_ILLAGER_VINDICATOR_SPAWN_PREVENT = false;
		MOBS_ILLAGER_VINDICATOR_SPAWN_WORLDS = new ArrayList<>();
		MOBS_ILLAGER_VINDICATOR_SPAWN_WORLDS.add("*");
		MOBS_ILLAGER_WITCH_PROTECT = false;
		MOBS_ILLAGER_WITCH_SPAWN_PREVENT = false;
		MOBS_ILLAGER_WITCH_SPAWN_WORLDS = new ArrayList<>();
		MOBS_ILLAGER_WITCH_SPAWN_WORLDS.add("*");
		
		
		// Damage settings
		DAMAGE_MOBS_EVOKER = false;
		DAMAGE_MOBS_ILLUSIONER = false;
		DAMAGE_MOBS_PILLAGER = false;
		DAMAGE_MOBS_RAVAGER = false;
		DAMAGE_MOBS_SKELETON = false;
		DAMAGE_MOBS_VEX = false;
		DAMAGE_MOBS_VINDICATOR = false;
		DAMAGE_MOBS_WITCH = false;
		DAMAGE_MOBS_WITHER = false;
		DAMAGE_MOBS_ZOMBIE = false;
		DAMAGE_OTHER_CONTACT = false;
		DAMAGE_OTHER_CRAMMING = false;
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
		DAMAGE_OTHER_POISON = false;
		DAMAGE_OTHER_SUFFOCATION = false;
		DAMAGE_OTHER_THORNS = false;
		DAMAGE_OTHER_VOID = false;
		
		
		// Profession settings
		PROFESSION_ENABLE = true;
		PROFESSION_TYPE_ARMORER = "Armorer";
		PROFESSION_TYPE_BUTCHER = "Butcher";
		PROFESSION_TYPE_CARTOGRAPHER = "Cartographer";
		PROFESSION_TYPE_CLERIC = "Cleric";
		PROFESSION_TYPE_FARMER = "Farmer";
		PROFESSION_TYPE_FISHERMAN = "Fisherman";
		PROFESSION_TYPE_FLETCHER = "Fletcher";
		PROFESSION_TYPE_LEATHERWORKER = "Leatherworker";
		PROFESSION_TYPE_LIBRARIAN = "Librarian";
		PROFESSION_TYPE_MASON = "Mason";
		PROFESSION_TYPE_NITWIT = "Nitwit";
		PROFESSION_TYPE_NONE = "None";
		PROFESSION_TYPE_SHEPHERD = "Shepherd";
		PROFESSION_TYPE_TOOLSMITH = "Toolsmith";
		PROFESSION_TYPE_WEAPONSMITH = "Weaponsmith";
		
		
		// Selection settings
		SELECTION_ITEM = "BLAZE_ROD";
		SELECTION_CLEAR_ON_QUIT = true;
		
		
		// Change age settings
		CHANGEAGE_ENABLE = true;
		
		
		// Rename settings
		RENAME_ENABLE = true;
		
		
		// Commands settings
		COMMANDS_TABSUPPORT = true;
		COMMANDS_DESCRIPTION_SV = "SecurityVillagers help page";
		
		COMMANDS_MAIN_HELP = "help";
		COMMANDS_MAIN_SV = "sv";
		COMMANDS_MAIN_CHANGEAGE = "changeage";
		COMMANDS_MAIN_PROFESSION = "profession";
		COMMANDS_MAIN_PROTECT = "protect";
		COMMANDS_MAIN_RELOAD = "reload";
		COMMANDS_MAIN_RENAME = "rename";
		COMMANDS_MAIN_VERSION = "version";
		COMMANDS_SUB_ON = "on";
		COMMANDS_SUB_OFF = "off";
		COMMANDS_SUB_REMOVE = "remove";
	}
	
	@Override
	public void loadConfiguration(ConfigurationAdapter confAdapter) {
		// Plugin settings
		SECURITYVILLAGERS_UPDATES_CHECK = confAdapter.getBoolean("securityvillagers.updates.check", SECURITYVILLAGERS_UPDATES_CHECK);
		SECURITYVILLAGERS_UPDATES_WARN = confAdapter.getBoolean("securityvillagers.updates.warn", SECURITYVILLAGERS_UPDATES_WARN);
		SECURITYVILLAGERS_LOGGING_DEBUG = confAdapter.getBoolean("securityvillagers.logging.debug", SECURITYVILLAGERS_LOGGING_DEBUG);
		SECURITYVILLAGERS_LOGGING_SAVE_ENABLE = confAdapter.getBoolean("securityvillagers.logging.save-file.enable", SECURITYVILLAGERS_LOGGING_SAVE_ENABLE);
		SECURITYVILLAGERS_LOGGING_SAVE_FORMAT = confAdapter.getString("securityvillagers.logging.save-file.format", SECURITYVILLAGERS_LOGGING_SAVE_FORMAT);
		SECURITYVILLAGERS_LOGGING_SAVE_FILE = confAdapter.getString("securityvillagers.logging.save-file.file", SECURITYVILLAGERS_LOGGING_SAVE_FILE);
		
		
		// Storage settings
		STORAGE_DATABASE_FILE = confAdapter.getString("storage.database-file", STORAGE_DATABASE_FILE);
		
		
		// General settings
		GENERAL_PROTECTIONTYPE = ProtectionType.getType(confAdapter.getString("general.protection-type", GENERAL_PROTECTIONTYPE.name()));
		GENERAL_IMMORTAL = confAdapter.getBoolean("general.immortal", GENERAL_IMMORTAL);
		
		GENERAL_DAMAGE_HIT = confAdapter.getBoolean("general.damage.disable-hit-damage", GENERAL_DAMAGE_HIT);
		GENERAL_DAMAGE_ARROW = confAdapter.getBoolean("general.damage.disable-arrow-damage", GENERAL_DAMAGE_ARROW);
		GENERAL_DAMAGE_WORLDS = confAdapter.getStringList("general.damage.prevent-worlds", GENERAL_DAMAGE_WORLDS);
		
		GENERAL_INTERACT_TRADE = confAdapter.getBoolean("general.interact.disable-trade", GENERAL_INTERACT_TRADE);
		GENERAL_INTERACT_EGG = confAdapter.getBoolean("general.interact.disable-egg", GENERAL_INTERACT_EGG);
		GENERAL_INTERACT_WORLDS = confAdapter.getStringList("general.interact.prevent-worlds", GENERAL_INTERACT_WORLDS);
		
		GENERAL_MUTE_SOUND = confAdapter.getBoolean("general.mute.disable-sound", GENERAL_MUTE_SOUND);
		GENERAL_MUTE_WORLDS = confAdapter.getStringList("general.mute.prevent-worlds", GENERAL_MUTE_WORLDS);
		
		GENERAL_TARGET_TARGET = confAdapter.getBoolean("general.target.disable-target", GENERAL_TARGET_TARGET);
		GENERAL_TARGET_WORLDS = confAdapter.getStringList("general.target.prevent-worlds", GENERAL_TARGET_WORLDS);
		
		GENERAL_FACTIONS_MEMBERBYPASS_PROTECTION = confAdapter.getBoolean("general.factions.members-bypass-protection", GENERAL_FACTIONS_MEMBERBYPASS_PROTECTION);
		GENERAL_FACTIONS_MEMBERBYPASS_INTERACT = confAdapter.getBoolean("general.factions.members-bypass-interact", GENERAL_FACTIONS_MEMBERBYPASS_INTERACT);
		
		
		// Mobs settings
		MOBS_VILLAGER_PROTECT = confAdapter.getBoolean("mobs.villager.protect", MOBS_VILLAGER_PROTECT);
		MOBS_VILLAGER_PREVENT_INTERACT = confAdapter.getBoolean("mobs.villager.prevent-interact", MOBS_VILLAGER_PREVENT_INTERACT);
		MOBS_VILLAGER_SPAWN_PREVENT = confAdapter.getBoolean("mobs.villager.spawn.prevent", MOBS_VILLAGER_SPAWN_PREVENT);
		MOBS_VILLAGER_SPAWN_WORLDS = confAdapter.getStringList("mobs.villager.spawn.prevent-worlds", MOBS_VILLAGER_SPAWN_WORLDS);
		
		MOBS_WANDERINGTRADER_PROTECT = confAdapter.getBoolean("mobs.wandering-trader.protect", MOBS_WANDERINGTRADER_PROTECT);
		MOBS_WANDERINGTRADER_PREVENT_INTERACT = confAdapter.getBoolean("mobs.wandering-trader.prevent-interact", MOBS_WANDERINGTRADER_PREVENT_INTERACT);
		MOBS_WANDERINGTRADER_SPAWN_PREVENT = confAdapter.getBoolean("mobs.wandering-trader.spawn.prevent", MOBS_WANDERINGTRADER_SPAWN_PREVENT);
		MOBS_WANDERINGTRADER_SPAWN_WORLDS = confAdapter.getStringList("mobs.wandering-trader.spawn.prevent-worlds", MOBS_WANDERINGTRADER_SPAWN_WORLDS);
		
		MOBS_TRADERLLAMA_PROTECT = confAdapter.getBoolean("mobs.trader-llama.protect", MOBS_TRADERLLAMA_PROTECT);
		MOBS_TRADERLLAMA_PREVENT_INTERACT = confAdapter.getBoolean("mobs.trader-llama.prevent-interact", MOBS_TRADERLLAMA_PREVENT_INTERACT);
		MOBS_TRADERLLAMA_SPAWN_PREVENT = confAdapter.getBoolean("mobs.trader-llama.spawn.prevent", MOBS_TRADERLLAMA_SPAWN_PREVENT);
		MOBS_TRADERLLAMA_SPAWN_WORLDS = confAdapter.getStringList("mobs.trader-llama.spawn.prevent-worlds", MOBS_TRADERLLAMA_SPAWN_WORLDS);
		
		MOBS_IRONGOLEM_PROTECT = confAdapter.getBoolean("mobs.iron-golem.protect", MOBS_IRONGOLEM_PROTECT);
		MOBS_IRONGOLEM_SPAWN_PREVENT = confAdapter.getBoolean("mobs.iron-golem.spawn.prevent", MOBS_IRONGOLEM_SPAWN_PREVENT);
		MOBS_IRONGOLEM_SPAWN_WORLDS = confAdapter.getStringList("mobs.iron-golem.spawn.prevent-worlds", MOBS_IRONGOLEM_SPAWN_WORLDS);
		
		// Illager settings
		MOBS_ILLAGER_EVOKER_PROTECT = confAdapter.getBoolean("mobs.illager.evoker.protect", MOBS_ILLAGER_EVOKER_PROTECT);
		MOBS_ILLAGER_EVOKER_SPAWN_PREVENT = confAdapter.getBoolean("mobs.illager.evoker.spawn.prevent", MOBS_ILLAGER_EVOKER_SPAWN_PREVENT);
		MOBS_ILLAGER_EVOKER_SPAWN_WORLDS = confAdapter.getStringList("mobs.illager.evoker.spawn.prevent-worlds", MOBS_ILLAGER_EVOKER_SPAWN_WORLDS);
		MOBS_ILLAGER_ILLUSIONER_PROTECT = confAdapter.getBoolean("mobs.illager.illusioner.protect", MOBS_ILLAGER_ILLUSIONER_PROTECT);
		MOBS_ILLAGER_ILLUSIONER_SPAWN_PREVENT = confAdapter.getBoolean("mobs.illager.illusioner.spawn.prevent", MOBS_ILLAGER_ILLUSIONER_SPAWN_PREVENT);
		MOBS_ILLAGER_ILLUSIONER_SPAWN_WORLDS = confAdapter.getStringList("mobs.illager.illusioner.spawn.prevent-worlds", MOBS_ILLAGER_ILLUSIONER_SPAWN_WORLDS);
		MOBS_ILLAGER_PILLAGER_PROTECT = confAdapter.getBoolean("mobs.illager.pillager.protect", MOBS_ILLAGER_PILLAGER_PROTECT);
		MOBS_ILLAGER_PILLAGER_SPAWN_PREVENT = confAdapter.getBoolean("mobs.illager.pillager.spawn.prevent", MOBS_ILLAGER_PILLAGER_SPAWN_PREVENT);
		MOBS_ILLAGER_PILLAGER_SPAWN_WORLDS = confAdapter.getStringList("mobs.illager.pillager.spawn.prevent-worlds", MOBS_ILLAGER_PILLAGER_SPAWN_WORLDS);
		MOBS_ILLAGER_RAVAGER_PROTECT = confAdapter.getBoolean("mobs.illager.ravager.protect", MOBS_ILLAGER_RAVAGER_PROTECT);
		MOBS_ILLAGER_RAVAGER_SPAWN_PREVENT = confAdapter.getBoolean("mobs.illager.ravager.spawn.prevent", MOBS_ILLAGER_RAVAGER_SPAWN_PREVENT);
		MOBS_ILLAGER_RAVAGER_SPAWN_WORLDS = confAdapter.getStringList("mobs.illager.ravager.spawn.prevent-worlds", MOBS_ILLAGER_RAVAGER_SPAWN_WORLDS);
		MOBS_ILLAGER_VEX_PROTECT = confAdapter.getBoolean("mobs.illager.vex.protect", MOBS_ILLAGER_VEX_PROTECT);
		MOBS_ILLAGER_VEX_SPAWN_PREVENT = confAdapter.getBoolean("mobs.illager.vex.spawn.prevent", MOBS_ILLAGER_VEX_SPAWN_PREVENT);
		MOBS_ILLAGER_VEX_SPAWN_WORLDS = confAdapter.getStringList("mobs.illager.vex.spawn.prevent-worlds", MOBS_ILLAGER_VEX_SPAWN_WORLDS);
		MOBS_ILLAGER_VINDICATOR_PROTECT = confAdapter.getBoolean("mobs.illager.vindicator.protect", MOBS_ILLAGER_VINDICATOR_PROTECT);
		MOBS_ILLAGER_VINDICATOR_SPAWN_PREVENT = confAdapter.getBoolean("mobs.illager.vindicator.spawn.prevent", MOBS_ILLAGER_VINDICATOR_SPAWN_PREVENT);
		MOBS_ILLAGER_VINDICATOR_SPAWN_WORLDS = confAdapter.getStringList("mobs.illager.vindicator.spawn.prevent-worlds", MOBS_ILLAGER_VINDICATOR_SPAWN_WORLDS);
		MOBS_ILLAGER_WITCH_PROTECT = confAdapter.getBoolean("mobs.illager.evoker.protect", MOBS_ILLAGER_WITCH_PROTECT);
		MOBS_ILLAGER_WITCH_SPAWN_PREVENT = confAdapter.getBoolean("mobs.illager.witch.spawn.prevent", MOBS_ILLAGER_WITCH_SPAWN_PREVENT);
		MOBS_ILLAGER_WITCH_SPAWN_WORLDS = confAdapter.getStringList("mobs.illager.witch.spawn.prevent-worlds", MOBS_ILLAGER_WITCH_SPAWN_WORLDS);
		
		
		// Damage settings
		DAMAGE_MOBS_EVOKER = confAdapter.getBoolean("damage.mobs.evoker", DAMAGE_MOBS_EVOKER);
		DAMAGE_MOBS_ILLUSIONER = confAdapter.getBoolean("damage.mobs.illusioner", DAMAGE_MOBS_ILLUSIONER);
		DAMAGE_MOBS_PILLAGER = confAdapter.getBoolean("damage.mobs.pillager", DAMAGE_MOBS_PILLAGER);
		DAMAGE_MOBS_RAVAGER = confAdapter.getBoolean("damage.mobs.ravager", DAMAGE_MOBS_RAVAGER);
		DAMAGE_MOBS_SKELETON = confAdapter.getBoolean("damage.mobs.skeleton", DAMAGE_MOBS_SKELETON);
		DAMAGE_MOBS_VEX = confAdapter.getBoolean("damage.mobs.vex", DAMAGE_MOBS_VEX);
		DAMAGE_MOBS_VINDICATOR = confAdapter.getBoolean("damage.mobs.vindicator", DAMAGE_MOBS_VINDICATOR);
		DAMAGE_MOBS_WITCH = confAdapter.getBoolean("damage.mobs.witch", DAMAGE_MOBS_WITCH);
		DAMAGE_MOBS_WITHER = confAdapter.getBoolean("damage.mobs.wither", DAMAGE_MOBS_WITHER);
		DAMAGE_MOBS_ZOMBIE = confAdapter.getBoolean("damage.mobs.zombie", DAMAGE_MOBS_ZOMBIE);
		DAMAGE_OTHER_CONTACT = confAdapter.getBoolean("damage.other.contact", DAMAGE_OTHER_CONTACT);
		DAMAGE_OTHER_CRAMMING = confAdapter.getBoolean("damage.other.cramming", DAMAGE_OTHER_CRAMMING);
		DAMAGE_OTHER_DRAGON = confAdapter.getBoolean("damage.other.dragon", DAMAGE_OTHER_DRAGON);
		DAMAGE_OTHER_DROWNING = confAdapter.getBoolean("damage.other.drowning", DAMAGE_OTHER_DROWNING);
		DAMAGE_OTHER_EXPLOSION = confAdapter.getBoolean("damage.other.explosion", DAMAGE_OTHER_EXPLOSION);
		DAMAGE_OTHER_FALL = confAdapter.getBoolean("damage.other.fall", DAMAGE_OTHER_FALL);
		DAMAGE_OTHER_FALLINGBLOCK = confAdapter.getBoolean("damage.other.falling-block", DAMAGE_OTHER_FALLINGBLOCK);
		DAMAGE_OTHER_FIRE = confAdapter.getBoolean("damage.other.fire", DAMAGE_OTHER_FIRE);
		DAMAGE_OTHER_HOTFLOOR = confAdapter.getBoolean("damage.other.hot-floor", DAMAGE_OTHER_HOTFLOOR);
		DAMAGE_OTHER_LAVA = confAdapter.getBoolean("damage.other.lava", DAMAGE_OTHER_LAVA);
		DAMAGE_OTHER_LIGHTNING = confAdapter.getBoolean("damage.other.lightning", DAMAGE_OTHER_LIGHTNING);
		DAMAGE_OTHER_MAGIC = confAdapter.getBoolean("damage.other.magic", DAMAGE_OTHER_MAGIC);
		DAMAGE_OTHER_POISON = confAdapter.getBoolean("damage.other.poison", DAMAGE_OTHER_POISON);
		DAMAGE_OTHER_SUFFOCATION = confAdapter.getBoolean("damage.other.suffocation", DAMAGE_OTHER_SUFFOCATION);
		DAMAGE_OTHER_THORNS = confAdapter.getBoolean("damage.other.thorns", DAMAGE_OTHER_THORNS);
		DAMAGE_OTHER_VOID = confAdapter.getBoolean("damage.other.void", DAMAGE_OTHER_VOID);
		
		
		
		// Selection settings
		SELECTION_ITEM = confAdapter.getString("selection.item", SELECTION_ITEM);
		SELECTION_CLEAR_ON_QUIT = confAdapter.getBoolean("selection.clear-on-quit", SELECTION_CLEAR_ON_QUIT);
		
		
		// Profession settings
		PROFESSION_ENABLE = confAdapter.getBoolean("profession.enable", PROFESSION_ENABLE);
		PROFESSION_TYPE_ARMORER = confAdapter.getString("profession.professions.armorer", PROFESSION_TYPE_ARMORER);
		PROFESSION_TYPE_BUTCHER = confAdapter.getString("profession.professions.butcher", PROFESSION_TYPE_BUTCHER);
		PROFESSION_TYPE_CARTOGRAPHER = confAdapter.getString("profession.professions.cartographer", PROFESSION_TYPE_CARTOGRAPHER);
		PROFESSION_TYPE_CLERIC = confAdapter.getString("profession.professions.cleric", PROFESSION_TYPE_CLERIC);
		PROFESSION_TYPE_FARMER = confAdapter.getString("profession.professions.farmer", PROFESSION_TYPE_FARMER);
		PROFESSION_TYPE_FISHERMAN = confAdapter.getString("profession.professions.fisherman", PROFESSION_TYPE_FISHERMAN);
		PROFESSION_TYPE_FLETCHER = confAdapter.getString("profession.professions.fletcher", PROFESSION_TYPE_FLETCHER);
		PROFESSION_TYPE_LEATHERWORKER = confAdapter.getString("profession.professions.leatherworker", PROFESSION_TYPE_LEATHERWORKER);
		PROFESSION_TYPE_LIBRARIAN = confAdapter.getString("profession.professions.librarian", PROFESSION_TYPE_LIBRARIAN);
		PROFESSION_TYPE_MASON = confAdapter.getString("profession.professions.mason", PROFESSION_TYPE_MASON);
		PROFESSION_TYPE_NITWIT = confAdapter.getString("profession.professions.nitwit", PROFESSION_TYPE_NITWIT);
		PROFESSION_TYPE_NONE = confAdapter.getString("profession.professions.none", PROFESSION_TYPE_NONE);
		PROFESSION_TYPE_SHEPHERD = confAdapter.getString("profession.professions.shepherd", PROFESSION_TYPE_SHEPHERD);
		PROFESSION_TYPE_TOOLSMITH = confAdapter.getString("profession.professions.toolsmith", PROFESSION_TYPE_TOOLSMITH);
		PROFESSION_TYPE_WEAPONSMITH = confAdapter.getString("profession.professions.weaponsmith", PROFESSION_TYPE_WEAPONSMITH);
		
		
		// Change age settings
		CHANGEAGE_ENABLE = confAdapter.getBoolean("changeage.enable", CHANGEAGE_ENABLE);
		
		
		// Rename settings
		RENAME_ENABLE = confAdapter.getBoolean("rename.enable", RENAME_ENABLE);
		
		
		// Commands settings
		COMMANDS_TABSUPPORT = confAdapter.getBoolean("commands.tab-support", COMMANDS_TABSUPPORT);
		COMMANDS_DESCRIPTION_SV = confAdapter.getString("commands.sv-description", COMMANDS_DESCRIPTION_SV);
		
		COMMANDS_MAIN_HELP = confAdapter.getString("commands.main-commands.help", COMMANDS_MAIN_HELP);
		COMMANDS_MAIN_SV = confAdapter.getString("commands.main-commands.sv", COMMANDS_MAIN_SV);
		COMMANDS_MAIN_CHANGEAGE = confAdapter.getString("commands.main-commands.changeage", COMMANDS_MAIN_CHANGEAGE);
		COMMANDS_MAIN_PROFESSION = confAdapter.getString("commands.main-commands.profession", COMMANDS_MAIN_PROFESSION);
		COMMANDS_MAIN_PROTECT = confAdapter.getString("commands.main-commands.protect", COMMANDS_MAIN_PROTECT);
		COMMANDS_MAIN_RELOAD = confAdapter.getString("commands.main-commands.reload", COMMANDS_MAIN_RELOAD);
		COMMANDS_MAIN_RENAME = confAdapter.getString("commands.main-commands.rename", COMMANDS_MAIN_RENAME);
		COMMANDS_MAIN_VERSION = confAdapter.getString("commands.main-commands.version", COMMANDS_MAIN_VERSION);
		COMMANDS_SUB_ON = confAdapter.getString("commands.sub-commands.on", COMMANDS_SUB_ON);
		COMMANDS_SUB_OFF = confAdapter.getString("commands.sub-commands.off", COMMANDS_SUB_OFF);
		COMMANDS_SUB_REMOVE = confAdapter.getString("commands.sub-commands.remove", COMMANDS_SUB_REMOVE);
	}
	
	public enum ProtectionType {
		GLOBAL, CUSTOM, FACTIONS;
		
		public static ProtectionType getType(String type) {
			for (ProtectionType t : ProtectionType.values()) {
				if (t.name().equalsIgnoreCase(type))
					return t;
			}
			return null;
		}
	}
}
