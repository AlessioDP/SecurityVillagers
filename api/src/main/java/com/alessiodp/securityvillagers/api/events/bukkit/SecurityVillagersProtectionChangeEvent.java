package com.alessiodp.securityvillagers.api.events.bukkit;

import com.alessiodp.securityvillagers.api.events.BukkitSecurityVillagersEvent;
import com.alessiodp.securityvillagers.api.events.interfaces.ISecurityVillagersProtectionChangeEvent;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class SecurityVillagersProtectionChangeEvent extends BukkitSecurityVillagersEvent implements ISecurityVillagersProtectionChangeEvent {
	private final Player player;
	private final Entity entity;
	private boolean protection;
	private boolean cancelled;
	
	public SecurityVillagersProtectionChangeEvent(Player player, Entity entity, boolean protection) {
		this.player = player;
		this.entity = entity;
		this.protection = protection;
		cancelled = false;
	}
	
	@NotNull
	@Override
	public Player getPlayer() {
		return player;
	}
	
	@NotNull
	@Override
	public Entity getEntity() {
		return entity;
	}
	
	@Override
	public boolean getProtection() {
		return protection;
	}
	
	@Override
	public void setProtection(boolean protection) {
		this.protection = protection;
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
