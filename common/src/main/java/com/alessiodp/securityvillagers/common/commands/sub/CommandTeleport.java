package com.alessiodp.securityvillagers.common.commands.sub;

import com.alessiodp.core.common.ADPPlugin;
import com.alessiodp.core.common.commands.utils.ADPMainCommand;
import com.alessiodp.core.common.commands.utils.ADPSubCommand;
import com.alessiodp.core.common.commands.utils.CommandData;
import com.alessiodp.core.common.user.User;
import com.alessiodp.securityvillagers.common.SecurityVillagersPlugin;
import com.alessiodp.securityvillagers.common.commands.list.CommonCommands;
import com.alessiodp.securityvillagers.common.configuration.data.ConfigMain;
import com.alessiodp.securityvillagers.common.configuration.data.Messages;
import com.alessiodp.securityvillagers.common.utils.SVPlayerUtils;
import com.alessiodp.securityvillagers.common.utils.SecurityVillagersPermission;
import com.alessiodp.securityvillagers.common.villagers.objects.ProtectedEntity;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.List;

public class CommandTeleport extends ADPSubCommand {
	
	public CommandTeleport(ADPPlugin plugin, ADPMainCommand mainCommand) {
		super(
				plugin,
				mainCommand,
				CommonCommands.TELEPORT,
				SecurityVillagersPermission.ADMIN_TELEPORT,
				ConfigMain.COMMANDS_SUB_TELEPORT,
				false
		);
		
		syntax = String.format("%s [%s]",
				baseSyntax(),
				Messages.SECURITYVILLAGERS_SYNTAX_PLAYER
		);
		
		description = Messages.HELP_CMD_DESCRIPTIONS_TELEPORT;
		help = Messages.HELP_CMD_TELEPORT;
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
		
		if (commandData.getArgs().length < 1 || commandData.getArgs().length > 2) {
			player.sendMessage(Messages.SECURITYVILLAGERS_SYNTAX_WRONGMESSAGE
					.replace("%syntax%", getSyntaxForUser(commandData.getSender())), true);
			return;
		}
		
		User teleportTo = player;
		if (commandData.getArgs().length > 1) {
			teleportTo = plugin.getPlayerByName(commandData.getArgs()[1]);
			
			if (teleportTo == null) {
				player.sendMessage(Messages.CMD_TELEPORT_PLAYER_NOT_FOUND
						.replace("%player%", commandData.getArgs()[1]), true);
				return;
			}
		}
		
		// Command starts
		protectedEntity.teleportTo(teleportTo);
		
		player.sendMessage(Messages.CMD_TELEPORT_TELEPORTED
				.replace("%player%", teleportTo.getName()), true);
	}
	
	@Override
	public List<String> onTabComplete(@NonNull User sender, String[] args) {
		if (args.length == 2)
			return plugin.getCommandManager().getCommandUtils().tabCompletePlayerList(args, 1);
		return new ArrayList<>();
	}
}
