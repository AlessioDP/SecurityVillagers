package com.alessiodp.securityvillagers.commands;

import org.bukkit.command.CommandSender;

public interface ICommand {
	void onCommand(CommandSender sender, String[] args);
}
