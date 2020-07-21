package com.alessiodp.securityvillagers.common.commands;

import com.alessiodp.core.common.ADPPlugin;
import com.alessiodp.core.common.commands.CommandManager;
import com.alessiodp.core.common.commands.utils.CommandData;
import com.alessiodp.securityvillagers.common.commands.utils.SVCommandData;

public abstract class SVCommandManager extends CommandManager {
	protected SVCommandManager(ADPPlugin plugin) {
		super(plugin);
	}
	
	@Override
	public CommandData initializeCommandData() {
		return new SVCommandData();
	}
}

