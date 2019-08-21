package com.alessiodp.securityvillagers.common.commands;

import com.alessiodp.core.common.ADPPlugin;
import com.alessiodp.core.common.commands.CommandManager;
import com.alessiodp.core.common.commands.utils.CommandData;
import com.alessiodp.securityvillagers.common.commands.list.CommonCommands;
import com.alessiodp.securityvillagers.common.commands.utils.SVCommandData;
import com.alessiodp.securityvillagers.common.villagers.objects.VillagerProfession;

public abstract class SVCommandManager extends CommandManager {
	protected SVCommandManager(ADPPlugin plugin) {
		super(plugin);
	}
	
	@Override
	protected void prepareCommands() {
		CommonCommands.setup();
		VillagerProfession.setup();
	}
	
	@Override
	public CommandData initializeCommandData() {
		return new SVCommandData();
	}
}

