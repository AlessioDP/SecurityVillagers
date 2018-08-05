package com.alessiodp.securityvillagers.utils;

import com.alessiodp.securityvillagers.addons.external.FactionsHandler;
import com.alessiodp.securityvillagers.configuration.Constants;
import com.alessiodp.securityvillagers.configuration.data.ConfigMain;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.IronGolem;
import org.bukkit.entity.Player;
import org.bukkit.entity.Skeleton;
import org.bukkit.entity.Villager;
import org.bukkit.entity.Wither;
import org.bukkit.entity.WitherSkull;
import org.bukkit.entity.Zombie;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SpawnEggMeta;
import org.bukkit.projectiles.ProjectileSource;

public class VillagersUtils {
	
	/*
	 * Check if the villager can be attacked
	 */
	public static AttackBlockResult canBeAttacked(Villager villager, Entity from) {
		AttackBlockResult ret = new AttackBlockResult();
		
		// Check if is protected
		if (!ConfigMain.PREVENTIONS_PROTECTIONTYPE.equalsIgnoreCase("global")) {
			if (!villager.hasMetadata(Constants.PROTECT_METADATA)) {
				ret.setResult(AttackBlockResult.AttackResult.SUCCESS);
				return ret;
			}
		}
		
		// Player attack
		if (from instanceof Player) {
			if (!canBeAttackedFromPlayer(villager, (Player) from)) {
				ret.setDamager(from);
				ret.setResult(AttackBlockResult.AttackResult.HIT);
			}
		}
		
		// Arrow from player attack
		if (from instanceof Arrow) {
			ProjectileSource shooter = ((Arrow) from).getShooter();
			if (shooter instanceof Player) {
				if (!canBeAttackedFromPlayerArrow(villager, (Player) shooter)) {
					ret.setDamager((Entity) shooter);
					ret.setResult(AttackBlockResult.AttackResult.SHOOT);
				}
			} else if (shooter instanceof Skeleton) {
				if (!canBeAttackedFromMob(villager, (Entity) shooter)) {
					ret.setDamager((Entity) shooter);
					ret.setResult(AttackBlockResult.AttackResult.SHOOT);
				}
			}
		}
		
		// Other mobs
		if (!canBeAttackedFromMob(villager, from)) {
			ret.setDamager(from);
			ret.setResult(AttackBlockResult.AttackResult.HIT);
		}
		
		return ret;
	}
	public static AttackBlockResult canBeAttacked(IronGolem golem, Entity from) {
		AttackBlockResult ret = new AttackBlockResult();
		
		// Player attack
		if (from instanceof Player) {
			if (!canBeAttackedFromPlayer(golem, (Player) from)){
				ret.setDamager(from);
				ret.setResult(AttackBlockResult.AttackResult.HIT);
			}
		}
		
		// Arrow from player attack
		if (from instanceof Arrow) {
			ProjectileSource shooter = ((Arrow) from).getShooter();
			if (shooter instanceof Player) {
				if (!canBeAttackedFromPlayerArrow(golem, (Player) shooter)) {
					ret.setDamager((Entity) shooter);
					ret.setResult(AttackBlockResult.AttackResult.SHOOT);
				}
			} else if (shooter instanceof Skeleton) {
				if (!canBeAttackedFromMob(golem, (Entity) shooter)) {
					ret.setDamager((Entity) shooter);
					ret.setResult(AttackBlockResult.AttackResult.SHOOT);
				}
			}
		}
		
		// Other mobs
		if (!canBeAttackedFromMob(golem, from)) {
			ret.setDamager(from);
			ret.setResult(AttackBlockResult.AttackResult.HIT);
		}
		
		return ret;
	}
	
