package com.alessiodp.securityvillagers.bukkit.addons;

import com.alessiodp.core.common.addons.AddonManager;
import com.alessiodp.securityvillagers.bukkit.addons.external.FactionsHandler;
import com.alessiodp.securityvillagers.bukkit.addons.external.ProtocolLibHandler;
import com.alessiodp.securityvillagers.common.SecurityVillagersPlugin;

public class BukkitSVAddonManager extends AddonManager {
	private final FactionsHandler factions;
	private final ProtocolLibHandler protocolLib;
	
	public BukkitSVAddonManager(SecurityVillagersPlugin plugin) {
		super(plugin);
		
		factions = new FactionsHandler(plugin);
		protocolLib = new ProtocolLibHandler(plugin);
	}
	
	@Override
	public void loadAddons() {
		factions.init();
		protocolLib.init();
	}
}
