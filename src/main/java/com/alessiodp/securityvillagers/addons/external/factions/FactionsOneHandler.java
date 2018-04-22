package com.alessiodp.securityvillagers.addons.external.factions;

import com.massivecraft.factions.Patch;
import com.massivecraft.factions.struct.FPerm;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.lang.reflect.Field;

public class FactionsOneHandler implements IFaction {
	@Override
	public String getName() {
		return "FactionsOne";
	}
	
	@Override
	public boolean isEnabled() {
		boolean ret = false;
		Plugin plugin = Bukkit.getPluginManager().getPlugin("Factions");
		if (plugin != null && plugin.isEnabled()) {
			try {
				Class patch = Class.forName("com.massivecraft.factions.Patch");
				Field f = patch.getField("NAME");
				if (f.get(new Patch()).equals("FactionsOne"))
					ret = true;
			} catch (Exception ex) {}
		}
		return ret;
	}
	
	@Override
	public boolean canHit(Player player, Location location) {
		return FPerm.BUILD.has(player, location, false);
	}
}
