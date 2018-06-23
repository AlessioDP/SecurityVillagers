package com.alessiodp.securityvillagers.tests;

import com.alessiodp.securityvillagers.configuration.data.ConfigMain;
import com.alessiodp.securityvillagers.utils.AttackBlockResult;
import com.alessiodp.securityvillagers.utils.VillagersUtils;
import org.bukkit.Material;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.IronGolem;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.entity.Wither;
import org.bukkit.entity.Zombie;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SpawnEggMeta;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.powermock.api.mockito.PowerMockito.mock;
import static org.powermock.api.mockito.PowerMockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest(VillagersUtils.class)
public class VillagersUtilsTest {
	@Test
	public void testCanBeAttacked() {
		Villager mockVillager = mock(Villager.class);
		IronGolem mockIronGolem = mock(IronGolem.class);
		Player mockFrom1 = mock(Player.class);
		Arrow mockFrom2 = mock(Arrow.class);
		Zombie mockFrom3 = mock(Zombie.class);
		Wither mockFrom4 = mock(Wither.class);
		
		// Initialize class
		AttackBlockResult result;
		
		// Plugin configuration
		ConfigMain.GENERAL_DAMAGE_HIT = true;
		ConfigMain.GENERAL_DAMAGE_ARROW = true;
		ConfigMain.GENERAL_DAMAGE_WORLDS = new ArrayList<>();
		ConfigMain.GENERAL_DAMAGE_WORLDS.add("*"); // Necessary to avoid NullPointerException on entity get world
		ConfigMain.DAMAGE_MOBS_ZOMBIE = true;
		ConfigMain.DAMAGE_MOBS_WITHER = false;
		
		// Global stubs
		when(mockFrom2.getShooter()).thenReturn(mockFrom1);
		
		// Test 1 (true): Player
		result = VillagersUtils.canBeAttacked(mockVillager, mockFrom1);
		assertEquals(AttackBlockResult.AttackResult.HIT, result.getResult());
		
		// Test 2 (true): Player arrow
		result = VillagersUtils.canBeAttacked(mockVillager, mockFrom2);
		assertEquals(AttackBlockResult.AttackResult.SHOOT, result.getResult());
		
		// Test 3 (true): Zombie
		result = VillagersUtils.canBeAttacked(mockVillager, mockFrom3);
		assertEquals(AttackBlockResult.AttackResult.HIT, result.getResult());
		
		// Test 4 (false): Wither
		result = VillagersUtils.canBeAttacked(mockVillager, mockFrom4);
		assertEquals(AttackBlockResult.AttackResult.SUCCESS, result.getResult());
		
		// Test 6 (true): Player vs IronGolem
		result = VillagersUtils.canBeAttacked(mockIronGolem, mockFrom1);
		assertEquals(AttackBlockResult.AttackResult.HIT, result.getResult());
	}
	
	@Test
	public void testIsVillagerEgg() {
		ItemStack mockItem = mock(ItemStack.class);
		SpawnEggMeta mockItemMeta = mock(SpawnEggMeta.class);
		
		// Global stubs
		when(mockItem.getItemMeta()).thenReturn(mockItemMeta);
		
		// Test 1 (true): Villager egg
		when(mockItem.getType()).thenReturn(Material.MONSTER_EGG);
		when(mockItemMeta.getSpawnedType()).thenReturn(EntityType.VILLAGER);
		assertTrue(VillagersUtils.isVillagerEgg(mockItem));
		
		// Test 2 (false): Zombie egg
		when(mockItem.getType()).thenReturn(Material.MONSTER_EGG);
		when(mockItemMeta.getSpawnedType()).thenReturn(EntityType.ZOMBIE);
		assertFalse(VillagersUtils.isVillagerEgg(mockItem));
		
		// Test 3 (false): Null egg
		when(mockItem.getType()).thenReturn(Material.MONSTER_EGG);
		when(mockItemMeta.getSpawnedType()).thenReturn(null);
		assertFalse(VillagersUtils.isVillagerEgg(mockItem));
		
		// Test 4 (false): Blaze rod
		when(mockItem.getType()).thenReturn(Material.BLAZE_ROD);
		assertFalse(VillagersUtils.isVillagerEgg(mockItem));
	}
}
