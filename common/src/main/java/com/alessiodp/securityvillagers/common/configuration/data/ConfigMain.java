package com.alessiodp.securityvillagers.common.configuration.data;

import com.alessiodp.core.common.ADPPlugin;
import com.alessiodp.core.common.configuration.ConfigOption;
import com.alessiodp.core.common.configuration.ConfigurationFile;
import lombok.NonNull;

import java.util.List;

public abstract class ConfigMain extends ConfigurationFile {
	// Plugin settings
	@ConfigOption(path = "securityvillagers.updates.check")
	public static boolean		SECURITYVILLAGERS_UPDATES_CHECK;
	@ConfigOption(path = "securityvillagers.updates.warn")
	public static boolean		SECURITYVILLAGERS_UPDATES_WARN;
	@ConfigOption(path = "securityvillagers.logging.debug")
	public static boolean		SECURITYVILLAGERS_LOGGING_DEBUG;
	@ConfigOption(path = "securityvillagers.logging.save-file.enable")
	public static boolean		SECURITYVILLAGERS_LOGGING_SAVE_ENABLE;
	@ConfigOption(path = "securityvillagers.logging.save-file.format")
	public static String		SECURITYVILLAGERS_LOGGING_SAVE_FORMAT;
	@ConfigOption(path = "securityvillagers.logging.save-file.file")
	public static String		SECURITYVILLAGERS_LOGGING_SAVE_FILE;
	
	
	// Storage settings
	@ConfigOption(path = "storage.database-file")
	public static String		STORAGE_DATABASE_FILE;
	
	
	// General settings
	public static ProtectionType GENERAL_PROTECTIONTYPE;
	
	@ConfigOption(path = "general.damage.immortal")
	public static boolean		GENERAL_DAMAGE_IMMORTAL;
	@ConfigOption(path = "general.damage.disable-hit-damage")
	public static boolean		GENERAL_DAMAGE_HIT;
	@ConfigOption(path = "general.damage.disable-arrow-damage")
	public static boolean		GENERAL_DAMAGE_ARROW;
	@ConfigOption(path = "general.damage.disable-dispenser-arrow-damage")
	public static boolean		GENERAL_DAMAGE_ARROW_DISPENSER;
	@ConfigOption(path = "general.damage.prevent-worlds")
	public static List<String>	GENERAL_DAMAGE_WORLDS;
	
	
	@ConfigOption(path = "general.interact.disable-trade")
	public static boolean		GENERAL_INTERACT_TRADE;
	@ConfigOption(path = "general.interact.disable-egg")
	public static boolean		GENERAL_INTERACT_EGG;
	@ConfigOption(path = "general.interact.prevent-worlds")
	public static List<String>	GENERAL_INTERACT_WORLDS;
	
	@ConfigOption(path = "general.mute.disable-sound")
	public static boolean		GENERAL_MUTE_SOUND;
	@ConfigOption(path = "general.mute.prevent-worlds")
	public static List<String>	GENERAL_MUTE_WORLDS;
	
	@ConfigOption(path = "general.target.disable-target")
	public static boolean		GENERAL_TARGET_TARGET;
	@ConfigOption(path = "general.target.prevent-worlds")
	public static List<String>	GENERAL_TARGET_WORLDS;
	
	@ConfigOption(path = "general.factions.members-bypass-protection")
	public static boolean		GENERAL_FACTIONS_MEMBERBYPASS_PROTECTION;
	@ConfigOption(path = "general.factions.members-bypass-interact")
	public static boolean		GENERAL_FACTIONS_MEMBERBYPASS_INTERACT;
	
	
	// Mobs settings
	@ConfigOption(path = "mobs.villager.protect")
	public static boolean		MOBS_VILLAGER_PROTECT;
	@ConfigOption(path = "mobs.villager.prevent-interact")
	public static boolean		MOBS_VILLAGER_PREVENT_INTERACT;
	@ConfigOption(path = "mobs.villager.spawn.prevent")
	public static boolean		MOBS_VILLAGER_SPAWN_PREVENT;
	@ConfigOption(path = "mobs.villager.spawn.prevent-worlds")
	public static List<String>	MOBS_VILLAGER_SPAWN_WORLDS;
	
