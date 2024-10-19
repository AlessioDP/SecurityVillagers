package com.alessiodp.securityvillagers.bukkit.addons.external.factions;

import com.alessiodp.securityvillagers.common.configuration.data.ConfigMain;
import net.prosavage.factionsx.core.FPlayer;
import net.prosavage.factionsx.core.Faction;
import net.prosavage.factionsx.manager.GridManager;
import net.prosavage.factionsx.manager.PlayerManager;
import net.prosavage.factionsx.persist.data.FLocation;
import net.prosavage.factionsx.util.PlayerAction;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class FactionsXHandler implements IFaction {
	@Override
	public String getName() {
		return "FactionsX";
	}
	
	@Override
	public boolean isEnabled() {
		boolean ret = false;
		Plugin plugin = Bukkit.getPluginManager().getPlugin("FactionsX");
		if (plugin != null && plugin.isEnabled()) {
			ret = true;
		}
		return ret;
	}
	
	private boolean isUnprotected(Faction f) {
		return f.isWilderness() || f.isWarzone();
	}
	
	@Override
	public boolean isClaimProtectedByAttack(Entity entity, Location location) {
		Faction f = GridManager.INSTANCE.getFactionAt(new FLocation(location.getChunk().getX(), location.getChunk().getZ(), location.getWorld().getName()));
		if (isUnprotected(f))
			return false;
		
		FPlayer player = PlayerManager.INSTANCE.getFPlayer(entity.getUniqueId());
		
		// Claim protected but members can bypass it
		return !(entity instanceof Player && ConfigMain.GENERAL_FACTIONS_MEMBERBYPASS_PROTECTION
				&& player != null && f.checkPermissionForFaction(player.getFaction(), PlayerAction.HURT_PLAYER));
	}
	
	@Override
	public boolean isClaimProtectedByInteract(Player player, Location location) {
		Faction f = GridManager.INSTANCE.getFactionAt(new FLocation(location.getChunk().getX(), location.getChunk().getZ(), location.getWorld().getName()));
		if (isUnprotected(f))
			return false;
		
		FPlayer fPlayer = PlayerManager.INSTANCE.getFPlayer(player);
		
		// Claim protected but members can bypass it
		return !(ConfigMain.GENERAL_FACTIONS_MEMBERBYPASS_INTERACT
				&& f.checkPermissionForFaction(fPlayer.getFaction(), PlayerAction.USE_ENTITY));
	}
}
