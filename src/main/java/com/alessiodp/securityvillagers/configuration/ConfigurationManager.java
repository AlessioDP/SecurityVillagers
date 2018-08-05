package com.alessiodp.securityvillagers.configuration;

import com.alessiodp.securityvillagers.SecurityVillagers;
import com.alessiodp.securityvillagers.configuration.data.ConfigMain;
import com.alessiodp.securityvillagers.configuration.data.Messages;
import com.google.common.io.ByteStreams;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.nio.file.Files;
import java.nio.file.Path;

public class ConfigurationManager {
	private SecurityVillagers plugin;
	
	public ConfigurationManager(SecurityVillagers instance) {
		plugin = instance;
	}
	
	public void reload() {
		ConfigMain configMain = new ConfigMain(plugin);
		Messages messages = new Messages(plugin);
		
		configMain.loadDefaults();
		messages.loadDefaults();
		
		Path configMainPath = saveDefaultConfiguration(plugin.getDataFolder().toPath(), "config.yml");
		Path messagesPath = saveDefaultConfiguration(plugin.getDataFolder().toPath(), "messages.yml");
		
		FileConfiguration configMainYaml = YamlConfiguration.loadConfiguration(configMainPath.toFile());
		FileConfiguration messagesYaml = YamlConfiguration.loadConfiguration(messagesPath.toFile());
		
		configMain.checkVersion(configMainYaml);
		messages.checkVersion(messagesYaml);
		
		configMain.loadConfiguration(configMainYaml);
		messages.loadConfiguration(messagesYaml);
	}
	
	private Path saveDefaultConfiguration(Path path, String fileName) {
		Path ret = path.resolve(fileName);
		try {
			if (!Files.exists(path)) {
				Files.createDirectories(path);
			}
			if (!Files.exists(ret)) {
				byte[] data = ByteStreams.toByteArray(plugin.getResource(fileName));
				
				Files.write(ret, data);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return ret;
	}
}