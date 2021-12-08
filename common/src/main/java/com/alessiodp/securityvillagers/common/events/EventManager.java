package com.alessiodp.securityvillagers.common.events;

import com.alessiodp.core.common.events.EventDispatcher;
import com.alessiodp.core.common.user.User;
import com.alessiodp.securityvillagers.api.enums.AttackResult;
import com.alessiodp.securityvillagers.api.enums.InteractType;
import com.alessiodp.securityvillagers.api.events.SecurityVillagersEvent;
import com.alessiodp.securityvillagers.api.events.interfaces.ISecurityVillagersDamageEvent;
import com.alessiodp.securityvillagers.api.events.interfaces.ISecurityVillagersInteractProtectionEvent;
import com.alessiodp.securityvillagers.api.events.interfaces.ISecurityVillagersProtectionChangeEvent;
import com.alessiodp.securityvillagers.api.events.interfaces.ISecurityVillagersSelectEvent;
import com.alessiodp.securityvillagers.common.SecurityVillagersPlugin;
import com.alessiodp.securityvillagers.common.villagers.objects.ProtectedEntity;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class EventManager {
	@NonNull protected final SecurityVillagersPlugin plugin;
	@NonNull protected final EventDispatcher eventDispatcher;
	
	public final void callEvent(SecurityVillagersEvent event) {
		event.setApi(plugin.getApi());
		eventDispatcher.callEvent(event);
	}
	
	public abstract ISecurityVillagersDamageEvent prepareDamageEvent(ProtectedEntity protectedEntity, Object damager, AttackResult attackResult);
	
	public abstract ISecurityVillagersInteractProtectionEvent prepareInteractEvent(ProtectedEntity protectedEntity, User interactor, InteractType interactType);
	
	public abstract ISecurityVillagersSelectEvent prepareSelectEvent(User player, ProtectedEntity newSelection, ProtectedEntity oldSelection);
	
	public abstract ISecurityVillagersProtectionChangeEvent prepareProtectionChangeEvent(User player, ProtectedEntity protectedEntity, boolean protection);
}
