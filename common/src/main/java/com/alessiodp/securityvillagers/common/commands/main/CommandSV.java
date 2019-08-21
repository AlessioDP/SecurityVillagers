package com.alessiodp.securityvillagers.common.commands.main;

import com.alessiodp.core.common.commands.utils.ADPMainCommand;
import com.alessiodp.core.common.user.User;
import com.alessiodp.securityvillagers.common.SecurityVillagersPlugin;
import com.alessiodp.securityvillagers.common.commands.list.CommonCommands;
import com.alessiodp.securityvillagers.common.commands.sub.CommandChangeAge;
import com.alessiodp.securityvillagers.common.commands.sub.CommandHelp;
import com.alessiodp.securityvillagers.common.commands.sub.CommandProfession;
import com.alessiodp.securityvillagers.common.commands.sub.CommandProtect;
import com.alessiodp.securityvillagers.common.commands.sub.CommandReload;
import com.alessiodp.securityvillagers.common.commands.sub.CommandRename;
import com.alessiodp.securityvillagers.common.commands.sub.CommandVersion;
import com.alessiodp.securityvillagers.common.configuration.data.ConfigMain;
import com.alessiodp.securityvillagers.common.configuration.data.Messages;

import java.util.ArrayList;
import java.util.HashMap;

public class CommandSV extends ADPMainCommand {
	
	public CommandSV(SecurityVillagersPlugin plugin) {
		super(plugin);
		
		commandName = ConfigMain.COMMANDS_MAIN_SV;
		description = ConfigMain.COMMANDS_DESCRIPTION_SV;
		subCommands = new HashMap<>();
		enabledSubCommands = new ArrayList<>();
		tabSupport = ConfigMain.COMMANDS_TABSUPPORT;
		
		register(CommonCommands.HELP, new CommandHelp(plugin, this));
		register(CommonCommands.RELOAD, new CommandReload(plugin, this));
		register(CommonCommands.VERSION, new CommandVersion(plugin, this));
		
		if (ConfigMain.CHANGEAGE_ENABLE)
			register(CommonCommands.CHANGEAGE, new CommandChangeAge(plugin, this));
		
		if (ConfigMain.PROFESSION_ENABLE)
			register(CommonCommands.PROFESSION, new CommandProfession(plugin, this));
		
		if (ConfigMain.GENERAL_PROTECTIONTYPE == ConfigMain.ProtectionType.CUSTOM)
			register(CommonCommands.PROTECT, new CommandProtect(plugin, this));
		
		if (ConfigMain.RENAME_ENABLE)
			register(CommonCommands.RENAME, new CommandRename(plugin, this));
	}
	
	@Override
	public boolean onCommand(User sender, String command, String[] args) {
		String subCommand;
		if (sender.isPlayer()) {
			if (args.length == 0) {
				// Set /sv to /sv help
				subCommand = ConfigMain.COMMANDS_MAIN_HELP.toLowerCase();
			} else {
				subCommand = args[0].toLowerCase();
			}
			
			if (exists(subCommand)) {
				plugin.getCommandManager().getCommandUtils().executeCommand(sender, getCommandName(), getSubCommand(subCommand), args);
			} else {
				sender.sendMessage(Messages.SECURITYVILLAGERS_COMMON_INVALIDCMD, true);
			}
		} else {
			// Console
			if (args.length > 0) {
				subCommand = args[0].toLowerCase();
				if (exists(subCommand) && getSubCommand(subCommand).isExecutableByConsole()) {
					plugin.getCommandManager().getCommandUtils().executeCommand(sender, getCommandName(), getSubCommand(subCommand), args);
				} else {
					plugin.logConsole(plugin.getColorUtils().removeColors(Messages.SECURITYVILLAGERS_COMMON_INVALIDCMD), false);
				}
			} else {
				// Print help
				for (String str : Messages.HELP_CONSOLEHELP) {
					plugin.logConsole(str, false);
				}
			}
		}
		return true;
	}
}