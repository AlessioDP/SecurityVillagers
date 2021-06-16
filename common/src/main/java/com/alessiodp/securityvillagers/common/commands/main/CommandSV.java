package com.alessiodp.securityvillagers.common.commands.main;

import com.alessiodp.core.common.commands.list.ADPCommand;
import com.alessiodp.core.common.commands.utils.ADPExecutableCommand;
import com.alessiodp.core.common.commands.utils.ADPMainCommand;
import com.alessiodp.core.common.user.User;
import com.alessiodp.core.common.utils.Color;
import com.alessiodp.core.common.utils.CommonUtils;
import com.alessiodp.securityvillagers.common.SecurityVillagersPlugin;
import com.alessiodp.securityvillagers.common.commands.list.CommonCommands;
import com.alessiodp.securityvillagers.common.commands.sub.CommandChangeAge;
import com.alessiodp.securityvillagers.common.commands.sub.CommandHelp;
import com.alessiodp.securityvillagers.common.commands.sub.CommandProfession;
import com.alessiodp.securityvillagers.common.commands.sub.CommandProtect;
import com.alessiodp.securityvillagers.common.commands.sub.CommandReload;
import com.alessiodp.securityvillagers.common.commands.sub.CommandRename;
import com.alessiodp.securityvillagers.common.commands.sub.CommandTeleport;
import com.alessiodp.securityvillagers.common.commands.sub.CommandVersion;
import com.alessiodp.securityvillagers.common.configuration.data.ConfigMain;
import com.alessiodp.securityvillagers.common.configuration.data.Messages;

import java.util.HashMap;
import java.util.Map;

public class CommandSV extends ADPMainCommand {
	
	public CommandSV(SecurityVillagersPlugin plugin) {
		super(plugin, CommonCommands.SV, ConfigMain.COMMANDS_MAIN_SV_COMMAND, true);
		
		description = ConfigMain.COMMANDS_MAIN_SV_DESCRIPTION;
		aliases = ConfigMain.COMMANDS_MAIN_SV_ALIASES;
		subCommands = new HashMap<>();
		subCommandsByEnum = new HashMap<>();
		tabSupport = ConfigMain.COMMANDS_TABSUPPORT;
		
		register(new CommandHelp(plugin, this));
		register(new CommandReload(plugin, this));
		register(new CommandVersion(plugin, this));
		
		if (ConfigMain.CHANGEAGE_ENABLE)
			register(new CommandChangeAge(plugin, this));
		
		if (ConfigMain.PROFESSION_ENABLE)
			register(new CommandProfession(plugin, this));
		
		if (ConfigMain.GENERAL_PROTECTIONTYPE == ConfigMain.ProtectionType.CUSTOM)
			register(new CommandProtect(plugin, this));
		
		if (ConfigMain.RENAME_ENABLE)
			register(new CommandRename(plugin, this));
		
		if (ConfigMain.TELEPORT_ENABLE)
			register(new CommandTeleport(plugin, this));
	}
	
	@Override
	public boolean onCommand(User sender, String command, String[] args) {
		String subCommand;
		if (sender.isPlayer()) {
			if (args.length == 0) {
				// Set /sv to /sv help
				subCommand = CommonUtils.toLowerCase(ConfigMain.COMMANDS_SUB_HELP);
			} else {
				subCommand = CommonUtils.toLowerCase(args[0]);
			}
			
			if (exists(subCommand)) {
				plugin.getCommandManager().getCommandUtils().executeCommand(sender, getCommandName(), getSubCommand(subCommand), args);
			} else {
				sender.sendMessage(Messages.SECURITYVILLAGERS_COMMON_INVALIDCMD, true);
			}
		} else {
			// Console
			if (args.length > 0) {
				subCommand = CommonUtils.toLowerCase(args[0]);
				if (exists(subCommand) && getSubCommand(subCommand).isExecutableByConsole()) {
					plugin.getCommandManager().getCommandUtils().executeCommand(sender, getCommandName(), getSubCommand(subCommand), args);
				} else {
					plugin.logConsole(Color.translateAndStripColor(Messages.SECURITYVILLAGERS_COMMON_INVALIDCMD));
				}
			} else {
				// Print help
				plugin.logConsole(Messages.HELP_CONSOLEHELP_HEADER);
				for(Map.Entry<ADPCommand, ADPExecutableCommand> e : plugin.getCommandManager().getOrderedCommands().entrySet()) {
					if (e.getValue().isExecutableByConsole()  && e.getValue().isListedInHelp()) {
						plugin.logConsole(Messages.HELP_CONSOLEHELP_COMMAND
								.replace("%command%", e.getValue().getConsoleSyntax())
								.replace("%description%", e.getValue().getDescription()));
					}
				}
			}
		}
		return true;
	}
}