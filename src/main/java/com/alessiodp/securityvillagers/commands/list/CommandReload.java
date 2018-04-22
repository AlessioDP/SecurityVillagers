package com.alessiodp.securityvillagers.commands.list;

import com.alessiodp.securityvillagers.SecurityVillagers;
import com.alessiodp.securityvillagers.commands.ICommand;
import com.alessiodp.securityvillagers.configuration.data.Messages;
import com.alessiodp.securityvillagers.utils.SVPermission;
import org.bukkit.command.CommandSender;

public class CommandReload implements ICommand {
	private SecurityVillagers plugin;
	
	public CommandReload(SecurityVillagers instance) {
		plugin = instance;
	}
	
	@Override
	public void onCommand(CommandSender sender, String[] args) {
		if (!sender.hasPermission(SVPermission.ADMIN_RELOAD.toString())) {
			plugin.sendMessage(sender, Messages.SECURITYVILLAGERS_NOPERMISSION);
			return;
		}
		
		plugin.reloadConfiguration();
		plugin.sendMessage(sender, Messages.SECURITYVILLAGERS_CONFIGURATIONRELOADED);
	}
}
