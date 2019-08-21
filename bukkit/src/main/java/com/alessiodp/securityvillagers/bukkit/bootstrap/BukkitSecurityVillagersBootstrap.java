package com.alessiodp.securityvillagers.bukkit.bootstrap;

import com.alessiodp.core.bukkit.bootstrap.ADPBukkitBootstrap;
import com.alessiodp.securityvillagers.bukkit.BukkitSecurityVillagersPlugin;

public class BukkitSecurityVillagersBootstrap  extends ADPBukkitBootstrap {
	public BukkitSecurityVillagersBootstrap() {
		plugin = new BukkitSecurityVillagersPlugin(this);
	}
}
