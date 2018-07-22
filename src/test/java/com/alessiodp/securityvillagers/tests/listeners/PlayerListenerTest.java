package com.alessiodp.securityvillagers.tests.listeners;

import com.alessiodp.securityvillagers.SecurityVillagers;
import com.alessiodp.securityvillagers.configuration.data.ConfigMain;
import com.alessiodp.securityvillagers.configuration.data.Messages;
import com.alessiodp.securityvillagers.handlers.VillagerHandler;
import com.alessiodp.securityvillagers.listeners.PlayerListener;
import com.alessiodp.securityvillagers.utils.MaterialUtils;
import com.alessiodp.securityvillagers.utils.SVPermission;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.powermock.api.mockito.PowerMockito.mock;
import static org.powermock.api.mockito.PowerMockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest({
		SecurityVillagers.class,
		PlayerInteractEntityEvent.class,
		PlayerInteractEvent.class,
		Bukkit.class,
		MaterialUtils.class
})
public class PlayerListenerTest {
	@Test
	public void testOnPlayerInteractEntity() {
		PowerMockito.mockStatic(Bukkit.class);
		PowerMockito.mockStatic(MaterialUtils.class);
		SecurityVillagers mockPlugin = mock(SecurityVillagers.class);
		VillagerHandler mockVillagerHandler = mock(VillagerHandler.class);
		PlayerInteractEntityEvent mockEvent = mock(PlayerInteractEntityEvent.class);
		Player mockPlayer = mock(Player.class);
		PlayerInventory mockInventory = mock(PlayerInventory.class);
		ItemStack mockItem = mock(ItemStack.class);
		Villager mockEntity1 = mock(Villager.class);
		Entity mockEntity2 = mock(Entity.class);
		
		// Initialize listener
		PlayerListener playerListener = new PlayerListener(mockPlugin);
		
		// Plugin configuration
		ConfigMain.SECURITYVILLAGERS_SELECTION_ENABLE = true;
		ConfigMain.SECURITYVILLAGERS_SELECTION_ITEM = Material.BLAZE_ROD;
		Messages.SELECTION_SELECTED = "";
		ConfigMain.CHANGEAGE_ENABLE = true;
		ConfigMain.CHANGEAGE_ITEM_ENABLE = true;
		ConfigMain.CHANGEAGE_ITEM_ITEM = Material.COMPASS;
		ConfigMain.CHANGEAGE_ALLOWEDWORLDS = new ArrayList<>();
		ConfigMain.CHANGEAGE_ALLOWEDWORLDS.add("*");
		ConfigMain.GENERAL_INTERACT_EGG = true;
		ConfigMain.GENERAL_INTERACT_WORLDS = new ArrayList<>();
		ConfigMain.GENERAL_INTERACT_WORLDS.add("*"); // Necessary to avoid NullPointerException on entity get world
		Messages.INTERACT_DISABLED_EGG = "";
		ConfigMain.GENERAL_INTERACT_TRADE = true;
		Messages.INTERACT_DISABLED_TRADE = "";
		
		// Global stubs
		when(mockPlugin.getVillagerHandler()).thenReturn(mockVillagerHandler);
		when(mockEvent.getHand()).thenReturn(EquipmentSlot.HAND);
		when(mockEvent.getPlayer()).thenReturn(mockPlayer);
		when(mockPlayer.getInventory()).thenReturn(mockInventory);
		when(mockInventory.getItemInMainHand()).thenReturn(mockItem);
		
		// Test 1 (true): Selection blaze rod
		when(mockEvent.getRightClicked()).thenReturn(mockEntity1);
		when(mockItem.getType()).thenReturn(Material.BLAZE_ROD);
		when(mockPlayer.hasPermission(SVPermission.ADMIN_SELECTION.toString())).thenReturn(true);
		playerListener.onPlayerInteractEntity(mockEvent);
		verify(mockEvent, times(1)).setCancelled(true);
		
		// Test 2 (true): Converter watch
		when(mockEvent.getRightClicked()).thenReturn(mockEntity1);
		when(mockItem.getType()).thenReturn(Material.COMPASS);
		when(mockPlayer.hasPermission(SVPermission.ADMIN_CHANGEAGE.toString())).thenReturn(true);
		playerListener.onPlayerInteractEntity(mockEvent);
		verify(mockEvent, times(2)).setCancelled(true);
		
		// Test 3 (true): Interact egg
		when(mockEvent.getRightClicked()).thenReturn(mockEntity1);
		when(mockItem.getType()).thenReturn(Material.VILLAGER_SPAWN_EGG);
		playerListener.onPlayerInteractEntity(mockEvent);
		verify(mockEvent, times(3)).setCancelled(true);
		
		// Test 4 (true): Trade any item
		when(mockEvent.getRightClicked()).thenReturn(mockEntity1);
		when(mockItem.getType()).thenReturn(Material.BOOK);
		playerListener.onPlayerInteractEntity(mockEvent);
		verify(mockEvent, times(4)).setCancelled(true);
		
		// Test 5 (true): Trade with blaze rod selection disabled
		ConfigMain.SECURITYVILLAGERS_SELECTION_ENABLE = false;
		when(mockEvent.getRightClicked()).thenReturn(mockEntity1);
		when(mockItem.getType()).thenReturn(Material.BLAZE_ROD);
		when(mockPlayer.hasPermission(SVPermission.ADMIN_SELECTION.toString())).thenReturn(true);
		playerListener.onPlayerInteractEntity(mockEvent);
		verify(mockEvent, times(5)).setCancelled(true);
		
		// Test 6 (false): Trade allowed any item
		when(mockEvent.getRightClicked()).thenReturn(mockEntity1);
		when(mockItem.getType()).thenReturn(Material.BLAZE_ROD);
		when(mockPlayer.hasPermission(SVPermission.TRADE.toString())).thenReturn(true);
		playerListener.onPlayerInteractEntity(mockEvent);
		verify(mockEvent, times(5)).setCancelled(true);
		
		// Test 7 (false): Interact on Entity with any item
		when(mockEvent.getRightClicked()).thenReturn(mockEntity2);
		when(mockItem.getType()).thenReturn(Material.BLAZE_ROD);
		playerListener.onPlayerInteractEntity(mockEvent);
		verify(mockEvent, times(5)).setCancelled(true);
	}
	
