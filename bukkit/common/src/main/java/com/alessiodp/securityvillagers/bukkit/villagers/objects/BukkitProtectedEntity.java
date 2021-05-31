package com.alessiodp.securityvillagers.bukkit.villagers.objects;

import com.alessiodp.core.common.user.User;
import com.alessiodp.securityvillagers.common.SecurityVillagersPlugin;
import com.alessiodp.securityvillagers.common.villagers.objects.ProtectedEntity;
import com.alessiodp.securityvillagers.common.villagers.objects.ProtectedEntityType;
import com.alessiodp.securityvillagers.common.villagers.objects.VillagerProfession;
import lombok.Getter;
import lombok.NonNull;
import org.bukkit.Bukkit;
import org.bukkit.entity.Ageable;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;

import java.util.UUID;

public class BukkitProtectedEntity extends ProtectedEntity {
	@Getter private final Entity entity;
	
	public BukkitProtectedEntity(@NonNull SecurityVillagersPlugin plugin, @NonNull Entity entity, ProtectedEntityType type) {
		super(plugin, type);
		this.entity = entity;
	}
	
	@Override
	public UUID getUuid() {
		return entity.getUniqueId();
	}
	
	@Override
	public void setCustomName(String name) {
		entity.setCustomName(name);
	}
	
	@Override
	public String getWorld() {
		return entity.getWorld().getName();
	}
	
	@Override
	public void teleportTo(User user) {
		if (user != null) {
			Player player = Bukkit.getPlayer(user.getUUID());
			if (player != null) {
				entity.teleport(player);
			}
		}
	}
	
	@Override
	public boolean isAgeable() {
		return entity instanceof Ageable;
	}
	
	@Override
	public boolean isAdult() {
		return isAgeable() && ((Ageable) entity).isAdult();
	}
	
	@Override
	public void setToAdult() {
		if (isAgeable())
			((Ageable) entity).setAdult();
	}
	
	@Override
	public void setToBaby() {
		if (isAgeable())
			((Ageable) entity).setBaby();
	}
	
	@Override
	public boolean haveProfession() {
		return entity instanceof Villager;
	}
	
	@Override
	public VillagerProfession getProfession() {
		return haveProfession() ? VillagerProfession.getProfession(((Villager) entity).getProfession().name()) : null;
	}
	
	@Override
	public boolean setProfession(VillagerProfession profession) {
		try {
			if (haveProfession())
				((Villager) entity).setProfession(Villager.Profession.valueOf(profession.name()));
			return true;
		} catch (Exception ignored) {} // Profession does not exist
		return false;
	}
}
