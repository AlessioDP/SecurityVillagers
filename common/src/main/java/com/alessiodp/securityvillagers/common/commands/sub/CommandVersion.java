package com.alessiodp.securityvillagers.common.commands.sub;

import com.alessiodp.core.common.ADPPlugin;
import com.alessiodp.core.common.commands.utils.ADPMainCommand;
import com.alessiodp.core.common.commands.utils.ADPSubCommand;
import com.alessiodp.core.common.commands.utils.CommandData;
import com.alessiodp.core.common.user.User;
import com.alessiodp.securityvillagers.common.commands.list.CommonCommands;
import com.alessiodp.securityvillagers.common.configuration.data.ConfigMain;
import com.alessiodp.securityvillagers.common.utils.SecurityVillagersPermission;
import com.alessiodp.securityvillagers.common.configuration.data.Messages;
import com.alessiodp.securityvillagers.common.utils.SVPlayerUtils;

public class CommandVersion extends ADPSubCommand {
	
	public CommandVersion(ADPPlugin plugin, ADPMainCommand mainCommand) {
		super(
				plugin,
				mainCommand,
				CommonCommands.VERSION,
				SecurityVillagersPermission.ADMIN_VERSION,
				ConfigMain.COMMANDS_SUB_VERSION,
				true
		);
		
		syntax = baseSyntax();
		
		description = Messages.HELP_CMD_DESCRIPTIONS_VERSION;
		help = Messages.HELP_CMD_VERSION;
	}
	
	@Override
	public boolean preRequisites(CommandData commandData) {
		User sender = commandData.getSender();
		
		if (sender.isPlayer() && !sender.hasPermission(permission)) {
			((SVPlayerUtils) plugin.getPlayerUtils()).sendNoPermissionMessage(sender, permission);
			return false;
		}
		return true;
	}
	
	@Override
	public void onCommand(CommandData commandData) {
		User player = commandData.getSender();
		
		// Command starts
		String version = plugin.getVersion();
		String newVersion = plugin.getAdpUpdater().getFoundVersion().isEmpty() ? version : plugin.getAdpUpdater().getFoundVersion();
		String message = (version.equals(newVersion) ? Messages.CMD_VERSION_UPDATED : Messages.CMD_VERSION_OUTDATED)
				.replace("%version%", version)
				.replace("%newversion%", newVersion)
				.replace("%platform%", plugin.getPlatform().getName());
		
		player.sendMessage(message, true);
	}
}