package com.alessiodp.securityvillagers.common.listeners;

import com.alessiodp.core.common.user.User;
import com.alessiodp.securityvillagers.api.enums.AttackResult;
import com.alessiodp.securityvillagers.api.events.interfaces.ISecurityVillagersDamageEvent;
import com.alessiodp.securityvillagers.common.SecurityVillagersPlugin;
import com.alessiodp.securityvillagers.common.configuration.data.Messages;
import com.alessiodp.securityvillagers.common.villagers.objects.ProtectedEntity;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class FightListener {
	@NonNull protected final SecurityVillagersPlugin plugin;
	
	protected boolean onDamageByEntity(ProtectedEntity protectedEntity, Object damager) {
		boolean ret = false;
		AttackResult ar = plugin.getVillagerManager().canBeAttacked(protectedEntity, damager);
		
		ISecurityVillagersDamageEvent event = plugin.getEventManager().prepareDamageEvent(protectedEntity, damager, ar);
		plugin.getEventManager().callEvent(event);
		ar = event.getAttackResult();
		if (!ar.isSuccess()) {
			User user = getPlayerFromEntity(damager);
			if (user != null) {
				user.sendMessage(ar.isHit() ? Messages.GENERAL_INTERACT_HIT : Messages.GENERAL_INTERACT_SHOOT, true);
			}
			
			ret = true;
		}
		return ret;
	}
	
	protected abstract User getPlayerFromEntity(Object entity);
}
