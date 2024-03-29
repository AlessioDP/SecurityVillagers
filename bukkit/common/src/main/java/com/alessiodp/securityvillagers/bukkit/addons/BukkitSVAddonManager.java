package com.alessiodp.securityvillagers.bukkit.addons;

import com.alessiodp.core.common.addons.AddonManager;
import com.alessiodp.securityvillagers.bukkit.addons.external.CitizensHandler;
import com.alessiodp.securityvillagers.bukkit.addons.external.FactionsHandler;
import com.alessiodp.securityvillagers.bukkit.addons.external.GlowHandler;
import com.alessiodp.securityvillagers.bukkit.addons.external.ProtocolLibHandler;
import com.alessiodp.securityvillagers.common.SecurityVillagersPlugin;

public class BukkitSVAddonManager extends AddonManager {
	private final CitizensHandler citizens;
	private final FactionsHandler factions;
	private final GlowHandler glow;
	private final ProtocolLibHandler protocolLib;
	
	public BukkitSVAddonManager(SecurityVillagersPlugin plugin) {
		super(plugin);
		
		citizens = new CitizensHandler(plugin);
		factions = new FactionsHandler(plugin);
		glow = new GlowHandler(plugin);
		protocolLib = new ProtocolLibHandler(plugin);
	}
	
	@Override
	public void loadAddons() {
		citizens.init();
		factions.init();
		glow.init();
		protocolLib.init();
	}
}
