package com.alessiodp.securityvillagers.bukkit.api;

import com.alessiodp.core.bukkit.bootstrap.ADPBukkitBootstrap;
import com.alessiodp.securityvillagers.api.enums.ProtectedEntityType;
import com.alessiodp.securityvillagers.common.SecurityVillagersPlugin;
import com.alessiodp.securityvillagers.common.api.ApiHandler;
import com.alessiodp.securityvillagers.common.villagers.objects.ProtectedEntity;
import lombok.NonNull;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class BukkitApiHandler extends ApiHandler {
	public BukkitApiHandler(@NonNull SecurityVillagersPlugin plugin) {
		super(plugin);
	}
	
	@Override
	public @Nullable ProtectedEntityType getEntityType(Entity entity) {
		return plugin.getVillagerManager().getEntityType(entity);
	}
	
	@Override
	public boolean isEntityDamageProtected(@NotNull Entity entity) {
		ProtectedEntity protectedEntity = plugin.getVillagerManager().initializeProtectedEntity(entity);
		if (protectedEntity != null)
			return !plugin.getVillagerManager().canBeDamaged(protectedEntity, null);
		return false;
	}
	
	@Override
	public Entity getSelectedEntity(Player player) {
		ProtectedEntity protectedEntity = plugin.getVillagerManager().getSelectedEntityBy(player.getUniqueId());
		return protectedEntity != null ? ((ADPBukkitBootstrap) plugin.getBootstrap()).getServer().getEntity(protectedEntity.getUuid()) : null;
	}
}
