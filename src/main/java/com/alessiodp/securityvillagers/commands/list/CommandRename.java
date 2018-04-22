package com.alessiodp.securityvillagers.commands.list;

import com.alessiodp.securityvillagers.SecurityVillagers;
import com.alessiodp.securityvillagers.commands.ICommand;
import com.alessiodp.securityvillagers.configuration.data.Messages;
import com.alessiodp.securityvillagers.utils.SVPermission;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;

public class CommandRename implements ICommand {
	private SecurityVillagers plugin;
	
	public CommandRename(SecurityVillagers instance) {
		plugin = instance;
	}
	
	@Override
	public void onCommand(CommandSender sender, String[] args) {
		if (!(sender instanceof Player)) {
			plugin.sendMessage(sender, Messages.SECURITYVILLAGERS_NOCONSOLE);
			return;
		}
		
		Player player = (Player) sender;
		if (!player.hasPermission(SVPermission.ADMIN_RENAME.toString())) {
			plugin.sendMessage(sender, Messages.SECURITYVILLAGERS_NOPERMISSION);
			return;
		}
		
		Villager villager = plugin.getVillagerHandler().getListSelectedVillagers().get(player.getUniqueId());
		if (villager == null) {
			plugin.sendMessage(player, Messages.SELECTION_MISSING);
			return;
		}
		
		if (args.length > 1) {
			String name = getFinalArgs(args, 1);
			villager.setCustomName(name);
			plugin.sendMessage(player, Messages.RENAME_RENAME
					.replace("%name%", name));
		} else {
			villager.setCustomName("");
			plugin.sendMessage(player, Messages.RENAME_REMOVE);
		}
	}
	
	private String getFinalArgs(String[] args, int start) {
		StringBuilder builder = new StringBuilder();
		for (int i = start; i < args.length; i++) {
			if (i != start) {
				builder.append(" ");
			}
			builder.append(args[i]);
		}
		return builder.toString();
	}
}
