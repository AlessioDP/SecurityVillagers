package com.alessiodp.securityvillagers.bukkit.commands;

import com.alessiodp.core.bukkit.commands.utils.BukkitCommandUtils;
import com.alessiodp.core.common.ADPPlugin;
import com.alessiodp.securityvillagers.common.SecurityVillagersPlugin;
import com.alessiodp.securityvillagers.common.commands.SVCommandManager;
import com.alessiodp.securityvillagers.common.commands.main.CommandSV;
import com.alessiodp.securityvillagers.common.configuration.data.ConfigMain;

import java.util.ArrayList;

public class BukkitSVCommandManager extends SVCommandManager {
	public BukkitSVCommandManager(ADPPlugin plugin) {
		super(plugin);
	}
	
	@Override
	protected void prepareCommands() {
		commandOrder = null; // Command order disabled
		commandUtils = new BukkitCommandUtils(plugin, ConfigMain.COMMANDS_SUB_ON, ConfigMain.COMMANDS_SUB_OFF);
		super.prepareCommands();
	}
	
	@Override
	protected void registerCommands() {
		mainCommands = new ArrayList<>();
		mainCommands.add(new CommandSV((SecurityVillagersPlugin) plugin));
	}
}