	@ConfigOption(path = "mobs.wandering-trader.protect")
	public static boolean		MOBS_WANDERINGTRADER_PROTECT;
	@ConfigOption(path = "mobs.wandering-trader.prevent-interact")
	public static boolean		MOBS_WANDERINGTRADER_PREVENT_INTERACT;
	@ConfigOption(path = "mobs.wandering-trader.spawn.prevent")
	public static boolean		MOBS_WANDERINGTRADER_SPAWN_PREVENT;
	@ConfigOption(path = "mobs.wandering-trader.spawn.prevent-worlds")
	public static List<String>	MOBS_WANDERINGTRADER_SPAWN_WORLDS;
	
	@ConfigOption(path = "mobs.trader-llama.protect")
	public static boolean		MOBS_TRADERLLAMA_PROTECT;
	@ConfigOption(path = "mobs.trader-llama.prevent-interact")
	public static boolean		MOBS_TRADERLLAMA_PREVENT_INTERACT;
	@ConfigOption(path = "mobs.trader-llama.spawn.prevent")
	public static boolean		MOBS_TRADERLLAMA_SPAWN_PREVENT;
	@ConfigOption(path = "mobs.trader-llama.spawn.prevent-worlds")
	public static List<String>	MOBS_TRADERLLAMA_SPAWN_WORLDS;
	
	@ConfigOption(path = "mobs.iron-golem.protect")
	public static boolean		MOBS_IRONGOLEM_PROTECT;
	@ConfigOption(path = "mobs.iron-golem.spawn.prevent")
	public static boolean		MOBS_IRONGOLEM_SPAWN_PREVENT;
	@ConfigOption(path = "mobs.iron-golem.spawn.prevent-worlds")
	public static List<String>	MOBS_IRONGOLEM_SPAWN_WORLDS;
	
