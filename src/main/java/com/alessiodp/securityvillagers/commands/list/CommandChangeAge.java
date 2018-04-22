package com.alessiodp.securityvillagers.commands.list;

import com.alessiodp.securityvillagers.SecurityVillagers;
import com.alessiodp.securityvillagers.commands.ICommand;
import com.alessiodp.securityvillagers.configuration.data.Messages;
import com.alessiodp.securityvillagers.utils.SVPermission;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;

public class CommandChangeAge implements ICommand {
	private SecurityVillagers plugin;
	
	public CommandChangeAge(SecurityVillagers instance) {
		plugin = instance;
	}
	
	@Override
	public void onCommand(CommandSender sender, String[] args) {
		if (!(sender instanceof Player)) {
			plugin.sendMessage(sender, Messages.SECURITYVILLAGERS_NOCONSOLE);
			return;
		}
		
		Player player = (Player) sender;
		if (!player.hasPermission(SVPermission.ADMIN_CHANGEAGE.toString())) {
			plugin.sendMessage(sender, Messages.SECURITYVILLAGERS_NOPERMISSION);
			return;
		}
		
		Villager villager = plugin.getVillagerHandler().getListSelectedVillagers().get(player.getUniqueId());
		if (villager == null) {
			plugin.sendMessage(player, Messages.SELECTION_MISSING);
			return;
		}
		
		if (villager.isAdult()) {
			villager.setBaby();
			plugin.sendMessage(player, Messages.CONVERTER_INTO_BABY);
		} else {
			villager.setAdult();
			plugin.sendMessage(player, Messages.CONVERTER_INTO_ADULT);
		}
	}
}
