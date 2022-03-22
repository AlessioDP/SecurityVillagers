package com.alessiodp.securityvillagers.common.storage.dispatchers;

import com.alessiodp.core.common.ADPPlugin;
import com.alessiodp.core.common.addons.external.simpleyaml.configuration.ConfigurationSection;
import com.alessiodp.core.common.configuration.Constants;
import com.alessiodp.core.common.storage.dispatchers.YAMLDispatcher;
import com.alessiodp.core.common.storage.file.YAMLDao;
import com.alessiodp.core.common.storage.file.YAMLUpgradeManager;
import com.alessiodp.securityvillagers.common.configuration.SVConstants;
import com.alessiodp.securityvillagers.common.configuration.data.ConfigMain;
import com.alessiodp.securityvillagers.common.villagers.objects.ProtectedEntity;
import lombok.NonNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

public class SVYAMLDispatcher extends YAMLDispatcher {
	
	public SVYAMLDispatcher(@NonNull ADPPlugin plugin) {
		super(plugin);
	}
	
	@Override
	protected YAMLDao initDao() {
		return new YAMLDao(
				plugin,
				ConfigMain.STORAGE_DATABASE_FILE,
				SVConstants.VERSION_DATABASE_YAML
		);
	}
	
	@Override
	protected YAMLUpgradeManager initUpgradeManager() {
		return null; // Nothing to upgrade
	}
	
	
	public void updateProtectedEntity(ProtectedEntity protectedEntity) {
		try {
			if (protectedEntity.isProtectionEnabled()) {
				ConfigurationSection node = database.getYaml().getConfigurationSection("mobs." + protectedEntity.getUuid().toString());
				if (node == null)
					node = database.getYaml().createSection("mobs." + protectedEntity.getUuid().toString());
				node.set("protection", protectedEntity.isProtectionEnabled());
			} else {
				database.getYaml().set("mobs." + protectedEntity.getUuid().toString(), null);
			}
		
			database.saveFile();
		} catch (IOException ex) {
			plugin.getLoggerManager().logError(Constants.DEBUG_DB_FILE_ERROR, ex);
		}
	}
	
	public ArrayList<UUID> getAllProtectedEntities() {
		ArrayList<UUID> ret = new ArrayList<>();
		ConfigurationSection mobsNode = database.getYaml().getConfigurationSection("mobs");
		if (mobsNode != null) {
			for (String nodeKey : mobsNode.getKeys(false)) {
				if (mobsNode.getBoolean(nodeKey + ".protection", false)) {
					try {
						ret.add(UUID.fromString(nodeKey));
					} catch (Exception ex) {
						ex.printStackTrace();
					}
				}
			}
		}
		return ret;
	}
}
