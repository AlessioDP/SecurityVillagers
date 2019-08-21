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


public class CommandReload extends ADPSubCommand {
	@Getter private final boolean executableByConsole = true;
	
	public CommandReload(ADPPlugin plugin, ADPMainCommand mainCommand) {
		super(plugin, mainCommand);
	}
	
	@Override
	public boolean preRequisites(CommandData commandData) {
		User sender = commandData.getSender();
		
		if (!sender.hasPermission(SecurityVillagersPermission.ADMIN_RELOAD.toString())) {
			((SVPlayerUtils) plugin.getPlayerUtils()).sendNoPermissionMessage(sender, SecurityVillagersPermission.ADMIN_RELOAD);
			return false;
		}
		
		return true;
	}
	
	@Override
	public void onCommand(CommandData commandData) {
		User player = commandData.getSender();
		
		if (player.isPlayer())
			plugin.getLoggerManager().logDebug(SVConstants.DEBUG_CMD_RELOAD
					.replace("{player}", player.getName()), true);
		else
			plugin.getLoggerManager().logDebug(SVConstants.DEBUG_CMD_RELOAD_CONSOLE, true);
		
		plugin.reloadConfiguration();
		
		if (player.isPlayer()) {
			player.sendMessage(Messages.SECURITYVILLAGERS_COMMON_CONFIGRELOAD, true);
			
			plugin.getLoggerManager().log(SVConstants.DEBUG_CMD_RELOADED
					.replace("{player}", player.getName()), true);
		} else {
			plugin.getLoggerManager().log(SVConstants.DEBUG_CMD_RELOADED_CONSOLE, true);
		}
	}
}