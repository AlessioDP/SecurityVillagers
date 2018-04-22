package com.alessiodp.securityvillagers.addons.external.factions;

import com.massivecraft.factions.listeners.FactionsBlockListener;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class SavageFactionsHandler implements IFaction {
	@Override
	public String getName() {
		return "SavageFactions";
	}
	
	@Override
	public boolean isEnabled() {
		boolean ret = false;
		Plugin plugin = Bukkit.getPluginManager().getPlugin("Factions");
		if (plugin != null && plugin.isEnabled()) {
			if (plugin.getDescription().getAuthors().contains("ProSavage")) {
				ret = true;
			}
		}
		return ret;
	}
	
	@Override
	public boolean canHit(Player player, Location location) {
		return FactionsBlockListener.playerCanBuildDestroyBlock(player, location, "SecurityVillagers check", true);
	}
}
