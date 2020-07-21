package com.alessiodp.securityvillagers.common.commands.list;

import com.alessiodp.core.common.commands.list.ADPCommand;

public enum CommonCommands implements ADPCommand {
	SV,
	
	CHANGEAGE,
	HELP,
	PROFESSION,
	PROTECT,
	RELOAD,
	RENAME,
	VERSION;
	
	@Override
	public String getOriginalName() {
		return this.name();
	}
}
