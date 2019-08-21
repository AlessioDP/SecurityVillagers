package com.alessiodp.securityvillagers.bukkit.addons.external;

import com.alessiodp.core.common.configuration.Constants;
import com.alessiodp.securityvillagers.bukkit.addons.external.factions.FactionMassiveCraftHandler;
import com.alessiodp.securityvillagers.bukkit.addons.external.factions.IFaction;
import com.alessiodp.securityvillagers.bukkit.addons.external.factions.SavageFactionsHandler;
import com.alessiodp.securityvillagers.common.SecurityVillagersPlugin;
import com.alessiodp.securityvillagers.common.configuration.data.ConfigMain;
import lombok.NonNull;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

public class FactionsHandler {
	private static SecurityVillagersPlugin plugin;
	private static final String ADDON_NAME = "Factions";
	private static boolean active;
	
	private static IFaction handler;
	
	public FactionsHandler(@NonNull SecurityVillagersPlugin sv) {
		plugin = sv;
	}
	
	public void init() {
		active = false;
		handler = null;
		if (ConfigMain.GENERAL_PROTECTIONTYPE == ConfigMain.ProtectionType.FACTIONS) {
			IFaction[] list = {
					new FactionMassiveCraftHandler(),
					new SavageFactionsHandler()
			};
			
			for (IFaction faction : list) {
				if (faction.isEnabled()) {
					handler = faction;
					break;
				}
			}
			
			if (handler != null) {
				active = true;
				
				plugin.getLoggerManager().log(Constants.DEBUG_ADDON_HOOKED
						.replace("{addon}", handler.getName()), true);
			} else {
				plugin.getLoggerManager().log(Constants.DEBUG_ADDON_FAILED
						.replace("{addon}", ADDON_NAME), true);
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
