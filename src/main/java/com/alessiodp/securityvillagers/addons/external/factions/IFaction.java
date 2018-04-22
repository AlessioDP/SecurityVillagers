package com.alessiodp.securityvillagers.addons.external.factions;

import org.bukkit.Location;
import org.bukkit.entity.Player;

public interface IFaction {
	String getName();
	boolean isEnabled();
	boolean canHit(Player player, Location location);
}
