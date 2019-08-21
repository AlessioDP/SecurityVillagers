package com.alessiodp.securityvillagers.bukkit.utils;

import com.alessiodp.securityvillagers.bukkit.BukkitSecurityVillagersPlugin;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class NMSManager {
	private IDataNMS dataNMS;
	
	public NMSManager() {
		try {
			// org.bukkit.craftbukkit.VERSION...
			String bukkitVersion = Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3];
			dataNMS = (IDataNMS) Class.forName(
					BukkitSecurityVillagersPlugin.class.getPackage().getName() + ".nms." + bukkitVersion + ".DataNMS"
			).newInstance();
			dataNMS.populateMobs();
		} catch (Exception ex) {
			// This version of Bukkit is not supported
			ex.printStackTrace();
		}
	}
	
	public Material getMaterialInMainHand(Player player) {
		return dataNMS.getHandMaterial(player);
	}
	
	public boolean existOffHand() {
		return dataNMS.existOffHand();
	}
	
	public Material getVillagerEgg() {
		return dataNMS.getVillagerEgg();
	}
}