	@Test
	public void testOnPlayerInteract() {
		SecurityVillagers mockPlugin = mock(SecurityVillagers.class);
		PlayerInteractEvent mockEvent = mock(PlayerInteractEvent.class);
		PowerMockito.mockStatic(Bukkit.class);
		PowerMockito.mockStatic(MaterialUtils.class);
		Action action = Action.RIGHT_CLICK_BLOCK;
		Player mockPlayer = mock(Player.class);
		PlayerInventory mockInventory = mock(PlayerInventory.class);
		ItemStack mockItem = mock(ItemStack.class);
		
		// Initialize listener
		PlayerListener playerListener = new PlayerListener(mockPlugin);
		
		// Plugin configuration
		ConfigMain.GENERAL_INTERACT_EGG = true;
		ConfigMain.GENERAL_INTERACT_WORLDS = new ArrayList<>();
		ConfigMain.GENERAL_INTERACT_WORLDS.add("*"); // Necessary to avoid NullPointerException on entity get world
		Messages.INTERACT_DISABLED_EGG = "";
		
		// Global stubs
		when(Bukkit.getVersion()).thenReturn("1.13");
		when(MaterialUtils.getMaterial(any(),any())).thenCallRealMethod();
		when(mockEvent.getAction()).thenReturn(action);
		when(mockEvent.getPlayer()).thenReturn(mockPlayer);
		when(mockPlayer.getInventory()).thenReturn(mockInventory);
		
		// Test 1 (true): Villager egg
		when(mockInventory.getItemInMainHand()).thenReturn(mockItem);
		when(mockItem.getType()).thenReturn(Material.VILLAGER_SPAWN_EGG);
		playerListener.onPlayerInteract(mockEvent);
		verify(mockEvent, times(1)).setCancelled(true);
		
		// Test 2 (false): Zombie egg
		when(mockInventory.getItemInMainHand()).thenReturn(mockItem);
		when(mockItem.getType()).thenReturn(Material.EGG);
		playerListener.onPlayerInteract(mockEvent);
		verify(mockEvent, times(1)).setCancelled(true);
		
		// Test 3 (false): Blaze rod
		when(mockInventory.getItemInMainHand()).thenReturn(mockItem);
		when(mockItem.getType()).thenReturn(Material.BLAZE_ROD);
		playerListener.onPlayerInteract(mockEvent);
		verify(mockEvent, times(1)).setCancelled(true);
	}
}