	private static boolean canBeAttackedFromPlayer(Entity entity, Player player) {
		boolean ret = true;
		
		// Factions check
		if (ConfigMain.FACTIONS_ENABLE && ConfigMain.FACTIONS_PREVENT_HIT
			&& !player.hasPermission(SVPermission.ADMIN_BYPASS_FACTIONS.toString())) {
			if (!FactionsHandler.canHit(player, entity.getLocation())) {
				ret = false;
			}
		}
		
		if (ConfigMain.GENERAL_DAMAGE_HIT) {
			if (ConfigMain.GENERAL_DAMAGE_WORLDS.contains("*")
					|| ConfigMain.GENERAL_DAMAGE_WORLDS.contains(entity.getLocation().getWorld().getName())) {
				if (!player.hasPermission(SVPermission.HIT.toString())) {
					ret = false;
				}
			}
		}
		return ret;
	}
	private static boolean canBeAttackedFromPlayerArrow(Entity entity, Player player) {
		boolean ret = true;
		
		// Factions check
		if (ConfigMain.FACTIONS_ENABLE && ConfigMain.FACTIONS_PREVENT_HIT
				&& !player.hasPermission(SVPermission.ADMIN_BYPASS_FACTIONS.toString())) {
			if (!FactionsHandler.canHit(player, entity.getLocation())) {
				ret = false;
			}
		}
		
		if (ConfigMain.GENERAL_DAMAGE_ARROW) {
			if (ConfigMain.GENERAL_DAMAGE_WORLDS.contains("*")
					|| ConfigMain.GENERAL_DAMAGE_WORLDS.contains(entity.getLocation().getWorld().getName())) {
				if (!player.hasPermission(SVPermission.SHOOT.toString())) {
					ret = false;
				}
			}
		}
		return ret;
	}
	private static boolean canBeAttackedFromMob(Entity entity, Entity mob) {
		boolean ret = true;
		
		/* Annotation:
		 *    Useful for ranged mobs
		if (mob instanceof Projectile) {
			ret = canBeAttackedFromMob(entity, (Entity) ((Projectile) mob).getShooter());
		}
		*/
		
		// Skeleton
		if (mob instanceof Skeleton && ConfigMain.DAMAGE_MOBS_SKELETON && isPreventedWorld(entity)) {
			ret = false;
		}
		// Wither
		if ((mob instanceof Wither || mob instanceof WitherSkull) && ConfigMain.DAMAGE_MOBS_WITHER && isPreventedWorld(entity)) {
			ret = false;
		}
		// Zombie
		if (mob instanceof Zombie && ConfigMain.DAMAGE_MOBS_ZOMBIE && isPreventedWorld(entity)) {
			ret = false;
		}
		
		return ret;
	}
	private static boolean isPreventedWorld(Entity entity) {
		// Returns true if world is protected
		boolean ret = false;
		if (ConfigMain.GENERAL_DAMAGE_WORLDS.contains("*")
				|| ConfigMain.GENERAL_DAMAGE_WORLDS.contains(entity.getLocation().getWorld().getName())) {
			ret = true;
		}
		return ret;
	}
	
	public static boolean canBeAttackedFromOther(Entity entity, EntityDamageEvent.DamageCause cause) {
		// Villager & IronGolem
		boolean ret = true;
		if (isPreventedWorld(entity)) {
			switch (cause) {
				case CONTACT:
					if (ConfigMain.DAMAGE_OTHER_CONTACT)
						ret = false;
					break;
				case DRAGON_BREATH:
					if (ConfigMain.DAMAGE_OTHER_DRAGON)
						ret = false;
				case DROWNING:
					if (ConfigMain.DAMAGE_OTHER_DROWNING)
						ret = false;
					break;
				case BLOCK_EXPLOSION:
				case ENTITY_EXPLOSION:
					if (ConfigMain.DAMAGE_OTHER_EXPLOSION)
						ret = false;
					break;
				case FALL:
					if (ConfigMain.DAMAGE_OTHER_FALL)
						ret = false;
					break;
				case FALLING_BLOCK:
					if (ConfigMain.DAMAGE_OTHER_FALLINGBLOCK)
						ret = false;
					break;
				case FIRE:
				case FIRE_TICK:
					if (ConfigMain.DAMAGE_OTHER_FIRE)
						ret = false;
					break;
				case HOT_FLOOR:
					if (ConfigMain.DAMAGE_OTHER_HOTFLOOR)
						ret = false;
				case LAVA:
					if (ConfigMain.DAMAGE_OTHER_LAVA)
						ret = false;
					break;
				case LIGHTNING:
					if (ConfigMain.DAMAGE_OTHER_LIGHTNING)
						ret = false;
					break;
				case MAGIC:
					if (ConfigMain.DAMAGE_OTHER_MAGIC)
						ret = false;
					break;
				case MELTING:
					if (ConfigMain.DAMAGE_OTHER_MELTING)
						ret = false;
					break;
				case POISON:
					if (ConfigMain.DAMAGE_OTHER_POISON)
						ret = false;
					break;
				case SUFFOCATION:
					if (ConfigMain.DAMAGE_OTHER_SUFFOCATION)
						ret = false;
					break;
				case THORNS:
					if (ConfigMain.DAMAGE_OTHER_THORNS)
						ret = false;
					break;
				case WITHER:
					if (ConfigMain.DAMAGE_MOBS_WITHER)
						ret = false;
				case VOID:
					if (ConfigMain.DAMAGE_OTHER_VOID)
						ret = false;
				case CRAMMING:
				case CUSTOM:
				case ENTITY_ATTACK:
				case ENTITY_SWEEP_ATTACK:
				case FLY_INTO_WALL:
				case PROJECTILE:
				case SUICIDE:
				case STARVATION:
					// Nothing
			}
		}
		return ret;
	}
	
	public static boolean isVillagerEgg(ItemStack itemStack) {
		boolean ret = false;
		Material villagerEgg = MaterialUtils.getMaterial("VILLAGER_SPAWN_EGG", "MONSTER_EGG");
		boolean useNewApi = Bukkit.getVersion().contains("1.13");
		if (useNewApi) {
			// New Bukkit API 1.13
			if (itemStack.getType().equals(villagerEgg)) {
				ret = true;
			}
		} else {
			// Old API
			if (itemStack.getType().equals(villagerEgg)) {
				if (itemStack.getItemMeta() instanceof SpawnEggMeta) {
					if (((SpawnEggMeta) itemStack.getItemMeta()).getSpawnedType() != null) {
						if (((SpawnEggMeta) itemStack.getItemMeta()).getSpawnedType().equals(EntityType.VILLAGER)) {
							ret = true;
						}
					}
				}
			}
		}
		return ret;
	}
}
