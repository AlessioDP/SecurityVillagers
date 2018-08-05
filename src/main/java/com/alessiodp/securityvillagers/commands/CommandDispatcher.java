package com.alessiodp.securityvillagers.commands;

import com.alessiodp.securityvillagers.SecurityVillagers;
import com.alessiodp.securityvillagers.commands.list.CommandChangeAge;
import com.alessiodp.securityvillagers.commands.list.CommandHelp;
import com.alessiodp.securityvillagers.commands.list.CommandProfession;
import com.alessiodp.securityvillagers.commands.list.CommandProtect;
import com.alessiodp.securityvillagers.commands.list.CommandReload;
import com.alessiodp.securityvillagers.commands.list.CommandRename;
import com.alessiodp.securityvillagers.configuration.data.ConfigMain;
import com.alessiodp.securityvillagers.configuration.data.Messages;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.HashMap;

public class CommandDispatcher implements CommandExecutor {
	private SecurityVillagers plugin;
	private static HashMap<String, ICommand> commands = new HashMap<>();
	
	public CommandDispatcher(SecurityVillagers instance) {
		plugin = instance;
	}
	
	public void reloadCommands() {
		commands = new HashMap<>();
		commands.put("help", new CommandHelp(plugin));
		commands.put("reload", new CommandReload(plugin));
		commands.put("protect", new CommandProtect(plugin));
		if (ConfigMain.CHANGEAGE_ENABLE)
			commands.put("changeage", new CommandChangeAge(plugin));
		if (ConfigMain.PROFESSION_ENABLE)
			commands.put("profession", new CommandProfession(plugin));
		if (ConfigMain.RENAME_ENABLE)
			commands.put("rename", new CommandRename(plugin));
		
		plugin.getCommand("securityvillagers").setExecutor(this);
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (label.equalsIgnoreCase("securityvillagers")
				|| label.equalsIgnoreCase("sv")) {
			if (args.length > 0) {
				if (commands.containsKey(args[0])) {
					commands.get(args[0]).onCommand(sender, args);
				} else {
					plugin.sendMessage(sender, Messages.SECURITYVILLAGERS_NOTFOUND);
				}
			} else {
				// Print help
				CommandHelp.printHelp(sender);
			}
			return true;
		}
		return false;
	}
}
