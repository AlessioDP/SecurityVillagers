package com.alessiodp.securityvillagers.utils;

import org.bukkit.Bukkit;
import org.bukkit.Material;

public class MaterialUtils {
	public static Material getMaterial(String updatedName, String legacyName) {
		if (Bukkit.getVersion().contains("1.13")) {
			return Material.getMaterial(updatedName);
		}
		return Material.getMaterial(legacyName);
	}
}
