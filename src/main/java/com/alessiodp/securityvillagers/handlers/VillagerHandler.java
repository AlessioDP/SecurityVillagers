package com.alessiodp.securityvillagers.handlers;

import com.alessiodp.securityvillagers.SecurityVillagers;
import lombok.Getter;
import org.bukkit.entity.Villager;

import java.util.HashMap;
import java.util.UUID;

public class VillagerHandler {
	private SecurityVillagers plugin;
	
	@Getter private HashMap<UUID, Villager> listSelectedVillagers;
	
	public VillagerHandler(SecurityVillagers instance) {
		plugin = instance;
		listSelectedVillagers = new HashMap<>();
	}
}
