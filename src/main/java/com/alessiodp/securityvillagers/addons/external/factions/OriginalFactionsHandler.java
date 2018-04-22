package com.alessiodp.securityvillagers.addons.external.factions;

import com.massivecraft.factions.engine.EnginePermBuild;
import com.massivecraft.massivecore.ps.PS;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class OriginalFactionsHandler implements IFaction {
	@Override
	public String getName() {
		return "Factions";
	}
	
	@Override
	public boolean isEnabled() {
		boolean ret = false;
		Plugin plugin = Bukkit.getPluginManager().getPlugin("Factions");
		if (plugin != null && plugin.isEnabled()) {
			if (plugin.getDescription().getMain().equals("com.massivecraft.factions.Factions")
					&& plugin.getDescription().getDepend().contains("MassiveCore")) {
				ret = true;
			}
		}
		return ret;
	}
	
	@Override
	public boolean canHit(Player player, Location location) {
		return EnginePermBuild.canPlayerBuildAt(player, PS.valueOf(location), false);
	}
}
