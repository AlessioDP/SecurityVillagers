package com.alessiodp.securityvillagers.addons.internal;

import com.alessiodp.securityvillagers.SecurityVillagers;
import com.alessiodp.securityvillagers.configuration.Constants;
import com.alessiodp.securityvillagers.configuration.data.ConfigMain;
import com.alessiodp.securityvillagers.configuration.data.Messages;
import com.alessiodp.securityvillagers.utils.SVPermission;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

public class ADPUpdater {
	private static SecurityVillagers plugin;
	
	@Getter private static String foundVersion = "";
	
	public ADPUpdater(SecurityVillagers instance) {
		plugin = instance;
	}
	
	public static void alertPlayers() {
		if (ConfigMain.SECURITYVILLAGERS_UPDATES_CHECK
				&& ConfigMain.SECURITYVILLAGERS_UPDATES_WARN
				&& !foundVersion.isEmpty()) {
			for (Player p : Bukkit.getServer().getOnlinePlayers()) {
				alertPlayer(p);
			}
		}
	}
	public static void alertPlayer(Player player) {
		if (player.hasPermission(SVPermission.ADMIN_UPDATES.toString())) {
			plugin.sendMessage(player, Messages.SECURITYVILLAGERS_UPDATEAVAILABLE
					.replace("%version%", foundVersion)
					.replace("%thisversion%", plugin.getDescription().getVersion()));
		}
	}
	
	public static void asyncTaskCheckUpdates() {
		if (ConfigMain.SECURITYVILLAGERS_UPDATES_CHECK) {
			plugin.getServer().getScheduler().runTaskTimerAsynchronously(plugin, ADPUpdater::checkUpdates, 20, 20*60*60*24); // 24 hours
		}
	}
	
	public static void asyncCheckUpdates() {
		if (ConfigMain.SECURITYVILLAGERS_UPDATES_CHECK) {
			plugin.getServer().getScheduler().runTaskAsynchronously(plugin, ADPUpdater::checkUpdates); // 24 hours
		}
	}
	
	private static void checkUpdates() {
		foundVersion = "";
		String version = getVersionInfo();
		
		if (version == null) {
			plugin.log(Constants.UPDATER_FALLBACK_WARN);
			version = getVersionFallback();
		}
		
		if (checkVersion(version, plugin.getDescription().getVersion())) {
			// New version found
			foundVersion = version;
			
			plugin.log(Constants.UPDATER_FOUND
					.replace("{currentVersion}", plugin.getDescription().getVersion())
					.replace("{newVersion}", foundVersion));
			alertPlayers();
		}
	}
	
	private static String getVersionInfo() {
		String ret = null;
		try {
			URLConnection conn = new URL(Constants.UPDATER_URL
					.replace("{version}", plugin.getDescription().getVersion())).openConnection();
			conn.setConnectTimeout(10000);
			conn.addRequestProperty("User-Agent", "ADP Updater");
			
			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			JsonObject response = new JsonParser().parse(br.readLine()).getAsJsonObject();
			// Get the version string
			if (response != null)
				ret = response.get(Constants.UPDATER_FIELD_VERSION).getAsString();
		} catch (IOException ex) {
			plugin.log(Constants.UPDATER_FAILED_IO);
		} catch (Exception ex) {
			plugin.log(Constants.UPDATER_FAILED_GENERAL);
		}
		return ret;
	}
	
	private static String getVersionFallback() {
		String ret = null;
		try {
			HttpURLConnection conn = (HttpURLConnection) new URL(Constants.UPDATER_FALLBACK_URL
					.replace("{version}", plugin.getDescription().getVersion())).openConnection();
			conn.setRequestMethod("POST");
			conn.setConnectTimeout(10000);
			conn.addRequestProperty("User-Agent", "ADP Updater");
			
			String postContent = "key=" +
					Constants.UPDATER_FALLBACK_KEY +
					"&resource=" +
					Constants.UPDATER_FALLBACK_RESOURCE;
			conn.getOutputStream().write(postContent.getBytes("UTF-8"));
			
			String response = new BufferedReader(new InputStreamReader(conn.getInputStream())).readLine();
			// Check if is a correct version and not a message
			if (response.length() < 10) {
				ret = response;
			}
		} catch (IOException ex) {
			plugin.log(Constants.UPDATER_FAILED_IO);
		} catch (Exception ex) {
			plugin.log(Constants.UPDATER_FAILED_GENERAL);
		}
		return ret;
	}
	
	private static boolean checkVersion(String ver, String compareWith) {
		boolean ret = false;
		String[] splitVer = splitVersion(ver);
		String[] splitCompareWith = splitVersion(compareWith);
		
		try {
			for (int c=0; c < splitVer.length && !ret; c++) {
				int a = Integer.parseInt(splitVer[c]);
				int b = c < splitCompareWith.length ? Integer.parseInt(splitCompareWith[c]) : 0;
				if (a > b)
					ret = true;
				else if (a < b)
					break;
			}
		} catch (Exception ex) {
			ret = true;
		}
		return ret;
	}
	
	private static String[] splitVersion(String value) {
		value = value.split(Constants.UPDATER_DELIMITER_TYPE)[0];
		return value.split(Constants.UPDATER_DELIMITER_VERSION);
	}
}
