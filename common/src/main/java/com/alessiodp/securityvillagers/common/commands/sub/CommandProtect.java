package com.alessiodp.securityvillagers.common.commands.sub;

import com.alessiodp.core.common.ADPPlugin;
import com.alessiodp.core.common.commands.utils.ADPMainCommand;
import com.alessiodp.core.common.commands.utils.ADPSubCommand;
import com.alessiodp.core.common.commands.utils.CommandData;
import com.alessiodp.core.common.user.User;
import com.alessiodp.securityvillagers.common.SecurityVillagersPlugin;
import com.alessiodp.securityvillagers.common.commands.list.CommonCommands;
import com.alessiodp.securityvillagers.common.configuration.data.ConfigMain;
import com.alessiodp.securityvillagers.common.utils.SecurityVillagersPermission;
import com.alessiodp.securityvillagers.common.configuration.SVConstants;
import com.alessiodp.securityvillagers.common.configuration.data.Messages;
import com.alessiodp.securityvillagers.common.utils.SVPlayerUtils;
import com.alessiodp.securityvillagers.common.villagers.objects.ProtectedEntity;
import lombok.NonNull;

import java.util.List;

public class CommandProtect extends ADPSubCommand {
	
	public CommandProtect(ADPPlugin plugin, ADPMainCommand mainCommand) {
		super(
				plugin,
				mainCommand,
				CommonCommands.PROTECT,
				SecurityVillagersPermission.ADMIN_PROTECT,
				ConfigMain.COMMANDS_CMD_PROTECT,
				false
		);
		
		syntax = String.format("%s [%s/%s]",
				baseSyntax(),
				ConfigMain.COMMANDS_SUB_ON,
				ConfigMain.COMMANDS_SUB_OFF
		);
		
		description = Messages.HELP_CMD_DESCRIPTIONS_PROTECT;
		help = Messages.HELP_CMD_PROTECT;
	}
	
	@Override
	public boolean preRequisites(CommandData commandData) {
		User sender = commandData.getSender();
		
		if (!sender.hasPermission(SecurityVillagersPermission.ADMIN_PROTECT.toString())) {
			((SVPlayerUtils) plugin.getPlayerUtils()).sendNoPermissionMessage(sender, SecurityVillagersPermission.ADMIN_PROTECT);
			return false;
		}
		
		return true;
	}
	
	@Override
	public void onCommand(CommandData commandData) {
		User player = commandData.getSender();
		
		plugin.getLoggerManager().logDebug(SVConstants.DEBUG_CMD_PROTECT
				.replace("{player}", player.getName())
				.replace("{value}", commandData.getArgs().length > 1 ? commandData.getArgs()[1] : ""), true);
		
		// Command handling
		ProtectedEntity protectedEntity = ((SecurityVillagersPlugin) plugin).getVillagerManager().getSelectedEntities().get(player.getUUID());
		if (protectedEntity == null) {
			player.sendMessage(Messages.GENERAL_SELECTION_REQUIRED, true);
			return;
		}
		
		Boolean protection = plugin.getCommandManager().getCommandUtils().handleOnOffCommand(protectedEntity.isProtectionEnabled(), commandData.getArgs());
		if (protection == null) {
			player.sendMessage(Messages.SECURITYVILLAGERS_SYNTAX_WRONGMESSAGE
					.replace("%syntax%", getSyntaxForUser(commandData.getSender())), true);
			return;
		}
		
		// Command starts
		protectedEntity.setProtectionEnabled(protection);
		protectedEntity.updateProtectedEntity();
		
		if (protection) {
			player.sendMessage(Messages.CMD_PROTECT_PROTECTED, true);
		} else {
			player.sendMessage(Messages.CMD_PROTECT_UNPROTECTED, true);
		}
	}
	
	@Override
	public List<String> onTabComplete(@NonNull User sender, String[] args) {
		return plugin.getCommandManager().getCommandUtils().tabCompleteOnOff(args);
	}
}
