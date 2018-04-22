package com.alessiodp.securityvillagers.configuration;

import com.alessiodp.securityvillagers.SecurityVillagers;
import com.alessiodp.securityvillagers.configuration.data.ConfigMain;
import com.alessiodp.securityvillagers.configuration.data.Messages;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class ConfigurationManager {
	private SecurityVillagers plugin;
	
	public ConfigurationManager(SecurityVillagers instance) {
		plugin = instance;
	}
	
	public void reload() {
		new ConfigMain();
		new Messages();
		
		reloadConfigMain();
		reloadMessages();
	}
	
	private void reloadConfigMain() {
		File cfgFile = new File(plugin.getDataFolder(), "config.yml");
		if (!cfgFile.exists()) {
			plugin.saveResource("config.yml", true);
		}
		FileConfiguration cfg = YamlConfiguration.loadConfiguration(cfgFile);
		
		if (cfg.getInt("dont-edit-this.config-version") < Constants.VERSION_CONFIG)
			plugin.log(Constants.CONFIGURATION_OUTDATED);
		
		loadConfigMain(cfg);
	}
	private void loadConfigMain(FileConfiguration cfg) {
		ConfigMain.SECURITYVILLAGERS_UPDATES_CHECK = cfg.getBoolean("securityvillagers.updates.check", ConfigMain.SECURITYVILLAGERS_UPDATES_CHECK);
		ConfigMain.SECURITYVILLAGERS_UPDATES_WARN = cfg.getBoolean("securityvillagers.updates.warn", ConfigMain.SECURITYVILLAGERS_UPDATES_WARN);
		ConfigMain.SECURITYVILLAGERS_SELECTION_ENABLE = cfg.getBoolean("securityvillagers.selection.enable", ConfigMain.SECURITYVILLAGERS_SELECTION_ENABLE);
		Material mat = Material.getMaterial(cfg.getString("securityvillagers.selection.item"));
		if (mat != null)
			ConfigMain.SECURITYVILLAGERS_SELECTION_ITEM = mat;
		
		ConfigMain.PREVENTIONS_SPAWN = cfg.getBoolean("preventions.spawn", ConfigMain.PREVENTIONS_SPAWN);
		ConfigMain.PREVENTIONS_TARGET = cfg.getBoolean("preventions.target", ConfigMain.PREVENTIONS_TARGET);
		ConfigMain.PREVENTIONS_MUTE = cfg.getBoolean("preventions.mute-villager-sounds", ConfigMain.PREVENTIONS_MUTE);
		
		ConfigMain.GENERAL_DAMAGE_HIT = cfg.getBoolean("general.damage.disable-hit-damage", ConfigMain.GENERAL_DAMAGE_HIT);
		ConfigMain.GENERAL_DAMAGE_ARROW = cfg.getBoolean("general.damage.disable-arrow-damage", ConfigMain.GENERAL_DAMAGE_ARROW);
		ConfigMain.GENERAL_DAMAGE_WORLDS = cfg.getStringList("general.damage.prevent-worlds");
		ConfigMain.GENERAL_INTERACT_TRADE = cfg.getBoolean("general.interact.disable-trade", ConfigMain.GENERAL_INTERACT_TRADE);
		ConfigMain.GENERAL_INTERACT_EGG = cfg.getBoolean("general.interact.disable-egg", ConfigMain.GENERAL_INTERACT_EGG);
		ConfigMain.GENERAL_INTERACT_WORLDS = cfg.getStringList("general.interact.prevent-worlds");
		
		ConfigMain.FACTIONS_ENABLE = cfg.getBoolean("factions.enable", ConfigMain.FACTIONS_ENABLE);
		ConfigMain.FACTIONS_PREVENT_HIT = cfg.getBoolean("factions.prevent-hit", ConfigMain.FACTIONS_PREVENT_HIT);
		ConfigMain.FACTIONS_PREVENT_INTERACT = cfg.getBoolean("factions.prevent-interact", ConfigMain.FACTIONS_PREVENT_INTERACT);
		
		ConfigMain.DAMAGE_MOBS_SKELETON = cfg.getBoolean("damage.mobs.skeleton", ConfigMain.DAMAGE_MOBS_SKELETON);
		ConfigMain.DAMAGE_MOBS_WITHER = cfg.getBoolean("damage.mobs.wither", ConfigMain.DAMAGE_MOBS_WITHER);
		ConfigMain.DAMAGE_MOBS_ZOMBIE = cfg.getBoolean("damage.mobs.zombie", ConfigMain.DAMAGE_MOBS_ZOMBIE);
		ConfigMain.DAMAGE_OTHER_CONTACT = cfg.getBoolean("damage.other.contact", ConfigMain.DAMAGE_OTHER_CONTACT);
		ConfigMain.DAMAGE_OTHER_DRAGON = cfg.getBoolean("damage.other.dragon", ConfigMain.DAMAGE_OTHER_DRAGON);
		ConfigMain.DAMAGE_OTHER_DROWNING = cfg.getBoolean("damage.other.drowning", ConfigMain.DAMAGE_OTHER_DROWNING);
		ConfigMain.DAMAGE_OTHER_EXPLOSION = cfg.getBoolean("damage.other.explosion", ConfigMain.DAMAGE_OTHER_EXPLOSION);
		ConfigMain.DAMAGE_OTHER_FALL = cfg.getBoolean("damage.other.fall", ConfigMain.DAMAGE_OTHER_FALL);
		ConfigMain.DAMAGE_OTHER_FALLINGBLOCK = cfg.getBoolean("damage.other.falling-block", ConfigMain.DAMAGE_OTHER_FALLINGBLOCK);
		ConfigMain.DAMAGE_OTHER_FIRE = cfg.getBoolean("damage.other.fire", ConfigMain.DAMAGE_OTHER_FIRE);
		ConfigMain.DAMAGE_OTHER_HOTFLOOR = cfg.getBoolean("damage.other.hot-floor", ConfigMain.DAMAGE_OTHER_HOTFLOOR);
		ConfigMain.DAMAGE_OTHER_LAVA = cfg.getBoolean("damage.other.lava", ConfigMain.DAMAGE_OTHER_LAVA);
		ConfigMain.DAMAGE_OTHER_LIGHTNING = cfg.getBoolean("damage.other.lightning", ConfigMain.DAMAGE_OTHER_LIGHTNING);
		ConfigMain.DAMAGE_OTHER_MAGIC = cfg.getBoolean("damage.other.magic", ConfigMain.DAMAGE_OTHER_MAGIC);
		ConfigMain.DAMAGE_OTHER_MELTING = cfg.getBoolean("damage.other.melting", ConfigMain.DAMAGE_OTHER_MELTING);
		ConfigMain.DAMAGE_OTHER_POISON = cfg.getBoolean("damage.other.poison", ConfigMain.DAMAGE_OTHER_POISON);
		ConfigMain.DAMAGE_OTHER_SUFFOCATION = cfg.getBoolean("damage.other.suffocation", ConfigMain.DAMAGE_OTHER_SUFFOCATION);
		ConfigMain.DAMAGE_OTHER_THORNS = cfg.getBoolean("damage.other.thorns", ConfigMain.DAMAGE_OTHER_THORNS);
		ConfigMain.DAMAGE_OTHER_VOID = cfg.getBoolean("damage.other.void", ConfigMain.DAMAGE_OTHER_VOID);
		
		ConfigMain.IRONGOLEM_PROTECT = cfg.getBoolean("iron-golem.protect", ConfigMain.IRONGOLEM_PROTECT);
		ConfigMain.IRONGOLEM_PREVENTSPAWN = cfg.getBoolean("iron-golem.prevent-spawn", ConfigMain.IRONGOLEM_PREVENTSPAWN);
		
		ConfigMain.PROFESSION_ENABLE = cfg.getBoolean("profession.enable", ConfigMain.PROFESSION_ENABLE);
		ConfigMain.PROFESSION_PROFESSION_BLACKSMITH = cfg.getString("profession.professions.blacksmith", ConfigMain.PROFESSION_PROFESSION_BLACKSMITH);
		ConfigMain.PROFESSION_PROFESSION_BUTCHER = cfg.getString("profession.professions.butcher", ConfigMain.PROFESSION_PROFESSION_BUTCHER);
		ConfigMain.PROFESSION_PROFESSION_FARMER = cfg.getString("profession.professions.farmer", ConfigMain.PROFESSION_PROFESSION_FARMER);
		ConfigMain.PROFESSION_PROFESSION_LIBRARIAN = cfg.getString("profession.professions.librarian", ConfigMain.PROFESSION_PROFESSION_LIBRARIAN);
		ConfigMain.PROFESSION_PROFESSION_NITWIT = cfg.getString("profession.professions.nitwit", ConfigMain.PROFESSION_PROFESSION_NITWIT);
		ConfigMain.PROFESSION_PROFESSION_PRIEST = cfg.getString("profession.professions.priest", ConfigMain.PROFESSION_PROFESSION_PRIEST);
		
		ConfigMain.CHANGEAGE_ENABLE = cfg.getBoolean("changeage.enable", ConfigMain.CHANGEAGE_ENABLE);
		ConfigMain.CHANGEAGE_ITEM_ENABLE = cfg.getBoolean("changeage.item.enable", ConfigMain.CHANGEAGE_ITEM_ENABLE);
		mat = Material.getMaterial(cfg.getString("changeage.item.item"));
		if (mat != null)
			ConfigMain.CHANGEAGE_ITEM_ITEM = mat;
		ConfigMain.CHANGEAGE_ALLOWEDWORLDS = cfg.getStringList("changeage.allowed-worlds");
		
		ConfigMain.RENAME_ENABLE = cfg.getBoolean("rename.enable", ConfigMain.RENAME_ENABLE);
	}
	
	private void reloadMessages() {
		File msgFile = new File(plugin.getDataFolder(), "messages.yml");
		if (!msgFile.exists()) {
			plugin.saveResource("messages.yml", true);
		}
		FileConfiguration msg = YamlConfiguration.loadConfiguration(msgFile);
		
		if (msg.getInt("dont-edit-this.messages-version") < Constants.VERSION_MESSAGES)
			plugin.log(Constants.MESSAGES_OUTDATED);
		
		loadMessages(msg);
	}
	private void loadMessages(FileConfiguration msg) {
		Messages.SECURITYVILLAGERS_UPDATEAVAILABLE = msg.getString("securityvillagers.update-available", Messages.SECURITYVILLAGERS_UPDATEAVAILABLE);
		Messages.SECURITYVILLAGERS_NOPERMISSION = msg.getString("securityvillagers.no-permission", Messages.SECURITYVILLAGERS_NOPERMISSION);
		Messages.SECURITYVILLAGERS_CONFIGURATIONRELOADED = msg.getString("securityvillagers.configuration-reloaded", Messages.SECURITYVILLAGERS_CONFIGURATIONRELOADED);
		Messages.SECURITYVILLAGERS_NOCONSOLE = msg.getString("securityvillagers.no-console", Messages.SECURITYVILLAGERS_NOCONSOLE);
		Messages.SECURITYVILLAGERS_NOTFOUND = msg.getString("securityvillagers.not-found", Messages.SECURITYVILLAGERS_NOTFOUND);
		Messages.SECURITYVILLAGERS_HELP = msg.getStringList("securityvillagers.help");
		
		Messages.INTERACT_HIT = msg.getString("interact.hit", Messages.INTERACT_HIT);
		Messages.INTERACT_SHOOT = msg.getString("interact.shoot", Messages.INTERACT_SHOOT);
		Messages.INTERACT_DISABLED_EGG = msg.getString("interact.disabled-egg", Messages.INTERACT_DISABLED_EGG);
		Messages.INTERACT_DISABLED_TRADE = msg.getString("interact.disabled-trade", Messages.INTERACT_DISABLED_TRADE);
		
		Messages.SELECTION_SELECTED = msg.getString("selection.selected", Messages.SELECTION_SELECTED);
		Messages.SELECTION_MISSING = msg.getString("selection.missing", Messages.SELECTION_MISSING);
		
		Messages.PROFESSION_CURRENT = msg.getString("profession.current", Messages.PROFESSION_CURRENT);
		Messages.PROFESSION_DONE = msg.getString("profession.done", Messages.PROFESSION_DONE);
		Messages.PROFESSION_WRONGCMD = msg.getString("profession.wrong-command", Messages.PROFESSION_WRONGCMD);
		
		Messages.CONVERTER_INTO_BABY = msg.getString("converter.into-baby", Messages.CONVERTER_INTO_BABY);
		Messages.CONVERTER_INTO_ADULT = msg.getString("converter.into-adult", Messages.CONVERTER_INTO_ADULT);
		
		Messages.RENAME_RENAME = msg.getString("rename.done", Messages.RENAME_RENAME);
		Messages.RENAME_REMOVE = msg.getString("rename.remove", Messages.RENAME_REMOVE);
		
		Messages.IRONGOLEM_HIT = msg.getString("iron-golem.hit", Messages.IRONGOLEM_HIT);
		Messages.IRONGOLEM_SHOOT = msg.getString("iron-golem.shoot", Messages.IRONGOLEM_SHOOT);
	}
}