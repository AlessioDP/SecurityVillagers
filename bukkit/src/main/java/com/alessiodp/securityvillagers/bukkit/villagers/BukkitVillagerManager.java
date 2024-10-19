package com.alessiodp.securityvillagers.bukkit.villagers;

import com.alessiodp.core.bukkit.bootstrap.ADPBukkitBootstrap;
import com.alessiodp.securityvillagers.api.enums.AttackResult;
import com.alessiodp.securityvillagers.api.enums.ProtectedEntityType;
import com.alessiodp.securityvillagers.bukkit.BukkitSecurityVillagersPlugin;
import com.alessiodp.securityvillagers.bukkit.addons.external.CitizensHandler;
import com.alessiodp.securityvillagers.bukkit.addons.external.FactionsHandler;
import com.alessiodp.securityvillagers.bukkit.addons.external.GlowHandler;
import com.alessiodp.securityvillagers.bukkit.villagers.objects.BukkitProtectedEntity;
import com.alessiodp.securityvillagers.common.SecurityVillagersPlugin;
import com.alessiodp.securityvillagers.common.utils.SecurityVillagersPermission;
import com.alessiodp.securityvillagers.common.configuration.SVConstants;
import com.alessiodp.securityvillagers.common.configuration.data.ConfigMain;
import com.alessiodp.securityvillagers.bukkit.villagers.objects.MobsType;
import com.alessiodp.securityvillagers.common.utils.WorldUtils;
import com.alessiodp.securityvillagers.common.villagers.VillagerManager;
import com.alessiodp.securityvillagers.common.villagers.objects.ProtectedEntity;
import lombok.NonNull;
import org.bukkit.Material;
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
import org.bukkit.projectiles.BlockProjectileSource;
import org.bukkit.projectiles.ProjectileSource;

import java.util.UUID;

public class BukkitVillagerManager extends VillagerManager {
	
	public BukkitVillagerManager(@NonNull SecurityVillagersPlugin plugin) {
		super(plugin);
	}
	
	@Override
	public void selectEntity(UUID selector, ProtectedEntity entity) {
		super.selectEntity(selector, entity);
		GlowHandler.glowEntity(entity, selector);
	}
	
	@Override
	public void unselectEntity(ProtectedEntity entity) {
		getSelectedEntities().forEach((s, e) -> {
			if (e.getUuid().equals(entity.getUuid()))
				GlowHandler.unglowEntity(e, s);
		});
		super.unselectEntity(entity);
	}
	
