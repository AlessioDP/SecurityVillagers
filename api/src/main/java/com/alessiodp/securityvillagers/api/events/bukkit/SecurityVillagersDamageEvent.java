package com.alessiodp.securityvillagers.api.events.bukkit;

import com.alessiodp.securityvillagers.api.enums.AttackResult;
import com.alessiodp.securityvillagers.api.events.BukkitSecurityVillagersEvent;
import com.alessiodp.securityvillagers.api.events.interfaces.ISecurityVillagersDamageEvent;
import org.bukkit.entity.Entity;
import org.jetbrains.annotations.NotNull;

public class SecurityVillagersDamageEvent extends BukkitSecurityVillagersEvent implements ISecurityVillagersDamageEvent {
	private final Entity entity;
	private final Entity damager;
	private AttackResult attackResult;
	
	public SecurityVillagersDamageEvent(Entity entity, Entity damager, AttackResult attackResult) {
		this.entity = entity;
		this.damager = damager;
		this.attackResult = attackResult;
	}
	
	@NotNull
	@Override
	public Entity getEntity() {
		return entity;
	}
	
	@NotNull
	@Override
	public Entity getDamager() {
		return damager;
	}
	
	@NotNull
	@Override
	public AttackResult getAttackResult() {
		return attackResult;
	}
	
	@Override
	public void setAttackResult(@NotNull AttackResult attackResult) {
		this.attackResult = attackResult;
	}
}
