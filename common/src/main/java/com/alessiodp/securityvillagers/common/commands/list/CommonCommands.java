package com.alessiodp.securityvillagers.common.commands.list;

import com.alessiodp.core.common.commands.list.ADPCommand;
import com.alessiodp.securityvillagers.common.commands.utils.SecurityVillagersPermission;
import com.alessiodp.securityvillagers.common.configuration.data.ConfigMain;
import com.alessiodp.securityvillagers.common.configuration.data.Messages;
import lombok.Getter;

public enum CommonCommands implements ADPCommand {
	SV,
	
	CHANGEAGE,
	HELP,
	PROFESSION,
	PROTECT,
	RELOAD,
	RENAME,
	VERSION;
	
	@Getter private String command;
	@Getter private String help;
	@Getter private String permission;
	
	CommonCommands() {
		command = "";
		help = "";
		permission = "";
	}
	
	public static void setup() {
		CommonCommands.SV.command = ConfigMain.COMMANDS_MAIN_SV;
		
		CommonCommands.CHANGEAGE.command = ConfigMain.COMMANDS_MAIN_CHANGEAGE;
		CommonCommands.CHANGEAGE.help = Messages.HELP_CMD_CHANGEAGE;
		CommonCommands.CHANGEAGE.permission = SecurityVillagersPermission.ADMIN_CHANGEAGE.toString();
		CommonCommands.HELP.command = ConfigMain.COMMANDS_MAIN_HELP;
		CommonCommands.HELP.help = Messages.HELP_CMD_HELP;
		CommonCommands.HELP.permission = SecurityVillagersPermission.ADMIN_HELP.toString();
		CommonCommands.PROFESSION.command = ConfigMain.COMMANDS_MAIN_PROFESSION;
		CommonCommands.PROFESSION.help = Messages.HELP_CMD_PROFESSION;
		CommonCommands.PROFESSION.permission = SecurityVillagersPermission.ADMIN_PROFESSION.toString();
		CommonCommands.PROTECT.command = ConfigMain.COMMANDS_MAIN_PROTECT;
		CommonCommands.PROTECT.help = Messages.HELP_CMD_PROTECT;
		CommonCommands.PROTECT.permission = SecurityVillagersPermission.ADMIN_PROTECT.toString();
		CommonCommands.RELOAD.command = ConfigMain.COMMANDS_MAIN_RELOAD;
		CommonCommands.RELOAD.help = Messages.HELP_CMD_RELOAD;
		CommonCommands.RELOAD.permission = SecurityVillagersPermission.ADMIN_RELOAD.toString();
		CommonCommands.RENAME.command = ConfigMain.COMMANDS_MAIN_RENAME;
		CommonCommands.RENAME.help = Messages.HELP_CMD_RENAME;
		CommonCommands.RENAME.permission = SecurityVillagersPermission.ADMIN_RENAME.toString();
		CommonCommands.VERSION.command = ConfigMain.COMMANDS_MAIN_VERSION;
		CommonCommands.VERSION.help = Messages.HELP_CMD_VERSION;
		CommonCommands.VERSION.permission = SecurityVillagersPermission.ADMIN_VERSION.toString();
	}
	
	@Override
	public String getOriginalName() {
		return this.name();
	}
}
