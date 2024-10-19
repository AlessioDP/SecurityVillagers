package com.alessiodp.securityvillagers.bukkit;

import com.alessiodp.core.common.logging.LoggerManager;
import com.alessiodp.securityvillagers.api.enums.AttackResult;
import com.alessiodp.securityvillagers.bukkit.utils.NMSManager;
import com.alessiodp.securityvillagers.bukkit.villagers.BukkitVillagerManager;
import com.alessiodp.securityvillagers.common.SecurityVillagersPlugin;
import com.alessiodp.securityvillagers.common.configuration.data.ConfigMain;
import com.alessiodp.securityvillagers.common.villagers.objects.ProtectedEntity;
import org.bukkit.World;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.IronGolem;
import org.bukkit.entity.Player;
import org.bukkit.entity.Skeleton;
import org.bukkit.entity.Villager;
import org.bukkit.entity.Zombie;
import org.bukkit.event.entity.EntityDamageEvent;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class VillagerManagerTest {
	private static final BukkitSecurityVillagersPlugin mockPlugin = mock(BukkitSecurityVillagersPlugin.class);
	private static final BukkitVillagerManager villagerManager = new BukkitVillagerManager(mockPlugin);
	private static final World mockWorld = mock(World.class);
	
	@BeforeAll
	public static void setUp() {
		LoggerManager mockLoggerManager = mock(LoggerManager.class);
		when(mockPlugin.getLoggerManager()).thenReturn(mockLoggerManager);
		when(mockWorld.getName()).thenReturn("world");
		
		NMSManager mockNmsManager = mock(NMSManager.class);
		when(mockPlugin.getNmsManager()).thenReturn(mockNmsManager);
		when(mockNmsManager.isMobInstanceOf(any(), anyString())).thenReturn(false);
	}
	
	@BeforeEach
	public void setUpEach() {
		ConfigMain.GENERAL_PROTECTIONTYPE = ConfigMain.ProtectionType.GLOBAL;
		ConfigMain.GENERAL_DAMAGE_IMMORTAL = false;
		ConfigMain.GENERAL_DAMAGE_WORLDS = Collections.singletonList("*");
		ConfigMain.GENERAL_DAMAGE_HIT = true;
		ConfigMain.GENERAL_DAMAGE_ARROW = true;
		ConfigMain.MOBS_VILLAGER_PROTECT = true;
	}
	
	@Test
	public void testInitializeProtectedEntity() {
		Player mockPlayer = mock(Player.class);
		Villager mockVillager = mock(Villager.class);
		IronGolem mockIronGolem = mock(IronGolem.class);
		
		assertNull(villagerManager.initializeProtectedEntity(mockPlayer));
		assertNotNull(villagerManager.initializeProtectedEntity(mockVillager));
		assertNotNull(villagerManager.initializeProtectedEntity(mockIronGolem));
	}
	
	@Test
	public void testCanBeAttackedByPlayerMelee() {
		Villager mockVillager = mock(Villager.class);
		Player mockPlayer = mock(Player.class);
		
		when(mockVillager.getWorld()).thenReturn(mockWorld);
		when(mockPlayer.getWorld()).thenReturn(mockWorld);
		
		when(mockPlayer.hasPermission(anyString())).thenReturn(false);
		when(mockPlayer.getName()).thenReturn("");
		
		ProtectedEntity protectedVillager = villagerManager.initializeProtectedEntity(mockVillager);
		
		// Melee
		assertEquals(AttackResult.HIT, villagerManager.canBeAttacked(protectedVillager, mockPlayer));
		// Melee bypass thanks to permission
		when(mockPlayer.hasPermission(anyString())).thenReturn(true);
		assertEquals(AttackResult.SUCCESS, villagerManager.canBeAttacked(protectedVillager, mockPlayer));
		// Villager unprotected
		when(mockPlayer.hasPermission(anyString())).thenReturn(false);
		ConfigMain.MOBS_VILLAGER_PROTECT = false;
		assertEquals(AttackResult.SUCCESS, villagerManager.canBeAttacked(protectedVillager, mockPlayer));
		// No world protection
		ConfigMain.MOBS_VILLAGER_PROTECT = true;
		ConfigMain.GENERAL_DAMAGE_WORLDS = Collections.singletonList("");
		assertEquals(AttackResult.SUCCESS, villagerManager.canBeAttacked(protectedVillager, mockPlayer));
	}
	
	@Test
	public void testCanBeAttackedByPlayerArrow() {
		Villager mockVillager = mock(Villager.class);
		Player mockPlayer = mock(Player.class);
		Arrow mockPlayerArrow = mock(Arrow.class);
		
		when(mockVillager.getWorld()).thenReturn(mockWorld);
		when(mockPlayer.getWorld()).thenReturn(mockWorld);
		
		when(mockPlayerArrow.getShooter()).thenReturn(mockPlayer);
		when(mockPlayer.hasPermission(anyString())).thenReturn(false);
		when(mockPlayer.getName()).thenReturn("");
		
		ProtectedEntity protectedVillager = villagerManager.initializeProtectedEntity(mockVillager);
		
		// Shoot
		assertEquals(AttackResult.SHOOT, villagerManager.canBeAttacked(protectedVillager, mockPlayerArrow));
		// Shoot bypass thanks to permission
		when(mockPlayer.hasPermission(anyString())).thenReturn(true);
		assertEquals(AttackResult.SUCCESS, villagerManager.canBeAttacked(protectedVillager, mockPlayerArrow));
		// No world protection
		when(mockPlayer.hasPermission(anyString())).thenReturn(false);
		ConfigMain.GENERAL_DAMAGE_WORLDS = Collections.singletonList("");
		assertEquals(AttackResult.SUCCESS, villagerManager.canBeAttacked(protectedVillager, mockPlayerArrow));
	}
	
	@Test
	public void testCanBeAttackedByZombie() {
		Villager mockVillager = mock(Villager.class);
		Zombie mockZombie = mock(Zombie.class);
		
		ConfigMain.DAMAGE_MOBS_ZOMBIE = true;
		
		when(mockVillager.getWorld()).thenReturn(mockWorld);
		when(mockZombie.getWorld()).thenReturn(mockWorld);
		
		when(mockZombie.getName()).thenReturn("");
		
		ProtectedEntity protectedVillager = villagerManager.initializeProtectedEntity(mockVillager);
		
		// Protected
		assertEquals(AttackResult.HIT, villagerManager.canBeAttacked(protectedVillager, mockZombie));
		// Unprotected
		ConfigMain.DAMAGE_MOBS_ZOMBIE = false;
		assertEquals(AttackResult.SUCCESS, villagerManager.canBeAttacked(protectedVillager, mockZombie));
		// No world protection
		ConfigMain.DAMAGE_MOBS_ZOMBIE = true;
		ConfigMain.GENERAL_DAMAGE_WORLDS = Collections.singletonList("");
		assertEquals(AttackResult.SUCCESS, villagerManager.canBeAttacked(protectedVillager, mockZombie));
	}
	
	@Test
	public void testCanBeAttackedBySkeleton() {
		Villager mockVillager = mock(Villager.class);
		Skeleton mockSkeleton = mock(Skeleton.class);
		Arrow mockArrow = mock(Arrow.class);
		
		ConfigMain.DAMAGE_MOBS_SKELETON = true;
		
		when(mockVillager.getWorld()).thenReturn(mockWorld);
		when(mockSkeleton.getWorld()).thenReturn(mockWorld);
		
		when(mockSkeleton.getName()).thenReturn("");
		when(mockArrow.getShooter()).thenReturn(mockSkeleton);
		
		ProtectedEntity protectedVillager = villagerManager.initializeProtectedEntity(mockVillager);
		
		// Protected
		assertEquals(AttackResult.SHOOT, villagerManager.canBeAttacked(protectedVillager, mockArrow));
		// Unprotected
		ConfigMain.DAMAGE_MOBS_SKELETON = false;
		assertEquals(AttackResult.SUCCESS, villagerManager.canBeAttacked(protectedVillager, mockArrow));
		// No world protection
		ConfigMain.DAMAGE_MOBS_SKELETON = true;
		ConfigMain.GENERAL_DAMAGE_WORLDS = Collections.singletonList("");
		assertEquals(AttackResult.SUCCESS, villagerManager.canBeAttacked(protectedVillager, mockArrow));
	}
	
	@Test
	public void testCanBeDamaged() {
		Villager mockVillager = mock(Villager.class);
		
		ConfigMain.DAMAGE_OTHER_FIRE = true;
		ConfigMain.DAMAGE_OTHER_LAVA = false;
		
		when(mockVillager.getWorld()).thenReturn(mockWorld);
		
		ProtectedEntity protectedVillager = villagerManager.initializeProtectedEntity(mockVillager);
		
		// Protected
		assertFalse(villagerManager.canBeDamaged(protectedVillager, EntityDamageEvent.DamageCause.FIRE));
		// Unprotected
		assertTrue(villagerManager.canBeDamaged(protectedVillager, EntityDamageEvent.DamageCause.LAVA));
		// No world protection
		ConfigMain.GENERAL_DAMAGE_WORLDS = Collections.singletonList("");
		assertTrue(villagerManager.canBeDamaged(protectedVillager, EntityDamageEvent.DamageCause.FIRE));
	}
}
