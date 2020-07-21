package com.alessiodp.securityvillagers.bukkit;

import com.alessiodp.securityvillagers.common.utils.WorldUtils;
import com.google.common.collect.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.Collections;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(PowerMockRunner.class)
@PrepareForTest({
		WorldUtils.class,
})
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
