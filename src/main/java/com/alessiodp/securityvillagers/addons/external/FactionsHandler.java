package com.alessiodp.securityvillagers.addons.external;

import com.alessiodp.securityvillagers.SecurityVillagers;
import com.alessiodp.securityvillagers.addons.external.factions.FactionsOneHandler;
import com.alessiodp.securityvillagers.addons.external.factions.IFaction;
import com.alessiodp.securityvillagers.addons.external.factions.OriginalFactionsHandler;
import com.alessiodp.securityvillagers.addons.external.factions.SavageFactionsHandler;
import com.alessiodp.securityvillagers.configuration.Constants;
import com.alessiodp.securityvillagers.configuration.data.ConfigMain;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class FactionsHandler {
	private SecurityVillagers plugin;
	
	private static IFaction factionPlugin;
	
	public FactionsHandler(SecurityVillagers instance) {
		plugin = instance;
		init();
	}
	
	private void init() {
		factionPlugin = null;
		if (ConfigMain.FACTIONS_ENABLE) {
			IFaction[] list = {
					new OriginalFactionsHandler(),
					new SavageFactionsHandler(),
					new FactionsOneHandler()
			};
			
			for (IFaction faction : list) {
				if (faction.isEnabled()) {
					factionPlugin = faction;
					break;
				}
			}
			
			if (factionPlugin != null) {
				plugin.log(Constants.DEBUG_ADDON_HOOK
						.replace("{addon}", factionPlugin.getName()));
			} else {
				plugin.log(Constants.DEBUG_ADDON_MISSING
						.replace("{addon}", "Factions"));
			}
		}
	}
	
	public static boolean canHit(Player player, Location location) {
		boolean ret = true;
		if (factionPlugin != null)
			ret = factionPlugin.canHit(player, location);
		return ret;
	}
}
