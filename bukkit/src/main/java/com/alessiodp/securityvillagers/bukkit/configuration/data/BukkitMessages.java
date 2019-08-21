package com.alessiodp.securityvillagers.bukkit.configuration.data;

import com.alessiodp.securityvillagers.common.SecurityVillagersPlugin;
import com.alessiodp.securityvillagers.common.configuration.SVConstants;
import com.alessiodp.securityvillagers.common.configuration.data.Messages;
import lombok.Getter;

public class BukkitMessages extends Messages {
	@Getter private final String fileName = "messages.yml";
	@Getter private final String resourceName = "bukkit/messages.yml";
	@Getter private final int latestVersion = SVConstants.VERSION_BUKKIT_MESSAGES;
	
	public BukkitMessages(SecurityVillagersPlugin plugin) {
		super(plugin);
	}
}
