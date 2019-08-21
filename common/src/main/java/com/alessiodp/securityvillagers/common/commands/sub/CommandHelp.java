package com.alessiodp.securityvillagers.common.commands.sub;

import com.alessiodp.core.common.ADPPlugin;
import com.alessiodp.core.common.commands.list.ADPCommand;
import com.alessiodp.core.common.commands.utils.ADPMainCommand;
import com.alessiodp.core.common.commands.utils.ADPSubCommand;
import com.alessiodp.core.common.commands.utils.CommandData;
import com.alessiodp.core.common.user.User;
import com.alessiodp.securityvillagers.common.commands.utils.SecurityVillagersPermission;
import com.alessiodp.securityvillagers.common.configuration.SVConstants;
import com.alessiodp.securityvillagers.common.configuration.data.Messages;
import com.alessiodp.securityvillagers.common.utils.SVPlayerUtils;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class CommandHelp extends ADPSubCommand {
	@Getter private final boolean executableByConsole = false;
	
	public CommandHelp(ADPPlugin plugin, ADPMainCommand mainCommand) {
		super(plugin, mainCommand);
	}
	
	@Override
	public boolean preRequisites(CommandData commandData) {
		User sender = commandData.getSender();
		
		if (!sender.hasPermission(SecurityVillagersPermission.ADMIN_HELP.toString())) {
			((SVPlayerUtils) plugin.getPlayerUtils()).sendNoPermissionMessage(sender, SecurityVillagersPermission.ADMIN_HELP);
			return false;
		}
		
		return true;
	}
	
	@Override
	public void onCommand(CommandData commandData) {
		User player = commandData.getSender();
		
		plugin.getLoggerManager().logDebug(SVConstants.DEBUG_CMD_HELP
				.replace("{player}", player.getName())
				.replace("{page}", commandData.getArgs().length > 1 ? commandData.getArgs()[1] : ""), true);
		
		// Command starts
		// Get all allowed commands
		List<String> list = new ArrayList<>();
		for (ADPCommand cmd : plugin.getPlayerUtils().getAllowedCommands(player)) {
			if (mainCommand.getEnabledSubCommands().contains(cmd))
				list.add(cmd.getHelp());
		}
		
		
		// Start printing
		player.sendMessage(Messages.HELP_HEADER, true);
		for (String string : list) {
			player.sendMessage(string, true);
		}
		player.sendMessage(Messages.HELP_FOOTER, true);
	}
}
