package com.alessiodp.securityvillagers.bukkit.villagers;

import com.alessiodp.securityvillagers.bukkit.addons.external.FactionsHandler;
import com.alessiodp.securityvillagers.bukkit.villagers.objects.BukkitProtectedEntity;
import com.alessiodp.securityvillagers.common.SecurityVillagersPlugin;
import com.alessiodp.securityvillagers.common.commands.utils.SecurityVillagersPermission;
import com.alessiodp.securityvillagers.common.configuration.SVConstants;
import com.alessiodp.securityvillagers.common.configuration.data.ConfigMain;
import com.alessiodp.securityvillagers.bukkit.villagers.objects.MobsType;
import com.alessiodp.securityvillagers.common.villagers.VillagerManager;
import com.alessiodp.securityvillagers.common.villagers.objects.ProtectedEntity;
import com.alessiodp.securityvillagers.common.villagers.objects.ProtectedEntityType;
import lombok.NonNull;
import org.bukkit.entity.Entity;
import org.bukkit.entity.IronGolem;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.entity.Skeleton;
import org.bukkit.entity.Villager;
import org.bukkit.entity.Witch;
import org.bukkit.entity.Wither;
import org.bukkit.entity.WitherSkull;
import org.bukkit.entity.Zombie;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.projectiles.ProjectileSource;

public class BukkitVillagerManager extends VillagerManager {
	
	public BukkitVillagerManager(@NonNull SecurityVillagersPlugin plugin) {
		super(plugin);
	}
	
	@Override
	public ProtectedEntity initializeProtectedEntity(Object protectedEntity) {
		BukkitProtectedEntity ret = null;
		ProtectedEntityType type = getEntityType(protectedEntity);
		if (type != null) {
			ret = new BukkitProtectedEntity(plugin, (Entity) protectedEntity, type);
			if (ConfigMain.GENERAL_PROTECTIONTYPE == ConfigMain.ProtectionType.CUSTOM)
				ret.setProtectionEnabled(getProtectedEntities().contains(ret.getUuid()));
		}
		return ret;
	}
	
	@Override
	public ProtectedEntityType getEntityType(Object entity) {
		ProtectedEntityType ret = null;
		if (entity instanceof Villager) {
			ret = ProtectedEntityType.VILLAGER;
		} else if (entity instanceof IronGolem) {
			ret = ProtectedEntityType.IRON_GOLEM;
		} else if (MobsType.WANDERING_TRADER.instanceOf(entity)) {
			ret = ProtectedEntityType.WANDERING_TRADER;
		} else if (MobsType.EVOKER.instanceOf(entity)) {
			ret = ProtectedEntityType.EVOKER;
		} else if (MobsType.ILLUSIONER.instanceOf(entity)) {
			ret = ProtectedEntityType.ILLUSIONER;
		} else if (MobsType.PILLAGER.instanceOf(entity)) {
			ret = ProtectedEntityType.PILLAGER;
		} else if (MobsType.RAVAGER.instanceOf(entity)) {
			ret = ProtectedEntityType.RAVAGER;
		} else if (MobsType.VINDICATOR.instanceOf(entity)) {
			ret = ProtectedEntityType.VINDICATOR;
		} else if (MobsType.VEX.instanceOf(entity)) {
			ret = ProtectedEntityType.VEX;
		} else if (entity instanceof Witch) {
			ret = ProtectedEntityType.WITCH;
		}
		return ret;
	}
	
	private boolean isUnprotected(ProtectedEntity protectedEntity, Entity attacker) {
		if (!protectedEntity.isConfigProtected()) {
			// Entity not protected
			return true;
		}
		
		if (!ConfigMain.GENERAL_DAMAGE_WORLDS.contains("*")
				&& !ConfigMain.GENERAL_DAMAGE_WORLDS.contains(protectedEntity.getWorld())) {
			// Entity not protected in this world
			return true;
		}
		
		// Check if custom protected
		if (ConfigMain.GENERAL_PROTECTIONTYPE == ConfigMain.ProtectionType.CUSTOM
				&& !protectedEntity.isProtectionEnabled()) {
			return true;
		}
		
		// Check for factions
		return (ConfigMain.GENERAL_PROTECTIONTYPE == ConfigMain.ProtectionType.FACTIONS
				&& !FactionsHandler.isClaimProtectedByAttack(attacker, ((Entity) protectedEntity.getEntity()).getLocation()));
	}
	
