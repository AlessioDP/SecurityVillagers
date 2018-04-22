package com.alessiodp.securityvillagers.addons;

import com.alessiodp.securityvillagers.SecurityVillagers;
import com.alessiodp.securityvillagers.addons.external.FactionsHandler;
import com.alessiodp.securityvillagers.addons.external.ProtocolLibHandler;

public class AddonManager {
	private SecurityVillagers plugin;
	
	public AddonManager(SecurityVillagers instance) {
		plugin = instance;
	}
	
	public void loadAddons() {
		new FactionsHandler(plugin);
		new ProtocolLibHandler(plugin);
	}
}