	@ConfigOption(path = "mobs.illager.evoker.protect")
	public static boolean		MOBS_ILLAGER_EVOKER_PROTECT;
	@ConfigOption(path = "mobs.illager.evoker.spawn.prevent")
	public static boolean		MOBS_ILLAGER_EVOKER_SPAWN_PREVENT;
	@ConfigOption(path = "mobs.illager.evoker.spawn.prevent-worlds")
	public static List<String>	MOBS_ILLAGER_EVOKER_SPAWN_WORLDS;
	@ConfigOption(path = "mobs.illager.illusioner.protect")
	public static boolean		MOBS_ILLAGER_ILLUSIONER_PROTECT;
	@ConfigOption(path = "mobs.illager.illusioner.spawn.prevent")
	public static boolean		MOBS_ILLAGER_ILLUSIONER_SPAWN_PREVENT;
	@ConfigOption(path = "mobs.illager.illusioner.spawn.prevent-worlds")
	public static List<String>	MOBS_ILLAGER_ILLUSIONER_SPAWN_WORLDS;
	@ConfigOption(path = "mobs.illager.pillager.protect")
	public static boolean		MOBS_ILLAGER_PILLAGER_PROTECT;
	@ConfigOption(path = "mobs.illager.pillager.spawn.prevent")
	public static boolean		MOBS_ILLAGER_PILLAGER_SPAWN_PREVENT;
	@ConfigOption(path = "mobs.illager.pillager.spawn.prevent-worlds")
	public static List<String>	MOBS_ILLAGER_PILLAGER_SPAWN_WORLDS;
	@ConfigOption(path = "mobs.illager.ravager.protect")
	public static boolean		MOBS_ILLAGER_RAVAGER_PROTECT;
	@ConfigOption(path = "mobs.illager.ravager.spawn.prevent")
	public static boolean		MOBS_ILLAGER_RAVAGER_SPAWN_PREVENT;
	@ConfigOption(path = "mobs.illager.ravager.spawn.prevent-worlds")
	public static List<String>	MOBS_ILLAGER_RAVAGER_SPAWN_WORLDS;
	@ConfigOption(path = "mobs.illager.vex.protect")
	public static boolean		MOBS_ILLAGER_VEX_PROTECT;
	@ConfigOption(path = "mobs.illager.vex.spawn.prevent")
	public static boolean		MOBS_ILLAGER_VEX_SPAWN_PREVENT;
	@ConfigOption(path = "mobs.illager.vex.spawn.prevent-worlds")
	public static List<String>	MOBS_ILLAGER_VEX_SPAWN_WORLDS;
	@ConfigOption(path = "mobs.illager.vindicator.protect")
	public static boolean		MOBS_ILLAGER_VINDICATOR_PROTECT;
	@ConfigOption(path = "mobs.illager.vindicator.spawn.prevent")
	public static boolean		MOBS_ILLAGER_VINDICATOR_SPAWN_PREVENT;
	@ConfigOption(path = "mobs.illager.vindicator.spawn.prevent-worlds")
	public static List<String>	MOBS_ILLAGER_VINDICATOR_SPAWN_WORLDS;
	@ConfigOption(path = "mobs.illager.witch.protect")
	public static boolean		MOBS_ILLAGER_WITCH_PROTECT;
	@ConfigOption(path = "mobs.illager.witch.spawn.prevent")
	public static boolean		MOBS_ILLAGER_WITCH_SPAWN_PREVENT;
	@ConfigOption(path = "mobs.illager.witch.spawn.prevent-worlds")
	public static List<String>	MOBS_ILLAGER_WITCH_SPAWN_WORLDS;
	
	
	// Damage settings
	@ConfigOption(path = "damage.mobs.evoker")
	public static boolean		DAMAGE_MOBS_EVOKER;
	@ConfigOption(path = "damage.mobs.illusioner")
	public static boolean		DAMAGE_MOBS_ILLUSIONER;
	@ConfigOption(path = "damage.mobs.pillager")
	public static boolean		DAMAGE_MOBS_PILLAGER;
	@ConfigOption(path = "damage.mobs.ravager")
	public static boolean		DAMAGE_MOBS_RAVAGER;
	@ConfigOption(path = "damage.mobs.skeleton")
	public static boolean		DAMAGE_MOBS_SKELETON;
	@ConfigOption(path = "damage.mobs.vex")
	public static boolean		DAMAGE_MOBS_VEX;
	@ConfigOption(path = "damage.mobs.vindicator")
	public static boolean		DAMAGE_MOBS_VINDICATOR;
	@ConfigOption(path = "damage.mobs.witch")
	public static boolean		DAMAGE_MOBS_WITCH;
	@ConfigOption(path = "damage.mobs.wither")
	public static boolean		DAMAGE_MOBS_WITHER;
	@ConfigOption(path = "damage.mobs.zombie")
	public static boolean		DAMAGE_MOBS_ZOMBIE;
	@ConfigOption(path = "damage.other.contact")
	public static boolean		DAMAGE_OTHER_CONTACT;
	@ConfigOption(path = "damage.other.cramming")
	public static boolean		DAMAGE_OTHER_CRAMMING;
	@ConfigOption(path = "damage.other.dragon")
	public static boolean		DAMAGE_OTHER_DRAGON;
	@ConfigOption(path = "damage.other.drowning")
	public static boolean		DAMAGE_OTHER_DROWNING;
	@ConfigOption(path = "damage.other.explosion")
	public static boolean		DAMAGE_OTHER_EXPLOSION;
	@ConfigOption(path = "damage.other.fall")
	public static boolean		DAMAGE_OTHER_FALL;
	@ConfigOption(path = "damage.other.falling-block")
	public static boolean		DAMAGE_OTHER_FALLINGBLOCK;
	@ConfigOption(path = "damage.other.fire")
	public static boolean		DAMAGE_OTHER_FIRE;
	@ConfigOption(path = "damage.other.hot-floor")
	public static boolean		DAMAGE_OTHER_HOTFLOOR;
	@ConfigOption(path = "damage.other.lava")
	public static boolean		DAMAGE_OTHER_LAVA;
	@ConfigOption(path = "damage.other.lightning")
	public static boolean		DAMAGE_OTHER_LIGHTNING;
	@ConfigOption(path = "damage.other.magic")
	public static boolean		DAMAGE_OTHER_MAGIC;
	@ConfigOption(path = "damage.other.poison")
	public static boolean		DAMAGE_OTHER_POISON;
	@ConfigOption(path = "damage.other.suffocation")
	public static boolean		DAMAGE_OTHER_SUFFOCATION;
	@ConfigOption(path = "damage.other.thorns")
	public static boolean		DAMAGE_OTHER_THORNS;
	@ConfigOption(path = "damage.other.void")
	public static boolean		DAMAGE_OTHER_VOID;
	
	
	// Selection settings
	@ConfigOption(path = "selection.item")
	public static String		SELECTION_ITEM;
	@ConfigOption(path = "selection.clear-selection-on-quit")
	public static boolean		SELECTION_CLEAR_SELECTION_ON_QUIT;
	
	
	// Profession settings
	@ConfigOption(path = "profession.enable")
	public static boolean		PROFESSION_ENABLE;
	@ConfigOption(path = "profession.cooldown")
	public static int			PROFESSION_COOLDOWN;
	@ConfigOption(path = "profession.professions.armorer")
	public static String		PROFESSION_TYPE_ARMORER;
	@ConfigOption(path = "profession.professions.butcher")
	public static String		PROFESSION_TYPE_BUTCHER;
	@ConfigOption(path = "profession.professions.cartographer")
	public static String		PROFESSION_TYPE_CARTOGRAPHER;
	@ConfigOption(path = "profession.professions.cleric")
	public static String		PROFESSION_TYPE_CLERIC;
	@ConfigOption(path = "profession.professions.farmer")
	public static String		PROFESSION_TYPE_FARMER;
	@ConfigOption(path = "profession.professions.fisherman")
	public static String		PROFESSION_TYPE_FISHERMAN;
	@ConfigOption(path = "profession.professions.fletcher")
	public static String		PROFESSION_TYPE_FLETCHER;
	@ConfigOption(path = "profession.professions.leatherworker")
	public static String		PROFESSION_TYPE_LEATHERWORKER;
	@ConfigOption(path = "profession.professions.librarian")
	public static String		PROFESSION_TYPE_LIBRARIAN;
	@ConfigOption(path = "profession.professions.mason")
	public static String		PROFESSION_TYPE_MASON;
	@ConfigOption(path = "profession.professions.nitwit")
	public static String		PROFESSION_TYPE_NITWIT;
	@ConfigOption(path = "profession.professions.none")
	public static String		PROFESSION_TYPE_NONE;
	@ConfigOption(path = "profession.professions.shepherd")
	public static String		PROFESSION_TYPE_SHEPHERD;
	@ConfigOption(path = "profession.professions.toolsmith")
	public static String		PROFESSION_TYPE_TOOLSMITH;
	@ConfigOption(path = "profession.professions.weaponsmith")
	public static String		PROFESSION_TYPE_WEAPONSMITH;
	
	
	// Change age settings
	@ConfigOption(path = "changeage.enable")
	public static boolean		CHANGEAGE_ENABLE;
	@ConfigOption(path = "changeage.cooldown")
	public static int			CHANGEAGE_COOLDOWN;
	
	
	// Rename settings
	@ConfigOption(path = "rename.enable")
	public static boolean		RENAME_ENABLE;
	
	
	// Commands settings
	@ConfigOption(path = "commands.tab-support")
	public static boolean		COMMANDS_TABSUPPORT;
	@ConfigOption(path = "commands.sv-description")
	public static String		COMMANDS_DESCRIPTION_SV;
	
