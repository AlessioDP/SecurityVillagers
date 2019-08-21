package com.alessiodp.securityvillagers.bukkit.addons.external.factions;

import com.alessiodp.securityvillagers.common.configuration.data.ConfigMain;
import com.massivecraft.factions.Board;
import com.massivecraft.factions.FLocation;
import com.massivecraft.factions.Faction;
import com.massivecraft.factions.listeners.FactionsBlockListener;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
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
	
	private boolean isUnprotected(Faction f) {
		return f.isWilderness() || f.isWarZone();
	}
	
	@Override
	public boolean isClaimProtectedByAttack(Entity entity, Location location) {
		Faction f = Board.getInstance().getFactionAt(new FLocation(location));
		if (isUnprotected(f))
			return false;
		
		// Claim protected but members can bypass it
		return !(entity instanceof Player && ConfigMain.GENERAL_FACTIONS_MEMBERBYPASS_PROTECTION
				&& FactionsBlockListener.playerCanBuildDestroyBlock((Player) entity, location, "SecurityVillagers check", true));
	}
	
	@Override
	public boolean isClaimProtectedByInteract(Player player, Location location) {
		Faction f = Board.getInstance().getFactionAt(new FLocation(location));
		if (isUnprotected(f))
			return false;
		
		// Claim protected but members can bypass it
		return !(ConfigMain.GENERAL_FACTIONS_MEMBERBYPASS_INTERACT
				&& FactionsBlockListener.playerCanBuildDestroyBlock(player, location, "SecurityVillagers check", true));
	}
}
