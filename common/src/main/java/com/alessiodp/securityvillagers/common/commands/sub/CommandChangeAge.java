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
import com.alessiodp.securityvillagers.common.tasks.ChangeAgeCooldown;
import com.alessiodp.securityvillagers.common.utils.SVPlayerUtils;
import com.alessiodp.securityvillagers.common.villagers.objects.ProtectedEntity;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class CommandChangeAge extends ADPSubCommand {
	
	public CommandChangeAge(ADPPlugin plugin, ADPMainCommand mainCommand) {
		super(
				plugin,
				mainCommand,
				CommonCommands.CHANGEAGE,
				SecurityVillagersPermission.ADMIN_CHANGEAGE,
				ConfigMain.COMMANDS_SUB_CHANGEAGE,
				false
		);
		
		syntax = String.format("%s [%s/%s]",
				baseSyntax(),
				ConfigMain.COMMANDS_MISC_ADULT,
				ConfigMain.COMMANDS_MISC_BABY
		);
		
		description = Messages.HELP_CMD_DESCRIPTIONS_CHANGEAGE;
		help = Messages.HELP_CMD_CHANGEAGE;
	}
	
	@Override
	public boolean preRequisites(CommandData commandData) {
		User sender = commandData.getSender();
		
		if (!sender.hasPermission(permission)) {
			((SVPlayerUtils) plugin.getPlayerUtils()).sendNoPermissionMessage(sender, permission);
			return false;
		}
		
		commandData.addPermission(SecurityVillagersPermission.ADMIN_CHANGEAGE_CD_BYPASS);
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
		
		if (!protectedEntity.isAgeable()) {
			player.sendMessage(Messages.CMD_CHANGEAGE_FAILED, true);
			return;
		}
		
		if (ConfigMain.CHANGEAGE_COOLDOWN > 0
				&& !commandData.havePermission(SecurityVillagersPermission.ADMIN_CHANGEAGE_CD_BYPASS)) {
			Long unixTimestamp = ((SecurityVillagersPlugin) plugin).getChangeAgeCooldown().get(player.getUUID());
			long unixNow = System.currentTimeMillis() / 1000L;
			// Check cooldown
			if (unixTimestamp != null && (unixNow - unixTimestamp) < ConfigMain.CHANGEAGE_COOLDOWN) {
				player.sendMessage(Messages.CMD_CHANGEAGE_COOLDOWN
						.replace("%seconds%", String.valueOf(ConfigMain.CHANGEAGE_COOLDOWN - (unixNow - unixTimestamp))), true);
				return;
			}
			
			((SecurityVillagersPlugin) plugin).getChangeAgeCooldown().put(player.getUUID(), unixNow);
			plugin.getScheduler().scheduleAsyncLater(new ChangeAgeCooldown((SecurityVillagersPlugin) plugin, player.getUUID()), ConfigMain.CHANGEAGE_COOLDOWN, TimeUnit.SECONDS);
			
			plugin.getLoggerManager().logDebug(String.format(SVConstants.DEBUG_TASK_CHANGEAGE_START,
					ConfigMain.CHANGEAGE_COOLDOWN,
					player.getName()
			), true);
		}
		
		boolean toBaby = protectedEntity.isAdult();
		
		if (commandData.getArgs().length > 1) {
			if (commandData.getArgs()[1].equalsIgnoreCase(ConfigMain.COMMANDS_MISC_BABY))
				toBaby = true;
			if (commandData.getArgs()[1].equalsIgnoreCase(ConfigMain.COMMANDS_MISC_ADULT))
				toBaby = false;
		}
		
		// Command starts
		if (toBaby) {
			protectedEntity.setToBaby();
			player.sendMessage(Messages.CMD_CHANGEAGE_BABY, true);
		} else {
			protectedEntity.setToAdult();
			player.sendMessage(Messages.CMD_CHANGEAGE_ADULT, true);
		}
	}
	
	@Override
	public List<String> onTabComplete(@NonNull User sender, String[] args) {
		ArrayList<String> ret = new ArrayList<>();
		if (args.length == 2) {
			ret.add(ConfigMain.COMMANDS_MISC_ADULT);
			ret.add(ConfigMain.COMMANDS_MISC_BABY);
		}
		return ret;
	}
}
