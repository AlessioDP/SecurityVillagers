package com.alessiodp.securityvillagers.tests.listeners;

import com.alessiodp.securityvillagers.SecurityVillagers;
import com.alessiodp.securityvillagers.configuration.data.ConfigMain;
import com.alessiodp.securityvillagers.configuration.data.Messages;
import com.alessiodp.securityvillagers.listeners.DamageListener;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.IronGolem;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.entity.Zombie;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.ArrayList;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.powermock.api.mockito.PowerMockito.mock;
import static org.powermock.api.mockito.PowerMockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest({SecurityVillagers.class, EntityDamageByEntityEvent.class, EntityDamageEvent.class})
public class DamageListenerTest {
	@Test
	public void testOnEntityDamageByEntity() {
		SecurityVillagers mockPlugin = mock(SecurityVillagers.class);
		EntityDamageByEntityEvent mockEvent = mock(EntityDamageByEntityEvent.class);
		Villager mockEntity1 = mock(Villager.class);
		LivingEntity mockEntity2 = mock(LivingEntity.class);
		IronGolem mockEntity3 = mock(IronGolem.class);
		Player mockDamager1 = mock(Player.class);
		Arrow mockDamager2 = mock(Arrow.class);
		Zombie mockDamager3 = mock(Zombie.class);
		
		// Initialize listener
		DamageListener damageListener = new DamageListener(mockPlugin);
		
		// Plugin configuration
		ConfigMain.GENERAL_DAMAGE_HIT = true;
		ConfigMain.GENERAL_DAMAGE_ARROW = true;
		ConfigMain.GENERAL_DAMAGE_WORLDS = new ArrayList<>();
		ConfigMain.GENERAL_DAMAGE_WORLDS.add("*"); // Necessary to avoid NullPointerException on entity get world
		ConfigMain.DAMAGE_MOBS_ZOMBIE = true;
		ConfigMain.IRONGOLEM_PROTECT = true;
		Messages.INTERACT_HIT = "";
		Messages.INTERACT_SHOOT = "";
		
		// Test 1 (true): Villager & Player hit
		when(mockEvent.getEntity()).thenReturn(mockEntity1);
		when(mockEvent.getDamager()).thenReturn(mockDamager1);
		damageListener.onEntityDamageByEntity(mockEvent);
		verify(mockEvent, times(1)).setCancelled(true);
		
		// Test 2 (true): Villager & Player shoot
		when(mockEvent.getEntity()).thenReturn(mockEntity1);
		when(mockEvent.getDamager()).thenReturn(mockDamager2);
		when(mockDamager2.getShooter()).thenReturn(mockDamager1);
		damageListener.onEntityDamageByEntity(mockEvent);
		verify(mockEvent, times(2)).setCancelled(true);
		
		// Test 3 (true): Villager & Zombie
		when(mockEvent.getEntity()).thenReturn(mockEntity1);
		when(mockEvent.getDamager()).thenReturn(mockDamager3);
		damageListener.onEntityDamageByEntity(mockEvent);
		verify(mockEvent, times(3)).setCancelled(true);
		
		// Test 4 (false): LivingEntity & Player hit
		when(mockEvent.getEntity()).thenReturn(mockEntity2);
		when(mockEvent.getDamager()).thenReturn(mockDamager1);
		damageListener.onEntityDamageByEntity(mockEvent);
		verify(mockEvent, times(3)).setCancelled(true);
		
		// Test 5 (true): IronGolem & Player hit
		when(mockEvent.getEntity()).thenReturn(mockEntity3);
		when(mockEvent.getDamager()).thenReturn(mockDamager1);
		damageListener.onEntityDamageByEntity(mockEvent);
		verify(mockEvent, times(4)).setCancelled(true);
	}
	
	@Test
	public void testOnEntityDamage() {
		EntityDamageEvent mockEvent = mock(EntityDamageEvent.class);
		Villager mockEntity1 = mock(Villager.class);
		IronGolem mockEntity2 = mock(IronGolem.class);
		EntityDamageEvent.DamageCause cause;
		
		// Initialize listener
		DamageListener damageListener = new DamageListener(null);
		
		// Plugin configuration
		ConfigMain.GENERAL_DAMAGE_WORLDS = new ArrayList<>();
		ConfigMain.GENERAL_DAMAGE_WORLDS.add("*"); // Necessary to avoid NullPointerException on entity get world
		ConfigMain.IRONGOLEM_PROTECT = true;
		ConfigMain.DAMAGE_OTHER_FIRE = true;
		ConfigMain.DAMAGE_OTHER_DROWNING = true;
		ConfigMain.DAMAGE_OTHER_POISON = true;
		ConfigMain.DAMAGE_OTHER_FALL = false;
		
		// Test 1 (true):  Villager & fire
		cause = EntityDamageEvent.DamageCause.FIRE;
		when(mockEvent.getEntity()).thenReturn(mockEntity1);
		when(mockEvent.getCause()).thenReturn(cause);
		damageListener.onEntityDamage(mockEvent);
		verify(mockEvent, times(1)).setCancelled(true);
		
		// Test 2 (true):  Villager & drowning
		cause = EntityDamageEvent.DamageCause.DROWNING;
		when(mockEvent.getEntity()).thenReturn(mockEntity1);
		when(mockEvent.getCause()).thenReturn(cause);
		damageListener.onEntityDamage(mockEvent);
		verify(mockEvent, times(2)).setCancelled(true);
		
		// Test 3 (true):  Villager & poison
		cause = EntityDamageEvent.DamageCause.POISON;
		when(mockEvent.getEntity()).thenReturn(mockEntity1);
		when(mockEvent.getCause()).thenReturn(cause);
		damageListener.onEntityDamage(mockEvent);
		verify(mockEvent, times(3)).setCancelled(true);
		
		// Test 4 (false):  Villager & fall
		cause = EntityDamageEvent.DamageCause.FALL;
		when(mockEvent.getEntity()).thenReturn(mockEntity1);
		when(mockEvent.getCause()).thenReturn(cause);
		damageListener.onEntityDamage(mockEvent);
		verify(mockEvent, times(3)).setCancelled(true);
		
		// Test 5 (true):  IronGolem & fire
		cause = EntityDamageEvent.DamageCause.FIRE;
		when(mockEvent.getEntity()).thenReturn(mockEntity2);
		when(mockEvent.getCause()).thenReturn(cause);
		damageListener.onEntityDamage(mockEvent);
		verify(mockEvent, times(4)).setCancelled(true);
		
		// Test 6 (false):  IronGolem & fire
		ConfigMain.IRONGOLEM_PROTECT = false;
		cause = EntityDamageEvent.DamageCause.FIRE;
		when(mockEvent.getEntity()).thenReturn(mockEntity2);
		when(mockEvent.getCause()).thenReturn(cause);
		damageListener.onEntityDamage(mockEvent);
		verify(mockEvent, times(4)).setCancelled(true);
	}
}
