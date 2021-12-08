package com.alessiodp.securityvillagers.api.interfaces;

import com.alessiodp.securityvillagers.api.enums.ProtectedEntityType;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.UUID;

public interface SecurityVillagersAPI {
	
	/**
	 * Reload SecurityVillagers configuration files
	 */
	void reloadPlugin();
	
	/**
	 * Get the entity type of SecurityVillagers for the given entity
	 * @param entity The entity
	 * @return Returns the {@link ProtectedEntityType}
	 */
	@Nullable
	ProtectedEntityType getEntityType(Entity entity);
	
	/**
	 * Is the entity protected by SecurityVillagers?
	 *
	 * @param entity The entity
	 * @return Returns true if protected
	 */
	boolean isEntityDamageProtected(@NotNull Entity entity);
	
	/**
	 * Is the entity selected by the given player?
	 *
	 * @param player The player
	 * @return Returns the entity selected or null
	 */
	@Nullable
	Entity getSelectedEntity(Player player);
	
	/**
	 * Get a list of protected entities. Used only if custom protection is enabled.
	 *
	 * @return Returns the list of protected entities
	 */
	List<UUID> getProtectedEntities();
}
