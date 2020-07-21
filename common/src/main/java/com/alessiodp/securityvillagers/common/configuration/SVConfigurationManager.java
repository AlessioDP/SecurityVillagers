package com.alessiodp.securityvillagers.common.configuration;

import com.alessiodp.core.common.ADPPlugin;
import com.alessiodp.core.common.configuration.ConfigurationManager;
import com.alessiodp.securityvillagers.common.utils.SecurityVillagersPermission;
import com.alessiodp.securityvillagers.common.configuration.data.Messages;
import com.alessiodp.securityvillagers.common.villagers.objects.VillagerProfession;

public abstract class SVConfigurationManager extends ConfigurationManager {
	
	public SVConfigurationManager(ADPPlugin plugin) {
		super(plugin);
	}
	
	@Override
	protected void performChanges() {
		plugin.getLoginAlertsManager().setPermission(SecurityVillagersPermission.ADMIN_ALERTS);
		checkOutdatedConfigs(Messages.SECURITYVILLAGERS_CONFIGURATION_OUTDATED);
		VillagerProfession.setup();
	}
}