	@Override
	public void unselectEntityBySelector(UUID selector) {
		GlowHandler.unglowEntity(getSelectedEntities().get(selector), selector);
		super.unselectEntityBySelector(selector);
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
	public ProtectedEntityType getEntityType(UUID uuid) {
		Entity entity = ((ADPBukkitBootstrap) plugin.getBootstrap()).getServer().getEntity(uuid);
		return entity != null ? getEntityType(entity) : null;
	}
	
	@Override
	public ProtectedEntityType getEntityType(Object entity) {
		ProtectedEntityType ret = null;
		if (entity instanceof Villager) {
			ret = ProtectedEntityType.VILLAGER;
		} else if (entity instanceof IronGolem) {
			ret = ProtectedEntityType.IRON_GOLEM;
		} else if (((BukkitSecurityVillagersPlugin) plugin).getNmsManager().isMobInstanceOf(MobsType.WANDERING_TRADER, entity)) {
			ret = ProtectedEntityType.WANDERING_TRADER;
		} else if (((BukkitSecurityVillagersPlugin) plugin).getNmsManager().isMobInstanceOf(MobsType.TRADER_LLAMA, entity)) {
			ret = ProtectedEntityType.TRADER_LLAMA;
		} else if (((BukkitSecurityVillagersPlugin) plugin).getNmsManager().isMobInstanceOf(MobsType.EVOKER, entity)) {
			ret = ProtectedEntityType.EVOKER;
		} else if (((BukkitSecurityVillagersPlugin) plugin).getNmsManager().isMobInstanceOf(MobsType.ILLUSIONER, entity)) {
			ret = ProtectedEntityType.ILLUSIONER;
		} else if (((BukkitSecurityVillagersPlugin) plugin).getNmsManager().isMobInstanceOf(MobsType.PILLAGER, entity)) {
			ret = ProtectedEntityType.PILLAGER;
		} else if (((BukkitSecurityVillagersPlugin) plugin).getNmsManager().isMobInstanceOf(MobsType.RAVAGER, entity)) {
			ret = ProtectedEntityType.RAVAGER;
		} else if (((BukkitSecurityVillagersPlugin) plugin).getNmsManager().isMobInstanceOf(MobsType.VINDICATOR, entity)) {
			ret = ProtectedEntityType.VINDICATOR;
		} else if (((BukkitSecurityVillagersPlugin) plugin).getNmsManager().isMobInstanceOf(MobsType.VEX, entity)) {
			ret = ProtectedEntityType.VEX;
		} else if (entity instanceof Witch) {
			ret = ProtectedEntityType.WITCH;
		}
		return ret;
	}
	
	private boolean isUnprotected(ProtectedEntity protectedEntity, Entity attacker) {
		if (!isConfigProtected(protectedEntity.getType())) {
			// Entity not protected
			return true;
		}
		
		if (!WorldUtils.containsWorld(ConfigMain.GENERAL_DAMAGE_WORLDS, protectedEntity.getWorld())) {
			// Entity not protected in this world
			return true;
		}
		
		// Check if custom protected
		if (ConfigMain.GENERAL_PROTECTIONTYPE == ConfigMain.ProtectionType.CUSTOM
				&& !protectedEntity.isProtectionEnabled()) {
			return true;
		}
		
		// Check for factions
		if (ConfigMain.GENERAL_PROTECTIONTYPE == ConfigMain.ProtectionType.FACTIONS
				&& !FactionsHandler.isClaimProtectedByAttack(attacker, ((Entity) protectedEntity.getEntity()).getLocation())) {
			return true;
		}
		
		// Check for citizens
		if (ConfigMain.GENERAL_CITIZENS_ENABLE
				&& ConfigMain.GENERAL_CITIZENS_BYPASS_PROTECTION
				&& CitizensHandler.isNPC((Entity) protectedEntity.getEntity())) {
			return true;
		}
		
		// The entity is protected
		return false;
	}
	
	@Override
	public AttackResult canBeAttacked(ProtectedEntity protectedEntity, Object attacker) {
		if (isUnprotected(protectedEntity, (Entity) attacker))
			return AttackResult.SUCCESS;
		
		// Check if immortal
		if (ConfigMain.GENERAL_DAMAGE_IMMORTAL) {
			Player playerAttacker;
			if (attacker instanceof Projectile) {
				playerAttacker = ((Projectile) attacker).getShooter() instanceof Player ? (Player) ((Projectile) attacker).getShooter() : null;
			} else {
				playerAttacker = attacker instanceof Player ? (Player) attacker : null;
			}
			
			if (playerAttacker != null && playerAttacker.hasPermission(
					(attacker instanceof Projectile ? SecurityVillagersPermission.USER_SHOOT.toString() : SecurityVillagersPermission.USER_HIT).toString())
			) {
				return AttackResult.SUCCESS;
			}
			
			plugin.getLoggerManager().logDebug(String.format(SVConstants.DEBUG_PROTECTION_IMMORTAL,
					protectedEntity.getType().name()), true);
			
			return attacker instanceof Projectile ? AttackResult.SHOOT : AttackResult.HIT;
		}
		
		// Player attack
		if (attacker instanceof Player) {
			if (!canBeAttackedFromPlayer((Player) attacker, true)) {
				plugin.getLoggerManager().logDebug(String.format(SVConstants.DEBUG_PROTECTION_HIT_PLAYER,
						((Player) attacker).getName(),
						protectedEntity.getType().name()), true);
				
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
						plugin.getLoggerManager().logDebug(String.format(SVConstants.DEBUG_PROTECTION_PROJECTILE_PLAYER,
								((Player) shooter).getName(),
								protectedEntity.getType().name()), true);
						
						return AttackResult.SHOOT;
					}
				} else if (shooter instanceof Entity && !canBeAttackedFromMob((Entity) shooter)) {
					plugin.getLoggerManager().logDebug(String.format(SVConstants.DEBUG_PROTECTION_PROJECTILE_MOB,
							((Entity) shooter).getName(),
							protectedEntity.getType().name()), true);
					
					return AttackResult.SHOOT;
				} else if (shooter instanceof BlockProjectileSource) {
					if (((BlockProjectileSource) shooter).getBlock().getType() == Material.DISPENSER && ConfigMain.GENERAL_DAMAGE_ARROW_DISPENSER) {
						plugin.getLoggerManager().logDebug(String.format(SVConstants.DEBUG_PROTECTION_PROJECTILE_DISPENSER,
								protectedEntity.getType().name()), true);
						return AttackResult.SHOOT;
					}
				}
			}
		}
		
