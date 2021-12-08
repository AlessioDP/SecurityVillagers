package com.alessiodp.securityvillagers.bukkit;

import com.alessiodp.core.common.addons.external.simpleyaml.configuration.file.YamlFile;
import com.alessiodp.core.common.configuration.ConfigOption;
import com.alessiodp.core.common.configuration.ConfigurationFile;
import com.alessiodp.securityvillagers.bukkit.configuration.data.BukkitConfigMain;
import com.alessiodp.securityvillagers.bukkit.configuration.data.BukkitMessages;
import com.alessiodp.securityvillagers.common.SecurityVillagersPlugin;
import org.junit.jupiter.api.Test;

import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;

public class BukkitConfigurationTest {
	private static final SecurityVillagersPlugin mockPlugin = mock(SecurityVillagersPlugin.class);
	
	@Test
	public void testConfigMain() throws IllegalAccessException {
		BukkitConfigMain configMain = new BukkitConfigMain(mockPlugin);
		
		List<String> skipPaths = Collections.emptyList();
		
		testConfiguration(configMain, skipPaths);
	}
	
	@Test
	public void testMessages() throws IllegalAccessException {
		BukkitMessages messages = new BukkitMessages(mockPlugin);
		
		List<String> skipPaths = Collections.emptyList();
		
		testConfiguration(messages, skipPaths);
	}
	
	private void testConfiguration(ConfigurationFile configurationFile, List<String> skipPaths) throws IllegalAccessException {
		Field[] fields = configurationFile.getClass().getFields();
		
		// Initialize YAML
		YamlFile yf = YamlFile.loadConfiguration(new InputStreamReader(
				Objects.requireNonNull(ClassLoader.getSystemResourceAsStream(configurationFile.getResourceName()))
		));
		
		// Check fields
		for (Field f : fields) {
			ConfigOption co = f.getAnnotation(ConfigOption.class);
			if (co != null && !skippablePath(co.path(), skipPaths)) {
				Object value = yf.get(co.path());
				assertNotNull(value, "The " + configurationFile.getClass().getSimpleName() + " path '" + co.path() + "' is null.");
				f.set(null, value);
			}
		}
	}
	
	private boolean skippablePath(String path, List<String> skipPaths) {
		for (String sp : skipPaths) {
			if (path.startsWith(sp))
				return true;
		}
		return false;
	}
}
