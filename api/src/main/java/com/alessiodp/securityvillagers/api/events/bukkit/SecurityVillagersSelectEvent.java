package com.alessiodp.securityvillagers.api.events.bukkit;

import com.alessiodp.securityvillagers.api.events.BukkitSecurityVillagersEvent;
import com.alessiodp.securityvillagers.api.events.interfaces.ISecurityVillagersSelectEvent;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class SecurityVillagersSelectEvent extends BukkitSecurityVillagersEvent implements ISecurityVillagersSelectEvent {
	private final Player player;
	private final Entity selectedEntity;
	private final Entity oldSelection;
	private boolean cancelled;
	
	public SecurityVillagersSelectEvent(Player player, Entity selectedEntity, Entity oldSelection) {
		this.player = player;
		this.selectedEntity = selectedEntity;
		this.oldSelection = oldSelection;
		cancelled = false;
	}
	
	@NotNull
	@Override
	public Player getPlayer() {
		return player;
	}
	
	@NotNull
	@Override
	public Entity getSelectedEntity() {
		return selectedEntity;
	}
	
	@Nullable
	@Override
	public Entity getOldSelection() {
		return oldSelection;
	}
	
	@Override
	public boolean isCancelled() {
		return cancelled;
	}
	
	@Override
	public void setCancelled(boolean cancel) {
		cancelled = cancel;
	}
}