	@Override
	public AttackResult canBeAttacked(ProtectedEntity protectedEntity, Object attacker) {
		if (isUnprotected(protectedEntity, (Entity) attacker))
			return AttackResult.SUCCESS;
		
		// Check if immortal
		if (ConfigMain.GENERAL_DAMAGE_IMMORTAL) {
			plugin.getLoggerManager().logDebug(SVConstants.DEBUG_PROTECTION_IMMORTAL
					.replace("{entity}", protectedEntity.getType().name()), true);
			
			return attacker instanceof Projectile ? AttackResult.SHOOT : AttackResult.HIT;
		}
		
		// Player attack
		if (attacker instanceof Player) {
			if (!canBeAttackedFromPlayer((Player) attacker, true)) {
				plugin.getLoggerManager().logDebug(SVConstants.DEBUG_PROTECTION_HIT_PLAYER
						.replace("{entity}", protectedEntity.getType().name())
						.replace("{player}", ((Player) attacker).getName()), true);
				
				return AttackResult.HIT;
			}
			return AttackResult.SUCCESS;
		}
		
		// Arrow from player attack
		if (attacker instanceof Projectile) {
			ProjectileSource shooter = ((Projectile) attacker).getShooter();
			if (shooter != null) {
				if (shooter instanceof Player) {
					if (!canBeAttackedFromPlayer((Player) shooter, false)) {
						plugin.getLoggerManager().logDebug(SVConstants.DEBUG_PROTECTION_PROJECTILE_PLAYER
								.replace("{entity}", protectedEntity.getType().name())
								.replace("{player}", ((Player) shooter).getName()), true);
						
						return AttackResult.SHOOT;
					}
				} else {
					if (!canBeAttackedFromMob((Entity) shooter)) {
						plugin.getLoggerManager().logDebug(SVConstants.DEBUG_PROTECTION_PROJECTILE_MOB
								.replace("{entity}", protectedEntity.getType().name())
								.replace("{mob}", ((Entity) shooter).getName()), true);
						
						return AttackResult.SHOOT;
					}
				}
			}
		}
		
		// Attack from mob
		if (!canBeAttackedFromMob((Entity) attacker)) {
			plugin.getLoggerManager().logDebug(SVConstants.DEBUG_PROTECTION_HIT_MOB
					.replace("{entity}", protectedEntity.getType().name())
					.replace("{mob}", ((Entity) attacker).getName()), true);
			
			return AttackResult.HIT;
		}
		return AttackResult.SUCCESS;
	}
	
