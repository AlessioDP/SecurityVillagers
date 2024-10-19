package com.alessiodp.securityvillagers.bukkit.addons.external;

import com.alessiodp.core.common.configuration.Constants;
import com.alessiodp.securityvillagers.bukkit.addons.external.factions.FactionMassiveCraftHandler;
import com.alessiodp.securityvillagers.bukkit.addons.external.factions.FactionsXHandler;
import com.alessiodp.securityvillagers.bukkit.addons.external.factions.IFaction;
import com.alessiodp.securityvillagers.common.SecurityVillagersPlugin;
import com.alessiodp.securityvillagers.common.configuration.data.ConfigMain;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

@RequiredArgsConstructor
public class FactionsHandler {
	@NonNull private final SecurityVillagersPlugin plugin;
	private static final String ADDON_NAME = "Factions";
	private static boolean active;
	
	private static IFaction handler;
	
	public void init() {
		active = false;
		handler = null;
		if (ConfigMain.GENERAL_PROTECTIONTYPE == ConfigMain.ProtectionType.FACTIONS) {
			IFaction[] list = {
					new FactionMassiveCraftHandler(),
					new FactionsXHandler()
			};
			
			for (IFaction faction : list) {
				if (faction.isEnabled()) {
					handler = faction;
					break;
				}
			}
			
			if (handler != null) {
				active = true;
				
				plugin.getLoggerManager().log(String.format(Constants.DEBUG_ADDON_HOOKED, handler.getName()), true);
			} else {
				plugin.getLoggerManager().log(String.format(Constants.DEBUG_ADDON_FAILED, ADDON_NAME), true);
			}
		}
	}
	
	public static boolean isClaimProtectedByAttack(Entity attacker, Location location) {
		return !active || handler.isClaimProtectedByAttack(attacker, location);
	}
	
	public static boolean isClaimProtectedByInteract(Player player, Location location) {
		return !active || handler.isClaimProtectedByInteract(player, location);
	}
}
