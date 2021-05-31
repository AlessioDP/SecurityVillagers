package com.alessiodp.securityvillagers.common.commands.sub;

import com.alessiodp.core.common.ADPPlugin;
import com.alessiodp.core.common.commands.utils.ADPMainCommand;
import com.alessiodp.core.common.commands.utils.ADPSubCommand;
import com.alessiodp.core.common.commands.utils.CommandData;
import com.alessiodp.core.common.user.User;
import com.alessiodp.securityvillagers.common.SecurityVillagersPlugin;
import com.alessiodp.securityvillagers.common.commands.list.CommonCommands;
import com.alessiodp.securityvillagers.common.utils.SecurityVillagersPermission;
import com.alessiodp.securityvillagers.common.configuration.data.ConfigMain;
import com.alessiodp.securityvillagers.common.configuration.data.Messages;
import com.alessiodp.securityvillagers.common.utils.SVPlayerUtils;
import com.alessiodp.securityvillagers.common.villagers.objects.ProtectedEntity;

public class CommandRename extends ADPSubCommand {
	
	public CommandRename(ADPPlugin plugin, ADPMainCommand mainCommand) {
		super(
				plugin,
				mainCommand,
				CommonCommands.RENAME,
				SecurityVillagersPermission.ADMIN_RENAME,
				ConfigMain.COMMANDS_SUB_RENAME,
				false
		);
		
		syntax = String.format("%s <%s/%s>",
				baseSyntax(),
				Messages.SECURITYVILLAGERS_SYNTAX_NAME,
				ConfigMain.COMMANDS_MISC_REMOVE
		);
		
		description = Messages.HELP_CMD_DESCRIPTIONS_RENAME;
		help = Messages.HELP_CMD_RENAME;
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
		
		// Command handling
		ProtectedEntity protectedEntity = ((SecurityVillagersPlugin) plugin).getVillagerManager().getSelectedEntityBy(player.getUUID());
		if (protectedEntity == null) {
			player.sendMessage(Messages.GENERAL_SELECTION_REQUIRED, true);
			return;
		}
		
		if (commandData.getArgs().length == 1) {
			player.sendMessage(Messages.SECURITYVILLAGERS_SYNTAX_WRONGMESSAGE
					.replace("%syntax%", getSyntaxForUser(commandData.getSender())), true);
			return;
		}
		
		String name = "";
		if (!commandData.getArgs()[1].equalsIgnoreCase(ConfigMain.COMMANDS_MISC_REMOVE)) {
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
