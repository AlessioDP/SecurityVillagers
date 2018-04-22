package com.alessiodp.securityvillagers.commands.list;

import com.alessiodp.securityvillagers.SecurityVillagers;
import com.alessiodp.securityvillagers.commands.ICommand;
import com.alessiodp.securityvillagers.configuration.data.ConfigMain;
import com.alessiodp.securityvillagers.configuration.data.Messages;
import com.alessiodp.securityvillagers.utils.SVPermission;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;

public class CommandProfession implements ICommand {
	private SecurityVillagers plugin;
	
	public CommandProfession(SecurityVillagers instance) {
		plugin = instance;
	}
	
	@Override
	public void onCommand(CommandSender sender, String[] args) {
		if (!(sender instanceof Player)) {
			plugin.sendMessage(sender, Messages.SECURITYVILLAGERS_NOCONSOLE);
			return;
		}
		
		Player player = (Player) sender;
		if (!player.hasPermission(SVPermission.ADMIN_PROFESSION.toString())) {
			plugin.sendMessage(sender, Messages.SECURITYVILLAGERS_NOPERMISSION);
			return;
		}
		
		Villager villager = plugin.getVillagerHandler().getListSelectedVillagers().get(player.getUniqueId());
		if (villager == null) {
			plugin.sendMessage(player, Messages.SELECTION_MISSING);
			return;
		}
		
		if (args.length == 1) {
			// Print current profession
			plugin.sendMessage(player, Messages.PROFESSION_CURRENT
					.replace("%profession%", getProfessionName(villager.getProfession())));
			return;
		}
		
		Villager.Profession prof = getProfession(args[1]);
		
		if (prof == null) {
			plugin.sendMessage(player, Messages.PROFESSION_WRONGCMD);
			return;
		}
		
		villager.setProfession(prof);
		plugin.sendMessage(player, Messages.PROFESSION_DONE
				.replace("%profession%", args[1]));
	}
	
	private Villager.Profession getProfession(String profession) {
		Villager.Profession ret = null;
		if (profession.equalsIgnoreCase(ConfigMain.PROFESSION_PROFESSION_BLACKSMITH)) {
			ret = Villager.Profession.BLACKSMITH;
		} else if (profession.equalsIgnoreCase(ConfigMain.PROFESSION_PROFESSION_BUTCHER)) {
			ret = Villager.Profession.BUTCHER;
		} else if (profession.equalsIgnoreCase(ConfigMain.PROFESSION_PROFESSION_FARMER)) {
			ret = Villager.Profession.FARMER;
		} else if (profession.equalsIgnoreCase(ConfigMain.PROFESSION_PROFESSION_LIBRARIAN)) {
			ret = Villager.Profession.LIBRARIAN;
		} else if (profession.equalsIgnoreCase(ConfigMain.PROFESSION_PROFESSION_NITWIT)) {
			ret = Villager.Profession.NITWIT;
		} else if (profession.equalsIgnoreCase(ConfigMain.PROFESSION_PROFESSION_PRIEST)) {
			ret = Villager.Profession.PRIEST;
		}
		return ret;
	}
	
	private String getProfessionName(Villager.Profession profession) {
		String ret = null;
		switch (profession) {
		case BLACKSMITH:
			ret = ConfigMain.PROFESSION_PROFESSION_BLACKSMITH;
			break;
		case BUTCHER:
			ret = ConfigMain.PROFESSION_PROFESSION_BUTCHER;
			break;
		case FARMER:
			ret = ConfigMain.PROFESSION_PROFESSION_FARMER;
			break;
		case LIBRARIAN:
			ret = ConfigMain.PROFESSION_PROFESSION_LIBRARIAN;
			break;
		case NITWIT:
			ret = ConfigMain.PROFESSION_PROFESSION_NITWIT;
			break;
		case PRIEST:
			ret = ConfigMain.PROFESSION_PROFESSION_PRIEST;
			break;
		}
		return ret;
	}
}