	@Override
	public boolean canBeDamaged(ProtectedEntity protectedEntity, Object damageCause) {
		System.out.println("here1");
		if (isUnprotected(protectedEntity, null))
			return true;
		System.out.println("here2");
		// Check if immortal
		if (ConfigMain.GENERAL_DAMAGE_IMMORTAL) {
			plugin.getLoggerManager().logDebug(SVConstants.DEBUG_PROTECTION_IMMORTAL
					.replace("{entity}", protectedEntity.getType().name()), true);
			
			return false;
		}
		System.out.println("here3");
		
		// Check damage cause
		boolean protection;
		switch ((EntityDamageEvent.DamageCause) damageCause) {
			case CONTACT:
				protection = ConfigMain.DAMAGE_OTHER_CONTACT;
				break;
			case CRAMMING:
				protection = ConfigMain.DAMAGE_OTHER_CRAMMING;
				break;
			case DRAGON_BREATH:
				protection = ConfigMain.DAMAGE_OTHER_DRAGON;
				break;
			case DROWNING:
				protection = ConfigMain.DAMAGE_OTHER_DROWNING;
				break;
			case BLOCK_EXPLOSION:
			case ENTITY_EXPLOSION:
				protection = ConfigMain.DAMAGE_OTHER_EXPLOSION;
				break;
			case FALL:
				protection = ConfigMain.DAMAGE_OTHER_FALL;
				break;
			case FALLING_BLOCK:
				protection = ConfigMain.DAMAGE_OTHER_FALLINGBLOCK;
				break;
			case FIRE:
			case FIRE_TICK:
				protection = ConfigMain.DAMAGE_OTHER_FIRE;
				break;
			case HOT_FLOOR:
				protection = ConfigMain.DAMAGE_OTHER_HOTFLOOR;
				break;
			case LAVA:
				protection = ConfigMain.DAMAGE_OTHER_LAVA;
				break;
			case LIGHTNING:
				protection = ConfigMain.DAMAGE_OTHER_LIGHTNING;
				break;
			case MAGIC:
				protection = ConfigMain.DAMAGE_OTHER_MAGIC;
				break;
			case POISON:
				protection = ConfigMain.DAMAGE_OTHER_POISON;
				break;
			case SUFFOCATION:
				protection = ConfigMain.DAMAGE_OTHER_SUFFOCATION;
				break;
			case THORNS:
				protection = ConfigMain.DAMAGE_OTHER_THORNS;
				break;
			case WITHER:
				protection = ConfigMain.DAMAGE_MOBS_WITHER;
				break;
			case VOID:
				protection = ConfigMain.DAMAGE_OTHER_VOID;
				break;
			case CUSTOM:
			case DRYOUT:
			case ENTITY_ATTACK:
			case ENTITY_SWEEP_ATTACK:
			case FLY_INTO_WALL:
			case MELTING:
			case PROJECTILE:
			case SUICIDE:
			case STARVATION:
			default:
				protection = false;
				break;
		}
		
		return !protection;
	}
	
	private boolean canBeAttackedFromPlayer(Player player, boolean isMelee) {
		return !((isMelee ? ConfigMain.GENERAL_DAMAGE_HIT : ConfigMain.GENERAL_DAMAGE_ARROW)
				&& (!player.hasPermission((isMelee ? SecurityVillagersPermission.USER_HIT : SecurityVillagersPermission.USER_SHOOT).toString())));
	}
	
	private boolean canBeAttackedFromMob(Entity mob) {
		return !(
				(ConfigMain.DAMAGE_MOBS_EVOKER && (MobsType.EVOKER.instanceOf(mob) || MobsType.EVOKER_FANGS.instanceOf(mob))) // Evoker
				|| (ConfigMain.DAMAGE_MOBS_ILLUSIONER && MobsType.ILLUSIONER.instanceOf(mob)) // Illusioner
				|| (ConfigMain.DAMAGE_MOBS_PILLAGER && MobsType.PILLAGER.instanceOf(mob)) // Pillager
				|| (ConfigMain.DAMAGE_MOBS_RAVAGER && MobsType.RAVAGER.instanceOf(mob)) // Ravager
				|| (ConfigMain.DAMAGE_MOBS_SKELETON && mob instanceof Skeleton) // Skeleton
				|| (ConfigMain.DAMAGE_MOBS_RAVAGER && MobsType.STRAY.instanceOf(mob)) // Skeleton
				|| (ConfigMain.DAMAGE_MOBS_VEX && MobsType.VEX.instanceOf(mob)) // Vex
				|| (ConfigMain.DAMAGE_MOBS_VINDICATOR && MobsType.VINDICATOR.instanceOf(mob)) // Vindicator
				|| (ConfigMain.DAMAGE_MOBS_WITCH && mob instanceof Witch) // Witch
				|| (ConfigMain.DAMAGE_MOBS_WITHER && (mob instanceof Wither || mob instanceof WitherSkull)) // Wither
				|| (ConfigMain.DAMAGE_MOBS_ZOMBIE && mob instanceof Zombie) // Zombie
		);
	}
}
