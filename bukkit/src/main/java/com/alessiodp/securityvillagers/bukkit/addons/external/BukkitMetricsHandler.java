package com.alessiodp.securityvillagers.bukkit.addons.external;

import com.alessiodp.core.bukkit.addons.external.bstats.bukkit.Metrics;
import com.alessiodp.core.common.ADPPlugin;
import com.alessiodp.core.common.addons.external.MetricsHandler;
import com.alessiodp.securityvillagers.common.configuration.data.ConfigMain;
import lombok.NonNull;
import org.bukkit.plugin.Plugin;

public class BukkitMetricsHandler extends MetricsHandler {
	public BukkitMetricsHandler(@NonNull ADPPlugin plugin) {
		super(plugin);
	}
	
	@Override
	protected void registerMetrics() {
		Metrics metrics = new Metrics((Plugin) plugin.getBootstrap());
		
		metrics.addCustomChart(new Metrics.SimplePie("villagers_protection", () -> {
			if (ConfigMain.GENERAL_DAMAGE_HIT || ConfigMain.GENERAL_DAMAGE_ARROW) {
				return "Protected";
			}
			return "Unprotected";
		}));
		
		metrics.addCustomChart(new Metrics.SimplePie("factions_support", () -> {
			if (ConfigMain.GENERAL_PROTECTIONTYPE == ConfigMain.ProtectionType.FACTIONS)
				return "True";
			return "False";
		}));
		
		metrics.addCustomChart(new Metrics.SimplePie("changeage_support", () -> {
			if (ConfigMain.CHANGEAGE_ENABLE)
				return "True";
			return "False";
		}));
		
		metrics.addCustomChart(new Metrics.SimplePie("mute_support", () -> {
			if (ConfigMain.GENERAL_MUTE_SOUND)
				return "True";
			return "False";
		}));
	}
}
