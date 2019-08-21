package com.alessiodp.securityvillagers.common.commands.sub;

import com.alessiodp.core.common.ADPPlugin;
import com.alessiodp.core.common.commands.utils.ADPMainCommand;
import com.alessiodp.core.common.commands.utils.ADPSubCommand;
import com.alessiodp.core.common.commands.utils.CommandData;
import com.alessiodp.core.common.user.User;
import com.alessiodp.securityvillagers.common.SecurityVillagersPlugin;
import com.alessiodp.securityvillagers.common.commands.utils.SecurityVillagersPermission;
import com.alessiodp.securityvillagers.common.configuration.SVConstants;
import com.alessiodp.securityvillagers.common.configuration.data.Messages;
import com.alessiodp.securityvillagers.common.utils.SVPlayerUtils;
import com.alessiodp.securityvillagers.common.villagers.objects.ProtectedEntity;
import com.alessiodp.securityvillagers.common.villagers.objects.VillagerProfession;
import lombok.Getter;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.List;

public class CommandProfession extends ADPSubCommand {
	@Getter private final boolean executableByConsole = false;
	
	public CommandProfession(ADPPlugin plugin, ADPMainCommand mainCommand) {
		super(plugin, mainCommand);
	}
	
	@Override
	public boolean preRequisites(CommandData commandData) {
		User sender = commandData.getSender();
		
		if (!sender.hasPermission(SecurityVillagersPermission.ADMIN_PROFESSION.toString())) {
			((SVPlayerUtils) plugin.getPlayerUtils()).sendNoPermissionMessage(sender, SecurityVillagersPermission.ADMIN_PROFESSION);
			return false;
		}
		
		return true;
	}
	
	@Override
	public void onCommand(CommandData commandData) {
		User player = commandData.getSender();
		
		plugin.getLoggerManager().logDebug(SVConstants.DEBUG_CMD_PROFESSION
				.replace("{player}", player.getName())
				.replace("{value}", commandData.getArgs().length > 1 ? commandData.getArgs()[1] : ""), true);
		
		// Command handling
		ProtectedEntity protectedEntity = ((SecurityVillagersPlugin) plugin).getVillagerManager().getSelectedEntities().get(player.getUUID());
		if (protectedEntity == null) {
			player.sendMessage(Messages.GENERAL_SELECTION_REQUIRED, true);
			return;
		}
		
		if (!protectedEntity.haveProfession()) {
			player.sendMessage(Messages.CMD_PROFESSION_FAILED, true);
			return;
		}
		
		// Command starts
		if (commandData.getArgs().length == 1) {
			player.sendMessage(Messages.CMD_PROFESSION_CURRENT
					.replace("%profession%", protectedEntity.getProfession().getName()), true);
		} else if (commandData.getArgs().length == 2) {
			VillagerProfession profession = VillagerProfession.getProfession(commandData.getArgs()[1]);
			if (profession == null
					|| !protectedEntity.setProfession(profession)) {
				player.sendMessage(Messages.CMD_PROFESSION_NOTFOUND
						.replace("%profession%", commandData.getArgs()[1]), true);
				return;
			}
			
			player.sendMessage(Messages.CMD_PROFESSION_CHANGED
					.replace("%profession%", profession.getName()), true);
		} else {
			player.sendMessage(Messages.CMD_PROFESSION_WRONGCMD, true);
		}
	}
	
	@Override
	public List<String> onTabComplete(@NonNull User sender, String[] args) {
		List<String> ret = new ArrayList<>();
		if (args.length == 2) {
			for (VillagerProfession profession : VillagerProfession.values()) {
				if (!profession.getName().isEmpty())
					ret.add(profession.getName());
			}
			ret = plugin.getCommandManager().getCommandUtils().tabCompleteParser(ret, args[1]);
		}
		return ret;
	}
}
