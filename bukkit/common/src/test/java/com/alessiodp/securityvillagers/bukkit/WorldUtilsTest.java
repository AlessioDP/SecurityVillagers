package com.alessiodp.securityvillagers.bukkit;

import com.alessiodp.securityvillagers.common.utils.WorldUtils;
import com.google.common.collect.Lists;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class WorldUtilsTest {
	
	@Test
	public void testEmpty() {
		assertFalse(WorldUtils.containsWorld(Collections.emptyList(), "world"));
	}
	
	@Test
	public void testWildcard() {
		assertTrue(WorldUtils.containsWorld(Lists.newArrayList("world_nether", "*"), "world"));
	}
	
	@Test
	public void testTrue() {
		assertTrue(WorldUtils.containsWorld(Lists.newArrayList("world_nether", "world"), "world"));
	}
	
	@Test
	public void testFalse() {
		assertFalse(WorldUtils.containsWorld(Lists.newArrayList("world_nether"), "world"));
	}
	
	@Test
	public void testNegated() {
		assertFalse(WorldUtils.containsWorld(Lists.newArrayList("-world", "*"), "world"));
	}
}
