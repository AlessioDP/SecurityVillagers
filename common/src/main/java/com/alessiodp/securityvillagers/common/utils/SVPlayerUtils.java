package com.alessiodp.securityvillagers.common.utils;

import com.alessiodp.core.common.commands.list.ADPCommand;
import com.alessiodp.core.common.user.User;
import com.alessiodp.core.common.utils.IPlayerUtils;
import com.alessiodp.securityvillagers.common.commands.list.CommonCommands;
import com.alessiodp.securityvillagers.common.commands.utils.SecurityVillagersPermission;
import com.alessiodp.securityvillagers.common.configuration.data.ConfigMain;
import com.alessiodp.securityvillagers.common.configuration.data.Messages;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.List;

public class SVPlayerUtils implements IPlayerUtils {
	@Override
	public List<ADPCommand> getAllowedCommands(@NonNull User user) {
		List<ADPCommand> ret = new ArrayList<>();
		if (user.isPlayer()) {
			if (user.hasPermission(SecurityVillagersPermission.ADMIN_HELP.toString()))
				ret.add(CommonCommands.HELP);
			if (user.hasPermission(SecurityVillagersPermission.ADMIN_RELOAD.toString()))
				ret.add(CommonCommands.RELOAD);
			if (user.hasPermission(SecurityVillagersPermission.ADMIN_VERSION.toString()))
				ret.add(CommonCommands.VERSION);
			
			if (ConfigMain.CHANGEAGE_ENABLE
					&& user.hasPermission(SecurityVillagersPermission.ADMIN_CHANGEAGE.toString()))
				ret.add(CommonCommands.CHANGEAGE);
			if (ConfigMain.PROFESSION_ENABLE
					&& user.hasPermission(SecurityVillagersPermission.ADMIN_PROFESSION.toString()))
				ret.add(CommonCommands.PROFESSION);
			if (ConfigMain.GENERAL_PROTECTIONTYPE == ConfigMain.ProtectionType.CUSTOM
					&& user.hasPermission(SecurityVillagersPermission.ADMIN_PROTECT.toString()))
				ret.add(CommonCommands.PROTECT);
			if (ConfigMain.RENAME_ENABLE
					&& user.hasPermission(SecurityVillagersPermission.ADMIN_RENAME.toString()))
				ret.add(CommonCommands.RENAME);
		}
		return ret;
	}
	
	public void sendNoPermissionMessage(User user, SecurityVillagersPermission permission) {
		if (user != null)
			user.sendMessage(Messages.SECURITYVILLAGERS_NOPERMISSION
					.replace("%permission%", permission.toString()), true);
	}
}
