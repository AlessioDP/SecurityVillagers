package com.alessiodp.securityvillagers.api.events.bukkit;

import com.alessiodp.securityvillagers.api.enums.InteractType;
import com.alessiodp.securityvillagers.api.events.BukkitSecurityVillagersEvent;
import com.alessiodp.securityvillagers.api.events.interfaces.ISecurityVillagersInteractProtectionEvent;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class SecurityVillagersInteractProtectionEvent extends BukkitSecurityVillagersEvent implements ISecurityVillagersInteractProtectionEvent {
	private final Entity entity;
	private final Player interactor;
	private final InteractType interactType;
	private boolean cancelled;
	
	public SecurityVillagersInteractProtectionEvent(Entity entity, Player interactor, InteractType interactType) {
		this.entity = entity;
		this.interactor = interactor;
		this.interactType = interactType;
		cancelled = false;
	}
	
	@NotNull
	@Override
	public Entity getEntity() {
		return entity;
	}
	
	@NotNull
	@Override
	public Player getInteractor() {
		return interactor;
	}
	
	
	@NotNull
	@Override
	public InteractType getInteractType() {
		return interactType;
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
