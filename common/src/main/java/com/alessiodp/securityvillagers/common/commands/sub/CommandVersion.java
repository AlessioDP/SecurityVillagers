package com.alessiodp.securityvillagers.common.commands.sub;

import com.alessiodp.core.common.ADPPlugin;
import com.alessiodp.core.common.commands.utils.ADPMainCommand;
import com.alessiodp.core.common.commands.utils.ADPSubCommand;
import com.alessiodp.core.common.commands.utils.CommandData;
import com.alessiodp.core.common.user.User;
import com.alessiodp.securityvillagers.common.commands.utils.SecurityVillagersPermission;
import com.alessiodp.securityvillagers.common.configuration.SVConstants;
import com.alessiodp.securityvillagers.common.configuration.data.Messages;
import com.alessiodp.securityvillagers.common.utils.SVPlayerUtils;
import lombok.Getter;

public class CommandVersion extends ADPSubCommand {
	@Getter private final boolean executableByConsole = true;
	
	public CommandVersion(ADPPlugin plugin, ADPMainCommand mainCommand) {
		super(plugin, mainCommand);
	}
	
	@Override
	public boolean preRequisites(CommandData commandData) {
		User sender = commandData.getSender();
		
		if (!sender.hasPermission(SecurityVillagersPermission.ADMIN_VERSION.toString())) {
			((SVPlayerUtils) plugin.getPlayerUtils()).sendNoPermissionMessage(sender, SecurityVillagersPermission.ADMIN_VERSION);
			return false;
		}
		
		return true;
	}
	
	@Override
	public void onCommand(CommandData commandData) {
		User player = commandData.getSender();
		
		if (player.isPlayer())
			plugin.getLoggerManager().logDebug(SVConstants.DEBUG_CMD_VERSION
					.replace("{player}", player.getName()), true);
		else
			plugin.getLoggerManager().logDebug(SVConstants.DEBUG_CMD_VERSION_CONSOLE, true);
		
		// Command starts
		String version = plugin.getVersion();
		String newVersion = plugin.getAdpUpdater().getFoundVersion().isEmpty() ? version : plugin.getAdpUpdater().getFoundVersion();
		String message = (version.equals(newVersion) ? Messages.CMD_VERSION_UPDATED : Messages.CMD_VERSION_OUTDATED)
				.replace("%version%", version)
				.replace("%newversion%", newVersion)
				.replace("%platform%", plugin.getPlatform());
		
		if (player.isPlayer()) {
			player.sendMessage(message, true);
		} else {
			plugin.logConsole(plugin.getColorUtils().removeColors(message), false);
		}
	}
}