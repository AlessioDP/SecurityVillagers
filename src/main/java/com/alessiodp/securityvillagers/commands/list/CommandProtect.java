package com.alessiodp.securityvillagers.commands.list;

import com.alessiodp.securityvillagers.SecurityVillagers;
import com.alessiodp.securityvillagers.commands.ICommand;
import com.alessiodp.securityvillagers.configuration.Constants;
import com.alessiodp.securityvillagers.configuration.data.ConfigMain;
import com.alessiodp.securityvillagers.configuration.data.Messages;
import com.alessiodp.securityvillagers.utils.SVPermission;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.metadata.FixedMetadataValue;

public class CommandProtect implements ICommand {
	private SecurityVillagers plugin;
	
	public CommandProtect(SecurityVillagers instance) {
		plugin = instance;
	}
	
	@Override
	public void onCommand(CommandSender sender, String[] args) {
		if (!(sender instanceof Player)) {
			plugin.sendMessage(sender, Messages.SECURITYVILLAGERS_NOCONSOLE);
			return;
		}
		
		Player player = (Player) sender;
		if (!player.hasPermission(SVPermission.ADMIN_PROTECT.toString())) {
			plugin.sendMessage(sender, Messages.SECURITYVILLAGERS_NOPERMISSION);
			return;
		}
		
		if (ConfigMain.PREVENTIONS_PROTECTIONTYPE.equalsIgnoreCase("global")) {
			plugin.sendMessage(player, Messages.PROTECTION_GLOBAL);
			return;
		}
		
		Villager villager = plugin.getVillagerHandler().getListSelectedVillagers().get(player.getUniqueId());
		if (villager == null) {
			plugin.sendMessage(player, Messages.SELECTION_MISSING);
			return;
		}
		
		// Start
		if (villager.hasMetadata(Constants.PROTECT_METADATA)) {
			villager.removeMetadata(Constants.PROTECT_METADATA, plugin);
			
			plugin.sendMessage(player, Messages.PROTECTION_UNPROTECTED);
		} else {
			villager.setMetadata(Constants.PROTECT_METADATA, new FixedMetadataValue(plugin, true));
			
			plugin.sendMessage(player, Messages.PROTECTION_PROTECTED);
		}
	}
}
