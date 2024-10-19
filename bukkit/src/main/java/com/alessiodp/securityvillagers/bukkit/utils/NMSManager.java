package com.alessiodp.securityvillagers.bukkit.utils;

import com.alessiodp.securityvillagers.bukkit.villagers.objects.MobsType;
import lombok.NonNull;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class NMSManager {
	private final HashMap<MobsType, List<String>> mobsTypeMap = new HashMap<>();
	private ServerVersion serverVersion;
	
	public NMSManager() {
		try {
			String fullServerVersion = Bukkit.getServer().getBukkitVersion();
			if (fullServerVersion.contains(".")) {
				fullServerVersion = fullServerVersion.split("-")[0];
			}
			String[] splitFullServerVersion = fullServerVersion.split("\\.");
			serverVersion = new ServerVersion(
					Integer.parseInt(splitFullServerVersion[0]),
					splitFullServerVersion.length > 1 ? Integer.parseInt(splitFullServerVersion[1]) : 0,
					splitFullServerVersion.length > 2 ? Integer.parseInt(splitFullServerVersion[2]) : 0
			);
		} catch (Exception ex) {
			// This version of Bukkit is not supported
			ex.printStackTrace();
			serverVersion = new ServerVersion(0, 0, 0);
		}
		
		initializeMobsTypeMap();
	}
	
	private void initializeMobsTypeMap() {
		// 1.11+
		mobsTypeMap.put(MobsType.EVOKER, Collections.singletonList("CraftEvoker"));
		mobsTypeMap.put(MobsType.EVOKER_FANGS, Collections.singletonList("CraftEvokerFangs"));
		mobsTypeMap.put(MobsType.STRAY, Collections.singletonList("CraftStray")); // <1.13
		mobsTypeMap.put(MobsType.VEX, Collections.singletonList("CraftVex"));
		mobsTypeMap.put(MobsType.VINDICATOR, Collections.singletonList("CraftVindicator"));
		
		// 1.13+
		mobsTypeMap.put(MobsType.ILLUSIONER, Collections.singletonList("CraftIllusioner"));
		
		// 1.14+
		mobsTypeMap.put(MobsType.TRADER_LLAMA, Collections.singletonList("CraftTraderLlama"));
		mobsTypeMap.put(MobsType.WANDERING_TRADER, Collections.singletonList("CraftWanderingTrader"));
		mobsTypeMap.put(MobsType.PILLAGER, Collections.singletonList("CraftPillager"));
		mobsTypeMap.put(MobsType.RAVAGER, Collections.singletonList("CraftRavager"));
		
		// 1.15+
		mobsTypeMap.put(MobsType.ZOGLIN, Collections.singletonList("CraftZoglin"));
	}
	
	public boolean isMobInstanceOf(@NonNull MobsType mobsType, @NonNull Object entity) {
		return mobsTypeMap.get(mobsType).contains(entity.getClass().getSimpleName());
	}
	
	public Material getMaterialInMainHand(Player player) {
		if (serverVersion.getMajor() == 1 && serverVersion.getMinor() < 9) {
			//noinspection deprecation
			return player.getItemInHand().getType();
		}
		return player.getInventory().getItemInMainHand().getType();
	}
	
	public boolean existOffHand() {
		if (serverVersion.getMajor() == 1 && serverVersion.getMinor() < 9) {
			return false;
		}
		return true;
	}
	
	public Material getVillagerEgg() {
		if (serverVersion.getMajor() == 1 && serverVersion.getMinor() < 13) {
			return Material.getMaterial("MONSTER_EGG");
		}
		return Material.VILLAGER_SPAWN_EGG;
	}
	
	public boolean isHarvestable(Material material) {
		if (serverVersion.getMajor() == 1 && serverVersion.getMinor() < 13) {
			return material == Material.WHEAT
					|| material == Material.POTATO
					|| material == Material.CARROT
					|| material == Material.BEETROOT;
		}
		return material == Material.WHEAT
				|| material == Material.POTATOES
				|| material == Material.CARROTS
				|| material == Material.BEETROOTS;
	}
}

