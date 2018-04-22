package com.alessiodp.securityvillagers.tests;

import com.alessiodp.securityvillagers.SecurityVillagers;
import com.alessiodp.securityvillagers.configuration.ConfigurationManager;
import com.alessiodp.securityvillagers.configuration.data.ConfigMain;
import com.alessiodp.securityvillagers.configuration.data.Messages;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.Whitebox;

import java.io.File;
import java.lang.reflect.Field;
import java.util.HashMap;

import static org.junit.Assert.fail;
import static org.powermock.api.mockito.PowerMockito.mock;
import static org.powermock.api.mockito.PowerMockito.mockStatic;

@RunWith(PowerMockRunner.class)
@PrepareForTest({ConfigMain.class, Messages.class})
public class ConfigurationTest {
	@Test
	public void testOnConfigMainLoadMatch() throws Exception {
		mockStatic(ConfigMain.class);
		ConfigMain configMainInstance = new ConfigMain();
		configMainInstance.loadDefaults();
		
		HashMap<String, Object> savedEntries = new HashMap<>();
		
		// Save all default fields into the hashmap
		Field[] listFields = PowerMockito.fields(configMainInstance.getClass());
		for (Field f : listFields) {
			savedEntries.put(f.getName(), f.get(configMainInstance));
		}
		
		// Mock ConfigurationManager
		ConfigurationManager configurationManager = mock(ConfigurationManager.class);
		File configFile = new File(getClass().getResource("/config.yml").toURI());
		FileConfiguration cfg = YamlConfiguration.loadConfiguration(configFile);
		
		// Invoke private method
		Whitebox.<Void>invokeMethod(configurationManager, "loadConfigMain", cfg);
		
		// Check if configuration matches
		for (Field f : listFields) {
			if (!savedEntries.get(f.getName()).equals(f.get(configMainInstance))) {
				fail("Fields are mismatched: " + f.getName());
			}
		}
	}
	
	@Test
	public void testOnMessagesLoadMatch() throws Exception {
		mockStatic(Messages.class);
		Messages messagesInstance = new Messages();
		messagesInstance.loadDefaults();
		
		HashMap<String, Object> savedEntries = new HashMap<>();
		
		// Save all default fields into the hashmap
		Field[] listFields = PowerMockito.fields(messagesInstance.getClass());
		for (Field f : listFields) {
			savedEntries.put(f.getName(), f.get(messagesInstance));
		}
		
		// Mock ConfigurationManager
		ConfigurationManager configurationManager = mock(ConfigurationManager.class);
		File configFile = new File(getClass().getResource("/messages.yml").toURI());
		FileConfiguration cfg = YamlConfiguration.loadConfiguration(configFile);
		
		// Invoke private method
		Whitebox.<Void>invokeMethod(configurationManager, "loadMessages", cfg);
		
		// Check if configuration matches
		for (Field f : listFields) {
			if (!savedEntries.get(f.getName()).equals(f.get(messagesInstance))) {
				fail("Fields are mismatched: " + f.getName());
			}
		}
	}
}
