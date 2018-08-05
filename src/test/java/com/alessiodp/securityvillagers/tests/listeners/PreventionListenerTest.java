package com.alessiodp.securityvillagers.tests.listeners;

import com.alessiodp.securityvillagers.configuration.data.ConfigMain;
import com.alessiodp.securityvillagers.listeners.PreventionListener;
import org.bukkit.entity.Entity;
import org.bukkit.entity.IronGolem;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Villager;
import org.bukkit.entity.Zombie;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.event.entity.EntityTargetLivingEntityEvent;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.powermock.api.mockito.PowerMockito.mock;
import static org.powermock.api.mockito.PowerMockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest({EntityTargetLivingEntityEvent.class, EntitySpawnEvent.class})
public class PreventionListenerTest {
	@Test
	public void testOnEntityTarget() {
		EntityTargetLivingEntityEvent mockEvent = mock(EntityTargetLivingEntityEvent.class);
		Villager mockTarget1 = mock(Villager.class);
		LivingEntity mockTarget2 = mock(LivingEntity.class);
		Zombie mockEntity = mock(Zombie.class);
		
		// Initialize listener
		PreventionListener preventionListener = new PreventionListener(null);
		
		// Plugin configuration
		ConfigMain.PREVENTIONS_TARGET = true;
		ConfigMain.PREVENTIONS_PROTECTIONTYPE = "global";
		ConfigMain.GENERAL_DAMAGE_WORLDS = new ArrayList<>();
		ConfigMain.GENERAL_DAMAGE_WORLDS.add("*"); // Necessary to avoid NullPointerException on entity get world
		ConfigMain.DAMAGE_MOBS_ZOMBIE = true;
		
		// Test 1 (true): Villager & Zombie
		when(mockEvent.getTarget()).thenReturn(mockTarget1);
		when(mockEvent.getEntity()).thenReturn(mockEntity);
		preventionListener.onEntityTarget(mockEvent);
		verify(mockEvent, times(1)).setCancelled(true);
		
		// Test 2 (false): LivingEntity & Zombie
		when(mockEvent.getTarget()).thenReturn(mockTarget2);
		when(mockEvent.getEntity()).thenReturn(mockEntity);
		preventionListener.onEntityTarget(mockEvent);
		verify(mockEvent, times(1)).setCancelled(true);
		
		// Test 3 (true): Villager & Zombie (custom protection on)
		ConfigMain.PREVENTIONS_PROTECTIONTYPE = "custom";
		when(mockEvent.getTarget()).thenReturn(mockTarget1);
		when(mockEvent.getEntity()).thenReturn(mockEntity);
		when(mockTarget1.hasMetadata(any())).thenReturn(true);
		preventionListener.onEntityTarget(mockEvent);
		verify(mockEvent, times(2)).setCancelled(true);
		
		// Test 4 (false): Villager & Zombie (custom protection off)
		when(mockEvent.getTarget()).thenReturn(mockTarget1);
		when(mockEvent.getEntity()).thenReturn(mockEntity);
		when(mockTarget1.hasMetadata(any())).thenReturn(false);
		preventionListener.onEntityTarget(mockEvent);
		verify(mockEvent, times(2)).setCancelled(true);
	}
	
	@Test
	public void testOnEntitySpawn() {
		EntitySpawnEvent mockEvent = mock(EntitySpawnEvent.class);
		Villager mockEntity1 = mock(Villager.class);
		IronGolem mockEntity2 = mock(IronGolem.class);
		Entity mockEntity3 = mock(Entity.class);
		
		// Initialize listener
		PreventionListener preventionListener = new PreventionListener(null);
		
		// Plugin configuration
		ConfigMain.PREVENTIONS_SPAWN = true;
		ConfigMain.IRONGOLEM_PREVENTSPAWN = true;
		
		// Test 1 (true):  Villager
		when(mockEvent.getEntity()).thenReturn(mockEntity1);
		preventionListener.onEntitySpawn(mockEvent);
		verify(mockEvent, times(1)).setCancelled(true);
		
		// Test 2 (true):  IronGolem
		when(mockEvent.getEntity()).thenReturn(mockEntity2);
		preventionListener.onEntitySpawn(mockEvent);
		verify(mockEvent, times(2)).setCancelled(true);
		
		// Test 3 (false):  Entity
		when(mockEvent.getEntity()).thenReturn(mockEntity3);
		preventionListener.onEntitySpawn(mockEvent);
		verify(mockEvent, times(2)).setCancelled(true);
	}
}