	@ConfigOption(path = "commands.main-commands.help")
	public static String		COMMANDS_CMD_HELP;
	@ConfigOption(path = "commands.main-commands.sv")
	public static String		COMMANDS_CMD_SV;
	@ConfigOption(path = "commands.main-commands.changeage")
	public static String		COMMANDS_CMD_CHANGEAGE;
	@ConfigOption(path = "commands.main-commands.profession")
	public static String		COMMANDS_CMD_PROFESSION;
	@ConfigOption(path = "commands.main-commands.protect")
	public static String		COMMANDS_CMD_PROTECT;
	@ConfigOption(path = "commands.main-commands.reload")
	public static String		COMMANDS_CMD_RELOAD;
	@ConfigOption(path = "commands.main-commands.rename")
	public static String		COMMANDS_CMD_RENAME;
	@ConfigOption(path = "commands.main-commands.version")
	public static String		COMMANDS_CMD_VERSION;
	
	@ConfigOption(path = "commands.sub-commands.word-on")
	public static String		COMMANDS_SUB_ON;
	@ConfigOption(path = "commands.sub-commands.word-off")
	public static String		COMMANDS_SUB_OFF;
	@ConfigOption(path = "commands.sub-commands.remove")
	public static String		COMMANDS_SUB_REMOVE;
	
	protected ConfigMain(@NonNull ADPPlugin plugin) {
		super(plugin);
	}
	
	
	@Override
	public void loadDefaults() {
		loadDefaultConfigOptions();
		
		GENERAL_PROTECTIONTYPE = ProtectionType.GLOBAL;
	}
	
	@Override
	public void loadConfiguration() {
		loadConfigOptions();
		
		GENERAL_PROTECTIONTYPE = ProtectionType.getType(configuration.getString("general.protection-type", GENERAL_PROTECTIONTYPE.name()));
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
