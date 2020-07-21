package com.alessiodp.securityvillagers.common.storage;

import com.alessiodp.core.common.ADPPlugin;
import com.alessiodp.core.common.storage.DatabaseManager;
import com.alessiodp.core.common.storage.StorageType;
import com.alessiodp.core.common.storage.interfaces.IDatabaseDispatcher;
import com.alessiodp.securityvillagers.common.configuration.SVConstants;
import com.alessiodp.securityvillagers.common.configuration.data.ConfigMain;
import com.alessiodp.securityvillagers.common.storage.dispatchers.SVFileDispatcher;
import com.alessiodp.securityvillagers.common.villagers.objects.ProtectedEntity;

import java.util.ArrayList;
import java.util.UUID;

public class SVDatabaseManager extends DatabaseManager {
	
	public SVDatabaseManager(ADPPlugin plugin) {
		super(plugin);
	}
	
	@Override
	public void reload() {
		if (ConfigMain.GENERAL_PROTECTIONTYPE == ConfigMain.ProtectionType.CUSTOM) {
			setDatabaseType(StorageType.YAML);
			super.reload();
		} else {
			database = null;
		}
	}
	
	@Override
	protected IDatabaseDispatcher initializeDispatcher(StorageType storageType) {
		return new SVFileDispatcher(plugin, storageType); // Database is only YAML
	}
	
	public void updateProtectedEntity(ProtectedEntity protectedEntity) {
		plugin.getScheduler().runAsync(() -> {
			plugin.getLoggerManager().logDebug(SVConstants.DEBUG_DB_UPDATEENTITY
					.replace("{mob}", protectedEntity.getType().name())
					.replace("{uuid}", protectedEntity.getUuid().toString()), true);
			
			((SVFileDispatcher) database).updateProtectedEntity(protectedEntity);
		});
	}
	
	public ArrayList<UUID> getAllProtectedEntities() {
		return plugin.getScheduler().runSupplyAsync(() -> {
			plugin.getLoggerManager().logDebug(SVConstants.DEBUG_DB_GETALLPROTECTED, true);
			
			return ((SVFileDispatcher) database).getAllProtectedEntities();
		}).join();
	}
}
