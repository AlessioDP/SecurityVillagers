package com.alessiodp.securityvillagers.common.commands.sub;

import com.alessiodp.core.common.ADPPlugin;
import com.alessiodp.core.common.commands.list.ADPCommand;
import com.alessiodp.core.common.commands.utils.ADPExecutableCommand;
import com.alessiodp.core.common.commands.utils.ADPMainCommand;
import com.alessiodp.core.common.commands.utils.ADPSubCommand;
import com.alessiodp.core.common.commands.utils.CommandData;
import com.alessiodp.core.common.user.User;
import com.alessiodp.securityvillagers.common.commands.list.CommonCommands;
import com.alessiodp.securityvillagers.common.configuration.data.ConfigMain;
import com.alessiodp.securityvillagers.common.utils.SecurityVillagersPermission;
import com.alessiodp.securityvillagers.common.configuration.data.Messages;
import com.alessiodp.securityvillagers.common.utils.SVPlayerUtils;

import java.util.Map;
import java.util.Set;

public class CommandHelp extends ADPSubCommand {
	
	public CommandHelp(ADPPlugin plugin, ADPMainCommand mainCommand) {
		super(
				plugin,
				mainCommand,
				CommonCommands.HELP,
				SecurityVillagersPermission.ADMIN_HELP,
				ConfigMain.COMMANDS_SUB_HELP,
				false
		);
		
		syntax = baseSyntax();
		
		description = Messages.HELP_CMD_DESCRIPTIONS_HELP;
		help = Messages.HELP_CMD_HELP;
	}
	
	@Override
	public boolean preRequisites(CommandData commandData) {
		User sender = commandData.getSender();
		
		if (!sender.hasPermission(permission)) {
			((SVPlayerUtils) plugin.getPlayerUtils()).sendNoPermissionMessage(sender, permission);
			return false;
		}
		
		return true;
	}
	
	@Override
	public void onCommand(CommandData commandData) {
		User player = commandData.getSender();
		
		// Command starts
		player.sendMessage(Messages.HELP_HEADER, true);
		
		Set<ADPCommand> allowedCommands = plugin.getPlayerUtils().getAllowedCommands(player);
		for(Map.Entry<ADPCommand, ADPExecutableCommand> e : plugin.getCommandManager().getOrderedCommands().entrySet()) {
			if (allowedCommands.contains(e.getKey()) && e.getValue().isListedInHelp()) {
				player.sendMessage(e.getValue().getHelp()
						.replace("%syntax%", e.getValue().getSyntaxForUser(commandData.getSender()))
						.replace("%description%", e.getValue().getDescription() != null ? e.getValue().getDescription() : "")
						.replace("%run_command%", e.getValue().getRunCommand())
						.replace("%perform_command%", Messages.HELP_PERFORM_COMMAND), true);
			}
		}
		
		player.sendMessage(Messages.HELP_FOOTER, true);
	}
}
