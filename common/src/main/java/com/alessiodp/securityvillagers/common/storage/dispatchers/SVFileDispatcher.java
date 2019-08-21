package com.alessiodp.securityvillagers.common.storage.dispatchers;

import com.alessiodp.core.common.ADPPlugin;
import com.alessiodp.core.common.addons.libraries.ILibrary;
import com.alessiodp.core.common.configuration.Constants;
import com.alessiodp.core.common.storage.StorageType;
import com.alessiodp.core.common.storage.dispatchers.FileDispatcher;
import com.alessiodp.core.common.storage.file.YAMLDao;
import com.alessiodp.securityvillagers.common.configuration.SVConstants;
import com.alessiodp.securityvillagers.common.configuration.data.ConfigMain;
import com.alessiodp.securityvillagers.common.villagers.objects.ProtectedEntity;
import lombok.NonNull;
import ninja.leaping.configurate.ConfigurationNode;

import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

public class SVFileDispatcher extends FileDispatcher {
	public SVFileDispatcher(@NonNull ADPPlugin plugin) {
		super(plugin);
	}
	
	@Override
	public void init(StorageType type) {
		// Configurate is necessary to handle every type of file database, loading core
		if (plugin.getLibraryManager().initLibrary(ILibrary.CONFIGURATE_CORE)) {
			database = new YAMLDao(
					plugin,
					ConfigMain.STORAGE_DATABASE_FILE,
					SVConstants.VERSION_DATABASE_YAML);
			database.initFile();
		}
	}
	
	public void updateProtectedEntity(ProtectedEntity protectedEntity) {
		ConfigurationNode node = database.getRootNode().getNode("mobs", protectedEntity.getUuid().toString());
		node.getNode("protection").setValue(protectedEntity.isProtectionEnabled() ? protectedEntity.isProtectionEnabled() : null);
		
		try {
			database.saveFile();
		} catch (IOException ex) {
			plugin.getLoggerManager().printErrorStacktrace(Constants.DEBUG_DB_FILE_ERROR, ex);
		}
	}
	
	public ArrayList<UUID> getAllProtectedEntities() {
		ArrayList<UUID> ret = new ArrayList<>();
		for (ConfigurationNode confNode : database.getRootNode().getNode("mobs").getChildrenMap().values()) {
			if (confNode.getKey() != null && confNode.getNode("protection").getBoolean(false)) {
				try {
					ret.add(UUID.fromString(confNode.getKey().toString()));
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		}
		return ret;
	}
}