		// Attack from mob
		if (!canBeAttackedFromMob((Entity) attacker)) {
			plugin.getLoggerManager().logDebug(String.format(SVConstants.DEBUG_PROTECTION_HIT_MOB,
					((Entity) attacker).getName(),
					protectedEntity.getType().name()), true);
			
			return AttackResult.HIT;
		}
		return AttackResult.SUCCESS;
	}
	
	@Override
	public boolean canBeDamaged(ProtectedEntity protectedEntity, Object damageCause) {
		if (isUnprotected(protectedEntity, null))
			return true;
		
		// Check if immortal
		if (ConfigMain.GENERAL_DAMAGE_IMMORTAL) {
			plugin.getLoggerManager().logDebug(String.format(SVConstants.DEBUG_PROTECTION_IMMORTAL,
					protectedEntity.getType().name()), true);
			
			return false;
		}
		
		// Check damage cause
		if (damageCause != null) {
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
		
		return true;
	}
	
	private boolean canBeAttackedFromPlayer(Player player, boolean isMelee) {
		return !((isMelee ? ConfigMain.GENERAL_DAMAGE_HIT : ConfigMain.GENERAL_DAMAGE_ARROW)
				&& (!player.hasPermission((isMelee ? SecurityVillagersPermission.USER_HIT : SecurityVillagersPermission.USER_SHOOT).toString())));
	}
	
	private boolean canBeAttackedFromMob(Entity mob) {
		return !(
				(ConfigMain.DAMAGE_MOBS_EVOKER
						&& (((BukkitSecurityVillagersPlugin) plugin).getNmsManager().isMobInstanceOf(MobsType.EVOKER, mob)
						|| ((BukkitSecurityVillagersPlugin) plugin).getNmsManager().isMobInstanceOf(MobsType.EVOKER_FANGS, mob))) // Evoker
				|| (ConfigMain.DAMAGE_MOBS_ILLUSIONER && ((BukkitSecurityVillagersPlugin) plugin).getNmsManager().isMobInstanceOf(MobsType.ILLUSIONER, mob)) // Illusioner
				|| (ConfigMain.DAMAGE_MOBS_PILLAGER && ((BukkitSecurityVillagersPlugin) plugin).getNmsManager().isMobInstanceOf(MobsType.PILLAGER, mob)) // Pillager
				|| (ConfigMain.DAMAGE_MOBS_RAVAGER && ((BukkitSecurityVillagersPlugin) plugin).getNmsManager().isMobInstanceOf(MobsType.RAVAGER, mob)) // Ravager
				|| (ConfigMain.DAMAGE_MOBS_SKELETON && mob instanceof Skeleton) // Skeleton
				|| (ConfigMain.DAMAGE_MOBS_RAVAGER && ((BukkitSecurityVillagersPlugin) plugin).getNmsManager().isMobInstanceOf(MobsType.STRAY, mob)) // Skeleton
				|| (ConfigMain.DAMAGE_MOBS_VEX && ((BukkitSecurityVillagersPlugin) plugin).getNmsManager().isMobInstanceOf(MobsType.VEX, mob)) // Vex
				|| (ConfigMain.DAMAGE_MOBS_VINDICATOR && ((BukkitSecurityVillagersPlugin) plugin).getNmsManager().isMobInstanceOf(MobsType.VINDICATOR, mob)) // Vindicator
				|| (ConfigMain.DAMAGE_MOBS_WITCH && mob instanceof Witch) // Witch
				|| (ConfigMain.DAMAGE_MOBS_WITHER && (mob instanceof Wither || mob instanceof WitherSkull)) // Wither
				|| (ConfigMain.DAMAGE_MOBS_ZOGLIN && ((BukkitSecurityVillagersPlugin) plugin).getNmsManager().isMobInstanceOf(MobsType.ZOGLIN, mob)) // Zombie
				|| (ConfigMain.DAMAGE_MOBS_ZOMBIE && mob instanceof Zombie) // Zombie
		);
	}
}
