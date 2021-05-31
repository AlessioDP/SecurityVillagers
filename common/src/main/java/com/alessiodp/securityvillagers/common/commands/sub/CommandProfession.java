package com.alessiodp.securityvillagers.common.commands.sub;

import com.alessiodp.core.common.ADPPlugin;
import com.alessiodp.core.common.commands.utils.ADPMainCommand;
import com.alessiodp.core.common.commands.utils.ADPSubCommand;
import com.alessiodp.core.common.commands.utils.CommandData;
import com.alessiodp.core.common.user.User;
import com.alessiodp.securityvillagers.common.SecurityVillagersPlugin;
import com.alessiodp.securityvillagers.common.commands.list.CommonCommands;
import com.alessiodp.securityvillagers.common.utils.SecurityVillagersPermission;
import com.alessiodp.securityvillagers.common.configuration.SVConstants;
import com.alessiodp.securityvillagers.common.configuration.data.ConfigMain;
import com.alessiodp.securityvillagers.common.configuration.data.Messages;
import com.alessiodp.securityvillagers.common.tasks.ProfessionCooldown;
import com.alessiodp.securityvillagers.common.utils.SVPlayerUtils;
import com.alessiodp.securityvillagers.common.villagers.objects.ProtectedEntity;
import com.alessiodp.securityvillagers.common.villagers.objects.VillagerProfession;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class CommandProfession extends ADPSubCommand {
	
	public CommandProfession(ADPPlugin plugin, ADPMainCommand mainCommand) {
		super(
				plugin,
				mainCommand,
				CommonCommands.PROFESSION,
				SecurityVillagersPermission.ADMIN_PROFESSION,
				ConfigMain.COMMANDS_SUB_PROFESSION,
				false
		);
		
		syntax = String.format("%s [%s]",
				baseSyntax(),
				Messages.SECURITYVILLAGERS_SYNTAX_PROFESSION
		);
		
		description = Messages.HELP_CMD_DESCRIPTIONS_PROFESSION;
		help = Messages.HELP_CMD_PROFESSION;
	}
	
	@Override
	public boolean preRequisites(CommandData commandData) {
		User sender = commandData.getSender();
		
		if (!sender.hasPermission(permission)) {
			((SVPlayerUtils) plugin.getPlayerUtils()).sendNoPermissionMessage(sender, permission);
			return false;
		}
		
		commandData.addPermission(SecurityVillagersPermission.ADMIN_PROFESSION_CD_BYPASS);
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
		
		if (!protectedEntity.haveProfession()) {
			player.sendMessage(Messages.CMD_PROFESSION_FAILED, true);
			return;
		}
		
		if (ConfigMain.PROFESSION_COOLDOWN > 0
				&& !commandData.havePermission(SecurityVillagersPermission.ADMIN_PROFESSION_CD_BYPASS)) {
			Long unixTimestamp = ((SecurityVillagersPlugin) plugin).getProfessionCooldown().get(player.getUUID());
			long unixNow = System.currentTimeMillis() / 1000L;
			// Check cooldown
			if (unixTimestamp != null && (unixNow - unixTimestamp) < ConfigMain.PROFESSION_COOLDOWN) {
				player.sendMessage(Messages.CMD_PROFESSION_COOLDOWN
						.replace("%seconds%", String.valueOf(ConfigMain.PROFESSION_COOLDOWN - (unixNow - unixTimestamp))), true);
				return;
			}
			
			((SecurityVillagersPlugin) plugin).getProfessionCooldown().put(player.getUUID(), unixNow);
			plugin.getScheduler().scheduleAsyncLater(new ProfessionCooldown((SecurityVillagersPlugin) plugin, player.getUUID()), ConfigMain.PROFESSION_COOLDOWN, TimeUnit.SECONDS);
			
			plugin.getLoggerManager().logDebug(String.format(SVConstants.DEBUG_TASK_PROFESSION_START,
					ConfigMain.PROFESSION_COOLDOWN,
					player.getName()
			), true);
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
			player.sendMessage(Messages.SECURITYVILLAGERS_SYNTAX_WRONGMESSAGE
					.replace("%syntax%", getSyntaxForUser(commandData.getSender())), true);
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
