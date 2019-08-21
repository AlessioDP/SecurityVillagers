package com.alessiodp.securityvillagers.common.commands.sub;

import com.alessiodp.core.common.ADPPlugin;
import com.alessiodp.core.common.commands.utils.ADPMainCommand;
import com.alessiodp.core.common.commands.utils.ADPSubCommand;
import com.alessiodp.core.common.commands.utils.CommandData;
import com.alessiodp.core.common.user.User;
import com.alessiodp.securityvillagers.common.SecurityVillagersPlugin;
import com.alessiodp.securityvillagers.common.commands.utils.SecurityVillagersPermission;
import com.alessiodp.securityvillagers.common.configuration.SVConstants;
import com.alessiodp.securityvillagers.common.configuration.data.ConfigMain;
import com.alessiodp.securityvillagers.common.configuration.data.Messages;
import com.alessiodp.securityvillagers.common.utils.SVPlayerUtils;
import com.alessiodp.securityvillagers.common.villagers.objects.ProtectedEntity;
import lombok.Getter;

public class CommandRename extends ADPSubCommand {
	@Getter private final boolean executableByConsole = false;
	
	public CommandRename(ADPPlugin plugin, ADPMainCommand mainCommand) {
		super(plugin, mainCommand);
	}
	
	@Override
	public boolean preRequisites(CommandData commandData) {
		User sender = commandData.getSender();
		
		if (!sender.hasPermission(SecurityVillagersPermission.ADMIN_RENAME.toString())) {
			((SVPlayerUtils) plugin.getPlayerUtils()).sendNoPermissionMessage(sender, SecurityVillagersPermission.ADMIN_RENAME);
			return false;
		}
		
		return true;
	}
	
	@Override
	public void onCommand(CommandData commandData) {
		User player = commandData.getSender();
		
		plugin.getLoggerManager().logDebug(SVConstants.DEBUG_CMD_RENAME
				.replace("{player}", player.getName())
				.replace("{value}", commandData.getArgs().length > 1 ? commandData.getArgs()[1] : ""), true);
		
		// Command handling
		ProtectedEntity protectedEntity = ((SecurityVillagersPlugin) plugin).getVillagerManager().getSelectedEntities().get(player.getUUID());
		if (protectedEntity == null) {
			player.sendMessage(Messages.GENERAL_SELECTION_REQUIRED, true);
			return;
		}
		
		if (commandData.getArgs().length == 1) {
			player.sendMessage(Messages.CMD_RENAME_WRONGCMD, true);
			return;
		}
		
		String name = "";
		if (!commandData.getArgs()[1].equalsIgnoreCase(ConfigMain.COMMANDS_SUB_REMOVE)) {
			StringBuilder sb = new StringBuilder();
			for (int word = 1; word < commandData.getArgs().length; word++) {
				if (sb.length() > 0)
					sb.append(" ");
				sb.append(commandData.getArgs()[word]);
			}
			name = sb.toString();
		}
		
		// Command starts
		protectedEntity.setCustomName(name);
		
		if (name.isEmpty()) {
			player.sendMessage(Messages.CMD_RENAME_REMOVED, true);
		} else {
			player.sendMessage(Messages.CMD_RENAME_RENAMED
					.replace("%name%", name), true);
		}
	}
}
