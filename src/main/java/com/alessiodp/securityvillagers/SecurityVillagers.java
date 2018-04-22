package com.alessiodp.securityvillagers;

import com.alessiodp.securityvillagers.addons.AddonManager;
import com.alessiodp.securityvillagers.addons.external.MetricsHandler;
import com.alessiodp.securityvillagers.addons.internal.ADPUpdater;
import com.alessiodp.securityvillagers.commands.CommandDispatcher;
import com.alessiodp.securityvillagers.configuration.ConfigurationManager;
import com.alessiodp.securityvillagers.configuration.Constants;
import com.alessiodp.securityvillagers.handlers.VillagerHandler;
import com.alessiodp.securityvillagers.listeners.DamageListener;
import com.alessiodp.securityvillagers.listeners.PlayerListener;
import com.alessiodp.securityvillagers.listeners.PreventionListener;
import lombok.Getter;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Level;

public class SecurityVillagers extends JavaPlugin {
	@Getter private static SecurityVillagers instance;
	
	@Getter private AddonManager addonManager;
	@Getter private CommandDispatcher commandDispatcher;
	@Getter private ConfigurationManager configurationManager;
	@Getter private VillagerHandler villagerHandler;
	
	@Override
	public void onEnable() {
		instance = this;
		log(Constants.DEBUG_SV_ENABLING
				.replace("{version}", getDescription().getVersion()));
		handle();
		log(Constants.DEBUG_SV_ENABLED
				.replace("{version}", getDescription().getVersion()));
	}
	
	@Override
	public void onDisable() {
		log(Constants.DEBUG_SV_DISABLED);
	}
	
	private void handle() {
		addonManager = new AddonManager(this);
		configurationManager = new ConfigurationManager(this);
		commandDispatcher = new CommandDispatcher(this);
		villagerHandler = new VillagerHandler(this);
		
		getConfigurationManager().reload();
		registerListeners();
		getAddonManager().loadAddons();
		getCommandDispatcher().reloadCommands();
		
		new MetricsHandler(this);
		
		new ADPUpdater(this);
		ADPUpdater.asyncTaskCheckUpdates();
	}
	
	
	private void registerListeners() {
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvents(new DamageListener(this), this);
		pm.registerEvents(new PlayerListener(this), this);
		pm.registerEvents(new PreventionListener(this), this);
	}
	
	public void reloadConfiguration() {
		getConfigurationManager().reload();
		getAddonManager().loadAddons();
		getCommandDispatcher().reloadCommands();
		ADPUpdater.asyncCheckUpdates();
	}
	
	public void log(String str) {
		getServer().getLogger().log(Level.INFO, "[SecurityVillagers] " + str);
	}
	
	
	public void sendMessage(CommandSender sender, String str) {
		if (sender instanceof Player) {
			sendPlayerMessage((Player) sender, str);
		} else {
			sender.sendMessage(ChatColor.stripColor(ChatColor.translateAlternateColorCodes('&', str)));
		}
	}
	private void sendPlayerMessage(Player player, String str) {
		if (!str.isEmpty()) {
			player.sendMessage(ChatColor.translateAlternateColorCodes('&', str));
		}
	}
}
