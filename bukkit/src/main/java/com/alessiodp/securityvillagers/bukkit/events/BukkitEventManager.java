package com.alessiodp.securityvillagers.bukkit.events;

import com.alessiodp.core.bukkit.bootstrap.ADPBukkitBootstrap;
import com.alessiodp.core.bukkit.events.BukkitEventDispatcher;
import com.alessiodp.core.common.user.User;
import com.alessiodp.securityvillagers.api.enums.AttackResult;
import com.alessiodp.securityvillagers.api.enums.InteractType;
import com.alessiodp.securityvillagers.api.events.bukkit.SecurityVillagersDamageEvent;
import com.alessiodp.securityvillagers.api.events.bukkit.SecurityVillagersInteractProtectionEvent;
import com.alessiodp.securityvillagers.api.events.bukkit.SecurityVillagersProtectionChangeEvent;
import com.alessiodp.securityvillagers.api.events.bukkit.SecurityVillagersSelectEvent;
import com.alessiodp.securityvillagers.api.events.interfaces.ISecurityVillagersDamageEvent;
import com.alessiodp.securityvillagers.api.events.interfaces.ISecurityVillagersInteractProtectionEvent;
import com.alessiodp.securityvillagers.api.events.interfaces.ISecurityVillagersProtectionChangeEvent;
import com.alessiodp.securityvillagers.api.events.interfaces.ISecurityVillagersSelectEvent;
import com.alessiodp.securityvillagers.common.SecurityVillagersPlugin;
import com.alessiodp.securityvillagers.common.events.EventManager;
import com.alessiodp.securityvillagers.common.villagers.objects.ProtectedEntity;
import lombok.NonNull;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

public class BukkitEventManager extends EventManager {
	public BukkitEventManager(@NonNull SecurityVillagersPlugin plugin) {
		super(plugin, new BukkitEventDispatcher(plugin));
	}
	
	@Override
	public ISecurityVillagersDamageEvent prepareDamageEvent(ProtectedEntity protectedEntity, Object damager, AttackResult attackResult) {
		return new SecurityVillagersDamageEvent((Entity) protectedEntity.getEntity(), (Entity) damager, attackResult);
	}
	
	@Override
	public ISecurityVillagersInteractProtectionEvent prepareInteractEvent(ProtectedEntity protectedEntity, User interactor, InteractType interactType) {
		Player player = ((ADPBukkitBootstrap) plugin.getBootstrap()).getServer().getPlayer(interactor.getUUID());
		return new SecurityVillagersInteractProtectionEvent((Entity) protectedEntity.getEntity(), player, interactType);
	}
	
	@Override
	public ISecurityVillagersSelectEvent prepareSelectEvent(User player, ProtectedEntity newSelection, ProtectedEntity oldSelection) {
		Player bukkitPlayer = ((ADPBukkitBootstrap) plugin.getBootstrap()).getServer().getPlayer(player.getUUID());
		return new SecurityVillagersSelectEvent(bukkitPlayer, (Entity) newSelection.getEntity(), oldSelection != null ? ((Entity) oldSelection.getEntity()) : null);
	}
	
	@Override
	public ISecurityVillagersProtectionChangeEvent prepareProtectionChangeEvent(User player, ProtectedEntity protectedEntity, boolean protection) {
		Player bukkitPlayer = ((ADPBukkitBootstrap) plugin.getBootstrap()).getServer().getPlayer(player.getUUID());
		return new SecurityVillagersProtectionChangeEvent(bukkitPlayer, (Entity) protectedEntity.getEntity(), protection);
	}
}
