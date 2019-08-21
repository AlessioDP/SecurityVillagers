package com.alessiodp.securityvillagers.bukkit.addons.external.factions;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

public interface IFaction {
	String getName();
	boolean isEnabled();
	boolean isClaimProtectedByAttack(Entity entity, Location location);
	boolean isClaimProtectedByInteract(Player player, Location location);
}
