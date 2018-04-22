package com.alessiodp.securityvillagers.commands.list;

import com.alessiodp.securityvillagers.SecurityVillagers;
import com.alessiodp.securityvillagers.commands.ICommand;
import com.alessiodp.securityvillagers.configuration.data.Messages;
import com.alessiodp.securityvillagers.utils.SVPermission;
import org.bukkit.command.CommandSender;

public class CommandHelp implements ICommand {
	private static SecurityVillagers plugin;
	
	public CommandHelp(SecurityVillagers instance) {
		plugin = instance;
	}
	
	@Override
	public void onCommand(CommandSender sender, String[] args) {
		printHelp(sender);
	}
	
	public static void printHelp(CommandSender sender) {
		if (!sender.hasPermission(SVPermission.ADMIN_HELP.toString())) {
			plugin.sendMessage(sender, Messages.SECURITYVILLAGERS_NOPERMISSION);
			return;
		}
		
		for (String line : Messages.SECURITYVILLAGERS_HELP) {
			plugin.sendMessage(sender, line);
		}
	}
}
