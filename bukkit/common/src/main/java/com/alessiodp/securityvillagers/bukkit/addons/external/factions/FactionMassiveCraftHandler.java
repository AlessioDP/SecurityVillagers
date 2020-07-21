package com.alessiodp.securityvillagers.bukkit.addons.external.factions;

import com.alessiodp.securityvillagers.common.configuration.data.ConfigMain;
import com.massivecraft.factions.engine.EnginePermBuild;
import com.massivecraft.factions.entity.BoardColl;
import com.massivecraft.factions.entity.Faction;
import com.massivecraft.massivecore.ps.PS;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class FactionMassiveCraftHandler implements IFaction {
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
	
	private boolean isUnprotected(Faction f) {
		return f.isNone();
	}
	
	@Override
	public boolean isClaimProtectedByAttack(Entity entity, Location location) {
		Faction f = BoardColl.get().getFactionAt(PS.valueOf(location));
		if (isUnprotected(f))
			return false;
		
		// Claim protected but members can bypass it
		return !(entity instanceof Player && ConfigMain.GENERAL_FACTIONS_MEMBERBYPASS_PROTECTION
				&& EnginePermBuild.canPlayerBuildAt(entity, PS.valueOf(location), false));
	}
	
	@Override
	public boolean isClaimProtectedByInteract(Player player, Location location) {
		Faction f = BoardColl.get().getFactionAt(PS.valueOf(location));
		if (isUnprotected(f))
			return false;
		
		// Claim protected but members can bypass it
		return !(ConfigMain.GENERAL_FACTIONS_MEMBERBYPASS_INTERACT
				&& EnginePermBuild.canPlayerBuildAt(player, PS.valueOf(location), false));
	}
}
