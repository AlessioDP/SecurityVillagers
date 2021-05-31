package com.alessiodp.securityvillagers.bukkit;

import com.alessiodp.core.bukkit.addons.internal.json.BukkitJsonHandler;
import com.alessiodp.core.bukkit.addons.internal.json.SpigotJsonHandler;
import com.alessiodp.core.bukkit.addons.internal.title.BukkitTitleHandler;
import com.alessiodp.core.bukkit.scheduling.ADPBukkitScheduler;
import com.alessiodp.core.common.bootstrap.ADPBootstrap;
import com.alessiodp.core.common.configuration.Constants;
import com.alessiodp.securityvillagers.bukkit.addons.BukkitSVAddonManager;
import com.alessiodp.securityvillagers.bukkit.addons.external.BukkitMetricsHandler;
import com.alessiodp.securityvillagers.bukkit.bootstrap.BukkitSecurityVillagersBootstrap;
import com.alessiodp.securityvillagers.bukkit.commands.BukkitSVCommandManager;
import com.alessiodp.securityvillagers.bukkit.configuration.BukkitSVConfigurationManager;
import com.alessiodp.securityvillagers.bukkit.listeners.BukkitDamageListener;
import com.alessiodp.securityvillagers.bukkit.listeners.BukkitDeathListener;
import com.alessiodp.securityvillagers.bukkit.listeners.BukkitFarmListener;
import com.alessiodp.securityvillagers.bukkit.listeners.BukkitFightListener;
import com.alessiodp.securityvillagers.bukkit.listeners.BukkitInteractListener;
import com.alessiodp.securityvillagers.bukkit.listeners.BukkitJoinLeaveListener;
import com.alessiodp.securityvillagers.bukkit.listeners.BukkitSelectListener;
import com.alessiodp.securityvillagers.bukkit.listeners.BukkitSpawnListener;
import com.alessiodp.securityvillagers.bukkit.listeners.BukkitTargetListener;
import com.alessiodp.securityvillagers.bukkit.utils.NMSManager;
import com.alessiodp.securityvillagers.bukkit.villagers.BukkitVillagerManager;
import com.alessiodp.securityvillagers.common.SecurityVillagersPlugin;
import com.alessiodp.securityvillagers.common.configuration.SVConstants;
import lombok.Getter;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;

public class BukkitSecurityVillagersPlugin extends SecurityVillagersPlugin {
	@Getter private final int bstatsId = SVConstants.PLUGIN_BSTATS_BUKKIT_ID;
	
	@Getter private NMSManager nmsManager;
	
	public BukkitSecurityVillagersPlugin(ADPBootstrap bootstrap) {
		super(bootstrap);
	}
	
	@Override
	protected void initializeCore() {
		scheduler = new ADPBukkitScheduler(this);
		configurationManager = new BukkitSVConfigurationManager(this);
		nmsManager = new NMSManager();
		
		super.initializeCore();
	}
	
	@Override
	protected void loadCore() {
		villagerManager = new BukkitVillagerManager(this);
		commandManager = new BukkitSVCommandManager(this);
		
		super.loadCore();
	}
	
	@Override
	protected void postHandle() {
		addonManager = new BukkitSVAddonManager(this);
		
		super.postHandle();
		
		new BukkitMetricsHandler(this);
	}
	
	@Override
	protected void initializeJsonHandler() {
		if (((BukkitSecurityVillagersBootstrap) getBootstrap()).isSpigot())
			jsonHandler = new SpigotJsonHandler();
		else
			jsonHandler = new BukkitJsonHandler();
	}
	
	@Override
	protected void initializeTitleHandler() {
		titleHandler = new BukkitTitleHandler();
	}
	
	@Override
	protected void registerListeners() {
		getLoggerManager().logDebug(Constants.DEBUG_PLUGIN_REGISTERING, true);
		PluginManager pm = ((Plugin) getBootstrap()).getServer().getPluginManager();
		pm.registerEvents(new BukkitDamageListener(this), ((Plugin) getBootstrap()));
		pm.registerEvents(new BukkitDeathListener(this), ((Plugin) getBootstrap()));
		pm.registerEvents(new BukkitFarmListener(this), ((Plugin) getBootstrap()));
		pm.registerEvents(new BukkitFightListener(this), ((Plugin) getBootstrap()));
		pm.registerEvents(new BukkitInteractListener(this), ((Plugin) getBootstrap()));
		pm.registerEvents(new BukkitJoinLeaveListener(this), ((Plugin) getBootstrap()));
		pm.registerEvents(new BukkitSelectListener(this), ((Plugin) getBootstrap()));
		pm.registerEvents(new BukkitSpawnListener(this), ((Plugin) getBootstrap()));
		pm.registerEvents(new BukkitTargetListener(this), ((Plugin) getBootstrap()));
	}
}